<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/editHopDong.css"%></style>
</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>

<div class="main_content">
	 <form:form action="../${hopDong.getMaHD()}/update" class="form">
        <h2>Sửa Thông Tin Hợp Đồng ${hopDong.getMaHD()}</h2>
        <div class="form-group full">
            <label for="maHD">Mã hợp đồng</label>
            <input class="edit_input" id="maHD" type="text" name="maHD" placeholder="Mã sinh viên" value="${hopDong.getMaHD()}" disabled="disabled"/>
        </div>
        <div class="form-group">
            <div class="half">
                <label for="ngayTao">Ngày tạo</label>
                <input class="edit_input" id="ngayTao" type="date" name="ngayTao" placeholder="Ngày tạo" value="${hopDong.getNgayTao()}"/>
            </div>
           <div class="half">
                <label for="ngayHetHan">Ngày hết hạn</label>
                <input class="edit_input" id="ngayHetHan" type="date" name="ngayHetHan" placeholder="Ngày hết hạn" value="${hopDong.getNgayHetHan()}"/>
            </div>
        </div>
        <div class="form-group">
         	<div class="half">
                <label for="soTien">Số tiền</label>
                <input class="edit_input" id="soTien" type="text" name="soTien" placeholder="Số tiền" value="${hopDong.getSoTien()}"/>
            </div>
            <div class="half">
                <label for="namHoc">Năm học</label>
                <%-- <input class="edit_input" id="namHoc" type="text" name="namHoc" placeholder="Năm học" value="${hopDong.getNamHoc()}"/> --%>
                 <select class="edit_input" name="namHoc" id="namHoc">
				    <option value="2023-2024" ${hopDong.getNamHoc() == '2023-2024' ? 'selected' : ''}>2023-2024</option>
				    <option value="2024-2025" ${hopDong.getNamHoc() == '2024-2025' ? 'selected' : ''}>2024-2025</option>
				</select>
            </div>
            
        </div>
        <div class="form-group">
           <div class="half">
                <label for="hocKy">Học kỳ</label>
                <select class="edit_input"  id="hocKy" name="hocKy">
                	 <option value="1" ${hopDong.getHocKy() == '1' ? 'selected' : ''}>1</option>
                	 <option value="2" ${hopDong.getHocKy() == '2' ? 'selected' : ''}>2</option>
                	 <option value="3" ${hopDong.getHocKy() == '3' ? 'selected' : ''}>3</option>	
                </select>
            </div>
            <div class="half">
                <label for="maPhong">Mã phòng</label>
                <input class="edit_input" id="maPhong" type="text" name="maPhong" placeholder="Mã phòng" value="${hopDong.getMaPhong()}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="half">
                <label for="maSV">Mã sinh viên</label>
                <input class="edit_input" id="maSV" type="text" name="maSV" disabled="disabled" placeholder="Mã sinh viên" value="${hopDong.getMaSV()}"/>
            </div>
            <div class="half">
                <label for="maQL">Mã quản lý</label>
                <input class="edit_input" id="maQL" type="text" name="maQL" disabled="disabled" placeholder="Mã quản lý" value="${hopDong.getMaQL()}"/>
            </div>
        </div>
        <button type="submit">Cập nhật</button>
    </form:form>

</div>

<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
    const currentYear = new Date().getFullYear();
    const currentDate = new Date().getDate();
    const currentMonth = new Date().getMonth() + 1;
    console.log("currentMonth:" + currentMonth);
    const select = document.getElementById('namHoc');
    const options = select.options;

    // Xác định năm bắt đầu của năm học dựa vào ngày và tháng hiện tại
    let startYear = currentYear;
    if (currentDate > 15 && currentMonth == 8) { // Từ 16 tháng 6 trở đi
        startYear += 1; // Bắt đầu từ năm sau
        // Cập nhật các lựa chọn hiện có
        for (let i = 0; i < options.length; i++) {
            const year = startYear + i;
            const optionValue = year + "-" + (year + 1);
            options[i].value = optionValue;
            options[i].text = optionValue;
        }
    }

   
    
});


</script>
</body>
</html>