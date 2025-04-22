/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author user
 */
public class Reservation {
    private int reservationId;
    private int fruitId;
    private int shopId;
    private int quantity;
    private String reservationDate;
    private String status; // e.g., "Pending", "Approved"

    // Constructors
    public Reservation() {}

    public Reservation(int reservationId, int fruitId, int shopId, int quantity, String reservationDate, String status) {
        this.reservationId = reservationId;
        this.fruitId = fruitId;
        this.shopId = shopId;
        this.quantity = quantity;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    // Getters and Setters
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public int getFruitId() { return fruitId; }
    public void setFruitId(int fruitId) { this.fruitId = fruitId; }

    public int getShopId() { return shopId; }
    public void setShopId(int shopId) { this.shopId = shopId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getReservationDate() { return reservationDate; }
    public void setReservationDate(String reservationDate) { this.reservationDate = reservationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}