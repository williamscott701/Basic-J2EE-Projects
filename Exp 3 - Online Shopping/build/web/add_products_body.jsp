
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    if (!session.getAttribute("username").toString().equals("William Scott")) {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    if (request.getParameter("sbtn") != null) {
        if (request.getParameter("sbtn").toString().equals("AddProduct")) {
            String pid = request.getParameter("pid");
            String pname = request.getParameter("pname");
            String pcost = request.getParameter("pcost");
            String purl = request.getParameter("purl");
            String pcatego = request.getParameter("pcatego");
            String pdesc = request.getParameter("pdesc");

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
            String query = "select * from ws.shoppingproducts";
            Statement st = con.createStatement();
            st.executeUpdate("insert into ws.shoppingproducts values('" + pid + "', '" + pname + "', '" + pcost + "', '" + purl + "', '" + pcatego + "', '" + pdesc + "')");
        }
    }

%>
<a class="btn btn-default col-sm-offset-9" href="admin_modify.jsp">Return to Admin Modify Page</a>

<form class="form-horizontal" role="form" style="overflow: hidden;">
    <div class="form-group">
        <label class="control-label col-sm-4" for="pid">Product ID</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="pid" name="pid" placeholder="Product ID">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="pname">Product Name</label>
        <div class="col-sm-5"> 
            <input type="text" class="form-control" id="pname" name="pname"  placeholder="Product Name">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="pcost">Product Cost</label>
        <div class="col-sm-5"> 
            <input type="text" class="form-control" id="pcost" name="pcost"  placeholder="Product Cost">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="purl">Product URL</label>
        <div class="col-sm-5"> 
            <input type="text" class="form-control" id="purl" name="purl"  placeholder="Product URL">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="pcatego">Product Category</label>
        <div class="col-sm-5"> 
            <input type="text" class="form-control" id="pcatego" name="pcatego"  placeholder="Product Category">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="pdesc">Product Description</label>
        <div class="col-sm-5"> 
            <input type="text" class="form-control" id="pdesc"  name="pdesc" placeholder="Product Description">
        </div>
    </div>
    <div class="form-group"> 
        <div class="col-sm-offset-9 col-sm-10">
            <input type="submit" name="sbtn" class="btn btn-default" value="AddProduct" />
        </div>
    </div>
</form>

<div style="margin-bottom: 30px;"></div>