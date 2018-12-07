
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
    String query = "select * from ws.tablets";
    Statement st = con.createStatement();
    Set<String> set;
    if (request.getParameter("name") != null && request.getParameter("sym") != null) {
        String name = request.getParameter("name");
        String sym = request.getParameter("sym");
        Statement st2 = con.createStatement();
        st2.executeUpdate("DELETE FROM tablets WHERE tabletname = '" + name + "' and tabletuse='" + sym + "'");

//        out.println("<script>alert('Sucessfully Added to Cart');</script>");
        out.println("<div class='alert alert-danger col-sm-8 col-sm-offset-2'>Sucessfully Removed</div>");
    }
    ResultSet rs = st.executeQuery(query);
%>


<a href="add_list.jsp" class="btn btn-success col-sm-offset-10">Add List to Database</a>

<div class="container">
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Tablet Name</th>
                <th>Tablet Can Cure</th>
                <th>Remove</th>
            </tr>
        </thead>
        <tbody>   
            <%while (rs.next()) {
            %>
            <tr>
                <td><%=rs.getString(1)%></td>
                <td><%=rs.getString(2)%></td>
                <td><a href="admin_modify.jsp?name=<%=rs.getString(1)%>&sym=<%=rs.getString(2)%>" class="btn btn-danger">Remove</a></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>


</div>

<div style="margin-bottom: 30px;"></div>