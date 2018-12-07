
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WS | Login</title>
        <script src="js/angularjs.min.js"></script> 
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/styles.css" />
    </head>
    <body style="background-image: url('img/login-background.jpg'); overflow-x: hidden;" ng-app="myApp" ng-controller="loginControl">
        <jsp:include flush="true" page="header.jsp"></jsp:include>
            <div id="body">
            <jsp:include flush="true" page="login_body.jsp"></jsp:include>
            </div>
            <div style="margin-bottom: 30px;"></div>
        <jsp:include flush="true" page="footer.jsp"></jsp:include>
    </body>
</html>
