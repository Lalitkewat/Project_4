<%@page import="in.co.rays.project4.util.HTMLUtility"%>
<%@page import="in.co.rays.project4.controller.RoleListCtl"%>
<%@page import="in.co.rays.project4.controller.BaseCtl"%>
<%@page import="in.co.rays.project4.bean.RoleBean"%>
<%@page import="in.co.rays.project4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<body>
<jsp:useBean id="bean" class="in.co.rays.project4.bean.RoleBean" scope="request"></jsp:useBean>
    <%@include file="Header.jsp"%>
<%List rlist =(List) request.getAttribute("name"); %>
    <center>
        <h1>Role List</h1>

        <form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
            <table width="100%">
                <tr align="center">
                    <td ><label>Name :</label>
                    <%=HTMLUtility.getList("fname",String.valueOf(bean.getId()), rlist) %>
                        &nbsp; <input type="submit" name="operation" value="<%=RoleListCtl.OP_SEARCH %>">
                        <input type="submit" name="operation" value="<%=RoleListCtl.OP_RESET %>">
                    </td>
                </tr>
            </table>
            <table border="1" width="100%" >
                <tr>
                    <th>S.No.</th>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Edit</th>
                </tr>
                <tr>
                  <%--   <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td> --%>
                </tr>

                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<RoleBean> it = list.iterator();
                    
                   while (it.hasNext()) {
                        bean = it.next();
                %>
                <tr align="center">
                    <td><%=index++%></td>
                    <td><%=bean.getId()%></td>
                    <td><%=bean.getName()%></td>
                    <td><%=bean.getDescription()%></td>
                    
                    
                    <% if(bean.getId()==1){ %>
					<td><a href="RoleCtl?id=<%=bean.getId()%>" onclick="return false;">Edit</a></td>
					<%} else{ %><td><a href="RoleCtl?id=<%=bean.getId()%>">Edit</a></td><%} %>
					
                    
                </tr>
                <%
                    }
                %>
            </table>
           <%--  <table width="100%">
                <tr>
                    <td><input type="submit" name="operation"
                        value="<%=RoleListCtl.OP_PREVIOUS%>"></td>
                    <td></td>
                    <td align="right"><input type="submit" name="operation"
                        value="<%=RoleListCtl.OP_NEXT%>"></td>
                </tr>
            </table> --%>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
    <%@include file="Footer.jsp"%>
</body>
</html>