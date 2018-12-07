<%@page import="java.sql.ResultSet"%>
<%
    if (session.getAttribute("name") != null) {
        if (session.getAttribute("name").toString().equals("William Scott")) {
%>
<a href="admin_modify.jsp" class="btn btn-success col-sm-offset-10"> Modify Database </a>
<%
        }
    }
%>

<div class="panel col-sm-4 col-sm-offset-4 panel-default">
    <div class="panel-heading panel-success">Note</div>
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
        %>
        To Check your Payroll Please <a class="btn btn-success" href="Action?payroll=true">Click Here</a>
        <%
            }
        %>
    </div>
</div>