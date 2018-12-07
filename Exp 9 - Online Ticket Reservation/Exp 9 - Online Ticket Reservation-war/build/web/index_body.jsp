<%
    if (session.getAttribute("name") != null) {
        if (session.getAttribute("name").toString().equals("William Scott")) {
%>
<a href="admin_modify.jsp" class="btn btn-success col-sm-offset-10"> Modify Database </a>
<%
        }
    }
%>

<div class='container alert alert-info col-sm-6 col-sm-offset-3 ws-hide' id="processingmessage">Processing!....</div>
<div class='container alert alert-danger col-sm-6 col-sm-offset-3 ws-hide' id="errormessage">Error, Please Contact William!....</div>
<div class='container alert alert-danger col-sm-6 col-sm-offset-3 ws-hide' id="errormailmessage">Error Sending Mail, Please Don't Use KU-WL ;)!....</div>
<div class='container alert alert-success col-sm-6 col-sm-offset-3 ws-hide' id="successmessage">Operation Successfully!....</div>

<div class="panel col-sm-6 col-sm-offset-3 panel-default">
    <div class="panel-heading panel-success">Search Results</div>
    <div class="panel-body"> 
        <form class="form-horizontal" role="form" style="overflow: hidden;" ng-submit="reserve()"> 
            <%
                if (session.getAttribute("name") != null) {
            %>
            <div class="form-group">
                <label class="control-label col-sm-3" for="name">Passenger Name</label>
                <div class="col-sm-7">
                    <input type="text" ng-model="reservedata.passengername" class="form-control" id="name" name="name" placeholder="Enter Passeger Name" required="">
                </div>
            </div> 
            <div class="form-group">
                <label class="control-label col-sm-3" for="email">Passenger Email</label>
                <div class="col-sm-7">
                    <input type="text" ng-model="reservedata.passengeremail" class="form-control" id="email" name="email" placeholder="Enter Passengers Email Address" required="">
                </div>
            </div> 
            <div class="form-group">
                <label class="control-label col-sm-3" for="age">Passenger Age</label>
                <div class="col-sm-7">
                    <input type="text"  ng-model="reservedata.passengerage" class="form-control" id="age" name="age" placeholder="Enter Passengers Age" required="">
                </div>
            </div> 
            <div class="form-group">
                <label class="control-label col-sm-3" for="dt">Date of Journey</label> 
                <div class="col-sm-7">
                    <input type="date"  ng-model="reservedata.dateofjourney" class="form-control" id="dt" name="dt" required="">
                </div>
            </div>  
            <%
                }
            %>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Available</th>
                        <th>Cost</th>
                        <th>Reserve</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="x in trains| filter:query | orderBy:orderByField:reverseSort">
                        <td>{{ x.id}}</td>
                        <td>{{ x.name}}</td>
                        <td>{{ x.available}}</td>
                        <td>{{ x.cost}}</td>
                        <td><input type="radio" name="select" ng-model="$parent.reservedata.trainid" value="{{x.id}}" required=""/></td> 
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <%
                            if (session.getAttribute("name") != null) {
                        %>
                        <td><input type="submit" class="btn btn-success" value="Reserve"/></td>  
                            <%
                            } else {
                            %>
                        <td><a href="login.jsp" class="btn btn-primary">Signin</a></td>
                        <%
                            }
                        %>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
<%    if (session.getAttribute("name") != null) {
%>

<div class="panel col-sm-10 col-sm-offset-1 panel-default"> 
    <div class="panel-heading panel-success">Book History</div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" style="overflow: hidden;" ng-submit="cancel()">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Date & Time of Booking</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Age</th>
                        <th>Train ID</th>
                        <th>Date of Journey</th>
                        <th>Amount Paid</th>
                        <th>Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="x in history| filter:query | orderBy:orderByField:reverseSort">
                        <td>{{ x.dateofbooking}}</td>
                        <td>{{ x.passengername}}</td>
                        <td>{{ x.passengeremail}}</td>
                        <td>{{ x.passengerage}}</td>
                        <td>{{ x.trainid}}</td>
                        <td>{{ x.dateofjourney}}</td>
                        <td>{{ x.paid}}</td> 
                        <td><input type="radio" name="select" ng-model="$parent.canceldata.dateofbooking" value="{{x.dateofbooking}}" required=""/></td> 
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td> 
                        <td></td>
                        <td><input type="submit" class="btn btn-success" value="Cancel"/></td>  
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
<script>

    var app = angular.module('myApp', []);
    app.controller('trainsCtrl', function ($scope, $http) {

        $scope.cancel = function () {
            $("#processingmessage").slideDown();
            $("#errormessage").slideUp();
            $("#errormailmessage").slideUp();
            $("#successmessage").slideUp();
            $http({
                method: 'POST',
                url: 'JCAction?cancelTrain=true',
                headers: {'Content-Type': 'application/json'},
                data: $scope.canceldata
            }).success(function (data)
            {
                $("#processingmessage").slideUp();
                if (data == "true") {
                    $("#successmessage").slideDown();
                } else if (data == "falsemail") {
                    $("#errormailmessage").slideDown();
                } else {
                    $("#errormessage").slideDown();
                }
                $scope.reset(); 
            });
        };
        $scope.reset = function () {
            $http.get("JCAction?GetAll=true")
                    .success(function (response) {
                        $scope.trains = response;
                    });
            $http.get("JCAction?GetHistory=true")
                    .success(function (response) {
                        $scope.history = response;
                    });
            checkBalance();
        };
        $scope.reserve = function () {
            $("#processingmessage").slideDown();
            $("#errormessage").slideUp();
            $("#errormailmessage").slideUp();
            $("#successmessage").slideUp();
            $http({
                method: 'POST',
                url: 'JCAction?reserveTrain=true',
                headers: {'Content-Type': 'application/json'},
                data: $scope.reservedata
            }).success(function (data)
            {
                $("#processingmessage").slideUp();
                if (data == "true") {
                    $("#successmessage").slideDown();
                } else if (data == "falsemail") {
                    $("#errormailmessage").slideDown();
                } else {
                    $("#errormessage").slideDown();
                }
                $scope.reset();
            });
        };
        $scope.reset();
    });</script>

<%
} else {
%>

<script>

    var app = angular.module('myApp', []);
    app.controller('trainsCtrl', function ($scope, $http) {
        $http.get("JCAction?GetAll=true")
                .success(function (response) {
                    $scope.trains = response;
                });
    });
</script>

<%
    }
%>