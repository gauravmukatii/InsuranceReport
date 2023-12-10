package in.springboot.Insurance_Report.util;

import in.springboot.Insurance_Report.entity.CitizenPlan;
import in.springboot.Insurance_Report.repo.CitizenPlanRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelGenerator {

    public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception {

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
                dataRows.createCell(6).setCellValue(plan.getPlanEndDate()+"");
            }else{
                dataRows.createCell(6).setCellValue("N/A");
            }

            if(null != plan.getBenefitAmt()){
                dataRows.createCell(7).setCellValue(plan.getBenefitAmt()+"");
            }else{
                dataRows.createCell(7).setCellValue("N/A");
            }

            dataRow++;
        }

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

    }
}
