<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>



<div class="panel col-sm-4 col-sm-offset-4 panel-default">
    <div class="panel-heading panel-success">Payroll</div>
    <div class="panel-body">
        <%
            if (session.getAttribute("name") == null) {
        %>
        To Check your Payroll Please <a class="btn btn-success" href="login.jsp">Signin</a>
        <%
        } else if (session.getAttribute("name") == "failed") {
        %>
        To Check your Payroll Please <a class="btn btn-success" href="login.jsp">Signin</a>
        <%
        } else {
            HashMap<String, Double> hm;
            if (session.getAttribute("payrollMap") != null) {
                hm = (HashMap) session.getAttribute("payrollMap");
        %>

        <table class="table table-hover">
            <tbody>
                <tr>
                    <td>Basic Pay</td>
                    <td>Rs <%=hm.get("Basic Pay")%>/-</td>
                </tr>
                <tr>
                    <td>HRA</td>
                    <td>Rs <%=hm.get("HRA")%>/-</td>
                </tr>
                <tr>
                    <td>Tax</td>
                    <td>Rs <%=hm.get("Tax")%>/-</td>
                </tr>
                <tr>
                    <td>Gross Pay</td>
                    <td>Rs <%=hm.get("Gross Pay")%>/-</td>
                </tr>
                <tr>
                    <td>Net Salary</td>
                    <td>Rs <%=hm.get("Net Salary")%>/-</td>
                </tr>
            </tbody>
        </table>
        <%
                }
            }
        %>
    </div>
</div>