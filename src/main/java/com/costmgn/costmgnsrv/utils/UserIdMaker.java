package com.costmgn.costmgnsrv.utils;

import java.util.Calendar;

public class UserIdMaker {
    private final static int DEPARTMENT_LEN = 3;
    private final static int POST_LEN = 2;
    private final static int ID_LEN = 4;

    private static String appendZero(int value, int len) {
        StringBuilder result = new StringBuilder(String.valueOf(value));
        while (result.length() < len) {
            result.insert(0, "0");
        }
        return result.toString();
    }

    public static String makeId(Department department, Post post, int id) {
        Calendar calendar = Calendar.getInstance();
        String yearStr = appendZero(calendar.get(Calendar.YEAR), 4);
        String departmentStr = appendZero(department.ordinal(), DEPARTMENT_LEN);
        String postStr = appendZero(post.ordinal(), POST_LEN);
        String idStr = appendZero(id, ID_LEN);

        return yearStr + departmentStr + postStr + idStr;
    }
}
