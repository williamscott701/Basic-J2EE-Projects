
<%
    if (session.getAttribute("name") == null || session.getAttribute("name") == "failed") {
%>
<div class="panel col-sm-4 col-sm-offset-4 panel-default">
    <div class="panel-heading panel-success">Note</div>
    <div class="panel-body">
        To Access your Bank and Reservation Facilities Please <a class="btn btn-success" href="login.jsp">Signin</a>
    </div>
</div>
<%
} else {
%>

<div class='container alert alert-danger col-sm-6 col-sm-offset-3 ws-hide' id="errormessage">Error, Please Contact William!....</div>
<div class='container alert alert-success col-sm-6 col-sm-offset-3 ws-hide' id="successmessage">Ticket Reserved Successfully!....</div>


<div class="panel col-sm-4 col-sm-offset-4 panel-default">
    <div class="panel-heading panel-success">Deposit Money</div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" id="depositForm">
            <div class="form-group">
                <label class="control-label col-sm-3" for="depositamount">Amount</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="depositamount" name="depositamount" placeholder="Enter Amount to Deposit">
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-8 col-sm-10">
                    <button type="button" class="btn btn-default btn-primary" onclick="depositMoney()">Deposit</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="panel col-sm-4 col-sm-offset-4 panel-default">
    <div class="panel-heading panel-success">Withdraw Money</div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" id="withdrawForm">
            <div class="form-group">
                <label class="control-label col-sm-3" for="withdrawamount">Amount</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="withdrawamount" name="withdrawamount" placeholder="Enter Amount to Deposit">
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-8 col-sm-10">
                    <button type="button" class="btn btn-default btn-primary" onclick="withdrawMoney()">Withdraw</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="panel col-sm-4 col-sm-offset-4 panel-default">
    <div class="panel-heading panel-success">Transfer Money</div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" id="transferForm">
            <div class="form-group">
                <label class="control-label col-sm-3" for="transferusername">Username:</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="transferusername" name="transferusername" placeholder="Enter Transfer Username">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="amount">Amount: </label>
                <div class="col-sm-9"> 
                    <input type="text" class="form-control" id="amount" name="amount" placeholder="Enter Transfer Amount">
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-8 col-sm-10">
                    <button type="button" class="btn btn-default btn-primary" onclick="transferMoney()">Transfer</button>
                </div>
            </div>
        </form>
    </div>
</div>
<%
    }
%>

<script>
    function depositMoney() {
        $("#errormessage").slideUp();
        $("#successmessage").slideUp();
        var form1 = $('#depositForm').serialize();
        $.ajax({
            type: "post",
            url: "JCAction?Deposit=true",
            data: form1,
            success: function (data) {
                $("#balance").html(data);
            }
        });
    }
    function transferMoney() {
        $("#errormessage").slideUp();
        $("#successmessage").slideUp();
        var form1 = $('#transferForm').serialize();
        $.ajax({
            type: "post",
            url: "JCAction?Transfer=true",
            data: form1,
            success: function (data) {
                if (data != "false") {
                    $("#balance").html(data);
                }
                else {
                    $("#errormessage").slideDown();
                }
            }
        });
    }
    function withdrawMoney() {
        $("#errormessage").slideUp();
        $("#successmessage").slideUp();
        var form1 = $('#withdrawForm').serialize();
        $.ajax({
            type: "post",
            url: "JCAction?Withdraw=true",
            data: form1,
            success: function (data) {
                if (data != "false") {
                    $("#balance").html(data);
                } else {
                    $("#errormessage").slideDown();
                }
            }
        });
    }
</script>