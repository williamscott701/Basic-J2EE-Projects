<script>
    $(document).ready(function () {
        $("#signupform").hide();

        $("#errormessage").hide();
        $("#signinclick").click(function () {
            $("#signupform").slideUp();
            $("#signinform").slideDown();
        });

        $("#signupclick").click(function () {
            $("#signinform").slideUp();
            $("#signupform").slideDown();
        });
    });
</script>

<div class='container alert alert-danger col-sm-12 ws-hide' id="errormessage">Error, Try Again!....</div>

<div id="loginbody">
    <ul id="tabs">
        <li id="signinclick">SignIn</li>
        <li id="signupclick">SignUp</li>
    </ul>
    <form id="signinform" class="form-horizontal" ng-submit="signin()">
        <p class="heading">Login</p>
        <div class="form-group">
            <label for="siuname" class="control-label col-sm-3">Username</label>
            <div class="col-sm-8">
                <input type="text" id="siuname" ng-model="signinData.username" placeholder="Enter Username" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="sipassword" class="control-label col-sm-3">Password</label>
            <div class="col-sm-8">
                <input type="password" id="sipassword" ng-model="signinData.password" placeholder="Enter Password" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-8 col-xs-offset-1">
                <input type="submit" value="SignIn" class="btn btn-default" />
            </div>
        </div>
    </form>

    <form id="signupform" class="form-horizontal ws-hide" ng-submit="signup()">
        <p class="heading">Sign Up</p>
        <div class="form-group">
            <label for="fullname" class="control-label col-sm-3">Full Name</label>
            <div class="col-sm-8">
                <input type="text" id="fullname" ng-model="signupData.fullname" placeholder="Enter Full Name" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="username" class="control-label col-sm-3">Username</label>
            <div class="col-sm-8">
                <input type="text" id="username" ng-model="signupData.username" placeholder="Enter Username" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pemail" class="control-label col-sm-3">Email</label>
            <div class="col-sm-8">
                <input type="email" id="pemail" ng-model="signupData.email" placeholder="Enter Email" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="dob" class="control-label col-sm-3">Date of Birth</label>
            <div class="col-sm-8">
                <input type="date" id="dob" ng-model="signupData.dob" id="signup_dob" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="control-label col-sm-3">Password</label>
            <div class="col-sm-8">
                <input type="password" id="password" ng-model="signupData.password" placeholder="Enter Password" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-8">
                <input type="submit" value="SignUp" class="btn btn-default" />
            </div>
        </div>
    </form>
</div>


<script>
    var app = angular.module('myApp', []);
    app.controller('loginControl', function ($scope, $http) {
        $scope.signin = function () {
            $("#errormessage").slideUp();
            $http({
                method: 'POST',
                url: 'JCAction?Signin=true',
                headers: {'Content-Type': 'application/json'},
                data: $scope.signinData
            }).success(function (data) {
                if (data == "false")
                    $("#errormessage").slideDown();
                else
                    window.location.href = 'index.jsp';
            });
        };
        $scope.signup = function () {
            $("#errormessage").slideUp();
            $http({
                method: 'POST',
                url: 'JCAction?Signup=true',
                headers: {'Content-Type': 'application/json'},
                data: $scope.signupData
            }).success(function (data) {
                if (data == "false")
                    $("#errormessage").slideDown();
                else
                    window.location.href = 'index.jsp';
            }); 
        };
    });
</script>
