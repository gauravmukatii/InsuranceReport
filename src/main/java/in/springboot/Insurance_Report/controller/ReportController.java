package in.springboot.Insurance_Report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
