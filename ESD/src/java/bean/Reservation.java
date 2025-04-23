/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import db.CityDB;
import db.FruitDB;
import db.ShopDB;
import db.WarehouseDB;

/**
 *
 * @author user
 */
public class Reservation {

    private int reservationId;
    private int fruitId;
    private Integer shopId;
    private Integer warehouseId;
    private int quantity;
    private String reservationDate;
    private String status; // e.g., "Pending", "Approved"

    // Constructors
    public Reservation() {
    }

    public Reservation(int reservationId, int fruitId, Integer shopId, Integer warehouseId, int quantity, String reservationDate, String status) {
        this.reservationId = reservationId;
        this.fruitId = fruitId;
        this.shopId = shopId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public String getFruitName() {
        FruitDB fruitDB = new FruitDB("jdbc:mysql://localhost:3306/esd_assignment", "root", "");
        return fruitDB.getFruitNameById(this.fruitId); // Fetch fruitName using fruitId
    }
    
    public String getWarehouseName(){
        WarehouseDB warehouseDB = new WarehouseDB("jdbc:mysql://localhost:3306/esd_assignment", "root", "");
        return warehouseDB.getWarehouseNameById(this.warehouseId);
    }
    
    public String getShopName(){
       ShopDB shopDB = new ShopDB("jdbc:mysql://localhost:3306/esd_assignment", "root", "");
       return shopDB.getShopById(this.shopId).getShopName();
    }

    // Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getFruitId() {
        return fruitId;
    }

    public void setFruitId(int fruitId) {
        this.fruitId = fruitId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    
    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{"
                + "reservationId=" + reservationId
                + ", shopId=" + shopId
                + ", fruitId=" + fruitId
                + ", quantity=" + quantity
                + ", reservationDate=" + reservationDate
                + ", status='" + status + '\''
                + '}';
    }
}
