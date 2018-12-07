<%-- 
    Document   : register
    Created on : 17 Jul, 2015, 2:11:41 PM
    Author     : William Scott
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
    String name = request.getParameter("signup_fullname");
    String username = request.getParameter("signup_username");
    String email = request.getParameter("signup_email");
    String dob = request.getParameter("signup_dob");
    String pwd = request.getParameter("signup_Password");

    Class.forName("org.apache.derby.jdbc.ClientDriver");
    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
    String query = "insert into ws.Registered_users values('" + name + "', '" + username + "', '" + email + "', '" + dob + "', '" + pwd.hashCode() + "')";
    Statement st = con.createStatement();
    int a = st.executeUpdate(query);
    if (a == 1) {
        out.print("Regestered Sucessfully");
    } else {
        out.print("Registration Error");
    }
%>