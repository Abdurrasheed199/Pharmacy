package com.shifa.pharmacy.repository;

import com.shifa.pharmacy.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query(value = "select c.* from category c where c.parent_id is null", nativeQuery = true)
    List<Category> findCategoryByParent();

    List<Category> findCategoriesByParentId(int id);

    Category findByName(String name);
}
