<nav class="navbar navbar-default ">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">WS | Placement Training Portal</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Extras <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" ng-model="query" class="form-control" placeholder="WS Dynamic Search...">
                </div>
                <button type="button" class="btn btn-default">Don't Click</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <%
                    if (session.getAttribute("name") == null) {
                %>
                <li><a href="#">Options</a></li>
                <li><a href="login.jsp">Login</a></li>
                        <%
                        } else {
                        %>
                <li><a href="#">Points: {{score}}</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <%=session.getAttribute("name")%> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Account Settings</a></li>
                        <li><a href="#">Privacy Settings</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">New</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="logout.jsp">Logout</a></li>
                    </ul>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>
