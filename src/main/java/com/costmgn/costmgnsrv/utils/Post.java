package com.costmgn.costmgnsrv.utils;

public enum Post {
    SalesMan("业务员"),
    DepartmentManager("部门经理"),
    SystemManager("系统管理员"),
    ERROR("不存在");

    private String string;

    Post(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    public static Post getPostFromString(String str) {
        switch (str) {
            case "业务员":
                return SalesMan;
            case "部门经理":
                return DepartmentManager;
            case "系统管理员":
                return SystemManager;
            default:
                return ERROR;
        }
    }
}
