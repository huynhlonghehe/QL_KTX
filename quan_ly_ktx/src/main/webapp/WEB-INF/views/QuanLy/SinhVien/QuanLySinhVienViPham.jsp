<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/QuanLySinhVienViPham.css"%></style>
<script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@latest"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>

<div class="main_content">
	<h1 class="title"><strong>Quản Lý Sinh Viên Vi Phạm Nội Quy</strong></h1>
	
	<div class="createReport">
		<button type="button" class="btn btn-primary" onclick="toggleCreateReportForm()">Lập biên bản</button>
		<div id="createReportForm" style="display: none">
			<div><i id="closeFormReport" class="fa-solid fa-xmark close" style="color: white;"></i></div>
		     <form:form action="/quan_ly_ktx/quanly/QLSinhVien/createReport" class="form" modelAttribute="newReport">
		        <h2>Lập biên bản</h2>
		        <div class="form-group full">
		            <label for="maSV">Mã sinh viên</label>
		            <input class="edit_input" id="maSV" type="text" name="maSV" placeholder="Mã sinh viên" required="required"/>
		        </div>
		        <div class="form-group">
		            <div class="half">
		                <label for="maQuyTac">Lỗi vi phạm</label>
		                <select class="edit_input" id="maQuyTac" name="maQuyTac" required="required" onchange="updateMucDoViPham()">
		                	<option>--Lỗi vi phạm--</option>
                            <c:forEach var="quyTac" items="${List_QuyTac}">
                                <option value="${quyTac.getMaQuyTac() }" data-mucdo="${quyTac.getMucDoViPham()}">${quyTac.getTenQuyTac() }</option>
                            </c:forEach>
                        </select>
		            </div>
		             <div class="half">
		                <label for="ten">Mức độ vi phạm</label>
		                <input class="edit_input" id="mucDoViPham" type="text" name="mucDoViPham" placeholder="Mức độ vi phạm" disabled="disabled" />
		            </div>
		        </div>
		        <div class="form-group">
		         	<div class="half">
		                <label for="ten">Thời gian vi phạm</label>
		                <input class="edit_input" id="thoiGianViPham" type="datetime-local" name="thoiGianViPham" placeholder="Thời gian vi phạm" required="required"/>
		            </div>
		            <div class="half">
		                <label for="ten">Người lập biên bản</label>
		                <select class="edit_input" id="nguoiLapBienBan" name="nguoiLapBienBan">
						    <c:forEach var="quanLySinhVien" items="${ListQuanLySinhVien}">
						        <option value="${quanLySinhVien}" 
						                ${quanLySinhVien ==  sessionScope.USERNAME ? 'selected' : ''}>
						            ${quanLySinhVien}
						        </option>
						    </c:forEach>
						</select>
		            </div>
		        </div>
		        <button type="submit">Thêm</button>
		    </form:form>
	    </div>
	</div>
	
	
	<div class="createRule">
		<button type="button" class="btn btn-success" onclick="toggleCreateRuleForm()">Thêm quy tắc mới</button>
		<div id="createRuleForm" style="display: none">
			<div><i id="closeFormRule" class="fa-solid fa-xmark close" style="color: white;"></i></div>
		     <form:form action="/quan_ly_ktx/quanly/QLSinhVien/SinhVienViPham/createRule" class="form" modelAttribute="newRule">
		        <h2>Quy tắc mới</h2>
		        <div class="form-group full">
		            <label for="maQuyTac">Mã quy tắc</label>
		            <input class="edit_input" id="maQuyTac" type="text" name="maQuyTac" placeholder="Mã quy tắc" required="required" value="${nextMaQuyTac }"/>
		        </div>
		        <div class="form-group">
		        	<div class="half">
			            <label for="tenQuyTac">Tên quy tắc</label>
			            <input class="edit_input" id="tenQuyTac" type="text" name=tenQuyTac placeholder="Tên quy tắc" required="required"/>
			        </div>
			        <div class="half">
			            <label for="mucDoViPham">Mức độ vi phạm</label>
			            <select class="edit_input" id="mucDoViPham" name="mucDoViPham" required="required">
			            	<option>---Mức độ vi phạm---</option>
			            	<option value="1">1</option>
			            	<option value="2">2</option>
			            	<option value="3">3</option>
			            </select>
			        </div>
		        </div>
		        <button type="submit">Thêm</button>
		    </form:form>
	    </div>
	</div>
	
	<div class="searchContainer" >
	    <form method="GET" action="/quan_ly_ktx/quanly/QLSinhVien/searchSinhVienViPham" class="searchSinhVien" id="searchForm">
	        <input type="text" name="maSV" placeholder="Mã sinh viên" class="inputSearch" value="${maSV}" id="maSV"/>
	        <input type="text" name="ho" placeholder="Họ" class="inputSearch" value="${ho}" id="ho"/>
	        <input type="text" name="ten" placeholder="Tên" class="inputSearch" value="${ten}" id="ten"/>
	        <select class="inputSearch" id="maPhong" name="maPhong" style="width: 100px">
	            <option value="">Phòng</option>
	            <c:forEach var="maPhong" items="${List_Phong}">
	            	<option value="${maPhong }" ${maPhong == maPhong_backEnd ? 'selected' : ''}>${maPhong }</option>
	            </c:forEach>
	        </select>
	        <select class="inputSearch" id="noiDungViPham" name="noiDungViPham" style="width: 155px">
	            <option value="">Nội dung vi phạm</option>
	            <c:forEach var="quyTac" items="${List_QuyTac}">
	                <option value="${quyTac.getTenQuyTac() }" ${quyTac.getTenQuyTac().equals(noiDungViPham) ? 'selected' : ''}>${quyTac.getTenQuyTac() }</option>
	            </c:forEach>
	        </select>
	        <select class="inputSearch" id="mucDoViPham" name="mucDoViPham" style="width: 150px">
	            <option value="">Mức độ vi phạm</option>
	            <option value="1" ${'1'.equals(mucDoViPham) ? 'selected' : ''}>1</option>
	            <option value="2" ${'2'.equals(mucDoViPham) ? 'selected' : ''}>2</option>
	            <option value="3" ${'3'.equals(mucDoViPham) ? 'selected' : ''}>3</option>
	        </select>
	        <select class="inputSearch" id="nguoiLapBienBan" name="nguoiLapBienBan" style="width: 170px">
			    <option value="">Người lập biên bản</option>
			    <c:forEach var="quanLySinhVien" items="${ListQuanLySinhVien}">
			        <option value="${quanLySinhVien}" ${quanLySinhVien.equals(nguoiLapBienBan) ? 'selected' : ''}>${quanLySinhVien}</option>
			    </c:forEach>
			</select>
	        <button type="submit" value="Search" class="buttonSearch" title="Tìm kiếm"><i class="fa-solid fa-magnifying-glass" style="color: #ffffff;"></i></button>
	        <button type="button" value="Refresh" class="buttonRefresh" onclick="refreshPage()" title="Làm mới tìm kiếm"><i class="fa-solid fa-arrows-rotate" ></i></button>
	    </form>
	</div>
	
	<div id="ListSinhVienViPhamNoiQuy" class="container mt-4">
        <table class="table table-hover table-striped table-bordered">
            <thead class="table-info">
                <tr>
                	<th scope="col" style="width: 50px">STT</th>
	                <th scope="col">Mã sinh viên <a href="#" data-column="MASV" data-mode="desc"><i class="fa-solid fa-sort"></i></a></th>
					<th scope="col">Họ </th>
					<th scope="col" style="width: 80px">Tên <a href="#" data-column="TEN" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
					<th scope="col">Mã phòng <a href="#" data-column="MAPHONG" data-mode="desc"><i class="fa-solid fa-sort"></i></a></th>
					<th scope="col">Ngày sinh <a href="#" data-column="NGAYSINH" data-mode="desc"><i class="fa-solid fa-sort"></i></a></th>
	                <th scope="col">Nội dung vi phạm</th>
	                <th scope="col">Mức độ vi phạm</th>
	                <th scope="col">Thời gian vi phạm</th>
	                <th scope="col">Người lập biên bản</th>
	                <th scope="col" style="width: 60px"></th>
                </tr>
            </thead>
            <tbody>
            <c:choose>
	        	<c:when test="${not empty ListSV_ViPhamNoiQuy}">
	                <c:forEach var="sinhVienVP" items="${ListSV_ViPhamNoiQuy}" varStatus = "i">
			        	<tr>
			        		<td style="width: 50px">${i.index + 1}</td>
			        		<td>${sinhVienVP.getMaSV()}</td>
			        		<td>${sinhVienVP.getHo() }</td>
			        		<td>${sinhVienVP.getTen() }</td>
			        		<td>${sinhVienVP.getMaPhong() }</td>
			        		<td>${sinhVienVP.getNgaySinh() }</td>
		        			<td>${sinhVienVP.getTenQuyTac() }</td>
		        			<td style="text-align: center">${sinhVienVP.getMucDoViPham() }</td>
		        			<td>${sinhVienVP.getThoiGianViPham() }</td>
		        			<td>${sinhVienVP.getNguoiLapBienBan() }</td>
		        			<td>
	        				 	<a class="btn-link" href="#" onclick="toggleEditDetailReport('${sinhVienVP.getMaSVVP()}', '${sinhVienVP.getMaSV()}', '${sinhVienVP.getMaQuyTac() }', '${sinhVienVP.getMucDoViPham() }', '${sinhVienVP.getThoiGianViPham() }')" title="Sửa thông tin vi phạm"><i class="fa-solid fa-pen-to-square" style="color: #63E6BE;"></i></a>
			                    <a class="btn-link delete-link" href="/quan_ly_ktx/quanly/QLSinhVien/${sinhVienVP.getMaSVVP()}/deleteDetailReport" title="Xoá thông tin vi phạm"><i class="fa-solid fa-trash" style="color: #fa0000;"></i></a>
		        			</td>
			        	</tr>
		        	</c:forEach>
	        	</c:when>
	            <c:otherwise>
			        <tr><td colspan="10" style="text-align: center; color: red">Không có dữ liệu sinh viên</td></tr>
			    </c:otherwise>
	    	</c:choose>
	        </tbody>
	    </table> 
	</div>
	
	<div class="editDetailReport">
		<div id="editDetailReportForm" style="display: none">
			<div><i id="closeEditDetailReport" class="fa-solid fa-xmark close" style="color: white;"></i></div>
		     <form:form id="editForm" action="/quan_ly_ktx/quanly/QLSinhVien/SinhVienViPham/editDetailReport" class="form" modelAttribute="editDetailReport">
		        <h2>Sửa thông tin vi phạm của sinh viên</h2>
		        <input class="edit_input" id="editMaSVVP" type="hidden" name="maSVVP"/>
		        <div class="form-group full">
		            <label for="maSinhVien">Mã sinh viên</label>
		            <input class="edit_input" id="editMaSinhVienViPham" type="text" name="maSV" placeholder="Mã sinh viên" required="required" readonly="readonly"/>
		        </div>
		        <div class="form-group">
		        	<div class="half">
			            <label for="tenQuyTac">Nội dung vi phạm</label>
			           	<select class="edit_input" id="editDetailReportNoiDungViPham" name="maQuyTac" required="required" onchange="updateMucDoViPhamEditDetailReport()">
			           		<option value="">Nội dung vi phạm</option>
				            <c:forEach var="quyTac" items="${List_QuyTac}">
				                <option value="${quyTac.getMaQuyTac() }" data-editMucdo = "${quyTac.getMucDoViPham() }">${quyTac.getTenQuyTac() }</option>
				            </c:forEach>
			           	</select>
			        </div>
			        <div class="half">
			            <label for="mucDoViPham">Mức độ vi phạm</label>
		                <input class="edit_input" id="editDetailReportMucDoViPham" type="text" name="mucDoViPham" placeholder="Mức độ vi phạm" readonly="readonly" />
			        </div>
		        </div>
		        <div class="form-group full">
		            <label for="maSinhVien">Thời gian vi phạm</label>
		            <input class="edit_input" id="editDetailReportThoiGianViPham" type="datetime-local" name="thoiGianViPham" placeholder="Thời gian vi phạm" required="required"/>
		        </div>
		        <button type="submit" style="background-color: #0dcaf0">Xác nhận</button>
		    </form:form>
	    </div>
    </div>
	
	
	
	<h1 class="title_QuyTac"><strong>Danh sách các quy tắc</strong></h1>
	<div id="ListQuyTac" class="container mt-4">
        <table class="table table-hover table-striped table-bordered">
            <thead class="table-info">
                <tr>
                	<th scope="col" style="width: 25px">STT</th>
	                <th scope="col" style="width: 55px">Mã quy tắc</th>
					<th scope="col" style="width: 450px">Tên quy tắc</th>
					<th scope="col" style="width: 70px">Mức độ vi phạm</th>
					<th scope="col" style="width: 30px"></th>
                </tr>
            </thead>
            <tbody>
            <c:choose>
	        	<c:when test="${not empty List_QuyTac}">
	                <c:forEach var="quyTac" items="${List_QuyTac}" varStatus = "i">
			        	<tr>
			        		<td style="width: 50px">${i.index + 1}</td>
			        		<td>${quyTac.getMaQuyTac()}</td>
			        		<td>${quyTac.getTenQuyTac()}</td>
			        		<td>${quyTac.getMucDoViPham()}</td>
			        		<td>
			        			<a class="btn-link" href="#ListQuyTac" title="Sửa thông tin quy tắc"><i class="fa-solid fa-pen-to-square" style="color: #74C0FC; font-size: 20px" onclick="toggleEditRuleForm('${quyTac.getMaQuyTac()}', '${quyTac.getTenQuyTac()}', '${quyTac.getMucDoViPham()}')" ></i></a>
			                   	<a class="btn-link delete-link" href="/quan_ly_ktx/quanly/QLSinhVien/SinhVienViPham/${quyTac.getMaQuyTac()}/deleteRule" title="Xoá quy tắc" style="font-size: 20px"><i class="fa-solid fa-trash" style="color: #fa0000;"></i></a>
			        		</td>
			        	</tr>
		        	</c:forEach>
	        	</c:when>
	            <c:otherwise>
			        <tr><td colspan="5" style="text-align: center; color: red">Không có dữ liệu quy tắc</td></tr>
			    </c:otherwise>
	    	</c:choose>
	        </tbody>
	    </table> 
	</div>
	
	<div class="editRule">
		<div id="editRuleForm" style="display: none">
			<div><i id="closeEditRule" class="fa-solid fa-xmark close" style="color: white;"></i></div>
		     <form id="editForm" action="/quan_ly_ktx/quanly/QLSinhVien/SinhVienViPham/editRule" class="form" method="POST">
		        <h2>Sửa quy tắc</h2>
		        <div class="form-group full">
		            <label for="maQuyTac">Mã quy tắc</label>
		            <input class="edit_input" id="editMaQuyTac" type="text" name="maQuyTac" placeholder="Mã quy tắc" required="required" readonly="readonly"/>
		        </div>
		        <div class="form-group">
		        	<div class="half">
			            <label for="tenQuyTac">Tên quy tắc</label>
			            <input class="edit_input" id="editTenQuyTac" type="text" name=tenQuyTac placeholder="Tên quy tắc" required="required"/>
			        </div>
			        <div class="half">
			            <label for="mucDoViPham">Mức độ vi phạm</label>
			            <select class="edit_input" id="editMucDoViPham" name="mucDoViPham" required="required">
			            	<option>---Mức độ vi phạm---</option>
			            	<option value="1">1</option>
			            	<option value="2">2</option>
			            	<option value="3">3</option>
			            </select>
			        </div>
		        </div>
		        <button type="submit"  style="background-color: #0dcaf0">Xác nhận</button>
		        <!-- onclick="changeActionAndSubmit()" -->
		    </form>
	    </div>
	</div>
	
