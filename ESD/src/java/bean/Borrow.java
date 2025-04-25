/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import db.FruitDB;
import db.ShopDB;

/**
 *
 * @author user
 */
public class Borrow {

    private int borrowId;
    private int fromShopId;
    private int toShopId;
    private int fruitId;
    private int quantity;
    private String borrowDate;
    private String status;

    public Borrow(int borrowId, int fromShopId, int toShopId, int fruitId, int quantity, String borrowDate, String status) {
        this.borrowId = borrowId;
        this.fromShopId = fromShopId;
        this.toShopId = toShopId;
        this.fruitId = fruitId;
        this.quantity = quantity;
        this.borrowDate = borrowDate;
        this.status = status;
    }
    
    public String getToShopName(){
        ShopDB shopDB = new ShopDB("jdbc:mysql://localhost:3306/esd_assignment", "root", "");
        return shopDB.getShopById(this.toShopId).getShopName();
    }
    
    public String getFromShopName(){
        ShopDB shopDB = new ShopDB("jdbc:mysql://localhost:3306/esd_assignment", "root", "");
        return shopDB.getShopById(this.fromShopId).getShopName();
    }

    public String getFruitName(){
        FruitDB fruitDB = new FruitDB("jdbc:mysql://localhost:3306/esd_assignment", "root", "");
        return fruitDB.getFruitNameById(this.fruitId); // Fetch fruitName using fruitId
    }
    
    // Getters and Setters
    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getFromShopId() {
        return fromShopId;
    }

    public void setFromShopId(int fromShopId) {
        this.fromShopId = fromShopId;
    }

    public int getToShopId() {
        return toShopId;
    }

    public void setToShopId(int toShopId) {
        this.toShopId = toShopId;
    }

    public int getFruitId() {
        return fruitId;
    }

    public void setFruitId(int fruitId) {
        this.fruitId = fruitId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
