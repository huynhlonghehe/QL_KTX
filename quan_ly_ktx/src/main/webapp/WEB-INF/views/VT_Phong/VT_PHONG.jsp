<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Quản lý vật tư</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        <%@include file="/WEB-INF/resources/css/VatTu_CSS/VatTu.css" %>
    </style>
    <script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
</head>

<body>
    <%@include file="/WEB-INF/views/includes/header.jsp"%>
    <%@include file="/WEB-INF/views/includes/menu.jsp"%>
    <div class="main_content">
        <h1 class="title">Quản Lý vật tư</h1>
        <table>
            <thead>
                <tr>
                    <th scope="col">Mã vật tư <a href="#" data-column="maVT" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                    <th scope="col">Mã phòng <a href="#" data-column="maPhong" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                    <th scope="col">Số tiền <a href="#" data-column="soTien" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                    <th scope="col">Ngày cấp <a href="#" data-column="ngayCap" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                    <th scope="col">Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vtphong" items="${listVT_Phong}">
                    <tr>
                        <td>${vtphong.maVT}</td>
                        <td>${vtphong.maPhong}</td>
                        <td>${vtphong.soTien}</td>
                        <td>${vtphong.ngayCap != null ? vtphong.ngayCap : 'N/A'}</td>
                        <td>
                            <a href="javascript:void(0);" class="button_action button_edit" onclick="toggleEditVatTuPhongForm('${vtphong.maVT}', '${vtphong.maPhong}', '${vtphong.soTien}', '${vtphong.ngayCap}')">Sửa</a>
                            <a href="#" class="button_action button_delete" onclick="deleteVatTuPhong('${vtphong.maVT}')">Xoá</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="search-and-buttons">
            <button type="button" class="btn btn-primary button_createTK" onclick="toggleCreateVatTuPhongForm()">Thêm Vật Tư_Phòng</button>
            <button type="button" class="btn btn-primary button_createTK" onclick="window.location.href='http://localhost:8080/quan_ly_ktx/vattu_phong/list'">Reload</button>
        </div>

        <div class="search">
            <!-- Form tìm kiếm -->
            <form action="${pageContext.request.contextPath}/vattu_phong/find" method="GET" id="searchForm">
                <label for="maVT">Mã vật tư:</label>
                <input type="text" id="maVT" name="maVT" placeholder="Nhập mã vật tư">

                <label for="maPhong">Mã phòng:</label>
                <input type="text" id="maPhong" name="maPhong" placeholder="Nhập mã phòng">

                <label for="soTien">Số tiền:</label>
                <input type="text" id="soTien" name="soTien" placeholder="Nhập số tiền">

                <label for="ngayCap">Ngày cấp:</label>
                <input type="text" id="ngayCap" name="ngayCap" placeholder="Nhập ngày cấp">

                <!-- Thêm nút tìm kiếm -->
                <button type="submit" class="btn btn-find">Tìm kiếm</button>
            </form>
        </div>

        <!-- Form thêm Vật Tư_Phòng -->
        <div id="createVatTuPhongForm" style="display: none">
            <span class="close" onclick="closeForm('createVatTuPhongForm')">&times;</span>
            <h2>Thêm Vật Tư_Phòng</h2>
            <form action="${pageContext.request.contextPath}/vattu_phong/add-vat-tu-phong" method="POST">
                <div class="form-group">
                    <label for="maVT" class="form-label">Mã vật tư:</label>
                    <input type="text" class="form-control" id="maVT" name="maVT" placeholder="Nhập mã vật tư">
                </div>
                <div class="form-group">
                    <label for="maPhong" class="form-label">Mã phòng:</label>
                    <input type="text" class="form-control" id="maPhong" name="maPhong" placeholder="Nhập mã phòng">
                </div>
                <div class="form-group">
                    <label for="soTien" class="form-label">Số tiền:</label>
                    <input type="number" class="form-control" id="soTien" name="soTien" placeholder="Nhập số tiền">
                </div>
                <div class="form-group">
                    <label for="ngayCap" class="form-label">Ngày cấp:</label>
                    <input type="text" class="form-control" id="ngayCap" name="ngayCap" placeholder="Nhập ngày cấp">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-Them" onclick="submitForm('create')">Thêm</button>
                </div>
            </form>
        </div>
       
       </div>
        
</body>
</html>