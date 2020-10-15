package com.company.rabbitmq.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "RABBITMQ_AGENCY")
@Entity(name = "rabbitmq_Agency")
@NamePattern("%s|name")
public class Agency extends StandardEntity {
    private static final long serialVersionUID = -6520768910987736187L;

    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}