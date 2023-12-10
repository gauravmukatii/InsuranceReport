package in.springboot.Insurance_Report.service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import in.springboot.Insurance_Report.entity.CitizenPlan;
import in.springboot.Insurance_Report.repo.CitizenPlanRepository;
import in.springboot.Insurance_Report.request.SearchRequest;
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

//        if(null!=request.getPlanStartDate() && !"".equals(request.getPlanStartDate())){
//            String startDate = request.getPlanStartDate();
//            entity.setPlanStartDate((request.getPlanStartDate()));
//        }
        return planRepo.findAll(Example.of(entity));
    }

    @Override
    public boolean exportToExcel(HttpServletResponse response) throws Exception{

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("plans-data");
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Citizen Name");
        headerRow.createCell(2).setCellValue("Gender");
        headerRow.createCell(3).setCellValue("Plan Name");
        headerRow.createCell(4).setCellValue("Plan Status");
        headerRow.createCell(5).setCellValue("Plan Start Date");
        headerRow.createCell(6).setCellValue("Plan End Date");
        headerRow.createCell(7).setCellValue("Benefit Amt");

        List<CitizenPlan> records = planRepo.findAll();

        int dataRow = 1;

        for(CitizenPlan plan : records){
            Row dataRows = sheet.createRow(dataRow);
            dataRows.createCell(0).setCellValue(plan.getCitizenId());
            dataRows.createCell(1).setCellValue(plan.getCitizenName());
            dataRows.createCell(2).setCellValue(plan.getGender());
            dataRows.createCell(3).setCellValue(plan.getPlanName());
            dataRows.createCell(4).setCellValue(plan.getPlanStatus());

            if(null != plan.getPlanStartDate()){
                dataRows.createCell(5).setCellValue(plan.getPlanStartDate()+"");
            }else{
                dataRows.createCell(5).setCellValue("N/A");
            }

            if(null != plan.getPlanEndDate()){
                dataRows.createCell(5).setCellValue(plan.getPlanEndDate()+"");
            }else{
                dataRows.createCell(5).setCellValue("N/A");
            }

            if(null != plan.getBenefitAmt()){
                dataRows.createCell(5).setCellValue(plan.getBenefitAmt()+"");
            }else{
                dataRows.createCell(5).setCellValue("N/A");
            }

            dataRow++;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return true;
    }

    @Override
    public boolean exportToPdf(HttpServletResponse response) throws Exception{

        Document document = new Document();

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Paragraph p = new Paragraph("Citizen Plans info");
        document.add(p);

        PdfPTable table = new PdfPTable(8);

        table.addCell("Id");
        table.addCell("Citizen Name");
        table.addCell("Gender");
        table.addCell("Plan Name");
        table.addCell("Plan Status");
        table.addCell("Plan Start Date");
        table.addCell("Plan End Date");
        table.addCell("Benefit Amount");

        List<CitizenPlan> plans = planRepo.findAll();

        for(CitizenPlan plan: plans){
            table.addCell(String.valueOf(plan.getCitizenId()));
            table.addCell(plan.getCitizenName());
            table.addCell(plan.getGender());
            table.addCell(plan.getPlanName());
            table.addCell(plan.getPlanStatus());
            table.addCell(plan.getPlanStartDate()+"");
            table.addCell(plan.getPlanEndDate()+"");
            table.addCell(String.valueOf(plan.getBenefitAmt()));
        }

        document.add(table);
        document.close();

        return true;
    }
}
