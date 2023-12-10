package in.springboot.Insurance_Report.service;

import in.springboot.Insurance_Report.entity.CitizenPlan;
import in.springboot.Insurance_Report.request.SearchRequest;

import java.util.List;

public interface ReportService {

    public List<String> getPlanName();
    public List<String> getPlanStatus();
    public List<CitizenPlan> getPlans(SearchRequest request);
    public boolean exportToExcel();
    public boolean exportToPdf();

}
