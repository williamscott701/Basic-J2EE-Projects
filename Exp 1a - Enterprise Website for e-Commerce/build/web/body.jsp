<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    Class.forName("org.apache.derby.jdbc.ClientDriver");
    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
    String query = "select category_name from ws.Exp1_categories_list";;
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(query);
%> 
<div class="tab-content">

    <div id="Home" class="tab-pane active fade in">
        <div style="background: rgba(0,0,0,0.3); margin: 30px; padding: 30px; border-radius: 5px;">

            <ul id="menu">
                <%while (rs.next()) {
                        out.write("<li><a>" + rs.getString(1) + "</a>"
                                + "<ul><span><li>Item</li></span><span><li>Item</li></span></ul>"
                                + "</li>");
                    }
                %>
            </ul>

            <div class="test1" style=" width: 600px; background: blue; height: 300px; margin: 30px auto;">
                <img src="images/1.jpg" id='slider' style="margin: 0 auto; width: 600px; background: blue; height: 300px;">
                <p style=" margin: 0 auto; width: 100px;margin-top: -30px; color: white;">This is Text</p>
            </div>

        </div>
        <div id="deals">
            <ul class="nav nav-pills" style="margin: 0 auto; width: 500px;">
                <li class="active"><a data-toggle="pill" href="#Electronics">Electronics</a></li>
                <li><a data-toggle="pill" href="#Clothes">Clothes</a></li>
                <li><a data-toggle="pill" href="#Shoes">Shoes</a></li>
                <li><a data-toggle="pill" href="#More">More</a></li>
            </ul>

            <div class="tab-content" style="height: 250px;">
                <div id="Electronics" class="tab-pane fade in active">
                    <%
                        for (int i = 1; i < 10; i++) {
                    %>       
                    <div class="col-md-3 col-sm-4">
                        <a href="aa" class="thumbnail">
                            <p>Electronics</p>
                            <img alt="alt" src="images/home.jpg" width="50%">
                        </a>
                    </div>
                    <%
                        }
                    %>
                </div>
                <div id="Clothes" class="tab-pane fade">
                    <%
                        for (int i = 1; i < 10; i++) {
                    %>       
                    <div class="col-md-3 col-sm-4">
                        <a href="aa" class="thumbnail">
                            <p>Clothes</p>
                            <img alt="alt" src="images/deals.jpg" width="50%">
                        </a>
                    </div>
                    <%
                        }
                    %>
                </div>
                <div id="Shoes" class="tab-pane fade">
                    <%
                        for (int i = 1; i < 10; i++) {
                    %>       
                    <div class="col-md-3 col-sm-4">
                        <a href="aa" class="thumbnail">
                            <p>Shoes</p>
                            <img alt="alt" src="images/goods.png" width="50%">
                        </a>
                    </div>
                    <%
                        }
                    %>
                </div>
                <div id="More" class="tab-pane fade">
                    <%
                        for (int i = 1; i < 10; i++) {
                    %>       
                    <div class="col-md-3 col-sm-4">
                        <a href="aa" class="thumbnail">
                            <p>More</p>
                            <img alt="alt" src="images/travel.jpg" width="50%">
                        </a>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
    <div id="SignIn" class="tab-pane fade main_block">

        <form class="form-horizontal" style="margin-left: 160px;">
            <p class="heading">Login</p>
            <div class="form-group">
                <label for="signin_username" class="control-label col-sm-3">Username</label>
                <div class="col-sm-5">
                    <input type="text" id="signin_username" placeholder="Enter Username" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label for="signin_password" class="control-label col-sm-3">Password</label>
                <div class="col-sm-5">
                    <input type="password" id="signin_password" placeholder="Enter Password" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-default">Login</button>
                </div>
            </div>
        </form>
    </div>

    <div id="SignUp" class="tab-pane fade main_block">
        <form class="form-horizontal" style="margin-left: 160px;" action="register.jsp" method="post">
            <p class="heading">Sign Up</p>
            <div class="form-group">
                <label for="signup_fullname" class="control-label col-sm-3">Full Name</label>
                <div class="col-sm-5">
                    <input type="text" name="signup_fullname" placeholder="Enter Full Name" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label for="signup_username" class="control-label col-sm-3">Full Username</label>
                <div class="col-sm-5">
                    <input type="text" name="signup_username" placeholder="Enter Username" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label for="signup_email" class="control-label col-sm-3">Email</label>
                <div class="col-sm-5">
                    <input type="email" name="signup_email" placeholder="Enter Email" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label for="signup_dob" class="control-label col-sm-3">Date of Birth</label>
                <div class="col-sm-5">
                    <input type="date" name="signup_dob" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="signup_Password" class="control-label col-sm-3">Password</label>
                <div class="col-sm-5">
                    <input type="password" name="signup_Password" placeholder="Enter Password" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <input type="submit" class="btn btn-default" value="SignUp" />
                </div>
            </div>
        </form>

    </div>


    <div id="AboutUs" class="tab-pane fade main_block" >
        <p class="heading">About Us</p>
        Essays are generally scholarly pieces of writing giving the author's own argument, but the definition is vague, overlapping with those of an article, a pamphlet and a short story.

        Essays can consist of a number of elements, including: literary criticism, political manifestos, learned arguments, observations of daily life, recollections, and reflections of the author. Almost all modern essays are written in prose, but works in verse have been dubbed essays (e.g. Alexander Pope's An Essay on Criticism and An Essay on Man). While brevity usually defines an essay, voluminous works like John Locke's An Essay Concerning Human Understanding and Thomas Malthus's An Essay on the Principle of Population are counterexamples. In some countries (e.g., the United States and Canada), essays have become a major part of formal education. Secondary students are taught structured essay formats to improve their writing skills; admission essays are often used by universities in selecting applicants, and in the humanities and social sciences essays are often used as a way of assessing the performance of students during final exams.

        The concept of an "essay" has been extended to other mediums beyond writing. A film essay is a movie that often incorporates documentary film making styles and which focuses more on the evolution of a theme or an idea. A photographic essay is an attempt to cover a topic with a linked series of photographs; it may or may not have an accompanying text or captions.
    </div>
</div>