</div>

<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() {
    var tbody = document.querySelector("table tbody");
    var rows = tbody.querySelectorAll("tr").length;
    var maxRows = 12;

    for (var i = rows; i < maxRows; i++) {
        var tr = document.createElement("tr");
        tr.innerHTML = "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
        tbody.appendChild(tr);
    }
});

document.addEventListener("DOMContentLoaded", function() {
    var rows = document.querySelectorAll("tbody tr"); // Lấy tất cả các dòng trong tbody

    rows.forEach(function(row) {
        if (row.cells[7].textContent == 3) {
            row.classList.add("table-danger"); // Thêm class 'serious' nếu vi phạm mức 3
        }
    });
});


document.addEventListener("DOMContentLoaded", function() {
    var sortIcons = document.querySelectorAll(".fa-sort");
    sortIcons.forEach(function(icon) {
        var link = icon.closest('a');
        var column = link.getAttribute('data-column');

        // Kiểm tra và cập nhật trạng thái ban đầu dựa vào localStorage
        var storedMode = localStorage.getItem('sortMode-' + column);
        if (storedMode) {
            link.setAttribute('data-mode', storedMode);
        }

        icon.addEventListener("click", function(event) {
            event.preventDefault(); // Ngăn chặn hành vi mặc định

            // Lấy URL hiện tại và các tham số tìm kiếm
            var currentUrl = new URL(window.location);
            var searchParams = new URLSearchParams(currentUrl.search);

            // Lấy trạng thái hiện tại và cập nhật
            var currentMode = link.getAttribute('data-mode');
            var newMode = currentMode === 'asc' ? 'desc' : 'asc';

            // Cập nhật localStorage
            localStorage.setItem('sortMode-' + column, newMode);

            // Thiết lập hoặc cập nhật tham số 'sort' và 'direction'
            searchParams.set('sort', column);
            searchParams.set('direction', newMode);

            // Cập nhật URL với tham số mới và chuyển hướng
            currentUrl.search = searchParams.toString();
            window.location.href = currentUrl.toString();
        });
    });
});


