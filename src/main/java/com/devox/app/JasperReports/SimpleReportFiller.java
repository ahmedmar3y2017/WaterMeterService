package com.devox.app.JasperReports;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SimpleReportFiller {

    private String reportFileName;

    private JasperReport jasperReport;

    private JasperPrint jasperPrint;


    public void compileReport() {
        try {

            InputStream reportStream = getClass().getResourceAsStream("/reports/".concat(reportFileName));
            jasperReport = JasperCompileManager.compileReport(reportStream);
//            JRSaver.saveObject(jasperReport, "reports/"+reportFileName.replace(".jrxml", ".jasper"));
        } catch (JRException ex) {
            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillReport(JRBeanCollectionDataSource dataSource, Map<String, Object> param) {
        try {
//            jasperReport.setProperty("imagesDir","D:\\asset\\Tool\\jupiterMigrationTool\\src\\main\\resources\\reports");
            jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void fillReport(JRBeanCollectionDataSource dataSource) {
        try {

            jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }


}