<style>
    form{
        margin-top: 5vh;
        overflow: hidden;
    }
</style>
<div class="col-sm-12">
    <div class="col-sm-8 col-sm-offset-2">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#home">Credit Card</a></li>
            <li><a data-toggle="tab" href="#menu1">Debit Card</a></li>
            <li><a data-toggle="tab" href="#menu2">Online Payment</a></li>
        </ul>

        <div class="tab-content">
            <div id="home" class="tab-pane fade in active">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="a">Card No</label>
                        <div class="col-sm-5">
                            <input type="email" class="form-control" id="a" placeholder="Enter Card No">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="b">Card Holder Name</label>
                        <div class="col-sm-5"> 
                            <input type="password" class="form-control" id="b" placeholder="Enter Card Holder Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="s">CVV Number</label>
                        <div class="col-sm-5"> 
                            <input type="password" class="form-control" id="s" placeholder="Enter CVV Number">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">Expiry Date</label>
                        <div class="col-sm-5"> 
                            <input type="date" class="form-control">
                        </div>
                    </div>
                    <div class="form-group"> 
                        <div class="col-sm-offset-9 col-sm-10">
                            <button type="submit" class="btn btn-default">Confirm Payment</button>
                        </div>
                    </div>
                </form>
            </div>
            <div id="menu1" class="tab-pane fade">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="a">Card No</label>
                        <div class="col-sm-5">
                            <input type="email" class="form-control" id="a" placeholder="Enter Card No">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="b">Card Holder Name</label>
                        <div class="col-sm-5"> 
                            <input type="password" class="form-control" id="b" placeholder="Enter Card Holder Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="s">CVV Number</label>
                        <div class="col-sm-5"> 
                            <input type="password" class="form-control" id="s" placeholder="Enter CVV Number">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">Expiry Date</label>
                        <div class="col-sm-5"> 
                            <input type="date" class="form-control">
                        </div>
                    </div>
                    <div class="form-group"> 
                        <div class="col-sm-offset-9 col-sm-10">
                            <button type="submit" class="btn btn-default">Confirm Payment</button>
                        </div>
                    </div>
                </form>
            </div>
            <div id="menu2" class="tab-pane fade">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="Username">Username:</label>
                        <div class="col-sm-5">
                            <input type="email" class="form-control" id="Username" placeholder="Enter Username">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="pwd">Password:</label>
                        <div class="col-sm-5"> 
                            <input type="password" class="form-control" id="pwd" placeholder="Enter Password">
                        </div>
                    </div>
                    <div class="form-group"> 
                        <div class="col-sm-offset-7 col-sm-10">
                            <div class="checkbox">
                                <label><input type="checkbox"> Remember me</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group"> 
                        <div class="col-sm-offset-8 col-sm-10">
                            <button type="submit" class="btn btn-default">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div style="margin-bottom: 30px;"></div>