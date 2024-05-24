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

    public List<DamageCategory> fetchAllDamageCategories(){
        return damageRepo.fetchAll();
    }

    public void addDamage(DamageCategory damage_category){
        damageRepo.AddDamage(damage_category);
    }

    public void updateCategory(DamageCategory damage_category, int category_id){
        damageRepo.updateDamage(damage_category, category_id);
    }
    public boolean deleteDamage(int category_id){
        return damageRepo.deleteDamage(category_id);
    }

    public DamageCategory findSpecifikDamage(int category_id){
        return damageRepo.findDamageByid(category_id);
    }

    public Double getSpecificDamagePrice(int category_id) {
        return damageRepo.findSpecificDamagePrice(category_id);
    }



}



