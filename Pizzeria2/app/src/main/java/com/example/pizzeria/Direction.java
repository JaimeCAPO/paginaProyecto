package com.example.pizzeria;

public class Direction {
    private int directionid;
    private String street;
    private String town;
    private int number;
    private int postalCode;

    public Direction(String street, String town, int number, int postalCode) {
        this.street = street;
        this.town = town;
        this.number = number;
        this.postalCode= postalCode;
    }

    public Direction(int directionid, String street, String town, int number, int postalCode) {
        this.directionid = directionid;
        this.street = street;
        this.town = town;
        this.number = number;
        this.postalCode = postalCode;
    }

    public int getDirectionid() {
        return directionid;
    }

    public void setDirectionid(int directionid) {
        this.directionid = directionid;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "directionid=" + directionid +
                ", street='" + street + '\'' +
                ", town='" + town + '\'' +
                ", number=" + number +
                ", postalCode=" + postalCode +
                '}';
    }
}
