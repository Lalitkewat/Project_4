<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project4.util.HTMLUtility"%>
<%@page import="in.co.rays.project4.util.DataUtility"%>
<%@page import="in.co.rays.project4.util.ServletUtility"%>
<%@page import="in.co.rays.project4.controller.MyProfileCtl"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> My Profile</title>

</head>
<body>
        <jsp:useBean id="bean" class="in.co.rays.project4.bean.UserBean" scope="request"></jsp:useBean>
<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

	 <%@ include file="Header.jsp"%>
<center>
            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDateTime())%>">
       <div align="center">
                <h1>My Profile</h1>
            <H2><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font> </H2>
            <H2><font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font> </H2>
     </div>
     <table style="font-size:20px">
     <tr>
                    <th align="left">LoginId <span style="color: red">*</span></th>
                    <td><input type="text" name="login" readonly="readonly" size="24.9px" value="<%=DataUtility.getStringData(bean.getLogin())%>"readonly="readonly">
                    <font  color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
                    </td>          
                   </tr>
	
	<tr><th style="padding: 3px"></th></tr>

                <tr>
                    <th align="left">First Name <span style="color: red">*</span></th>
                    <td><input type="text" name="firstName" size="24.9px"  value="<%=DataUtility.getStringData(bean.getFirstName())%>">
                   <font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
                   </td>
                </tr>
    
    <tr><th style="padding: 3px"></th></tr>
                <tr>
                    <th align="left">Last Name <span style="color: red">*</span></th>
                    <td><input type="text" name="lastName" size="24.9px"  value="<%=DataUtility.getStringData(bean.getLastName())%>">
                   <font  color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
                   </td>
                </tr>
   <tr><th style="padding: 3px"></th></tr>             
                <tr>
                    <th align="left">Gender <span style="color: red" size="24.9px">*</span></th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("Male", "Male");
                            map.put("Female", "Female");
                            
                        %> <%=HTMLUtility.getList("gender", bean.getGender(),map)%>
                    <font  color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>
                    </td>
                    
                </tr>
    <tr><th style="padding: 3px"></th></tr>
                <tr>
                    <th align="left">Mobile No <span style="color: red">*</span></th>
                    <td><input type="text" name="mobileNo" size="24.9px" maxlength="10" value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
                    <font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
                    </td>
                 </tr>
<tr><th style="padding: 3px"></th></tr>
                <tr>
                    <th align="left">Date Of Birth <span style="color: red">*</span></th>
                    <td><input type="text" name="dob" readonly="readonly" size="24.9px" value="<%=DataUtility.getDateString(bean.getDob())%>">
                    <font  color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font>
                    </td>
                </tr>
   <tr><th style="padding: 3px"></th></tr>	
                <tr> <th></th><td colspan="2">
                    
                    <input type="submit" name="operation" value="<%=MyProfileCtl.OP_SAVE %>"> 
                   
                    <input type="submit" name="operation" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD %>"> 
                    
                    </td>
                </tr>
            </table>
     
    </form>
    </center>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <%@ include file="Footer.jsp"%>
</body>
</html>