package com.company.rabbitmq.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "RABBITMQ_LENDER")
@Entity(name = "rabbitmq_Lender")
@NamePattern("%s|name")
public class Lender extends StandardEntity {
    private static final long serialVersionUID = 4735636354694832117L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SIZE_")
    private Integer size;

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Lender{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}