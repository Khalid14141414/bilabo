package com.example.bilabo.reporsitories;

import com.example.bilabo.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DamageReportRepo {

    @Autowired
    JdbcTemplate template;

    private RowMapper<DamageReport> rowMapper = new BeanPropertyRowMapper<>(DamageReport.class);

    public List<DamageReport> fetchAll() {
        return template.query("SELECT * FROM damage_report", rowMapper);
    }

    public void createDamageReport(DamageReport d) {
        template.update("INSERT INTO damage_report (report_id,total_price,contract_id) VALUES (?,?,?)",
                d.getReport_id(),d.getTotal_price(),d.getContract_id());
    }

    public void updateDamageReport(DamageReport damageReport) {
        template.update("UPDATE damage_report SET total_price= ?, contract_id= ? where report_id=?",
                damageReport.getTotal_price(), damageReport.getContract_id(), damageReport.getReport_id());
    }

    public DamageReport findDamageReportById(int report_id) {
        List<DamageReport> reports = template.query("SELECT * FROM damage_report WHERE report_id = ?", rowMapper, report_id);
        return reports.size() == 1 ? reports.get(0) : null;
    }

    public boolean deleteReport(int report_id) {
        return template.update("DELETE FROM damage_report WHERE report_id=?", report_id) > 0;
    }
}