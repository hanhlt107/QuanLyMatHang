/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Category;
import Model.Medication;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MedicationRepository {

    public ArrayList<Medication> getAll() {
        ArrayList<Medication> lstMedication = new ArrayList<>();

        try {
            Connection connection = DBContext.getConnection();
            String query = "select \n"
                    + "medication.id,\n"
                    + "medication.medication_code,\n"
                    + "medication.medication_name,\n"
                    + "medication.quantity,\n"
                    + "medication.current_price,\n"
                    + "category.id as category_id,\n"
                    + "category.category_name as category_name\n"
                    + "from medication\n"
                    + "left join category on category.id = medication.category_id ";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                Integer idThuoc = rs.getInt("id");
                String maThuoc = rs.getString("medication_code");
                String tenThuoc = rs.getString("medication_name");
                Integer soLuong = rs.getInt("quantity");
                BigDecimal gia = rs.getBigDecimal("current_price");
                Integer idDM = rs.getInt("category_id");
                String tenDM = rs.getString("category_name");

                Category category = new Category();
                category.setId(idDM);
                category.setCategoryName(tenDM);

                Medication medication = new Medication(idThuoc, maThuoc, tenThuoc, soLuong, gia, category);
                lstMedication.add(medication);
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstMedication;
    }

    public Integer insert(Medication medication) {
        Integer ketQua = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "insert into medication(medication_code,medication_name,quantity,current_price,category_id)\n"
                    + "values(?,?,?,?,?) ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, medication.getMedicationCode());
            ps.setString(2, medication.getMedicationName());
            ps.setInt(3, medication.getQuantity());
            ps.setBigDecimal(4, medication.getCurrentPrice());
            Integer idDM = null;
            if (medication.getCategory() != null) {
                idDM = medication.getCategory().getId();
            }
            ps.setInt(5, idDM);
            ketQua = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public Integer update(String ma, Medication medication) {
        Integer ketQua = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "update medication"
                    + " set "
                    + "medication_name = ?,"
                    + "quantity = ?,"
                    + "current_price = ?,"
                    + "category_id = ? "
                    + "where medication_code = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, medication.getMedicationName());
            ps.setInt(2, medication.getQuantity());
            ps.setBigDecimal(3, medication.getCurrentPrice());
            Integer idDM = null;
            if (medication.getCategory() != null) {
                idDM = medication.getCategory().getId();
            }
            ps.setInt(4, idDM);
            ps.setString(5, ma);
            ketQua = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ketQua;
    }

    public Integer delete(int id) {
        Integer ketQua = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "delete from inventory_log where medication_id = ? "
                    + "delete from medication where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ketQua = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ketQua;
    }

    public Integer getID(String ma) {
        Integer id = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "select id from medication where medication_code = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                id = rs.getInt("Id");
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public Medication getAllByMa(String ma) {

        try {
            Connection connection = DBContext.getConnection();
            String query = "select \n"
                    + "medication.id,\n"
                    + "medication.medication_code,\n"
                    + "medication.medication_name,\n"
                    + "medication.quantity,\n"
                    + "medication.current_price,\n"
                    + "category.id as category_id,\n"
                    + "category.category_name as category_name\n"
                    + "from medication\n"
                    + "left join category on category.id = medication.category_id "
                    + " where medication.medication_code = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next() == true) {
                Integer idThuoc = rs.getInt("id");
                String maThuoc = rs.getString("medication_code");
                String tenThuoc = rs.getString("medication_name");
                Integer soLuong = rs.getInt("quantity");
                BigDecimal gia = rs.getBigDecimal("current_price");
                Integer idDM = rs.getInt("category_id");
                String tenDM = rs.getString("category_name");

                Category category = new Category();
                category.setId(idDM);
                category.setCategoryName(tenDM);

                Medication medication = new Medication(idThuoc, maThuoc, tenThuoc, soLuong, gia, category);
                return medication;
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Medication> getAllByName(String ten) {
        ArrayList<Medication> lstMedication = new ArrayList<>();

        try {
            Connection connection = DBContext.getConnection();
            String query = "select \n"
                    + "medication.id,\n"
                    + "medication.medication_code,\n"
                    + "medication.medication_name,\n"
                    + "medication.quantity,\n"
                    + "medication.current_price,\n"
                    + "category.id as category_id,\n"
                    + "category.category_name as category_name\n"
                    + "from medication\n"
                    + "left join category on category.id = medication.category_id "
                    + " where medication.medication_name like '%'+ltrim(rtrim(N'"+ten+"')) +'%' ";

            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, ten);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                Integer idThuoc = rs.getInt("id");
                String maThuoc = rs.getString("medication_code");
                String tenThuoc = rs.getString("medication_name");
                Integer soLuong = rs.getInt("quantity");
                BigDecimal gia = rs.getBigDecimal("current_price");
                Integer idDM = rs.getInt("category_id");
                String tenDM = rs.getString("category_name");

                Category category = new Category();
                category.setId(idDM);
                category.setCategoryName(tenDM);

                Medication medication = new Medication(idThuoc, maThuoc, tenThuoc, soLuong, gia, category);
                lstMedication.add(medication);
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstMedication;
    }

    public Integer addQuantity(Integer idThuoc, Integer soLuong) {
        Integer SL = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "update medication set quantity += ?"
                    + " where id = ? ";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, soLuong);
            ps.setInt(2, idThuoc);
            SL = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SL;
    }

    public Integer minusQuantity(Integer idThuoc, Integer soLuong) {
        Integer SL = -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "update medication set quantity -= ?"
                    + " where id = ? ";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, soLuong);
            ps.setInt(2, idThuoc);
            SL = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SL;
    }
}
