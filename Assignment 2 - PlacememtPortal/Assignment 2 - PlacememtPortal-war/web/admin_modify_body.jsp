
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    if (session.getAttribute("name") == null) {
%>
<script>
    window.location.href = "index.jsp";
</script>
<%
} else if (!session.getAttribute("name").toString().equals("William Scott")) {
%>
<script>
    window.location.href = "index.jsp";
</script>
<%
    }
%>

<div class='container alert alert-danger col-sm-6 col-sm-offset-3 ws-hide' id="errormessage">Error, Please Contact William!....</div>
<div class='container alert alert-success col-sm-6 col-sm-offset-3 ws-hide' id="successmessage">Data Added Successfully!....</div>


<div class="panel col-sm-6 col-sm-offset-3 panel-default">
    <div class="panel-heading panel-success">Add / Update Trains</div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" style="overflow: hidden;" ng-submit="add()">
            <div class="form-group">
                <label class="control-label col-sm-3" for="q">Question</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" ng-model="qData.question" id="q" placeholder="Enter Question">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="o1">Option 1</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" ng-model="qData.option1" id="o1" placeholder="Enter Option 1">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="o2">Option 2</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" ng-model="qData.option2" id="o2" placeholder="Enter Option 2">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="o3">Option 3</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" ng-model="qData.option3" id="o3" placeholder="Enter Option 3">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="o4">Option 4</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" ng-model="qData.option4" id="o4" placeholder="Enter Option 4">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="answer">Answer</label>
                <div class="col-sm-7">
                    <select class="form-control" id="answer"  ng-model="qData.answer" >
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="company">Company</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" ng-model="qData.company" id="company" placeholder="Enter Company">
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-8 col-sm-10">
                    <input type="submit" value="Add Question" class="btn btn-default" />
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    var app = angular.module('myApp', []);
    app.controller('adminControl', function ($scope, $http) {
        $scope.add = function () {
            $("#errormessage").slideUp();
            $("#successmessage").slideUp();
            $http({
                method: 'POST',
                url: 'JCAction?Add=true',
                headers: {'Content-Type': 'application/json'},
                data: $scope.qData
            }).success(function (data) {
                if (data == "false")
                    $("#errormessage").slideDown();
                else 
                    $("#successmessage").slideDown();
            });
        };
    });
</script>