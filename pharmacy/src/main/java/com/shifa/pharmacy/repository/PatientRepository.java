package com.shifa.pharmacy.repository;

import com.shifa.pharmacy.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
}