/* Ẩn hiện form lập biên bản */
function toggleCreateReportForm(event) {
	    var form_Report = document.getElementById('createReportForm');
	    var form_Rule = document.getElementById('createRuleForm');
	    var form_editRule = document.getElementById('editRuleForm');
	    var form_EditDetailReport = document.getElementById('editDetailReportForm');
	    if (form_Report.style.display === 'none' || form_Report.style.display === '') {
	    	form_Report.style.display = 'block';
	    	form_Rule.style.display = 'none';
	    	form_editRule.style.display = 'none';
	    	form_EditDetailReport.style.display = 'none';
	    } else {
	    	form_Report.style.display = 'none';
	    }
	}
/* Kết thúc */

/* Ẩn hiện form thêm lỗi mới */
function toggleCreateRuleForm(event) {
	   var form_Rule = document.getElementById('createRuleForm');
	   var form_Report = document.getElementById('createReportForm');
	   var form_editRule = document.getElementById('editRuleForm');
	   var form_EditDetailReport = document.getElementById('editDetailReportForm');
	    if (form_Rule.style.display === 'none' || form_Rule.style.display === '') {
	    	form_Rule.style.display = 'block';
	    	form_Report.style.display = 'none';
	    	form_editRule.style.display = 'none';
	    	form_EditDetailReport.style.display = 'none';
	    } else {
	    	form_Rule.style.display = 'none';
	    }
	}
