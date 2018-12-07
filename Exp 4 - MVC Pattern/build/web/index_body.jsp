<%@page import="java.sql.ResultSet"%>
<%
    if (session.getAttribute("username") != null) {
        if (session.getAttribute("username").toString().equals("William Scott")) {
%>
<a href="admin_modify.jsp" class="btn btn-success col-sm-offset-10"> Modify Database </a>
<%
        }
    }
%>

<div class="col-sm-4 col-sm-offset-4" style="overflow: hidden;">
    <form role="form" action="Action" method="post">
        <div class="form-group">
            <label for="sym">Enter your Symptoms</label>
            <input type="text" class="form-control" id="sym" name="sym" placeholder="Eg: Headache Backpain... etc">
        </div>
        <button type="submit" class="btn btn-default col-sm-offset-10">Search</button>
    </form>
</div>