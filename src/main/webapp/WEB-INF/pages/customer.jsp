<%-- 
    Document   : customer
    Created on : Feb 4, 2016, 12:50:22 PM
    Author : Ankith Jain
    Reference    : sas691
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/header.jspf" %>



<c:if test="${not empty requestScope[customer]}">
    <h2>${requestScope.customer.firstName} ${requestScope.customer.lastName}</h2>
</c:if>

<c:if test = "${not empty requestScope.violations}">

    <ul>
        <c:forEach items="${requestScope.violations}" var= "violation">
            <li>
                <c:out value = "${violation.propertyPath}"/>: ${violation.message}
            </li>
        </c:forEach>
    </ul>

</c:if>

<form method="POST" action="<c:url value="/customer"/>">

    <fieldset class="form-group">

        <div id = "container"> 
            <h1>Insert Customer Page</h1>
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
                <label for="storeId">Store ID</label>
                <input type="number" name="storeId" id="storeId" value="${customer.storeId}"/>
            </div>
            <div>
                <label for="addressId">Address ID</label>
                <input type="number" name="addressId" id="addressId" value="${customer.addressId}"/>
            </div>
            <br/>
            <div>
                <input class="btn btn-primary" type="submit" name="submitCustomer" id="submitCustomer"/> 

                <a class="btn btn-primary" href="/ajain62-MP2">  Back to Home</a>
            </div>
    </fieldset>

</form>
<div> 
    <%@include file="/WEB-INF/jspf/footer.jspf" %>