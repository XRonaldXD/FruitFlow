/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Delivery {

    private int deliveryId;
    private int reservationId;
    private int fruitId;
    private int fromWarehouseId;
    private Integer toWarehouseId;
    private Integer toShopId;
    private int quantity;
    private String deliveryDate;
    private String status;

    // Constructor
    public Delivery(int deliveryId, int reservationId, int fruitId, int fromWarehouseId, Integer toWarehouseId, Integer toShopId, int quantity, String deliveryDate, String status) {
        this.deliveryId = deliveryId;
        this.reservationId = reservationId;
        this.fruitId = fruitId;
        this.fromWarehouseId = fromWarehouseId;
        this.toWarehouseId = toWarehouseId;
        this.toShopId = toShopId;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    // Getters and Setters
    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

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

    public int getFromWarehouseId() {
        return fromWarehouseId;
    }

    public void setFromWarehouseId(int fromWarehouseId) {
        this.fromWarehouseId = fromWarehouseId;
    }

    public Integer getToWarehouseId() {
        return toWarehouseId;
    }

    public void setToWarehouseId(Integer toWarehouseId) {
        this.toWarehouseId = toWarehouseId;
    }

    public Integer getToShopId() {
        return toShopId;
    }

    public void setToShopId(Integer toShopId) {
        this.toShopId = toShopId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
