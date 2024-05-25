package com.example.bilabo.reporsitories;

import com.example.bilabo.model.DamageCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DamageRepo {

    @Autowired
    JdbcTemplate template;

    private RowMapper<DamageCategory> rowMapper = new BeanPropertyRowMapper<>(DamageCategory.class);

    public List<DamageCategory> fetchAll() {
        return template.query("SELECT * FROM damage_category", rowMapper);
    }

    public void AddDamage(DamageCategory d) {
        template.update("INSERT INTO damage_category (category_id, damage_name, price) VALUES (?,?,?)",
                d.getCategory_id(),d.getDamage_name(),d.getPrice());
    }

    public void updateDamage(DamageCategory damage_category, int category_id) {
        template.update("UPDATE damage_category SET damage_name= ?, price= ? where category_id=?",
                damage_category.getDamage_name(), damage_category.getPrice(), damage_category.getCategory_id());
    }

    public Boolean deleteDamage(int category_id) {
        return template.update("DELETE FROM damage_category WHERE category_id = ?", category_id) > 0;
    }

    public DamageCategory findDamageByid(int category_id) {
        List<DamageCategory> categories = template.query("SELECT * FROM damage_category WHERE category_id = ?", rowMapper, category_id);
        return categories.size() == 1 ? categories.get(0) : null;
    }

    public Double findSpecificDamagePrice(int category_id) {
        return template.queryForObject("SELECT price FROM damage_category WHERE category_id = ?", Double.class, category_id);
    }
}