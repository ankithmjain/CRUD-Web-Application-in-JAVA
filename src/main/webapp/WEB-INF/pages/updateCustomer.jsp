<%-- 
    Document   : customer
    Created on : Feb 19, 2016, 12:50:22 PM
    Author     : Ankith Jain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/header.jspf" %>



<c:if test="${not empty requestScope[customer]}">
    <h2>${requestScope.customer.firstName} ${requestScope.customer.lastName}</h2>
</c:if>

<c:if test = "${not empty requestScope.violations}">
    <!--  <h2>Violations were found in my controller! (and passed back in the request scope)</h2> -->
    <ul>
        <c:forEach items="${requestScope.violations}" var= "violation">
            <li>
                <c:out value = "${violation.propertyPath}"/>: ${violation.message}
            </li>
        </c:forEach>
    </ul>

</c:if>

<form method="POST" action="<c:url value="/updateCustomer"/>">
    <fieldset class="form-group">
        <div id = "container"> 
            <h1>Update Customer Details </h1>
            <br/>
            <div>
                <label for="customerId">Customer ID</label>
                <input type="number" name="customerId" id="customerId" value="${customer.id}"/>
            </div>
            <div>
                <label for="customerId">Customer First Name</label>
                <input type="text" name="firstName" id="firstName" value="${customer.firstName}"/>
            </div>
            <div>
                <label for="customerId">Customer Last Name</label>
                <input type="text" name="lastName" id="lastName" value="${customer.lastName}"/>
            </div>
            <div>
                <label for="email">Customer Email</label>
                <input type="email" name="email" id="email" value="${customer.email}"/>
            </div>
            <div>
                <input class="btn btn-primary" type="submit" name="submitCustomer" id="submitCustomer"/>
                <a class="btn btn-primary" href="/ajain62-MP2/readCustomer">Back to View</a>
            </div>
    </fieldset>
</form>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>