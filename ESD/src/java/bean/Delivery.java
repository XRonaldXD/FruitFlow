/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author user
 */
public class Delivery {
    private int deliveryId;
    private int fruitId;
    private String fromLocation;
    private String toLocation;
    private int quantity;
    private String deliveryDate;
    private String status; // e.g., "In Transit", "Delivered"

    // Constructors
    public Delivery() {}

    public Delivery(int deliveryId, int fruitId, String fromLocation, String toLocation, int quantity, String deliveryDate, String status) {
        this.deliveryId = deliveryId;
        this.fruitId = fruitId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    // Getters and Setters
    public int getDeliveryId() { return deliveryId; }
    public void setDeliveryId(int deliveryId) { this.deliveryId = deliveryId; }

    public int getFruitId() { return fruitId; }
    public void setFruitId(int fruitId) { this.fruitId = fruitId; }

    public String getFromLocation() { return fromLocation; }
    public void setFromLocation(String fromLocation) { this.fromLocation = fromLocation; }

    public String getToLocation() { return toLocation; }
    public void setToLocation(String toLocation) { this.toLocation = toLocation; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(String deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
