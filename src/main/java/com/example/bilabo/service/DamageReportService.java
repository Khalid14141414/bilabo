package com.example.bilabo.service;

import com.example.bilabo.model.DamageReport;
import com.example.bilabo.reporsitories.Damage_reportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageReportService {
    @Autowired
    Damage_reportRepo damage_reportRepo;

    public List<DamageReport> showReport() {
        return damage_reportRepo.fetchAll();
    }
    public void addDamage_report(DamageReport damage_report){
        damage_reportRepo.CreateDamage_report(damage_report);
    }

    public void updateReport(DamageReport damageReport, int report_id){
        damage_reportRepo.updateDamageReport(damageReport, report_id);
    }

    public DamageReport findSpecifikReport(int report_id){
        return damage_reportRepo.findDamageReportByid(report_id);
    }
    public boolean deleteReport(int report_id){
        return damage_reportRepo.deleteReport(report_id);
    }

}
