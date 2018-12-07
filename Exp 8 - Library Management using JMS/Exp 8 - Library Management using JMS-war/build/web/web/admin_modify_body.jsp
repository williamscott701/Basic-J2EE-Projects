
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    if (!session.getAttribute("name").toString().equals("William Scott")) {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
    if (request.getAttribute("flag") != null) {
        if (request.getAttribute("flag").toString() == "true") {
%>
<div class='container alert alert-success'>Successfully Updated / Inserted</div>

<%
} else {
%>
<div class='container alert alert-danger'>Error, Try Again!....</div>
<%
        }
    }
%>

<form class="form-horizontal" role="form" style="overflow: hidden;" action="Action" method="post">
    <div class="form-group">
        <label class="control-label col-sm-4" for="username">Username</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="bp">Basic Pay</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="bp" name="bp" placeholder="Enter Basic Pay">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="hra">HRA</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="hra" name="hra" placeholder="Enter HRA">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="tax">Tax</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="tax" name="tax" placeholder="Enter Tax">
        </div>
    </div>
    <div class="form-group"> 
        <div class="col-sm-offset-8 col-sm-10">
            <input type="submit" name="sbtn" class="btn btn-primary" value="Add / Update Details for Employee" />
        </div>
    </div>
</form>

<div style="margin-bottom: 30px;"></div>