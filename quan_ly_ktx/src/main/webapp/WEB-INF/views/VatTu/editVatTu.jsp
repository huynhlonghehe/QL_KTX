<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<style>
    <%@include file="/WEB-INF/resources/css/VatTu_CSS/editVatTu.css"%>
</style>
<head>
    <meta charset="UTF-8">
    <title>Sửa Thông Tin Vật Tư</title>
    <!-- Import Bootstrap CSS -->
</head>
<body>
    <!-- Include Header and Menu -->
    <%@include file="/WEB-INF/views/includes/header.jsp"%>
    <%@include file="/WEB-INF/views/includes/menu.jsp"%>

    <div class="main_content">
    	<table>
    		<!-- Form to edit VatTu -->
        	<form:form class="edit_form" modelAttribute="vatTu" action="../update-vat-tu" method="POST">
            <h2>Sửa Thông Tin Vật Tư</h2>
            
			<label for="maVT">Mã vật tư: &nbsp;</label>
			<span id="maVT">${vatTu.maVT}</span>
			<input class="edit_input" type="hidden" name="maVT" value="${vatTu.maVT}"/>
			
			<label for="tenVT"style="display: block;">Tên vật tư: </label>
			<input class="edit_input" type="text" name="tenVT" placeholder="Tên vật tư" value="${vatTu.tenVT}"/>
			
			<label for="giaTien"style="display: block;">Giá tiền:</label>
			<input class="edit_input" type="number" name="giaTien" placeholder="Giá tiền" value="${vatTu.giaTien}"/>

            <!-- Submit button -->
            <button class="edit_button" type="submit">Lưu</button>
        </form:form>
    	</table>
        
    </div>
</body>
</html>
