<script>
    function signin() {
        $("#errormessage").slideUp();
        $.ajax({
            type: "post",
            url: "JCAction?Signin=true",
            data: $("#signinform").serialize(),
            success: function (data) {
                if (data == "failed")
                    $("#errormessage").slideDown();
                else
                    window.location.href = 'index.jsp';
            }
        });
    }
    function signup() {
        $("#errormessage").slideUp();
        $.ajax({
            type: "post",
            url: "JCAction?Signup=true",
            data: $("#signupform").serialize(),
            success: function (data) {
                if (data == "failed")
                    $("#errormessage").slideDown();
                else
                    window.location.href = 'index.jsp';
            }
        });
    }
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
    <form id="signinform" class="form-horizontal">
        <p class="heading">Login</p>
        <div class="form-group">
            <label for="signin_username" class="control-label col-sm-3">Username</label>
            <div class="col-sm-8">
                <input type="text" name="signin_username" id="signin_username" placeholder="Enter Username" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="signin_password" class="control-label col-sm-3">Password</label>
            <div class="col-sm-8">
                <input type="password" name="signin_password" id="signin_password" placeholder="Enter Password" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-8">
                <input type="button" onclick="signin()" class="btn btn-default" value="SignIn" />
            </div>
        </div>
    </form>

    <form id="signupform" class="form-horizontal ws-hide">
        <p class="heading">Sign Up</p>
        <div class="form-group">
            <label for="signup_fullname" class="control-label col-sm-3">Full Name</label>
            <div class="col-sm-8">
                <input type="text" name="signup_fullname" id="signup_fullname" placeholder="Enter Full Name" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="signup_username" class="control-label col-sm-3">Username</label>
            <div class="col-sm-8">
                <input type="text" name="signup_username" id="signup_username" placeholder="Enter Username" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="signup_email" class="control-label col-sm-3">Email</label>
            <div class="col-sm-8">
                <input type="email" name="signup_email" id="signup_email" placeholder="Enter Email" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="signup_dob" class="control-label col-sm-3">Date of Birth</label>
            <div class="col-sm-8">
                <input type="date" name="signup_dob" id="signup_dob" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="signup_Password" class="control-label col-sm-3">Password</label>
            <div class="col-sm-8">
                <input type="password" name="signup_Password" id="signup_Password" placeholder="Enter Password" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-8">
                <input type="button" onclick="signup()" class="btn btn-default" value="SignUp" />
            </div>
        </div>
    </form>
</div>