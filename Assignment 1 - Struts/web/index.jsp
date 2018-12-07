<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:form action="/loginpath" method="post">

    <table>
        <tr>
            <td>Username </td>
            <td>
                <html:text property="user" />
                <html:errors property="usererror" />
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td>
                <html:text property="pass" />
                <html:errors property="passerror" />
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <html:submit value="submit" />
                <html:reset value="reset" />
            </td>
        </tr>
    </table>


</html:form>