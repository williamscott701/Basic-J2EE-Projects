
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
<div class='container alert alert-success col-sm-6 col-sm-offset-3 ws-hide' id="successmessage">Ticket Added Successfully!....</div>


<div class="panel col-sm-6 col-sm-offset-3 panel-default">
    <div class="panel-heading panel-success">Add / Update Trains</div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" style="overflow: hidden;" action="Action" method="post" id="actionForm">
            <div class="form-group">
                <label class="control-label col-sm-3" for="id">Train ID</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="id" name="id" placeholder="New Record Will be Created for New Trains">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="name">Train Name</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Existing Trains Will be Recreated With This Name">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="available">Available</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="available" name="available" placeholder="No of Available Seats">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="cost">Cost</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="cost" name="cost" placeholder="Cost of Ticket">
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
            url: "JCAction?addTrain=true",
            data: $("#actionForm").serialize(),
            success: function (data) {
                if (data == "success")
                    $("#successmessage").slideDown();
                else
                    $("#successmessage").slideDown();
            }
        });
    }
</script>