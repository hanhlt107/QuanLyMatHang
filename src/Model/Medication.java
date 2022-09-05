/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class Medication {
    private Integer id;
    private String medicationCode;
    private String medicationName;
    private Integer quantity;
    private BigDecimal currentPrice;
    private Category category;

    public Medication() {
    }

    public Medication(Integer id, String medicationCode, String medicationName, Integer quantity, BigDecimal currentPrice, Category category) {
        this.id = id;
        this.medicationCode = medicationCode;
        this.medicationName = medicationName;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

       

    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        this.medicationCode = medicationCode;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
    
    
    
}
