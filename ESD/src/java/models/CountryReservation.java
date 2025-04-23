/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author user
 */
public class CountryReservation {
    private int countryId;
    private String countryName;
    private int fruitId;
    private String fruitName;
    private int totalQuantity;

    // Constructor
    public CountryReservation(int countryId, String countryName, int fruitId, String fruitName, int totalQuantity) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.totalQuantity = totalQuantity;
    }

    // Getters and Setters
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getFruitId() {
        return fruitId;
    }

    public void setFruitId(int fruitId) {
        this.fruitId = fruitId;
    }

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
}
