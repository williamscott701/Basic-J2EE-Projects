function signUpCheck() {
    if ($("#signup_Password").val() == $("#signup_confirm_Password").val() && $("#signup_confirm_Password").val().length > 1)
        alert("Sign Up Validated");
    else
        alert("Please Provide Proper Details");
}

$(document).ready(function () {
    i = 1;
    setInterval(function () {
//                    alert("a");
        j = i;
        i = (1 + i) % 6;
//                    alert(i + "" + j);
        var src = $("#slider").attr("src").replace(j + ".jpg", i + ".jpg");
        $("#slider").attr("src", src);
    }, 3000);
});