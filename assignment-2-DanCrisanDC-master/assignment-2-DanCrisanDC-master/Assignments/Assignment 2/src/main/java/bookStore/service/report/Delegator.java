package bookStore.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Delegator {

    @Autowired
    private CSVReportWriter csvReportWriter;
    @Autowired
    private PDFReportWriter pdfReportWriter;



    public ReportWriter chooseReportType(String type) {

        if (type.equals("csv")) {
            return csvReportWriter;
        }
        if (type.equals("pdf")) {
            return pdfReportWriter;
        }
        return null;
    }
}
