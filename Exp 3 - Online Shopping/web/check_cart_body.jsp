<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%
    Class.forName("org.apache.derby.jdbc.ClientDriver");
    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
    Set<String> set;
    int i = 0;

    if (session.getAttribute("list") == null) {
%>
<div class="col-sm-12">
    <div class="thumbnail col-sm-5 col-sm-offset-3" style="text-align: center;">Your Cart is Empty 
        <a href='index.jsp' class="btn btn-primary" role="button">
            Continue Shopping
        </a>
    </div>
</div>
<%    } else {
    set = (HashSet) session.getAttribute("list");
    if (request.getParameter("remove") != null) {
        String a = request.getParameter("remove");
        set.remove(a);
        session.setAttribute("list", set);
    }
%>
<form action="payment.jsp" method="post" name="psd">
    <table class="table table-hover" ng-app="">
        <thead>
            <tr>
                <th>Product</th>
                <th>No. of Items</th>
                <th>Cost per Item</th>
                <th>Over all Cost</th>
                <th>Remove</th>
            </tr>
        </thead>
        <tbody>
            <%    for (String productid : set) {
                    Statement st2 = con.createStatement();
                    ResultSet rs2 = st2.executeQuery("select * from WS.SHOPPINGPRODUCTS WHERE PRODUCTID='" + productid + "'");
                    rs2.next();
//                    out.println("asd");
    //                out.println(rs2.getString("productid"));
                    String productname = rs2.getString("productname");
                    String producturl = rs2.getString("producturl");
                    String productcost = rs2.getString("productcost");
            %>
            <tr>
                <td>
                    <div class="thumbnail" style="width: 200px;">
                        <img src="<%=producturl%>" style="width: 150px;"/>
                        <%=productname%>
                    </div>
                </td>
                <td>
                    <!--<input type="text" value="1"/>-->
                    <select id="<%=(productid + "no")%>">
                        <option>--Select--</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </td>
                <td><%=productcost%></td>
                <td>
                    <input id="<%=(productid + "total")%>" name="add" readonly="" />
                </td>
                <td><a href="check_cart.jsp?remove=<%=productid%>" class="btn btn-danger">Remove</a></td>
            </tr>
        <script>
            $(document).ready(function () {
                $('<%=("#" + productid + "no")%>').on('change', function (e) {
                    $('<%=("#" + productid + "total")%>').val(<%=productcost%> * $('<%=("#" + productid + "no")%>').val());
                });
            });
        </script>
        <%
            }
        %>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td><a class="btn btn-success" onclick="a()">Check Total Cost</a></td>
            <td>
                <input id="final" readonly=""/>
            </td>
        </tr>
        </tbody>
    </table>

        <a class="btn btn-primary pull-right disabled" id="ptp" onclick="document.psd.submit();">Proceed to Payment</a>
</form>
<%
    }
%>
<script>
    $(document).ready(function () {
        $("#final").val(0);
        var getStatus = function () {
            if ($("#final").val() > 0) {
                $("#ptp").removeClass("disabled");
            }
            else
                $("#ptp").addClass("disabled");
            setTimeout(getStatus, 10);
        };
        getStatus();
    });
    function a() {
        var i = 0;
        $("input[name='add']").each(function () {
            i = i + parseInt($(this).val());
//            alert(i);
        });
        $("#final").val(i);
    }

</script>

<div style="margin-bottom: 30px;"></div>