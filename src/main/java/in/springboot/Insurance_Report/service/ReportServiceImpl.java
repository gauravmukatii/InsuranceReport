package in.springboot.Insurance_Report.service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import in.springboot.Insurance_Report.entity.CitizenPlan;
import in.springboot.Insurance_Report.repo.CitizenPlanRepository;
import in.springboot.Insurance_Report.request.SearchRequest;
import in.springboot.Insurance_Report.util.EmailUtils;
import in.springboot.Insurance_Report.util.ExcelGenerator;
import in.springboot.Insurance_Report.util.PdfGenerator;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CitizenPlanRepository planRepo;

    @Autowired
    private ExcelGenerator excelGenerator;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private EmailUtils emailUtils;

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

//        if(null!=request.getPlanStartDate() && !"".equals(request.getPlanStartDate())){
//            String startDate = request.getPlanStartDate();
//            entity.setPlanStartDate((request.getPlanStartDate()));
//        }
        return planRepo.findAll(Example.of(entity));
    }

    @Override
    public boolean exportToExcel(HttpServletResponse response) throws Exception{

        File f = new File("Plans-Info.xls");

        List<CitizenPlan> records = planRepo.findAll();
        excelGenerator.generate(response, records, f);

        String subject = "Test Subject";
        String body = "<h1>Test Email Body</h1>";
        String to = "gaurav.mukati99@gmail.com";

        emailUtils.sendMail(subject, body, to, f);

        f.delete();

        return true;
    }

    @Override
    public boolean exportToPdf(HttpServletResponse response) throws Exception{
        File f = new File("plans.pdf");

        List<CitizenPlan> plans = planRepo.findAll();
        pdfGenerator.generate(response, plans, f);
        String subject = "Test Subject";
        String body = "<h1>Test Email Body</h1>";
        String to = "gaurav.mukati99@gmail.com";

        emailUtils.sendMail(subject, body, to, f);
        f.delete();

        return true;
    }
}
