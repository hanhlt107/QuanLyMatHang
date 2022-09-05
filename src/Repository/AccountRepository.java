/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AccountRepository {

    public Integer getRole(String userName, String pass) {
        Integer role= -1;
        try {
            Connection connection = DBContext.getConnection();
            String query = "select role from account\n"
                    + "where username = ? and password = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, pass);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                role = rs.getInt("role");
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }
}
