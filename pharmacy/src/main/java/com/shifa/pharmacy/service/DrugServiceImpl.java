package com.shifa.pharmacy.service;

import com.shifa.pharmacy.exception.FileStorageException;
import com.shifa.pharmacy.model.Category;
import com.shifa.pharmacy.model.Drug;
import com.shifa.pharmacy.model.DrugManufacturer;
import com.shifa.pharmacy.repository.CategoryRepository;
import com.shifa.pharmacy.repository.DrugManufacturerRepository;
import com.shifa.pharmacy.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DrugServiceImpl implements IDrugService {

    final
    DrugRepository drugRepository;

    final
    DrugManufacturerRepository drugManufacturerRepository;

    final
    CategoryRepository categoryRepository;

    public DrugServiceImpl(DrugRepository drugRepository, DrugManufacturerRepository drugManufacturerRepository, CategoryRepository categoryRepository) {
        this.drugRepository = drugRepository;
        this.drugManufacturerRepository = drugManufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    private final String UPLOAD_DIR = "C:\\Users\\USER\\IdeaProjects\\pharmacy\\src\\main\\resources\\static\\photo";

    public List drugs(int id){
//        Category category = categoryRepository.findById(id).get();
        List<Drug> subDrugs = drugRepository.findDrugsByCategoryId(id);
//        for(Product p: products){
//            List<Category> categories = p.getCategories();
//            for(Category c:categories){
//                if(c.getName().equals(category.getName()));
//                subProducts.add(p);
//            }
//
//        }
        return  subDrugs;
    }

    public Drug createDrug(DrugManufacturer drugManufacturer, String name, double price, List<String> categories, MultipartFile file, String description){
        Drug drug = new Drug();
        drug.setDrugManufacturer(drugManufacturer);
        drug.setName(name);
        drug.setPrice(price);
        drug.setDescription(description);

        List<Category> p_categories = new ArrayList<>();
        for(String catName:categories){
            Category category = categoryRepository.findByName(catName);
            p_categories.add(category);
        }
        drug.setCategories(p_categories);

        try {
            // Generate Universally Unique Identifier, convert to string and add it to original name...
            String fileName = StringUtils.cleanPath(UUID.randomUUID().toString() + file.getOriginalFilename());
            Path imagePath = Paths.get(UPLOAD_DIR + File.separator + fileName);
            Files.copy(file.getInputStream(),imagePath, StandardCopyOption.REPLACE_EXISTING);
            drug.setUrl(fileName);

        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("File Not Found");
        }

        return drug;
    }

}
