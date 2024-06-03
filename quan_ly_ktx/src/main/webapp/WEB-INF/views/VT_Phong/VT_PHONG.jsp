<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Quản lý Vật Tư Phòng</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        <%@include file="/WEB-INF/resources/css/VatTu_CSS/VT_Phong.css" %>
    </style>
    <script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>

<body>
    <%@include file="/WEB-INF/views/includes/header.jsp"%>
    <%@include file="/WEB-INF/views/includes/menu.jsp"%>

    <!-- có thể cân nhắc làm thêm tính năng thùng rác  -->
    <div class="main_content">
        <h1 class="title">Quản Lý Vật Tư Phòng</h1>
        <div class="search-and-buttons">
            <button type="button" class="btn btn-primary button_create" onclick="toggleCreateVTPhongForm()">Thêm Vật Tư Phòng</button>
            <button type="button" class="btn btn-primary button_create" onclick="window.location.href='${pageContext.request.contextPath}/vtphong/list'">Reload</button>
        	<button type="button" class="btn btn-primary button-create" id="deleteSelectedButton" style="display: none;" onclick="confirmDeleteSelected()">Xóa các vật tư phòng đã lựa chọn</button>
        </div>
        <form id="deleteSelectedForm" action="${pageContext.request.contextPath}/vtphong/delete-selected" method="POST">
            <table>
                <thead>
                    <tr>
                        <th scope="col"  class="checkbox-column"><input type="checkbox" id="selectAll"></th>
                        <th scope="col">Mã Phòng <a href="#" data-column="MAPHONG" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                        <th scope="col">Mã Vật Tư <a href="#" data-column="MAVT" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                        <th scope="col">Số Lượng <a href="#" data-column="SOLUONG" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                        <!-- <th scope="col">Số Tiền <a href="#" data-column="soTien" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th> -->
                        <th scope="col">Tình Trạng <a href="#" data-column="TINHTRANG" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                        <th scope="col">Ngày Cấp <a href="#" data-column="NGAYCAP" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                        <th scope="col">Ngày Sửa Đổi <a href="#" data-column="NGAYSUADOI" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                        <th scope="col">Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vt" items="${listVTPhong}">
                        <tr>
                            <td class="checkbox-column"><input type="checkbox" class="select-item" name="selectedItems" value="${vt.maVTPhong}"></td>
                            <td>${vt.maPhong}</td>
                            <td>${vt.maVT}</td>
                            <td>${vt.soLuong}</td>
                            <td>${vt.tinhTrang}</td>
                            <%-- <td>${vt.soTien}</td> --%>
                            <td>${vt.ngayCap}</td>
                            <td>${vt.ngaySuaDoi}</td>
                            <td>
                                <!-- Sửa button with tooltip -->
                                <a href="javascript:void(0);" class="fa-solid fa-pen-to-square" style="color: #63E6BE;" title="Sửa" onclick="toggleEditVTPhongForm(${vt.maVTPhong}, '${vt.maVT}', '${vt.maPhong}', '${vt.soLuong}', '${vt.ngayCap}', '${vt.tinhTrang}')"></a>

                                <!-- Xoá button with tooltip -->
                                <a href="javascript:void(0);" class="fa-solid fa-trash" style="color: #fa0000;" title="Xoá" onclick="showConfirm('${pageContext.request.contextPath}/vtphong/delete-vt-phong/${vt.maVTPhong}'); return false;"></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </form>

        <div class="search">
            <form action="${pageContext.request.contextPath}/vtphong/find" method="GET" id="searchForm">
                <label for="maPhong">Mã Phòng:</label>
                <input type="text" id="maPhong" name="maPhong" value="${maPhong}" placeholder="Nhập mã phòng">

                <label for="maVT">Mã Vật Tư:</label>
                <input type="text" id="maVT" name="maVT" value="${maVT}" placeholder="Nhập mã vật tư">

                <label for="soLuong">Số Lượng:</label>
                <input type="text" id="soLuong" name="soLuong" value="${soLuong}" placeholder="Nhập vào số lượng">

                <label for="tinhTrang" class="form-label">Tình Trạng:</label>
                <select id="tinhTrang" name="tinhTrang" class="form-select">
                    <option value="" ${empty tinhTrang ? 'selected' : '' }>Chọn tình trạng</option>
                    <option value="Tốt" ${tinhTrang=='Tốt' ? 'selected' : '' }>Tốt</option>
                    <option value="Khá" ${tinhTrang=='Khá' ? 'selected' : '' }>Khá</option>
                    <option value="Hỏng hóc" ${tinhTrang=='Hỏng hóc' ? 'selected' : '' }>Hỏng hóc</option>
                </select>

                <label for="ngayCap">Ngày Cấp:</label>
                <input type="text" id="ngayCap" name="ngayCap" value="${ngayCap}" placeholder="Nhập ngày cấp">

                <label for="ngaySuaDoi">Ngày Sửa Đổi:</label>
                <input type="text" id="ngaySuaDoi" name="ngaySuaDoi" value="${ngaySuaDoi}" placeholder="Nhập ngày sửa đổi">

                <button type="button" class="btn btn-find" onclick="submitSearchForm()">Tìm kiếm</button>
                <button type="button" class="btn btn-delete" title="Xóa theo những dữ liệu tìm kiếm" onclick="showDeleteConfirm(() => submitDeleteForm())">Xóa</button>
            </form>
        </div>

        <div id="createVTPhongForm" style="display: none">
            <span class="close" onclick="closeForm('createVTPhongForm')">&times;</span>
            <h2>Thêm Vật Tư Phòng</h2>
            <form action="${pageContext.request.contextPath}/vtphong/add-vt-phong" method="POST" onsubmit="return validateForm('create')">

                <div class="form-group">
                    <label for="maPhong" class="form-label">Mã Phòng:</label>
                    <select class="form-control" id="add_maPhong" name="maPhong" required>
                        <c:forEach var="phong" items="${listPhong}">
                            <option value="${phong}">${phong}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="maVT" class="form-label">Mã Vật Tư:</label>
                    <select class="form-control" id="add_maVT" name="maVT" required>
                        <c:forEach var="vatTu" items="${listVatTu}">
                            <option value="${vatTu}">${vatTu}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="soLuong" class="form-label">Số Lượng:</label>
                    <input type="number" class="form-control" id="add_soLuong" name="soLuong" min="0" required>
                </div>
                <div class="form-group">
                    <label for="ngayCap" class="form-label">Ngày Cấp:</label>
                    <input type="date" class="form-control" id="add_ngayCap" name="ngayCap" required>
                </div>
                <div class="form-group">
                    <label for="tinhTrang" class="form-label">Tình Trạng:</label>
                    <select class="form-control" id="add_tinhTrang" name="tinhTrang" required>
                        <option value="Tốt">Tốt</option>
                        <option value="Khá">Khá</option>
                        <option value="Hỏng hóc">Hỏng hóc</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Thêm</button>
            </form>
        </div>

        <div id="editVTPhongForm" style="display: none">
            <span class="close" onclick="closeForm('editVTPhongForm')">&times;</span>
            <h2>Sửa Vật Tư Phòng</h2>
            <form action="${pageContext.request.contextPath}/vtphong/update-vt-phong" method="POST" onsubmit="return validateForm('edit')">
                <input type="hidden" id="edit_maVTPhong" name="maVTPhong">

                <div class="form-group">
                    <label for="maPhong" class="form-label">Mã Phòng:</label>
                    <select class="form-control" id="edit_maPhong" name="maPhong" required>
                        <c:forEach var="phong" items="${listPhong}">
                            <option value="${phong}">${phong}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="maVT" class="form-label">Mã Vật Tư:</label>
                    <select class="form-control" id="edit_maVT" name="maVT" required>
                        <c:forEach var="vatTu" items="${listVatTu}">
                            <option value="${vatTu}">${vatTu}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="soLuong" class="form-label">Số Lượng:</label>
                    <input type="number" class="form-control" id="edit_soLuong" name="soLuong" min="0" required>
                </div>
                <div class="form-group">
                    <label for="ngayCap" class="form-label">Ngày Cấp:</label>
                    <input type="date" class="form-control" id="edit_ngayCap" name="ngayCap" required>
                </div>
                <div class="form-group">
                    <label for="tinhTrang" class="form-label">Tình Trạng:</label>
                    <select class="form-control" id="edit_tinhTrang" name="tinhTrang" required>
                        <option value="Tốt">Tốt</option>
                        <option value="Khá">Khá</option>
                        <option value="Hỏng hóc">Hỏng hóc</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Sửa</button>
            </form>
        </div>
    </div>

    <script>
	    function showAlert(title, message, icon) {
	        Swal.fire({
	            title: title,
	            text: message,
	            icon: icon,
	            confirmButtonText: 'OK'
	        });
	    }
	
	    /* Thông báo lỗi và kết quả*/
	    document.addEventListener('DOMContentLoaded', function() {
	        var errorMessage = "${errorMessage}";
	        var successMessage = "${successMessage}";
	
	        if (errorMessage) {
	            showAlert("Lỗi", errorMessage, 'error')
	        } else if (successMessage) {
	            showAlert("Thành công", successMessage, 'success')
	        }
	    });
    
        function toggleCreateVTPhongForm() {
            var form = document.getElementById('createVTPhongForm');
            form.style.display = form.style.display === 'none' ? 'block' : 'none';
        }

        function closeForm(formId) {
            var form = document.getElementById(formId);
            form.style.display = 'none';
        }

        function toggleEditVTPhongForm(maVTPhong, maVT, maPhong, soLuong, ngayCap, tinhTrang) {
            var form = document.getElementById('editVTPhongForm');
            form.style.display = form.style.display === 'none' ? 'block' : 'none';
            document.getElementById('edit_maVTPhong').value = maVTPhong;
            document.getElementById('edit_maVT').value = maVT;
            document.getElementById('edit_maPhong').value = maPhong;
            document.getElementById('edit_soLuong').value = soLuong;
            document.getElementById('edit_ngayCap').value = ngayCap;
            document.getElementById('edit_tinhTrang').value = tinhTrang;
        }

        function validateForm(action) {
            if (action === 'create') {
                var maPhong = document.getElementById('add_maPhong').value;
                var maVT = document.getElementById('add_maVT').value;
                var soLuong = document.getElementById('add_soLuong').value;
                var ngayCap = document.getElementById('add_ngayCap').value;
                var tinhTrang = document.getElementById('add_tinhTrang').value;
            } else {
                var maPhong = document.getElementById('edit_maPhong').value;
                var maVT = document.getElementById('edit_maVT').value;
                var soLuong = document.getElementById('edit_soLuong').value;
                var ngayCap = document.getElementById('edit_ngayCap').value;
                var tinhTrang = document.getElementById('edit_tinhTrang').value;
            }

            if (!maPhong || !maVT || !soLuong || !ngayCap || !tinhTrang) {
                alert('Vui lòng điền đầy đủ thông tin.');
                return false;
            }
            return true;
        }

        function showConfirm(deleteUrl) {
            if (confirm("Bạn có chắc chắn muốn xóa vật tư phòng này?")) {
                window.location.href = deleteUrl;
            }
        }

        function submitSearchForm() {
            document.getElementById('searchForm').submit();
        }

        function showDeleteConfirm(callback) {
            if (confirm("Bạn có chắc chắn muốn xóa các mục đã chọn?")) {
                callback();
            }
        }

        function submitDeleteForm() {
            document.getElementById('deleteForm').submit();
        }

        function confirmDeleteSelected() {
            if (confirm("Bạn có chắc chắn muốn xóa các vật tư phòng đã chọn?")) {
                document.getElementById('deleteSelectedForm').submit();
            }
        }

        $(document).ready(function() {
            $('#selectAll').on('change', function() {
                $('.select-item').prop('checked', this.checked);
                toggleDeleteSelectedButton();
            });

            $('.select-item').on('change', function() {
                $('#selectAll').prop('checked', $('.select-item:checked').length === $('.select-item').length);
                toggleDeleteSelectedButton();
            });

            function toggleDeleteSelectedButton() {
                if ($('.select-item:checked').length > 0) {
                    $('#deleteSelectedButton').show();
                } else {
                    $('#deleteSelectedButton').hide();
                }
            }
        });
    </script>
</body>

</html>
