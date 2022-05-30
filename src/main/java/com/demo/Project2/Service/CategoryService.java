package com.demo.Project2.Service;

import com.demo.Project2.Model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private ArrayList<Category> categories = new ArrayList();

    public ArrayList<Category> getCategory() {
        return categories;
    }

    public boolean addCategory(Category category) {
        return categories.add(category);
    }

    public boolean updateCategory(Integer index, Category category) {
        if (index >= categories.size() || index < 0) {
            return false;
        }
        categories.set(index, category);
        return true;
    }

    public boolean removeCategory(String id) {
        Integer currentCategory = getCategory(id);
        categories.remove((int)currentCategory);
        return true;
    }

    public Integer getCategory(String id){
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getID().equals(id)){
                return i;
            }
        }
        return null;
    }
}
