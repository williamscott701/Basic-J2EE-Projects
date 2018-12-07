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
    ResultSet rs = st.executeQuery(query);
    Set<String> set;
    if (request.getParameter("addid") != null) {
        String productid = request.getParameter("addid");
        Statement st2 = con.createStatement();
        ResultSet rs2 = st2.executeQuery("select * from WS.SHOPPINGPRODUCTS WHERE PRODUCTID='" + productid + "'");
        rs2.next();
//        out.print(rs2.getString("productcost"));

        if (session.getAttribute("list") == null) {
//            out.print("productcost");
            set = new HashSet();
            set.add(productid);
        } else {
            set = (HashSet) session.getAttribute("list");
            set.add(productid);
        }
//        out.println("<script>alert('Sucessfully Added to Cart');</script>");
        out.println("<div class='alert alert-success col-sm-8 col-sm-offset-2'>Sucessfully Added to Cart</div>");
        session.setAttribute("list", set);
        out.print(set);
    }
    if (session.getAttribute("username") != null) {
        if (session.getAttribute("username").toString().equals("William Scott")) {
%>
<a href="admin_modify.jsp" class="btn btn-success col-sm-offset-10"> Modify Products </a>
<%
        }
    }
%>

<div class="container">
    <div class="row">
        <%while (rs.next()) {
        %>
        <div class="col-sm-6 col-md-3 panel ">
            <div class="thumbnail">
                <img src='<%=rs.getString("producturl")%>' alt='<%=rs.getString("productname")%>' style="height: 150px;">
            </div>
            <div class="caption">
                <h3><%=rs.getString("productname")%></h3>
                <span>Rs <%=rs.getString("productcost")%>/-</span>
                <p><%=rs.getString("productdescription")%></p>
                <p>
                    <a href='index.jsp?addid=<%=rs.getString("productid")%>' class="btn btn-primary" role="button">
                        Add to Cart
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