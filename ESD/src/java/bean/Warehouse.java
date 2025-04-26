/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

public class Warehouse {

    private int warehouseId;
    private String warehouseName;
    private int countryId;

    // Default Constructor
    public Warehouse() {
    }

    // Parameterized Constructor
    public Warehouse(int warehouseId, String warehouseName, int countryId) {
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.countryId = countryId;
    }

    // Getters and Setters
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Warehouse{"
                + "warehouseId=" + warehouseId
                + ", warehouseName='" + warehouseName + '\''
                + ", countryId=" + countryId
                + '}';
    }
}
