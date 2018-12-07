<%
    if (session.getAttribute("name") != null) {
        if (session.getAttribute("name").toString().equals("William Scott")) {
%>
<a href="admin_modify.jsp" class="btn btn-success col-sm-offset-10"> Modify Database </a>
<%
        }
    }
%>
<div class='container alert alert-danger col-sm-6 col-sm-offset-3' id="errormessage">Error, Try After Login!....</div>
<div class='container alert alert-success col-sm-6 col-sm-offset-3' id="successmessage">Book Rented Successfully!....</div>

<div class="panel col-sm-6 col-sm-offset-3 panel-default">
    <div class="panel-heading panel-success">Search Results</div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Book Name</th>
                    <th>Book ID</th>
                    <th>Count of Books</th>
                    <th>Rent</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="x in books| filter:query | orderBy:orderByField:reverseSort">
                    <td>{{ x.name}}</td>
                    <td>{{ x.id}}</td>
                    <td>{{ x.count}}</td>
                    <td><input type="radio" name="select" ng-model="$parent.rentid" value="{{x.id}}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a ng-click="rent()" class="btn btn-success">Rent</a></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<script>
    $(document).ready(function () {
        $("#errormessage").hide();
        $("#successmessage").hide();
    });

    var app = angular.module('myApp', []);

    app.controller('booksCtrl', function ($scope, $http) {

        $scope.reset = function () {
            $http.get("JCAction?GetAll=true")
                    .success(function (response) {
                        $scope.books = response;
                    });

        };

        $scope.rent = function () {
            $("#errormessage").slideUp();
            $("#successmessage").slideUp();
            $http.get("JCAction?Rent=" + $scope.rentid)
                    .success(function (response) {
                        if (response == "success") {
                            $scope.reset();
                            $("#successmessage").slideDown();
                        } else
                            $("#errormessage").slideDown();
                    });

        };

        $scope.reset();
    });
</script>