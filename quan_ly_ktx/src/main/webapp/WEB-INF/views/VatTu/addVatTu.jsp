<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Vật Tư</title>
    <!-- Import Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Import CSS for addVatTu -->
    <style>
        <%@include file="/WEB-INF/resources/css/VatTu_CSS/addVatTu.css"%>
    </style>
</head>
<body>
    <%@include file="/WEB-INF/views/includes/header.jsp"%>
    <%@include file="/WEB-INF/views/includes/menu.jsp"%>

    <div class="container">
        <h2>Thêm Vật Tư Mới</h2>
        <!-- Form để nhập thông tin vật tư mới -->
		<form action="${pageContext.request.contextPath}/vattu/add-vat-tu" method="POST">

            <!-- Dropdown để chọn khu -->
            <div class="mb-3">
                <label for="maKhu" class="form-label">Khu:</label>
                <select name="maKhu" class="form-select">
                    <!-- Sử dụng vòng lặp để tạo các tùy chọn cho các khu -->
                    <c:forEach var="khu" items="${listKhu}">
                        <option value="${khu}">${khu}</option>
                    </c:forEach>
                </select>
            </div>
            <!-- Ô nhập để nhập mã vật tư -->
            <div class="mb-3">
                <label for="maSo" class="form-label">Mã Vật Tư:</label>
                <input type="text" class="form-control" id="maSo" name="maSo" placeholder="Nhập mã vật tư">
            </div>
            <!-- Ô nhập để nhập tên vật tư -->
            <div class="mb-3">
                <label for="tenVT" class="form-label">Tên Vật Tư:</label>
                <input type="text" class="form-control" id="tenVT" name="tenVT" placeholder="Nhập tên vật tư">
            </div>
            <!-- Ô nhập để nhập giá tiền -->
            <div class="mb-3">
                <label for="giaTien" class="form-label">Giá Tiền:</label>
                <input type="number" class="form-control" id="giaTien" name="giaTien" placeholder="Nhập giá tiền">
            </div>
            <!-- Nút "Thêm" để gửi biểu mẫu -->
            <button type="submit" class="btn btn-primary">Thêm</button>
        </form>
    </div>
</body>
</html>
