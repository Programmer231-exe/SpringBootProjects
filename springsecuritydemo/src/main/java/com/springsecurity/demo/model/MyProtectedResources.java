package com.springsecurity.demo.model;

public class MyProtectedResources {
    private long id;
    private String name;

    public MyProtectedResources(long parseLong, String randomAlphabetic) {
        this.id = parseLong;
        this.name = randomAlphabetic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
