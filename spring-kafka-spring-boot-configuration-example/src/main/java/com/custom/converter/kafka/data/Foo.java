package com.custom.converter.kafka.data;

public class Foo {

    private String fooName;

    public Foo() {
    }

    public Foo(String fooName) {
        this.fooName = fooName;
    }

    public String getFooName() {
        return fooName;
    }

    public void setFooName(String fooName) {
        this.fooName = fooName;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "fooName='" + fooName + '\'' +
                '}';
    }
}
