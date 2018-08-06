package com.hdn;

import com.hdn.mapper.UserMapper;
import com.hdn.model.User;
import com.hdn.sqlSession.MySqlSession;

public class Test {

    public static void main(String[] args) {
        MySqlSession session = new MySqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectUserById(1);
        System.out.println(user);
    }
}
