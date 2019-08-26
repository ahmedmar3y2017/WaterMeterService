package com.devox.app.webservice;

import com.devox.app.JasperReports.SimpleReportExporter;
import com.devox.app.JasperReports.SimpleReportFiller;
import com.devox.app.services.FileStorageService;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/rest")
@CrossOrigin
public class ReportWebService {

    org.slf4j.Logger logger = LoggerFactory.getLogger(ReportWebService.class);

    @Autowired
    SimpleReportFiller simpleReportFiller;
    @Autowired
    SimpleReportExporter simpleExporter;
    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "report", method = RequestMethod.GET)
    public ResponseEntity<Resource> report(HttpServletResponse response , HttpServletRequest request) throws Exception {
//        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report());


        simpleReportFiller.setReportFileName("report1.jrxml");
        simpleReportFiller.compileReport();
        simpleReportFiller.fillReport(dataSource);

        simpleExporter.setJasperPrint(simpleReportFiller.getJasperPrint());
        Date date = new Date();
        long time = date.getTime();

        simpleExporter.exportToPdf(time + ".pdf", "Jupiter Migration Tool");
//        simpleExporter.exportToXlsx(time + ".xlsx", "Jupiter Migration Tool");
//        simpleExporter.exportToCsv(time + ".csv");
//        simpleExporter.exportToHtml(time + ".html");



        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(time + ".pdf");

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }
    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result = new ArrayList<>();
//        for (Product product : productRepository.findAll()) {
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("id", 1);
        item.put("name", "NAME");
        item.put("price", new BigDecimal(20));
        item.put("quantity", 30);
        item.put("categoryName", "CATEGORY");
        result.add(item);
//        }
        return result;
    }
}
