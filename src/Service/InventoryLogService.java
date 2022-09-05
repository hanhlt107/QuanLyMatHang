/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.InventoryLog;
import Model.Medication;
import Repository.InventoryLogRepository;
import Repository.MedicationRepository;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class InventoryLogService {

    private InventoryLogRepository inventoryLogRepository;

    public InventoryLogService() {
        inventoryLogRepository = new InventoryLogRepository();
    }

    public ArrayList<InventoryLog> getAll() {
        return inventoryLogRepository.getAll();
    }

    public InventoryLog getAllByID(Integer idThuoc, String maPhieu) {
        return inventoryLogRepository.getAllByID(idThuoc, maPhieu);
    }

    public Integer insert(InventoryLog inventoryLog) {
        InventoryLog nx = inventoryLogRepository.getAllByID(inventoryLog.getMedication().getId(), inventoryLog.getFormId());
        if (nx != null) {
            return 0; //da ton tai
        }
        return inventoryLogRepository.insert(inventoryLog);
    }

    public Integer update(Integer isThuoc, String maPhieu, InventoryLog inventoryLog) {
        InventoryLog nx = inventoryLogRepository.getAllByID(inventoryLog.getMedication().getId(), inventoryLog.getFormId());
        if (nx == null) {
            return 0; //ko ton tai
        }
        return inventoryLogRepository.update(isThuoc, maPhieu, inventoryLog);
    }

    public Integer delete(Integer isThuoc, String maPhieu) {
        return inventoryLogRepository.delete(isThuoc, maPhieu);
    }

    public Integer getIdThuoc(String maPhieu) {
        return inventoryLogRepository.getIdThuoc(maPhieu);
    }

    public Integer kiemTraID(Integer idThuoc, String maPhieu) {
        int dem = inventoryLogRepository.kiemTraID(idThuoc, maPhieu);
        if (dem == 0) {
            return 0; //ko ton tai
        } else {
            return 1;//da ton tai
        }

    }
}
