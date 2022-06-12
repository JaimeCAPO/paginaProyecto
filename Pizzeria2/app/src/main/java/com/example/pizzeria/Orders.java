package com.example.pizzeria;

import java.util.ArrayList;

public class Orders {
    private int idPedido;
    private User userPedido;
    private ArrayList<Pizza> pizzas;
    private ArrayList<Drink> bebidas;
    private String coment;
    private double price;

    private static int place=0;

    public Orders(User userPedido, ArrayList<Pizza> pizzas, ArrayList<Drink> bebidas, String coment) {
        this.idPedido=place;
        place++;
        this.userPedido = userPedido;
        this.pizzas = pizzas;
        this.bebidas = bebidas;
        this.coment = coment;

        double count=0.00;
        for(Pizza pizza:pizzas){
            count=count+pizza.getPrice();
        }
        for (Drink bebida:bebidas){
            count=count+bebida.getPrice();
        }
        this.price=count;
    }

    public Orders(int idPedido, User userPedido, ArrayList<Pizza> pizzas, ArrayList<Drink> bebidas, String coment) {
        this.idPedido = idPedido;
        this.userPedido = userPedido;
        this.pizzas = pizzas;
        this.bebidas = bebidas;
        this.coment = coment;

        double count=0.00;
        for(Pizza pizza:pizzas){
            count=count+pizza.getPrice();
        }
        for (Drink bebida:bebidas){
            count=count+bebida.getPrice();
        }
        this.price=count;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public User getUserPedido() {
        return userPedido;
    }

    public void setUserPedido(User userPedido) {
        this.userPedido = userPedido;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public ArrayList<Drink> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Drink> bebidas) {
        this.bebidas = bebidas;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static int getPlace() {
        return place;
    }

    public static void setPlace(int place) {
        Orders.place = place;
    }

    public String orderDescription() {
        return "Orders{" +
                "idPedido=" + idPedido +
                ", userPedido=" + userPedido +
                ", pizzas=" + pizzas +
                ", bebidas=" + bebidas +
                ", coment='" + coment + '\'' +
                ", price=" + price +
                '}';
    }
    @Override
    public String toString(){
        return "Order "+idPedido+" -- Price:"+price+"€";
    }
}
