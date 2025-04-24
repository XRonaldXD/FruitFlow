/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class ConsumptionRecord {

    private int consumptionId;
    private String shopName;
    private String cityName;
    private String countryName;
    private String fruitName;
    private int quantity;
    private String consumptionDate;
    private String season;

    public ConsumptionRecord(int consumptionId, String shopName, String cityName, String countryName, String fruitName, int quantity, String consumptionDate, String season) {
        this.consumptionId = consumptionId;
        this.shopName = shopName;
        this.cityName = cityName;
        this.countryName = countryName;
        this.fruitName = fruitName;
        this.quantity = quantity;
        this.consumptionDate = consumptionDate;
        this.season = season;
    }

    // Getters and Setters
    public int getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(int consumptionId) {
        this.consumptionId = consumptionId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(String consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
