/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class ConsumptionReport {
    private String fruitName;
    private int totalQuantity;
    private String season; // Optional for seasonal reports

    // Constructor for seasonal reports
    public ConsumptionReport(String fruitName, int totalQuantity, String season) {
        this.fruitName = fruitName;
        this.totalQuantity = totalQuantity;
        this.season = season;
    }

    // Constructor for non-seasonal reports
    public ConsumptionReport(String fruitName, int totalQuantity) {
        this.fruitName = fruitName;
        this.totalQuantity = totalQuantity;
    }

    // Getters and Setters
    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}