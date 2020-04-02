package com.costmgn.costmgnsrv.utils;

public enum Post {
    SalesMan("业务员"),
    DepartmentManager("部门经理"),
    SystemManager("系统管理员");

    private String string;

    Post(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
