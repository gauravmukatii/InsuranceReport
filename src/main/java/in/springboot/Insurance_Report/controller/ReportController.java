package in.springboot.Insurance_Report.controller;

import in.springboot.Insurance_Report.request.SearchRequest;
import in.springboot.Insurance_Report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/excel")
    public void excelExport(HttpServletResponse response) throws Exception{
        response.setContentType("application/octet-stream");

        response.addHeader("content-Disposition", "attachment;filename=plans.xls");

        service.exportToExcel(response);
    }

    @PostMapping("/search")
    public String handleSearch(@ModelAttribute("search") SearchRequest searchRequest, Model model){

        System.out.println(searchRequest);
        model.addAttribute("plans", service.getPlans(searchRequest));

        init(model);

        return "index";
    }


    /**
     * This method is used to load index page
     * @param model
     * @return String
     */
    @GetMapping("/")
    public String indexPage(Model model){

        SearchRequest request = new SearchRequest();
        model.addAttribute("search", request);
        init(model);

        return "index";
    }

    private void init(Model model) {
        model.addAttribute("getplannames", service.getPlanName());
        model.addAttribute("getplanstatus", service.getPlanStatus());
//        model.addAttribute("plans", service.getPlans());
    }
}
