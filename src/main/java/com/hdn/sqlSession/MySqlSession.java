package com.hdn.sqlSession;

import com.hdn.config.MyConfiguration;

import java.lang.reflect.Proxy;

public class MySqlSession {

    private MyExecutor executor = new MyExecutor();
    private MyConfiguration configuration = new MyConfiguration();

    //实现查询一条语句
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

    //动态代理调用返回接口对象
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new MyMapperProxy(configuration,this));
    }

}
