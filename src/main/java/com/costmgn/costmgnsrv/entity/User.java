package com.costmgn.costmgnsrv.entity;

import java.io.Serializable;

/**
 * user
 *
 * @author
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getDepartment() == null ? other.getDepartment() == null : this.getDepartment().equals(other.getDepartment()))
                && (this.getPost() == null ? other.getPost() == null : this.getPost().equals(other.getPost()))
                && (this.getInpost() == null ? other.getInpost() == null : this.getInpost().equals(other.getInpost()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDepartment() == null) ? 0 : getDepartment().hashCode());
        result = prime * result + ((getPost() == null) ? 0 : getPost().hashCode());
        result = prime * result + ((getInpost() == null) ? 0 : getInpost().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", password=").append(password);
        sb.append(", name=").append(name);
        sb.append(", department=").append(department);
        sb.append(", post=").append(post);
        sb.append(", inpost=").append(inpost);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}