/* Kết thúc */


/* Ẩn hiện form edit lỗi */
function toggleEditRuleForm(maQuyTac, tenQuyTac, mucDoViPham) {
	   	var form_editRule = document.getElementById('editRuleForm');
	   	var form_Rule = document.getElementById('createRuleForm');
	   	var form_Report = document.getElementById('createReportForm');
	   	var form_EditDetailReport = document.getElementById('editDetailReportForm');
	   	document.getElementById('editMaQuyTac').value = maQuyTac;
	   	document.getElementById('editTenQuyTac').value = tenQuyTac;
	   	document.getElementById('editMucDoViPham').value = mucDoViPham;
	    if (form_editRule.style.display === 'none' || form_editRule.style.display === '') {
	    	form_editRule.style.display = 'block';
	    	form_Report.style.display = 'none';
	    	form_Rule.style.display = 'none';
	    	form_EditDetailReport.style.display = 'none';
	    }else{
	    	form_editRule.style.display = 'none';
	    }
	}
/* Kết thúc */

/* Ẩn hiện form editDetailReport*/
function toggleEditDetailReport(maSVVP, maSV,noiDungViPham, mucDoViPham, thoiGianViPham) {
	    var form_EditDetailReport = document.getElementById('editDetailReportForm');
	    var form_Report = document.getElementById('createReportForm');
	    var form_Rule = document.getElementById('createRuleForm');
	    var form_editRule = document.getElementById('editRuleForm');
	    document.getElementById('editMaSVVP').value = maSVVP;
	    document.getElementById('editMaSinhVienViPham').value = maSV;
	    document.getElementById('editDetailReportNoiDungViPham').value = noiDungViPham;
	    document.getElementById('editDetailReportMucDoViPham').value = mucDoViPham;
	    document.getElementById('editDetailReportThoiGianViPham').value = thoiGianViPham;
	    if (form_EditDetailReport.style.display === 'none' || form_EditDetailReport.style.display === '') {
	    	form_EditDetailReport.style.display = 'block';
	    	form_Report.style.display = 'none';
	    	form_Rule.style.display = 'none';
	    	form_editRule.style.display = 'none';
	    }else{
	    	form_EditDetailReport.style.display = 'none';
	    }
	}
