<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.rays.project4.controller.LoginCtl"%>
<%@page import="in.co.rays.project4.util.DataUtility"%>
<%@page import="in.co.rays.project4.util.ServletUtility"%>

<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Login Page</title>
</head>
<body>
	<jsp:useBean id="bean" class="in.co.rays.project4.bean.UserBean" scope="request"></jsp:useBean>
	<form action="<%=ORSView.LOGIN_CTL%>" method="post">
	<%@ include file="Header.jsp"%>
<center>

              <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDateTime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDateTime())%>">
              <input type="hidden" name="URI" value= "<%=session.getAttribute("uri") %>">
              
<div align="center" >
            <h1>Login</h1>
            <H2><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></H2>
            <H2><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%></font></H2>
            
			<%
			String msg =(String) request.getAttribute("message");
            if(msg!= null){ 
            %>
            <h1 align="center"><font style="color: red"><%=msg %></font>
            <%} %></h1>
</div>

            <table>
                <tr>
                    <th align="left">LoginId <span style="color: red">*</span></th>
                    <td ><input type="text" name="login" placeholder="Enter Vallid Login-ID"   size=25 value="<%=DataUtility.getStringData(bean.getLogin())%>">
                   <%System.out.println("========>.."+bean.getLogin());
                    %>
                   </td>
                    <td style="position: fixed;"><font color="red" ><%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
               	
               	<tr><th style="padding: 3px"></th></tr>
              	<tr><th></th></tr>
                
                <tr>
                    <th align="left">Password <span style="color: red">*</span></th>
                    <td><input type="password" name="password" placeholder="Enter Vallid Password" size=25 value="<%=DataUtility.getStringData(bean.getPassword())%>">
                    </td><td style="position:fixed; "><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
	            
	            <tr><th style="padding: 3px"></th></tr>
              	<tr><th></th></tr>            	    
                
                <tr>
                    <th></th>
                    <td colspan="4" >
                    
                    <input type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_IN %>">   
                    <input type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP %>" >
                    </td>
                </tr>
                <tr><th style="padding: 3px"></th>
                </tr>
                <tr><th></th>
                <td><font size="4px" ; color="red"><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget my password</b></a></font></td>
            </tr>
            </table>    
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>