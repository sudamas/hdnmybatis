package com.hdn.config;

import java.util.List;

public class MapperBean {

    //接口下所有方法
    private List<Function> list;
    //接口名
    private String interfaceName;

    public List<Function> getList() {
        return list;
    }

    public void setList(List<Function> list) {
        this.list = list;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
