/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class InventoryLog {
    private String formId;
    private Integer typeForm;
    private Integer quantity;
    private Date createdDate;
    private Medication medication;

    public InventoryLog() {
    }

    public InventoryLog(String formId, Integer typeForm, Integer quantity, Date createdDate, Medication medication) {
        this.formId = formId;
        this.typeForm = typeForm;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.medication = medication;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Integer getTypeForm() {
        return typeForm;
    }

    public void setTypeForm(Integer typeForm) {
        this.typeForm = typeForm;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

   
    
    
}
