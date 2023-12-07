package in.springboot.Insurance_Report.repo;

import in.springboot.Insurance_Report.entity.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer> {

    @Query("select distinct(planName) from CitizenPlan")
    public List<String> getPlanName();

    @Query("select distinct(planStatus) from CitizenPlan")
    public List<String> getPlanStatus();
}
