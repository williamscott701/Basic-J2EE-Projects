
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    if (!session.getAttribute("username").toString().equals("William Scott")) {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    if (request.getParameter("sbtn") != null) {
        if (request.getParameter("sbtn").toString().equals("Add List")) {
            String tname = request.getParameter("tname");
            String tuse = request.getParameter("tuse");

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
            Statement st = con.createStatement();
            st.executeUpdate("insert into tablets values('" + tname + "', '" + tuse + "')");
        }
    }

%>
<a class="btn btn-default col-sm-offset-9" href="admin_modify.jsp">Return to Admin Modify Page</a>

<form class="form-horizontal" role="form" style="overflow: hidden;">
    <div class="form-group">
        <label class="control-label col-sm-4" for="tname">Tablet Name</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="tname" name="tname" placeholder="Tablet Name">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="tuse">Tablet Use</label>
        <div class="col-sm-5"> 
            <input type="text" class="form-control" id="tuse" name="tuse"  placeholder="Tablet Use">
        </div>
    </div>
    <div class="form-group"> 
        <div class="col-sm-offset-9 col-sm-10">
            <input type="submit" name="sbtn" class="btn btn-primary" value="Add List" />
        </div>
    </div>
</form>

<div style="margin-bottom: 30px;"></div>