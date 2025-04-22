/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author user
 */
public class Shop {
    private int shopId;
    private String shopName;
    private int cityId;

    // Constructor
    public Shop(int shopId, String shopName, int cityId) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.cityId = cityId;
    }

    // Getters and Setters
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    // Optional: Override toString for debugging
    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
