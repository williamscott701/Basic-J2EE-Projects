<script>
    $(document).ready(function () {
        $("#signupform").hide();

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
<style>
    body{
        background-image: url('img/login-background.jpg');
        overflow-x: hidden;
    }

    #body  form, #body{
        width: 450px;
        margin: 0px auto;
        padding: 0px;
    }
    #body  form{
        background: rgba(0,0,0,0.4);
        padding: 7px;
        margin-top: 40px;
        border-radius: 2px;
        color: white;
        border:1px white solid;
        box-shadow: 0 0 5px black;
        transition: box-shadow 1s ease;
    }

    #body  form:hover{
        box-shadow: 0 0 15px black;    
    }

    #body{
        margin-top: 50px;
    }

    #body .heading{
        font-size: 2em;
        margin:0 auto;
    }

    #body ul{
        margin-left: 70px
    }

    #body ul li{
        display: inline;
        padding: 10px 15px;
        cursor: pointer;
        background: rgba(0,0,0,0.3);
        transition: background 0.3s ease;
        margin-left: 20px;
        border-radius: 2px;
        color: white;
    }

    #body ul li:hover{
        background: rgba(0,0,255,0.2);
        color: black;
    }
</style>


<%
    if (session.getAttribute("name") != null) {
        if (session.getAttribute("name") == "failed") {
%>
<div class='container alert alert-danger'>Error, Try Again!....</div>
<%
        }
    }
%>


<div id="body">
    <ul id="tabs">
        <li id="signinclick">SignIn</li>
        <li id="signupclick">SignUp</li>
    </ul>
    <form id="signinform" class="form-horizontal" action="LoginSignup" method="post">
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
                <input type="submit"  name="submit_button" class="btn btn-default" value="SignIn" />
            </div>
        </div>
    </form>

    <form id="signupform" class="form-horizontal" action="LoginSignup" method="post">
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
                <input type="submit"  name="submit_button" class="btn btn-default" value="SignUp" />
            </div>
        </div>
    </form>
</div>