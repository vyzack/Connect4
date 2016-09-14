<%-- 
    Document   : newjsp
    Created on : 12-Sep-2016, 8:08:47 PM
    Author     : vyzac
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Welcome to Connect 4 game ;)</h1>
        <br/>
        <h2> Enter your email to continue..</h2>
        <form action="indexController" method="post">
            <table>
                <tr>
                    <td>email</td>
                    <td><input type="text" name="email" required pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$"/></td>
                    
                </tr>
                <tr>
                    <td><input type="submit" value="ok"/></td>
                    <td><input type="reset" value="cancel"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
