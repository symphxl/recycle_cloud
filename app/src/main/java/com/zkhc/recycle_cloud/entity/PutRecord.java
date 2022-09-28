package com.zkhc.recycle_cloud.entity;

import java.io.Serializable;

public class PutRecord implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private Integer weight;
    private String type;
    private Integer qualified;

    public Integer getId() {
        return id;
    }

    public PutRecord setId(Integer id) {
        this.id = id;
        return null;
    }

    public String getName() {
        return name;
    }

    public PutRecord setName(String name) {
        this.name = name;
        return null;
    }

    public String getAddress() {
        return address;
    }

    public PutRecord setAddress(String address) {
        this.address = address;
        return null;
    }

    public Integer getWeight() {
        return weight;
    }

    public PutRecord setWeight(Integer weight) {
        this.weight = weight;
        return null;
    }

    public String getType() {
        return type;
    }

    public PutRecord setType(String type) {
        this.type = type;
        return null;
    }

    public Integer getQualified() {
        return qualified;
    }

    public PutRecord setQualified(Integer qualified) {
        this.qualified = qualified;
        return null;
    }

    public PutRecord() {
    }

    public PutRecord(Integer id, String name, String address, Integer weight, String type, Integer qualified) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.weight = weight;
        this.type = type;
        this.qualified = qualified;
    }
}
