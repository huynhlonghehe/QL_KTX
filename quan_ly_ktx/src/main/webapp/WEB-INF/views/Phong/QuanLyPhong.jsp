<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Quản lý Phòng</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <style>
            <%@include file="/WEB-INF/resources/css/Phong_CSS/QuanLyPhong.css" %>
        </style>
        <script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    </head>

    <body>
        <%@include file="/WEB-INF/views/includes/header.jsp"%>
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="main_content">
            <h1 class="title">QUẢN LÝ PHÒNG</h1>
            <form id="deleteSelectedForm" action="${pageContext.request.contextPath}/phong/delete-selected" method="POST">
                <table>
                    <thead>
                        <tr>
                            <th scope="col" class="checkbox-column"><input type="checkbox" id="selectAll"></th>
                            <th scope="col">Mã Phòng <a href="#" data-column="MAPHONG" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Tình Trạng <a href="#" data-column="TINHTRANG" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Sức Chứa <a href="#" data-column="SUCCHUA" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Khu KTX <a href="#" data-column="KHUKTX" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Số Lượng SV Đang Có <a href="#" data-column="SOLUONG" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Loại Phòng <a href="#" data-column="MALOAIPHONG" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Ngày Tạo <a href="#" data-column="NGAYTAO" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Ngày Sửa Đổi <a href="#" data-column="NGAYSUADOI" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Người Sửa Đổi <a href="#" data-column="NGUOISUADOICUOI" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="phong" items="${listPhong}">
                            <tr>
                                <td class="checkbox-column"><input type="checkbox" class="select-item" name="selectedItems" value="${phong.maPhong}"></td>
                                <td>${phong.maPhong}</td>
                                <td>${phong.tinhTrang}</td>
                                <td>${phong.sucChua}</td>
                                <td>${phong.khuKTX}</td>
                                <td>${phong.soLuong}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${phong.maLoaiPhong == 'LP001'}">Dịch vụ</c:when>
                                        <c:when test="${phong.maLoaiPhong == 'LP002'}">Tốt</c:when>
                                        <c:when test="${phong.maLoaiPhong == 'LP003'}">Bình thường</c:when>
                                        <c:otherwise>${phong.maLoaiPhong}</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${phong.ngayTao != null ? phong.ngayTao : 'N/A'}</td>
                                <td>${phong.ngaySuaDoi != null ? phong.ngaySuaDoi : 'N/A'}</td>
                                <td>${phong.nguoiSuaDoiCuoi != null ? phong.nguoiSuaDoiCuoi : 'N/A'}</td>
                                <td>
                                    <!-- Sửa button with tooltip and new style -->
                                    <a href="javascript:void(0);" class="fa-solid fa-pen-to-square" style="color: #63E6BE;" title="Sửa" onclick="toggleEditPhongForm('${phong.maPhong}', '${phong.tinhTrang}', '${phong.sucChua}', '${phong.maLoaiPhong}','${phong.khuKTX}','${sessionScope.USERNAME}' )"></a>

                                    <!-- Xoá button with tooltip and new style -->
                                    <a href="javascript:void(0);" class="fa-solid fa-trash" style="color: #fa0000;" title="Xoá" onclick="showConfirm('${pageContext.request.contextPath}/phong/delete-phong/${phong.maPhong}'); return false;"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>



			<div class="search-and-buttons">
			  <button type="button" class="btn btn-primary button_createTK" onclick="toggleCreatePhongForm()">
			    <i class="fa-solid fa-building"></i> Thêm Phòng
			  </button>
			  <button type="button" class="btn btn-primary button_createTK" onclick="window.location.href='http://localhost:8080/quan_ly_ktx/phong/list'">
			    <i class="fa-solid fa-redo"></i> Reload
			  </button>
			  <button type="button" class="btn btn-primary button-createTK" id="deleteSelectedButton" style="display: none;" onclick="confirmDeleteSelected()">
			    <i class="fa-solid fa-trash-can"></i> Xóa các vật tư phòng đã lựa chọn
			  </button>
			</div>

            <div class="search">
                <!-- Form tìm kiếm -->
                <form action="${pageContext.request.contextPath}/phong/find" method="GET" id="searchForm">
                    <label for="maPhong">Mã phòng:</label>
                    <input type="text" id="maPhong" name="maPhong" value="${maPhong}" placeholder="(cách nhau bằng dấu phẩy)">

                    <label for="tinhTrang">Tình trạng:</label>
                    <input type="text" id="tinhTrang" name="tinhTrang" value="${tinhTrang}" placeholder="(cách nhau bằng dấu phẩy)">

                    <label for="sucChua">Sức chứa:</label>
                    <input type="text" id="sucChua" name="sucChua" value="${sucChua}" placeholder="Nhập sức chứa">

                    <%--         <label for="khuKTX">Khu KTX:</label>
        <input type="text" title="Nhập vào khu KTX" id="khuKTX" name="khuKTX" value="${khuKTX}" placeholder="Nhập khu KTX">
 --%>
                    <label for="soLuong">Số lượng sinh viên:</label>
                    <input type="text" id="soLuong" name="soLuong" value="${soLuong}" placeholder="Nhập số lượng sinh viên">

                    <label for="maLoaiPhong">Mã loại phòng:</label>
                    <input type="text" id="maLoaiPhong" name="maLoaiPhong" value="${maLoaiPhong}" placeholder="Nhập mã loại phòng">

                    <button type="submit" class="btn btn-find">
                        <i class="fas fa-search"></i> Tìm kiếm
                    </button>
                </form>
            </div>


            <!-- Form thêm phòng -->
            <div id="createPhongForm" style="display: none">
                <span class="close" onclick="closeForm('createPhongForm')">&times;</span>
                <h2>Thêm phòng</h2>
                <form action="${pageContext.request.contextPath}/phong/add-phong" method="POST">
                    <div class="form-group">
                        <label for="maKhu" class="form-label">Khu:</label>
                        <select id="add_maKhu" name="maKhu" class="form-select">
                            <option value="A">A</option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                            <option value="D">D</option>
                            <option value="E">E</option>
                            <option value="F">F</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="maPhong" class="form-label">Mã Phòng:</label>
                        <input type="text" class="form-control" id="add_maSo" name="maSo" placeholder="Nhập mã phòng">
                    </div>
                    <div class="form-group">
                        <label for="tinhTrang" class="form-label">Tình Trạng:</label>
                        <select id="add_tinhTrang" name="tinhTrang" class="form-select">
                            <option value="Tốt">Tốt</option>
                            <option value="Khá">Khá</option>
                            <option value="Trung Bình">Trung bình</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="sucChua" class="form-label">Sức chứa:</label>
                        <input type="number" class="form-control" id="add_sucChua" name="sucChua" placeholder="Nhập sức chứa">
                    </div>
                    <div class="form-group">
                        <label for="maLoaiPhong" class="form-label">Mã loại phòng:</label>
                        <select id="add_maLoaiPhong" name="maLoaiPhong" class="form-select">
                            <option value="LP001">Dịch vụ</option>
                            <option value="LP002">Tốt</option>
                            <option value="LP003">Thường</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-them" onclick="submitForm('create')">Thêm</button>
                    </div>
                </form>
            </div>

            <!-- Form sửa thông tin phòng -->
            <div id="editPhongForm" style="display: none">
                <span class="close" onclick="closeForm('editPhongForm')">&times;</span>
                <h2>Sửa Thông Tin Phòng</h2>
                <form action="${pageContext.request.contextPath}/phong/update-phong/" method="POST">
                    <div class="form-group">
                        <label for="edit_maPhong">Mã Phòng:</label>
                        <!-- Input readonly để hiển thị giá trị edit_maVT -->
                        <input type="hidden" name="maKhu" id="edit_maKhu" readonly>
                        <input type="text" name="maPhong" id="edit_maPhong" readonly>
                        <!-- Hiển thị giá trị edit_maVT -->
                        <span id="edit_maPhong_display" style="display: block;"></span>
                    </div>
                    <div class="form-group">
                        <label for="edit_tinhTrang" class="form-label">Tình Trạng:</label>
                        <select id="edit_tinhTrang" name="tinhTrang" class="form-select">
                            <option value="Tốt">Tốt</option>
                            <option value="Khá">Khá</option>
                            <option value="Trung Bình">Trung bình</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="edit_sucChua" class="form-label">Sức chứa:</label>
                        <input type="number" class="form-control" id="edit_sucChua" name="sucChua" placeholder="Nhập sức chứa">
                    </div>
                    <div class="form-group">
                        <label for="edit_maLoaiPhong" class="form-label">Mã loại phòng:</label>
                        <select id="edit_maLoaiPhong" name="maLoaiPhong" class="form-select">
                            <option value="LP001">Dịch vụ</option>
                            <option value="LP002">Tốt</option>
                            <option value="LP003">Thường</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="hidden" class="form-control" id="edit_nguoiSuaDoiCuoi" name="nguoiSuaDoiCuoi">
                    </div>
                    <div class="form-group">
                        <input type="hidden" class="form-control" id="edit_khuKTX" name="KhuKTX">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-them" onclick="submitForm('edit')">Lưu</button>
                    </div>
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


            //tạo maPhong từ maKhu và maSo
            function generateMaPhong() {
                var maKhu = document.getElementById("add_maKhu").value;
                var maSo = document.getElementById("add_maSo").value;
                console.log(maKhu);
                console.log(maSo);
                var maPhong = maKhu + maSo;
                console.log(maPhong);
                return maPhong;
            }


            //Hàm xử lý submit cho cả hai form
            function submitForm(formType) {
                event.preventDefault(); // Ngăn chặn hành động mặc định của sự kiện submit

                // Lấy dữ liệu từ form
                var maPhong, tinhTrang, sucChua, maLoaiPhong, khuKTX;
                if (formType === 'create') {
                    maPhong = generateMaPhong();
                    console.log(maPhong);
                    tinhTrang = document.getElementById("add_tinhTrang").value;
                    sucChua = document.getElementById("add_sucChua").value;
                    maLoaiPhong = document.getElementById("add_maLoaiPhong").value;
                    khuKTX = document.getElementById("add_maKhu").value;
                } else if (formType === 'edit') {
                    maPhong = document.getElementById("edit_maPhong").value;
                    tinhTrang = document.getElementById("edit_tinhTrang").value;
                    sucChua = document.getElementById("edit_sucChua").value;
                    maLoaiPhong = document.getElementById("edit_maLoaiPhong").value;
                    nguoiSuaDoi = document.getElementById("edit_nguoiSuaDoiCuoi").value;
                    khuKTX = document.getElementById('edit_khuKTX').value;
                }

                // Kiểm tra dữ liệu
                if (maPhong === '' || tinhTrang === '' || sucChua === '' || maLoaiPhong === '') {
                    showAlert('Error!', 'Vui lòng điền đầy đủ thông tin.', 'error');
                    return false;
                }

                // Kiểm tra giá trị của sức chứa
                if (sucChua < 4 || sucChua > 12) {
                    showAlert('Error!', 'Sức chứa phải nằm trong khoảng từ 4 đến 12.', 'error');
                    return false; // Ngăn chặn việc gửi biểu mẫu nếu giá trị không hợp lệ
                }

                // Xử lý submit tùy thuộc vào loại form
                if (formType === 'create') {
                    // Thêm phòng mới
                    document.querySelector("#createPhongForm form").submit();
                } else if (formType === 'edit') {
                    // Sửa thông tin phòng
                    document.querySelector("#editPhongForm form").submit();
                }
            }


            //================================================//

            $(document).ready(function() {
                $('#selectAll').on('change', function() {
                    $('.select-item').prop('checked', this.checked);
                    toggleDeleteSelectedButton();
                });

                $('.select-item').on('change', function() {
                    toggleDeleteSelectedButton();
                });
            });

            function toggleDeleteSelectedButton() {
                if ($('.select-item:checked').length > 0) {
                    $('#deleteSelectedButton').show();
                } else {
                    $('#deleteSelectedButton').hide();
                }
            }

            function confirmDeleteSelected() {
                var form = document.getElementById('deleteSelectedForm');
                Swal.fire({
                    title: 'Xác nhận xóa',
                    text: 'Bạn có chắc chắn muốn xóa các vật tư phòng đã chọn?',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: 'Có',
                    cancelButtonText: 'Không'
                }).then((result) => {
                    if (result.isConfirmed) {
                        form.submit();
                    }
                });
            }
            //=====================================================//
            function toggleCreatePhongForm() {
                document.getElementById('createPhongForm').style.display = 'block';
            }

            function closeForm(formId) {
                document.getElementById(formId).style.display = 'none';
            }

            function toggleEditPhongForm(maPhong, tinhTrang, sucChua, maLoaiPhong, khuKTX, nguoiSuaDoiCuoi) {
                document.getElementById('editPhongForm').style.display = 'block';
                document.getElementById('edit_maPhong').value = maPhong;
                document.getElementById('edit_tinhTrang').value = tinhTrang;
                document.getElementById('edit_sucChua').value = sucChua;
                document.getElementById('edit_maLoaiPhong').value = maLoaiPhong;
                document.getElementById('edit_khuKTX').value = khuKTX;
                document.getElementById('edit_nguoiSuaDoiCuoi').value = nguoiSuaDoiCuoi;

            }

            function showConfirm(url) {
                Swal.fire({
                    title: 'Xác nhận xóa',
                    text: 'Bạn có chắc chắn muốn xóa?',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: 'Có',
                    cancelButtonText: 'Không'
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = url;
                    }
                });
            }
            //sort
            document.addEventListener("DOMContentLoaded", function() {
                var sortIcons = document.querySelectorAll(".fa-sort");
                sortIcons.forEach(function(icon) {
                    var link = icon.closest('a');
                    var column = link.getAttribute('data-column');

                    // Tải trạng thái sắp xếp đã lưu từ localStorage
                    var savedMode = localStorage.getItem('sortMode-' + column);
                    if (savedMode) {
                        link.setAttribute('data-mode', savedMode);
                        link.setAttribute('href', "http://localhost:8080/quan_ly_ktx/phong/list/" + column + "/sort/" + savedMode);
                    }

                    icon.addEventListener("click", function(event) {
                        var currentMode = link.getAttribute('data-mode');
                        var newMode = currentMode === 'asc' ? 'desc' : 'asc';

                        // Cập nhật data-mode và href trên thẻ a
                        link.setAttribute('data-mode', newMode);
                        var newHref = "http://localhost:8080/quan_ly_ktx/phong/list/" + column + "/sort/" + newMode;
                        link.setAttribute('href', newHref);

                        // Lưu trạng thái mới vào localStorage
                        localStorage.setItem('sortMode-' + column, newMode);

                        window.location.href = newHref; // Chuyển hướng người dùng
                    });
                });
            });
        </script>
    </body>

</html>