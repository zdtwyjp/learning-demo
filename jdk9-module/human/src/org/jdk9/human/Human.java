package org.jdk9.human;

import org.jdk9.address.Address;

public class Human {
    private String name;
    private Address address = new Address();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static void main(String[] args) {
        Human human = new Human();
        System.out.println(human.getAddress());
    }
}
