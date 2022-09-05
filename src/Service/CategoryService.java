/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Category;
import Repository.CategoryRepository;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService() {
        categoryRepository = new CategoryRepository();
    }
    
    public ArrayList<Category> getAll(){
        return categoryRepository.getAll();
    }
    
}
