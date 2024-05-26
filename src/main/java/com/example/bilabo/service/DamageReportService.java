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

    // Viser alle skaderapporter
    public List<DamageReport> showReport() {
        return damageReportRepo.fetchAll();
    }

    // Tilføjer en ny skaderapport
    public void addDamageReport(DamageReport damageReport) {
        damageReportRepo.createDamageReport(damageReport);
    }

    // Opdaterer en skaderapport ved rapport-id
    public void updateReport(DamageReport damageReport, int report_id) {
        damageReportRepo.updateDamageReport(damageReport);
    }

    // Finder en specifik skaderapport ved rapport-id
    public DamageReport findSpecificReport(int reportId) {
        return damageReportRepo.findDamageReportById(reportId);
    }

    // Sletter en skaderapport ved rapport-id
    public boolean deleteReport(int reportId) {
        return damageReportRepo.deleteReport(reportId);
    }
}