/* Kết thúc */

function changeActionAndSubmit() {
    // Lấy giá trị của mã quy tắc
    var maQuyTac = document.getElementById('editMaQuyTac').value;
    // Lấy form
    var form = document.getElementById('editForm');
    // Thiết lập thuộc tính action mới
    form.action = '/quan_ly_ktx/quanly/QLSinhVien/SinhVienViPham/' + maQuyTac + '/editRule';
    // Submit form
    form.submit();
}

function updateMucDoViPham() {
    var selectElement = document.getElementById('maQuyTac');
    var selectedOption = selectElement.options[selectElement.selectedIndex];
    var mucDoViPham = selectedOption.getAttribute('data-mucdo');
    document.getElementById('mucDoViPham').value = mucDoViPham;
}

function updateMucDoViPhamEditDetailReport() {
    var selectElement = document.getElementById('editDetailReportNoiDungViPham');
    var selectedOption = selectElement.options[selectElement.selectedIndex];
    var mucDoViPham = selectedOption.getAttribute('data-editMucdo');
    document.getElementById('editDetailReportMucDoViPham').value = mucDoViPham;
}

/* Thông báo lỗi và kết quả*/
document.addEventListener('DOMContentLoaded', function() {
    var errorMessage = "${errorMessage}";
    var successMessage = "${successMessage}";

    if (errorMessage) {
        Swal.fire({
            title: 'Error!',
            text: errorMessage,
            icon: 'error',
            confirmButtonText: 'OK'
        });
    } else if (successMessage) {
        Swal.fire({
            title: 'Success!',
            text: successMessage,
            icon: 'success',
            confirmButtonText: 'OK'
        });
    }
});
/* Kết thúc */

