/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Medication;
import Repository.MedicationRepository;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class MedicationService {
    private MedicationRepository medicationRepository;

    public MedicationService() {
        medicationRepository = new MedicationRepository();
    }
    
    public ArrayList<Medication> getAll(){
        return medicationRepository.getAll();
    }
    public Medication getAllByMa(String ma){
        return medicationRepository.getAllByMa(ma);
    }
    public ArrayList<Medication> getAllByName(String ten){
        return medicationRepository.getAllByName(ten);
    }
    
    public Integer insert(Medication medication){
        return medicationRepository.insert(medication);
    }
    public Integer update(String ma,Medication medication){
        return medicationRepository.update(ma, medication);
    }
    public Integer delete(int id){
        return medicationRepository.delete(id);
    }
    public Integer getID(String ma){
        return medicationRepository.getID(ma);
    }
    public Integer getSoLuong(String ma){
        return medicationRepository.getSoLuong(ma);
    }
    public Integer addQuantity(Integer idThuoc, Integer soLuong){
        return medicationRepository.addQuantity(idThuoc, soLuong);
    }
    public Integer minusQuantity(Integer idThuoc, Integer soLuong){
        return medicationRepository.minusQuantity(idThuoc, soLuong);
    }
    
}
