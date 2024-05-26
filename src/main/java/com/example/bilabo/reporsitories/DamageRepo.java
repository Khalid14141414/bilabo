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

    // Henter alle skadekategorier fra databasen
    public List<DamageCategory> fetchAll() {
        return template.query("SELECT * FROM damage_category", rowMapper);
    }

    // Tilføjer en ny skadekategori til databasen
    public void AddDamage(DamageCategory d) {
        template.update("INSERT INTO damage_category (category_id, damage_name, price) VALUES (?,?,?)",
                d.getCategory_id(), d.getDamage_name(), d.getPrice());
    }

    // Opdaterer en skadekategori i databasen
    public void updateDamage(DamageCategory damage_category, int category_id) {
        template.update("UPDATE damage_category SET damage_name = ?, price = ? WHERE category_id = ?",
                damage_category.getDamage_name(), damage_category.getPrice(), damage_category.getCategory_id());
    }

    // Sletter en skadekategori fra databasen ved dens kategori-id
    public Boolean deleteDamage(int category_id) {
        return template.update("DELETE FROM damage_category WHERE category_id = ?", category_id) > 0;
    }

    // Finder en skadekategori i databasen ved dens kategori-id
    public DamageCategory findDamageByid(int category_id) {
        List<DamageCategory> categories = template.query("SELECT * FROM damage_category WHERE category_id = ?", rowMapper, category_id);
        return categories.size() == 1 ? categories.get(0) : null;
    }

    // Finder prisen for en specifik skadekategori ved dens kategori-id
    public Double findSpecificDamagePrice(int category_id) {
        return template.queryForObject("SELECT price FROM damage_category WHERE category_id = ?", Double.class, category_id);
    }
}

