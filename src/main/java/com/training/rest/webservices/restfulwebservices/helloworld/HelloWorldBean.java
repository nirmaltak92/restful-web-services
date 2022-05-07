package com.training.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
    private String m;
    public HelloWorldBean(String message) {
        this.m = message;
    }

    public String getMessage() {
        return m;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "msg='" + m + '\'' +
                '}';
    }
}
