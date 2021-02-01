package com.shifa.pharmacy.repository;

import com.shifa.pharmacy.model.Drug;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DrugRepository extends CrudRepository<Drug, Integer>{

    @Query(value = "select p.* from product p inner join product_category pc on pc.product_id=p.id where pc.category_id = :id", nativeQuery = true)
    List<Drug> findDrugsByCategoryId(int id);

    @Query(value = "SELECT p.* FROM product p where p.name like %:searchText% ", nativeQuery = true)
    List<Drug> search(String searchText);

}
