package com.hdn.sqlSession;

//定义了JDBC的SQL操作接口，由继承类实现具体。
public interface Executor {

    public <T> T query(String statement, Object parameter);
}
