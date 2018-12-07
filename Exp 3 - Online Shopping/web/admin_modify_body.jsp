
<%
    if (!session.getAttribute("username").toString().equals("William Scott")) {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
%>


<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%
    Class.forName("org.apache.derby.jdbc.ClientDriver");
    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
    String query = "select * from ws.shoppingproducts";
    Statement st = con.createStatement();
    Set<String> set;
    if (request.getParameter("removeid") != null) {
        String productid = request.getParameter("removeid");
        Statement st2 = con.createStatement();
        st2.executeUpdate("DELETE FROM SHOPPINGPRODUCTS WHERE PRODUCTID = '" + productid + "'");

//        out.println("<script>alert('Sucessfully Added to Cart');</script>");
        out.println("<div class='alert alert-danger col-sm-8 col-sm-offset-2'>Sucessfully Removed</div>");
    }
    ResultSet rs = st.executeQuery(query);
%>


<a href="add_products.jsp" class="btn btn-success col-sm-offset-10">Add Products</a>

<div class="container">
    <div class="row">
        <%while (rs.next()) {
        %>
        <div class="col-sm-6 col-md-3 panel">
            <div class="thumbnail">
                <img src='<%=rs.getString("producturl")%>' alt='<%=rs.getString("productname")%>' style="height: 150px;">
            </div>
            <div class="caption">
                <h3><%=rs.getString("productname")%></h3>
                <span>Rs <%=rs.getString("productcost")%>/-</span>
                <p><%=rs.getString("productdescription")%></p>
                <p>
                    <a href='admin_modify.jsp?removeid=<%=rs.getString("productid")%>' class="btn btn-danger" role="button">
                        Remove Product
                    </a>
                </p>
            </div>
        </div>
        <%
            }
        %>
    </div>

</div>

<div style="margin-bottom: 30px;"></div>