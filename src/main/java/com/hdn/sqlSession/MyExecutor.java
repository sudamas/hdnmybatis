package com.hdn.sqlSession;

import com.hdn.config.MyConfiguration;
import com.hdn.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//封装了JDBC的SQL操作
public class MyExecutor implements Executor {

    private MyConfiguration configuration = new MyConfiguration();

    @Override
    public <T> T query(String sql, Object parameter) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            //设置SQL语句，获取statement
            statement = connection.prepareStatement(sql);
            //设置参数
            statement.setString(1, parameter.toString());
            //执行statement,获取结果集
            resultSet = statement.executeQuery();
            //遍历结果 封装成对象
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
            }
            return (T) user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Connection getConnection() {
        return configuration.build("config.xml");
    }
}
