package com.shifa.pharmacy.service;

import com.shifa.pharmacy.model.Drug;
import com.shifa.pharmacy.model.DrugManufacturer;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDrugService {

    public List drugs(int id);
    public Drug createDrug(DrugManufacturer drugManufacturer, String name, double price, List<String> categories, MultipartFile file, String description);

}
