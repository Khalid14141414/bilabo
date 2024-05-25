package com.example.bilabo.service;

import com.example.bilabo.model.DamageReport;
import com.example.bilabo.reporsitories.DamageReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageReportService {

    @Autowired
    DamageReportRepo damageReportRepo;

    public List<DamageReport> showReport() {
        return damageReportRepo.fetchAll();
    }

    public void addDamageReport(DamageReport damageReport){
        damageReportRepo.createDamageReport(damageReport);
    }

    public void updateReport(DamageReport damageReport, int report_id){
        damageReportRepo.updateDamageReport(damageReport);
    }

    public DamageReport findSpecificReport(int reportId){
        return damageReportRepo.findDamageReportById(reportId);
    }

    public boolean deleteReport(int reportId){
        return damageReportRepo.deleteReport(reportId);
    }
}