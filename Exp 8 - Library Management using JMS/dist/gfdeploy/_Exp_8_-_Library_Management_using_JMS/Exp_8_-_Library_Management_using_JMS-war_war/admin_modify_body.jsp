
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

<div class='container alert alert-success col-sm-6 col-sm-offset-3' id="success">Successfully Updated / Inserted</div>
<div class='container alert alert-danger col-sm-6 col-sm-offset-3' id="failed">Error, Try Again!....</div>

<div class="panel col-sm-6 col-sm-offset-3 panel-default">
    <div class="panel-heading panel-success">Add / Update Books</div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" style="overflow: hidden;" action="Action" method="post" id="actionForm">
            <div class="form-group">
                <label class="control-label col-sm-3" for="id">Book ID</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="id" name="id" placeholder="New Record Will be Created for New Books">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="name">Book Name</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Old Books Will be Recreated With This Name">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="count">Book Count</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="count" name="count" placeholder="This Will be Added to previous Count">
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-8 col-sm-10">
                    <a onclick="action()" class="btn btn-primary">Add / Update Details</a>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    $(document).ready(function () {
        $("#success").hide();
        $("#failed").hide();
    });

    function action() {
        $("#success").slideUp();
        $("#failed").slideUp();
        $.ajax({
            type: "post",
            url: "JMSAction",
            data: $("#actionForm").serialize(),
            success: function (data) {
                if (data == "success")
                    $("#success").slideDown();
                else
                    $("#failed").slideDown();
            }
        });
    }
</script>