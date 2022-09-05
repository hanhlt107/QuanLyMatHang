/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class CategoryRepository {
    public ArrayList<Category> getAll() {
        ArrayList<Category> lstCategory = new ArrayList<>();

        try {
            Connection connection = DBContext.getConnection();
            String query = "select * from category ";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maDM = rs.getString("category_code");
                Integer idDM = rs.getInt("id");
                String tenDM = rs.getString("category_name");

                Category category = new Category(idDM, maDM, tenDM);
                lstCategory.add(category);

            }

            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCategory;
    }
}
