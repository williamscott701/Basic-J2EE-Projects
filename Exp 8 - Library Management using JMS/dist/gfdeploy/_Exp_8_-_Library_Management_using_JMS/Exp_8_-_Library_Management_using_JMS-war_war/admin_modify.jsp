
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WS | Admin Modify</title>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/styles.css" />
    </head>
    <body ng-app="myApp" ng-controller="booksCtrl">
        <jsp:include flush="true" page="header.jsp"></jsp:include>
        <jsp:include flush="true" page="admin_modify_body.jsp"></jsp:include>
            <div style="margin-bottom: 30px;"></div>
        <jsp:include flush="true" page="footer.jsp"></jsp:include>
    </body>
</html>