/* Thông báo xác nhận muốn xoá quy tắc không */
document.addEventListener('DOMContentLoaded', function() {
	    // Tìm tất cả các thẻ a có class là 'delete-link'
	    var deleteLinks = document.querySelectorAll('.delete-link');

	    deleteLinks.forEach(function(link) {
	        link.addEventListener('click', function(event) {
	            event.preventDefault(); // Ngăn thẻ a chuyển hướng trực tiếp
	            var href = this.href; // Lưu lại đường dẫn href của thẻ a

	            Swal.fire({
	                title: 'Bạn có chắc chắn muốn xoá?',
	                text: "Bạn sẽ không thể hoàn tác điều này!",
	                icon: 'warning',
	                showCancelButton: true,
	                confirmButtonColor: '#3085d6',
	                cancelButtonColor: '#d33',
	                confirmButtonText: 'Xoá',
	                cancelButtonText: 'Huỷ'
	            }).then((result) => {
	                if (result.isConfirmed) {
	                    // Chuyển hướng đến href nếu người dùng xác nhận
	                    window.location.href = href;
	                }
	            });
	        });
	    });
	});
/* Kết thúc */
 

  /* Đóng form lập biên bản mới khi nhấn vào biểu tượng close */
document.getElementById('closeFormReport').addEventListener('click', function() {
    var form = document.getElementById('createReportForm');
    form.style.display = 'none';
});
/* Kết thúc */

  /* Đóng form tạo quy tắc mới khi nhấn vào biểu tượng close */
document.getElementById('closeFormRule').addEventListener('click', function() {
    var form = document.getElementById('createRuleForm');
    form.style.display = 'none';
});
/* Kết thúc */

  /* Đóng form edit quy tắc khi nhấn vào biểu tượng close */
document.getElementById('closeEditRule').addEventListener('click', function() {
    var form = document.getElementById('editRuleForm');
    form.style.display = 'none';
});

/* Đóng form editDetailReport khi nhấn vào biểu tượng close */
document.getElementById('closeEditDetailReport').addEventListener('click', function() {
    var form = document.getElementById('editDetailReportForm');
    form.style.display = 'none';
});

/* Làm mới trang khi ấn vào nút refresh */
function refreshPage() {
	 window.location.href = '/quan_ly_ktx/quanly/QLSinhVien/SinhVienViPham';
}
/* Kết thúc */

</script>
</body>
</html>