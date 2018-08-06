package com.hdn.sqlSession;

import com.hdn.config.Function;
import com.hdn.config.MapperBean;
import com.hdn.config.MyConfiguration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyMapperProxy implements InvocationHandler {

    private MyConfiguration configuration;
    private MySqlSession sqlSession;

    public MyMapperProxy(MyConfiguration configuration,
                         MySqlSession sqlSession) {
        this.configuration = configuration;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method,
                         Object[] args) throws Throwable {
        MapperBean mapper = configuration.readMapper("UserMapper.xml");
        //对应xml的接口
        if (method.getDeclaringClass().getName()
                .equals(mapper.getInterfaceName())) {
            //遍历xml中声明的所有方法
            List<Function> list = mapper.getList();
            if (list != null || !list.isEmpty()){
                for (Function function: list){
                    //动态代理实现xml方法和真实方法对应，执行并查询
                    if(method.getName().equals(function.getFunctionName())){
                        return sqlSession.selectOne(function.getSql(),String.valueOf(args[0]));
                    }
                }
            }
        }
        return null;
    }
}
