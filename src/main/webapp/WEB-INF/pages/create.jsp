<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>CreateCity</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
            integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
            crossorigin="anonymous"></script>
    <style>
        body {
            background: url("<c:url value="/resources/image/picture.jpg" />") no-repeat center center fixed;
            background-size: cover;
        }
    </style>
</head>
<body>

<div class="container">
    <div id="title-row" class="row justify-content-center align-items-center">
        <div id="title-column" class="col-md-6">
            <div id="title-box" class="col-md-12">

                <form:form method="POST" modelAttribute="createCity">
                    <h3 class="text-center">Создать</h3>
                    <div class="form-group">
                        <label for="city">Название город:</label><br>
                        <input type="text" name="cityName" id="city" class="form-control"
                               value="${createCity.cityName}">
                        <c:if test="${createCity.cityName != null}"><b class="text-danger">${error}</b></c:if>
                    </div>

                    <div class="form-group">
                        <label for="cityInfo">Информация:</label><br>
                        <input type="text" name="cityInfo" id="cityInfo" class="form-control"
                               value="${createCity.cityInfo}">
                    </div>

                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Создать">
                        <div id="register-link" class="text-right">
                            <a href="/jsp/cities/" class="text-info">&#8592; Вернуться к списку</a>
                        </div>
                    </div>
                </form:form>

            </div>
        </div>
    </div>
</div>

</body>
</html>
