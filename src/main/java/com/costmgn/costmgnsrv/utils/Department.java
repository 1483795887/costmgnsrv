package com.costmgn.costmgnsrv.utils;

public enum Department {
    PRODUCE("生产"),
    SALES("营销"),
    MANAGEMENT("管理"),
    ERROR("不存在");
    private String name;

    Department(String name) {
        this.name = name;
    }

    public static Department getDepartmentFromString(String str) {
        switch (str) {
            case "生产":
                return PRODUCE;
            case "营销":
                return SALES;
            case "管理":
                return MANAGEMENT;
            default:
                return ERROR;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
