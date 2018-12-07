<%@page import="java.util.HashSet"%>
<%
    HashSet suggested_tablets = (HashSet) request.getAttribute("suggested_tablets");
%>
<div class="panel panel-primary col-sm-4 col-sm-offset-4">
    <div class="panel-heading">Tablet Suggestions</div>
    <div class="panel-body">
        <ul class="list-group">
            <%
                for (Object str : suggested_tablets) {
            %>
            <li class="list-group-item" href="#"><%=str%></li>
                <%
                    }
                %>
        </ul>
    </div>
</div>