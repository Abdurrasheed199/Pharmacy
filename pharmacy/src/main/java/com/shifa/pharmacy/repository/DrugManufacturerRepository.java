package com.shifa.pharmacy.repository;

import com.shifa.pharmacy.model.DrugManufacturer;
import org.springframework.data.repository.CrudRepository;

public interface DrugManufacturerRepository extends CrudRepository<DrugManufacturer, Integer> {
    DrugManufacturer findDrugManufacturerByName(String name);

}
