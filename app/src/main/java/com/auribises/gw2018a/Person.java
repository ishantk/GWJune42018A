package com.auribises.gw2018a;

public class Person {

    public String name;
    public String email;

    public Person(){

    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

