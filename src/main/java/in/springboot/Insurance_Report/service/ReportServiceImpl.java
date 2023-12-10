package in.springboot.Insurance_Report.service;

import in.springboot.Insurance_Report.entity.CitizenPlan;
import in.springboot.Insurance_Report.repo.CitizenPlanRepository;
import in.springboot.Insurance_Report.request.SearchRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CitizenPlanRepository planRepo;

    @Override
    public List<String> getPlanName() {
        return planRepo.getPlanName();
    }

    @Override
    public List<String> getPlanStatus() {
        return planRepo.getPlanStatus();
    }

    @Override
    public List<CitizenPlan> getPlans(SearchRequest request) {
        CitizenPlan entity = new CitizenPlan();
        if(null!=request.getPlanName() && !"".equals(request.getPlanName())){
            entity.setPlanName((request.getPlanName()));
        }

        if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())){
            entity.setPlanStatus((request.getPlanStatus()));
        }

        if(null!=request.getGender() && !"".equals(request.getGender())){
            entity.setGender((request.getGender()));
        }
        return planRepo.findAll(Example.of(entity));
    }

    @Override
    public boolean exportToExcel() {

        return false;
    }

    @Override
    public boolean exportToPdf() {

        return false;
    }
}
