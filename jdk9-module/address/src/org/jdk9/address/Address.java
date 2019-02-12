package org.jdk9.address;

public class Address {
    private String line = "1111 Main Blvd";
    private String city = "Jacksonville";
    private String state = "FL";
    private String zip = "32256";

    public Address() {
    }

    public Address(String line, String city, String state, String zip) {
        this.line = line;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "[line: " + line + ", city: " + city + ", state: " + state + ", zip: " + zip + "]";
    }
}
