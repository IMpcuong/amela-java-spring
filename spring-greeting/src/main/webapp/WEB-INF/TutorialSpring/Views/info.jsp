<%@ page import="TutorialSpringMVC.Service.ICustomerService" %>
<%@ page import="TutorialSpringMVC.Service.CustomerServiceFactory" %>
<%@ page import="TutorialSpringMVC.Models.Customer" %>
<%@ page import="java.util.List" %>

<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
%>
<%
    Long id = Long.valueOf(request.getParameter("id"));
    Customer customer = ICustomerService.findOne(id);
%>
<form action="/customers" method="post">
    <fieldset>
        <legend>Customer Information</legend>
        <input type="hidden" name="id" value="<%= customer.getId() %>">
        <table>
            <tr>
                <td>Id</td>
                <td>
                    <%= customer.getId() %>
                </td>
            </tr>
            <tr>
                <td>Name</td>
                <td>
                    <input type="text" name="name" value="<%= customer.getName() %>">
                </td>
            </tr>
            <tr>
                <td>Email</td>
                <td>
                    <input type="text" name="email" value="<%= customer.getEmail() %>">
                </td>
            </tr>
            <tr>
                <td>Address</td>
                <td>
                    <input type="text" name="address" value="<%= customer.getAddress() %>">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Update">
                </td>
            </tr>
        </table>
    </fieldset>
</form>
<a href="tongquanSpringMVC/views/customers/list.jsp">Back to list</a>.