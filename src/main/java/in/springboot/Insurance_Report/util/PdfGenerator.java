package in.springboot.Insurance_Report.util;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import in.springboot.Insurance_Report.entity.CitizenPlan;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class PdfGenerator {

    public void generate(HttpServletResponse response, List<CitizenPlan> plans, File f) throws Exception{

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());
        PdfWriter.getInstance(document, new FileOutputStream(f));

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

    }
}
