package in.springboot.Insurance_Report.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "CITIZEN_PLAN_INFO")
public class CitizenPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer citizenId;
    private String citizenName;
    private String gender;
    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Integer benefitAmt;
    private String denialReason;
    private LocalDate terminationDate;
    private String terminationRsn;

}

