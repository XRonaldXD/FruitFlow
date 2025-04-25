/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author user
 */
public class Fruit {
    private int fruitId;
    private String fruitName;
    private String sourceLocation;

    // Constructors
    public Fruit() {}

    public Fruit(int fruitId, String fruitName, String sourceLocation) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.sourceLocation = sourceLocation;
    }

    // Getters and Setters
    public int getFruitId() { return fruitId; }
    public void setFruitId(int fruitId) { this.fruitId = fruitId; }

    public String getFruitName() { return fruitName; }
    public void setFruitName(String fruitName) { this.fruitName = fruitName; }

    public String getSourceLocation() { return sourceLocation; }
    public void setSourceLocation(String sourceLocation) { this.sourceLocation = sourceLocation; }
}