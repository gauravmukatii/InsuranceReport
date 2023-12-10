<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Insurance Report</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

    <div class="container">
        <h2>Insurace Report</h2>
             <p>
             <font color="green">${msg}</font>
             </p>
         <hr />
        <form:form action="search" modelAttribute="search" method="POST">
             <table>
                <tr>
                    <td>Plan Name : </td>
                     <td>
                          <form:select path="planName">
                              <form:option value="">Select Option</form:option>
                              <form:options items="${getplannames}"/>
                           </form:select>
                     </td>
                </tr>
                <tr>
                    <td>Plan Status : </td>
                     <td>
                          <form:select path="planStatus">
                              <form:option value="">Select Option</form:option>
                              <form:options items="${getplanstatus}"/>
                           </form:select>
                     </td>
                </tr>
                <tr>
                     <td>Gender : </td>
                     <td>

                             <form:radiobutton value="M" path="gender"/>Male
                             <form:radiobutton value="F" path="gender"/>Female
                     </td>
                </tr>
                <tr>
                   <td>Plan Start Date : </td>
                   <td>
                   <form:input path="planStartDate"/>
                   </td>
                </tr>
                <tr>
                   <td>Plan End Date : </td>
                   <td>
                   <form:input path="planEndDate"/>
                   </td>
                </tr>

                <tr>
                   <td>
                   <a type="Submit" href="/" class="btn btn-secondary">Reset</a>
                   </td>
                </tr>
                <tr>
                   <td>
                   <input type="Submit" name="Submit" class="btn btn-primary"/>
                   </td>
                </tr>
             </table>
        </form:form>

        <hr/>

        <table class="table table-striped">
           <thead>
                 <tr>
                     <th>SNo</th>
                     <th>PHolder Name</th>
                     <th>Gender</th>
                     <th>Plan Name</th>
                     <th>Plan Status</th>
                     <th>Start Date</th>
                     <th>End Date</th>
                     <th>benefitAmt</th>
                     <th>denialReason</th>
                     <th>terminationDate</th>
                     <th>terminationRsn</th>
                 </tr>
           </thead>
           <tbody>
                <c:forEach items="${plans}" var="plan">
                   <tr>
                      <td>${plan.citizenId}</td>
                      <td>${plan.citizenName}</td>
                      <td>${plan.gender}</td>
                      <td>${plan.planName}</td>
                      <td>${plan.planStatus}</td>
                      <td>${plan.planStartDate}</td>
                      <td>${plan.planEndDate}</td>
                      <td>${plan.benefitAmt}</td>
                      <td>${plan.denialReason}</td>
                      <td>${plan.terminationDate}</td>
                      <td>${plan.terminationRsn}</td>
                   </tr>
                 </c:forEach>
                 <tr>
                    <c:if test="${empty plans}">
                       <td colspan="11" style="text-align:center">No Records Found</td>
                    </c:if>
                 </tr>

           </tbody>
        </table>

        <hr/>

        Export : <a href="" value="Excel">Excel</a> <a href="" value="Pdf">Pdf</a>
</div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
