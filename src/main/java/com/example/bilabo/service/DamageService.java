package com.example.bilabo.service;

import com.example.bilabo.model.DamageCategory;
import com.example.bilabo.reporsitories.DamageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageService {
    @Autowired
    DamageRepo damageRepo;

    // Henter alle skadekategorier
    public List<DamageCategory> fetchAllDamageCategories() {
        return damageRepo.fetchAll();
    }

    // Tilføjer en ny skadekategori
    public void addDamage(DamageCategory damage_category) {
        damageRepo.AddDamage(damage_category);
    }

    // Opdaterer en skadekategori ved kategori-id
    public void updateCategory(DamageCategory damage_category, int category_id) {
        damageRepo.updateDamage(damage_category, category_id);
    }

    // Sletter en skadekategori ved kategori-id
    public boolean deleteDamage(int category_id) {
        return damageRepo.deleteDamage(category_id);
    }

    // Finder en specifik skadekategori ved kategori-id
    public DamageCategory findSpecifikDamage(int category_id) {
        return damageRepo.findDamageByid(category_id);
    }

    // Henter prisen for en specifik skadekategori ved kategori-id
    public Double getSpecificDamagePrice(int category_id) {
        return damageRepo.findSpecificDamagePrice(category_id);
    }
}



