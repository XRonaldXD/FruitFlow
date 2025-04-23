/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author user
 */

public class Stock {

    private int fruit_id;
    private int shop_id;
    private int warehouse_id;
    private int stock_level;

    // Constructor
    public Stock(int fruit_id, int shop_id, int warehouse_id, int stock_level) {
        this.fruit_id = fruit_id;
        this.shop_id = shop_id;
        this.warehouse_id = warehouse_id;
        this.stock_level = stock_level;
    }

    // Getters and Setters
    public void setFruit_id(int fruit_id){
        this.fruit_id = fruit_id;
    }
    public void setShop_id(int shop_id){
        this.shop_id = shop_id;
    }
    public void setWarehouse_id(int warehouse_id){
        this.warehouse_id = warehouse_id;
    }
    public void setStock_level(int stock_level){
        this.stock_level = stock_level;
    }
    public int getFruit_id(){
        return fruit_id;
    }
    public int getShop_id(){
        return shop_id;
    }
    public int getWarehouse_id(){
        return warehouse_id;
    }
    public int getStock_level(){
        return stock_level;
    }
}
