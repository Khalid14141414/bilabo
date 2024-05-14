package com.example.bilabo.service;

import com.example.bilabo.model.DamageReport;
import com.example.bilabo.reporsitories.DamageReportRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DamageReportService {

    @Autowired
    private DamageReportRepository damageReportRepository;

    public DamageReport createDamageReport(DamageReport damageReport) {
        return damageReportRepository.save(damageReport);
    }

    public DamageReport getDamageReportById(int damageId) {
        return damageReportRepository.findById(damageId);
    }

    public List<DamageReport> getAllDamageReports() {
        return damageReportRepository.findAll();
    }

    public void updateDamageReport(int damageId, DamageReport updatedDamageReport) {
        DamageReport existingDamageReport = damageReportRepository.findById(damageId);
        if (existingDamageReport != null) {
            updatedDamageReport.setDamageID(damageId);
            damageReportRepository.save(updatedDamageReport);
        }
    }

    public void deleteDamageReport(int damageId) {
        damageReportRepository.deleteById(damageId);
    }
}

