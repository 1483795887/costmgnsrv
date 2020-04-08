package com.costmgn.costmgnsrv.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Receipt implements Serializable {
    private Integer id;

    private BigDecimal money;

    private String type;

    private Integer budgetId;

    private Work work;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
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
        Receipt other = (Receipt) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getBudgetId() == null ? other.getBudgetId() == null : this.getBudgetId().equals(other.getBudgetId()))
                && (this.getWork() == null ? other.getWork() == null : this.getWork().equals(other.getWork()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getBudgetId() == null) ? 0 : getBudgetId().hashCode());
        result = prime * result + ((getWork() == null) ? 0 : getWork().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", money=" + money +
                ", type=" + type +
                ", budgetId=" + budgetId +
                ", work=" + work +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}