<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa Tài Khoản Sinh Viên</title>
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/editSinhVien.css"%></style>
</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>
<div class="main_content">
    <form:form action="../${sinhVien.getMaSV()}/update" class="form">
        <h2>Sửa Tài Khoản Sinh Viên ${sinhVien.getMaSV()}</h2>
        <div class="form-group full">
            <label for="maSV">Mã sinh viên</label>
            <input class="edit_input" id="maSV" type="text" name="maSV" placeholder="Mã sinh viên" value="${sinhVien.getMaSV()}"/>
        </div>
        <div class="form-group">
            <div class="half">
                <label for="ho">Họ</label>
                <input class="edit_input" id="ho" type="text" name="ho" placeholder="Họ" value="${sinhVien.getHo()}"/>
            </div>
            <div class="half">
                <label for="ten">Tên</label>
                <input class="edit_input" id="ten" type="text" name="ten" placeholder="Tên" value="${sinhVien.getTen()}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="half">
                <label for="ngaySinh">Ngày sinh</label>
                <input class="edit_input" id="ngaySinh" type="date" name="ngaySinh" placeholder="Ngày sinh" value="${sinhVien.getNgaySinh()}"/>
            </div>
            <div class="half">
                <label for="gioiTinh">Giới tính</label>
                <select class="edit_input"  id="gioiTinh" name="gioiTinh">
                	 <option value="Nam" ${sinhVien.getGioiTinh() == 'Nam' ? 'selected' : ''}>Nam</option>
                	 <option value="Nữ" ${sinhVien.getGioiTinh() == 'Nữ' ? 'selected' : ''}>Nữ</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="half">
                <label for="diaChi">Địa chỉ</label>
                <input class="edit_input" id="diaChi" type="text" name="diaChi" placeholder="Địa chỉ" value="${sinhVien.getDiaChi()}"/>
            </div>
            <div class="half">
                <label for="sdt">Số điện thoại</label>
                <input class="edit_input" id="sdt" type="text" name="sdt" placeholder="Số điện thoại" value="${sinhVien.getSdt()}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="half">
                <label for="maQL">Người quản lý</label>
                <input class="edit_input" id="maQL" type="text" name="maQL" placeholder="Mã quản lý" value="${sinhVien.getMaQL()}"/>
            </div>
            <div class="half">
                <label for="username">Tên tài khoản</label>
                <input class="edit_input" id="username" type="text" name="username" disabled="disabled" placeholder="Tên tài khoản" value="${sinhVien.getUsername()}"/>
            </div>
        </div>
        <button type="submit">Cập nhật</button>
    </form:form>
</div>

</body>
</html>
