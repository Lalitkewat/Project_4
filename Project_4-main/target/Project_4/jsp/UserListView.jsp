<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.co.rays.project4.model.RoleModel"%>
<%@page import="in.co.rays.project4.model.UserModel"%>
<%@page import="in.co.rays.project4.util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project4.util.ServletUtility"%>
<%@page import="in.co.rays.project4.controller.UserListCtl"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> User List</title>

<script src="<%=ORSView.APP_CONTEXT %>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT %>/js/Checkbox11.js"></script>

</head>
<body>
<%List rlist =(List) request.getAttribute("name"); %>
<jsp:useBean id="bean" class="in.co.rays.project4.bean.UserBean" scope="request" ></jsp:useBean>
    <form action="<%=ORSView.USER_LIST_CTL%>" method="post">
<%@include file="Header.jsp"%>

    <center>
    
	<div align="center">
    			<h1>User List</h1>
                <h2><font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
                <font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h2>
                    
	</div>

	<%
		List rolelist = (List)request.getAttribute("RoleList"); 
	%>
	    <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<UserBean> it = list.iterator();
                    
                    if(list.size() !=0){
                    	
        %>

            
                <tr><th></th>
                    <td align="center"><label>FirstName :</label> 
                     <%=HTMLUtility.getList("fname",String.valueOf(bean.getId()), rolelist) %>
                     
                   
                    <label>LoginId:</label> 
                    <input type="text" name="login" placeholder="Enter Login-Id" value="<%=ServletUtility.getParameter("login", request)%>">
                    &nbsp; 
                    <input type="submit" name="operation" value="<%=UserListCtl.OP_SEARCH %>">
                    &nbsp; 
                    <input type="submit" name="operation" value="<%=UserListCtl.OP_RESET %>">
         	
                    </td>
                </tr>
            </table>
            <br>
            <br>
            <br>

            <table border="1" width="100%" align="center" cellpadding=6px cellspacing=".2">
                <tr>
                	<th> <input type="checkbox" id="select_all" name="select">Select All </th>
                    
                    <th>S.No.</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Role</th>
                    <th>LoginId</th>
                    <th>Gender</th>
                    <th>Date Of Birth</th>
                    <th>Mobile No </th>
                    <th>Edit</th>
                </tr>
				
				<%
					while (it.hasNext())
					{
					UserBean	bean1 = it.next();
					//	RoleModel model = new RoleModel();
					//	RoleBean rolebean = new RoleBean();
						//		rolebean = model.findByPK(bean.getRoleId());
				
				%>


                <tr align="center">
                    <td><input type="checkbox" class="checkbox" name="ids" value="<%=bean1.getId()%>"
                    <%if( bean1.getRoleId() == 1) {                    
                    %>
                    <%="disabled" %><% } %>
 
                    ></td>
                    <td><%=index++%></td>
                    <td><%=bean1.getFirstName()%></td>
                    <td><%=bean1.getLastName()%></td>
                    <%
						if (bean1.getRoleId() == 1) {
					%><td>Admin</td>
					<%
						} else if (bean1.getRoleId() == 2) {
					%><td>Student</td>
					<%
						} else if (bean1.getRoleId() == 3) {
					%><td>Faculty</td>
					<%
						} else if (bean1.getRoleId() == 4) {
					%><td>College_School</td>
					<%
						} else {
					%><td>Kiosk</td>
					<%
						}
					%>
					
                    <td><%=bean1.getLogin()%></td>
                    <td><%=bean1.getGender()%></td>
                    <td><%=bean1.getDob()%></td>
                    <td><%=bean1.getMobileNo()%></td>
                   
                    
                   <% if(bean1.getRoleId()==1){ %>
					<td><a href="UserCtl?id=<%=bean1.getId()%>" onclick="return false;">Edit</a></td>
					<%} else{ %><td><a href="UserCtl?id=<%=bean1.getId()%>">Edit</a></td><%} %>
					
					
					
                     <%-- <%if(userBean.getId() == bean.getId() || bean.getRoleId() == RoleBean.ADMIN) {%>
 						onclick = "return false;"                   
                    <% } %> --%> 
                </tr>
                <%
                    }
                %>
            </table>
				
            <table width="100%">
                <tr><th></th>
					<%if(pageNo == 1){ %> 
                   <td ><input type="submit" name="operation" disabled="disabled" value="<%=UserListCtl.OP_PREVIOUS%>"></td>
                    <%}else{ %>
                    <td ><input type="submit" name="operation"  value="<%=UserListCtl.OP_PREVIOUS%>"></td>
                    <%} %>
                     
                     <td ><input type="submit" name="operation" value="<%=UserListCtl.OP_DELETE%>"></td>
                     <td ><input type="submit" name="operation" value="<%=UserListCtl.OP_NEW%>"></td>
                     
                     <%	UserModel model = new UserModel();
                     %>
                     
                     <% if(list.size() < pageSize || model.nextPK()-1 == bean.getId() ){%>

                     		<td align="right"><input type="submit" name="operation" disabled="disabled" value="<%=UserListCtl.OP_NEXT%>"></td>
                     <% }else{%>
                     		<td align="right"><input type="submit" name="operation" value="<%=UserListCtl.OP_NEXT%>"></td>
                     <%} %>
       
                </tr>          
            </table>
            		<%}if(list.size() == 0){ %>
            		<td align="center"><input type="submit" name="operation" value="<%=UserListCtl.OP_BACK%>"></td>	
            		<% } %>
            		
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
<br><br><br><br><br>
    <%@include file="Footer.jsp"%>
</body>
</html>	