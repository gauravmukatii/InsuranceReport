package in.springboot.Insurance_Report.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchRequest {

    private String planName;
    private String planStatus;
    private String gender;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
}
