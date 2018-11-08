package com.headers.example.kafka.data;

public class Bar extends AbstractEvent {

    private String name;

    public Bar() {
    }

    public Bar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "name='" + name + '\'' +
                '}';
    }
}
