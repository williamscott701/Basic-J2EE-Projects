
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WS | E-Commerce</title>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/styles.css" />
    </head>
    <body>
        <%
            if (session.getAttribute("username") == null) {
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        %>
        <jsp:include flush="true" page="header.jsp"></jsp:include>
        <jsp:include flush="true" page="payment_body.jsp"></jsp:include>
        <jsp:include flush="true" page="footer.jsp"></jsp:include>
    </body>
</html>
