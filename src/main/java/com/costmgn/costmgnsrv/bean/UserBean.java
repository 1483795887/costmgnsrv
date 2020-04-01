package com.costmgn.costmgnsrv.bean;

public class UserBean {
    private Integer id;
    private String userid;
    private String password;
    private String name;
    private String department;
    private String post;
    private Boolean inpost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Boolean getInpost() {
        return inpost;
    }

    public void setInpost(Boolean inpost) {
        this.inpost = inpost;
    }
}
