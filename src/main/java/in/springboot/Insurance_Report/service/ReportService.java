package in.springboot.Insurance_Report.service;

import in.springboot.Insurance_Report.entity.CitizenPlan;
import in.springboot.Insurance_Report.request.SearchRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ReportService {

    public List<String> getPlanName();
    public List<String> getPlanStatus();
    public List<CitizenPlan> getPlans(SearchRequest request);
    public boolean exportToExcel(HttpServletResponse response) throws Exception;
    public boolean exportToPdf();

}
