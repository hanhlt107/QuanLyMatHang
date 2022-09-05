/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.InventoryLog;
import Model.Medication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class InventoryLogRepository {

    public ArrayList<InventoryLog> getAll() {
        ArrayList<InventoryLog> lstLog = new ArrayList<>();

        try {
            Connection connection = DBContext.getConnection();
            String query = "select \n"
                    + "medication_code,\n"
                    + "form_id,\n"
                    + "type_form,\n"
                    + "created_date,\n"
                    + "inventory_log.quantity as so_luong_nx,\n"
                    + "medication.quantity as so_luong_ton\n"
                    + "from inventory_log\n"
                    + "left join medication on medication.id = inventory_log.medication_id";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String formID = rs.getString("form_id");
                Integer typeForm = rs.getInt("type_form");
                Date createdDate = rs.getDate("created_date");
                Integer quantity = rs.getInt("so_luong_nx");

                String medicationCode = rs.getString("medication_code");
                Integer medicationQuantity = rs.getInt("so_luong_ton");

                Medication medication = new Medication();
                medication.setMedicationCode(medicationCode);
                medication.setQuantity(medicationQuantity);

                InventoryLog inventoryLog = new InventoryLog(formID, typeForm, quantity, createdDate, medication);

                lstLog.add(inventoryLog);
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstLog;
    }

    public InventoryLog getAllByID(Integer idThuoc, String maPhieu) {

        try {
            Connection connection = DBContext.getConnection();
            String query = "select \n"
                    + "inventory_log.medication_id,\n"
                    + "medication_code,\n"
                    + "form_id,\n"
                    + "type_form,\n"
                    + "created_date,\n"
                    + "inventory_log.quantity as so_luong_nx,\n"
                    + "medication.quantity as so_luong_ton\n"
                    + "from inventory_log\n"
                    + "left join medication on medication.id = inventory_log.medication_id"
                    + " where inventory_log.medication_id = ? and inventory_log.form_id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idThuoc);
            ps.setString(2, maPhieu);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String formID = rs.getString("form_id");
                Integer typeForm = rs.getInt("type_form");
                Date createdDate = rs.getDate("created_date");
                Integer quantity = rs.getInt("so_luong_nx");

                String medicationCode = rs.getString("medication_code");
                Integer medicationId = rs.getInt("medication_id");
                Integer medicationQuantity = rs.getInt("so_luong_ton");

                Medication medication = new Medication();
                medication.setMedicationCode(medicationCode);
                medication.setQuantity(medicationQuantity);
                medication.setId(medicationId);

                InventoryLog inventoryLog = new InventoryLog(formID, typeForm, quantity, createdDate, medication);

                return inventoryLog;
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer insert(InventoryLog inventoryLog) {
        Integer ketQua = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "insert into inventory_log(medication_id,form_id,type_form,created_date,quantity)"
                    + " values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            Integer medicationId = null;
            if (inventoryLog.getMedication() != null) {
                medicationId = inventoryLog.getMedication().getId();
            }
            ps.setInt(1, medicationId);
            ps.setString(2, inventoryLog.getFormId());
            ps.setInt(3, inventoryLog.getTypeForm());
            ps.setDate(4, new java.sql.Date(inventoryLog.getCreatedDate().getTime()));
            ps.setInt(5, inventoryLog.getQuantity());
            ketQua = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public Integer update(Integer idThuoc, String maPhieu, InventoryLog inventoryLog) {
        Integer ketQua = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "update inventory_log "
                    + "set "
                    + "type_form = ?,"
                    + "quantity = ?,"
                    + "created_date = ?"
                    + " where medication_id = ? and form_id = ? ";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, inventoryLog.getTypeForm());
            ps.setInt(2, inventoryLog.getQuantity());
            ps.setDate(3, new java.sql.Date(inventoryLog.getCreatedDate().getTime()));
            ps.setInt(4, idThuoc);
            ps.setString(5, maPhieu);
            ketQua = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public Integer delete(Integer idThuoc, String maPhieu) {
        Integer ketQua = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "delete from inventory_log "
                    + " where medication_id = ? and form_id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idThuoc);
            ps.setString(2, maPhieu);
            ketQua = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }


    public Integer getIdThuoc(String maPhieu) {
        Integer id = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "select medication_id from inventory_log "
                    + " where form_id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maPhieu);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                id = rs.getInt("medication_id");
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public Integer kiemTraID(Integer idThuoc, String maPhieu) {
        Integer dem = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "select count(*) as so_luong from inventory_log "
                    + " where medication_id = ? and form_id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idThuoc);
            ps.setString(2, maPhieu);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                dem = rs.getInt("so_luong");
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dem);
        return dem;
    }

}
