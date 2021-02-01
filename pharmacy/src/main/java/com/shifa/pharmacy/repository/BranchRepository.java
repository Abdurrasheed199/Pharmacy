package com.shifa.pharmacy.repository;

import com.shifa.pharmacy.model.Branch;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepository extends CrudRepository<Branch, Integer> {
   /* Branch findPharmacyByRegistrationNumber(String registrationNumber);*/
}
