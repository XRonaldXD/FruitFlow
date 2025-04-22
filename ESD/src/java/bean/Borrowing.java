/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author user
 */
public class Borrowing {
    private int borrowId;
    private int fruitId;
    private int fromShopId;
    private int toShopId;
    private int quantity;
    private String borrowDate;
    private String status; // e.g., "Pending", "Approved"

    // Constructors
    public Borrowing() {}

    public Borrowing(int borrowId, int fruitId, int fromShopId, int toShopId, int quantity, String borrowDate, String status) {
        this.borrowId = borrowId;
        this.fruitId = fruitId;
        this.fromShopId = fromShopId;
        this.toShopId = toShopId;
        this.quantity = quantity;
        this.borrowDate = borrowDate;
        this.status = status;
    }

    // Getters and Setters
    public int getBorrowId() { return borrowId; }
    public void setBorrowId(int borrowId) { this.borrowId = borrowId; }

    public int getFruitId() { return fruitId; }
    public void setFruitId(int fruitId) { this.fruitId = fruitId; }

    public int getFromShopId() { return fromShopId; }
    public void setFromShopId(int fromShopId) { this.fromShopId = fromShopId; }

    public int getToShopId() { return toShopId; }
    public void setToShopId(int toShopId) { this.toShopId = toShopId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getBorrowDate() { return borrowDate; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}