package in.springboot.Insurance_Report.controller;

import in.springboot.Insurance_Report.request.SearchRequest;
import in.springboot.Insurance_Report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportController {

    @Autowired
    private ReportService service;

    @PostMapping("/searchrequest")
    public String handleSearch(SearchRequest searchRequest, Model model){



        init(model);

        return "index";
    }

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
    }
}
