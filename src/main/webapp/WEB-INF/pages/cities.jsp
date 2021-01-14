<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<c:forEach var="city" items="${cityList}">
    <tbody>
    <tr>
        <th scope="row" class="text-center text-info">${city.city}</th>
        <th scope="row" class="text-center text-info">${city.cityInfo}</th>
    </tr>
    </tbody>
</c:forEach>