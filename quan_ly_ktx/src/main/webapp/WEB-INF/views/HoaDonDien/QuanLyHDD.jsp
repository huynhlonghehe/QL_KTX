<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Quản lý Hóa Đơn Điện</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <style>
            <%@include file="/WEB-INF/resources/css/HoaDonDien_CSS/QuanLyHDD.css" %>
        </style>
        <script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    </head>

    <body>
        <%@include file="/WEB-INF/views/includes/header.jsp"%>
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="main_content">
            <h1 class="title">QUẢN LÝ HÓA ĐƠN ĐIỆN</h1>
            <form id="deleteSelectedForm" action="${pageContext.request.contextPath}/hoadondien/delete-selected" method="POST">
                <table>
                    <thead>
                        <tr>
                            <th scope="col" class="checkbox-column"><input type="checkbox" id="selectAll"></th>
                            <th scope="col">Mã HDD <a href="#" data-column="MAHDD" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Chỉ Số Trước <a href="#" data-column="CHISOTRUOC" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Chỉ Số Sau <a href="#" data-column="CHISOSAU" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Hệ Số Tiền Điện <a href="#" data-column="HESOTIENDIEN" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Tiền Nợ <a href="#" data-column="TIENNO" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Mã Phòng <a href="#" data-column="MAPHONG" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Ngày Tạo <a href="#" data-column="NGAYTAO" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Ngày Sửa Đổi <a href="#" data-column="NGAYSUADOI" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Người Sửa Đổi <a href="#" data-column="NGUOISUADOICUOI" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="hdd" items="${listHDD}">
                            <tr>
                                <td class="checkbox-column"><input type="checkbox" class="select-item" name="selectedItems" value="${hdd.maHDD}"></td>
                                <td>${hdd.maHDD}</td>
                                <td>${hdd.chiSoTruoc}</td>
                                <td>${hdd.chiSoSau}</td>
                                <td>${hdd.heSoTienDien}</td>
                                <td>${hdd.tienNo ? "Có" : "Không"}</td>
                                <td>${hdd.maPhong}</td>
                                <td>${hdd.ngayTao != null ? hdd.ngayTao : 'N/A'}</td>
                                <td>${hdd.ngaySuaDoi != null ? hdd.ngaySuaDoi : 'N/A'}</td>
                                <td>${hdd.nguoiSuaDoiCuoi != null ? hdd.nguoiSuaDoiCuoi : 'N/A'}</td>
                                <td>
                                    <a href="javascript:void(0);" class="fa-solid fa-pen-to-square" style="color: #63E6BE;" title="Sửa" onclick="toggleEditHDDForm('${hdd.maHDD}', '${hdd.chiSoTruoc}', '${hdd.chiSoSau}', '${hdd.heSoTienDien}', '${hdd.tienNo}', '${hdd.maPhong}', '${hdd.ngayTao}', '${sessionScope.USERNAME}')"></a>
                                    <a href="javascript:void(0);" class="fa-solid fa-trash" style="color: #fa0000;" title="Xoá" onclick="showConfirm('${pageContext.request.contextPath}/hoadondien/delete-HDD/${hdd.maHDD}'); return false;"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>

            <div class="search-and-buttons">
                <button type="button" class="btn btn-primary button_createTK" onclick="toggleCreateHDDForm()">
                    <i class="fa-solid fa-file-invoice-dollar"></i> Thêm Hóa Đơn
                </button>
                <button type="button" class="btn btn-primary button_createTK" onclick="window.location.href='${pageContext.request.contextPath}/hoadondien/list'">
                    <i class="fa-solid fa-redo"></i> Reload
                </button>
                <button type="button" class="btn btn-primary button-createTK" id="deleteSelectedButton" style="display: none;" onclick="confirmDeleteSelected()">
                    <i class="fa-solid fa-trash-can"></i> Xóa các hóa đơn đã lựa chọn
                </button>
            </div>

            <div class="search">
                <form action="${pageContext.request.contextPath}/hoadondien/find" method="GET" id="searchForm">
                    <label for="maHDD">Mã HDD:</label>
                    <input type="text" id="maHDD" name="maHDD" value="${maHDD}" placeholder="Nhập mã HDD">

                    <label for="maPhong">Mã Phòng:</label>
                    <input type="text" id="maPhong" name="maPhong" value="${maPhong}" placeholder="Nhập mã phòng">

                    <button type="submit" class="btn btn-find">
                        <i class="fas fa-search"></i> Tìm kiếm
                    </button>
                </form>
            </div>

            <!-- Form thêm hóa đơn -->
            <div id="createHDDForm" style="display: none">
                <span class="close" onclick="closeForm('createHDDForm')">&times;</span>
                <h2>Thêm Hóa Đơn Điện</h2>
                <form action="${pageContext.request.contextPath}/hoadondien/add-HDD" method="POST">
                    <div class="form-group">
                        <label for="add_maHDD" class="form-label">Mã HDD:</label>
                        <input type="text" class="form-control" id="add_maHDD" name="maHDD" placeholder="Nhập mã HDD" required>
                    </div>
                    <div class="form-group">
                        <label for="maPhong" class="form-label">Mã Phòng:</label>
                        <select class="form-control" id="add_maPhong" name="maPhong" required>
                            <option value="" disabled selected>Chọn phòng</option>
                            <c:forEach var="phong" items="${listPhong}">
                                <option value="${phong}">${phong}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="add_chiSoTruoc" class="form-label">Chỉ Số Trước:</label>
                        <input type="number" class="form-control" id="add_chiSoTruoc" name="chiSoTruoc" placeholder="Nhập chỉ số trước" required>
                    </div>
                    <div class="form-group">
                        <label for="add_chiSoSau" class="form-label">Chỉ Số Sau:</label>
                        <input type="number" class="form-control" id="add_chiSoSau" name="chiSoSau" placeholder="Nhập chỉ số sau" required>
                    </div>
                    <div class="form-group">
                        <label for="add_heSoTienDien" class="form-label">Hệ Số Tiền Điện:</label>
                        <input type="number" step="0.01" class="form-control" id="add_heSoTienDien" name="heSoTienDien" placeholder="Nhập hệ số tiền điện" required>
                    </div>
                    <div class="form-group">
                        <label for="add_tienNo" class="form-label">Tiền Nợ:</label>
                        <select id="add_tienNo" name="tienNo" class="form-select" required>
                            <option value="False">Không</option>
                            <option value="True">Có</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-them">Thêm Hóa Đơn</button>
                </form>
            </div>
        </div>

        <!-- Form chỉnh sửa hóa đơn -->
        <div id="editHDDForm" style="display: none">
            <span class="close" onclick="closeForm('editHDDForm')">&times;</span>
            <h2>Chỉnh Sửa Hóa Đơn Điện</h2>
            <form id="editHDDFormDetails" action="${pageContext.request.contextPath}/hoadondien/update-HDD" method="POST" onsubmit="return validateEditHDDForm()">
                <input type="hidden" id="edit_maHDD_hidden" name="maHDD">
                <!--  <input type="hidden" id="edit_ngayTao_hidden" name="ngayTao"> -->
                <input type="hidden" id="edit_tenNguoiChinhSua_hidden" name="tenNguoiChinhSua" value="${sessionScope.USERNAME}">
                <div class="form-group">
                    <label for="edit_maHDD" class="form-label">Mã HDD:</label>
                    <input type="text" class="form-control" id="edit_maHDD" disabled>
                </div>

                <div class="form-group">
                    <label for="maPhong" class="form-label">Mã Phòng:</label>
                    <select class="form-control" id="edit_maPhong" name="maPhong" required>
                        <c:forEach var="phong" items="${listPhong}">
                            <option value="${phong}">${phong}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="edit_ngayTao" class="form-label">Ngày tạo:</label>
                    <input type="datetime-local" class="form-control" id="edit_ngayTao" name="ngayTao" required>
                </div>
                <div class="form-group">
                    <label for="edit_chiSoTruoc" class="form-label">Chỉ Số Trước:</label>
                    <input type="number" class="form-control" id="edit_chiSoTruoc" name="chiSoTruoc" required>
                </div>
                <div class="form-group">
                    <label for="edit_chiSoSau" class="form-label">Chỉ Số Sau:</label>
                    <input type="number" class="form-control" id="edit_chiSoSau" name="chiSoSau" required>
                </div>
                <div class="form-group">
                    <label for="edit_heSoTienDien" class="form-label">Hệ Số Tiền Điện:</label>
                    <input type="number" step="0.01" class="form-control" id="edit_heSoTienDien" name="heSoTienDien" required>
                </div>
                <div class="form-group">
                    <label for="edit_tienNo" class="form-label">Tiền Nợ:</label>
                    <select id="edit_tienNo" name="tienNo" class="form-select" required>
                        <option value="False">Không</option>
                        <option value="True">Có</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-them">Lưu Thay Đổi</button>
            </form>
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

            /* Thông báo lỗi và kết quả */
            document.addEventListener('DOMContentLoaded', function() {
                var errorMessage = "${errorMessage}";
                var successMessage = "${successMessage}";

                if (errorMessage) {
                    showAlert("Lỗi", errorMessage, 'error');
                } else if (successMessage) {
                    showAlert("Thành công", successMessage, 'success');
                }
            });

            document.addEventListener('DOMContentLoaded', function() {
                var selectAll = document.getElementById("selectAll");
                var selectItems = document.querySelectorAll(".select-item");
                var deleteSelectedButton = document.getElementById("deleteSelectedButton");

                selectAll.addEventListener('click', function() {
                    selectItems.forEach(function(item) {
                        item.checked = selectAll.checked;
                    });
                    toggleDeleteSelectedButton();
                });

                selectItems.forEach(function(item) {
                    item.addEventListener('click', function() {
                        if (document.querySelectorAll(".select-item:checked").length === selectItems.length) {
                            selectAll.checked = true;
                        } else {
                            selectAll.checked = false;
                        }
                        toggleDeleteSelectedButton();
                    });
                });
            });

            function toggleCreateHDDForm() {
                var form = document.getElementById("createHDDForm");
                form.style.display = form.style.display === 'none' ? 'block' : 'none';
            }

            function toggleEditHDDForm(maHDD, chiSoTruoc, chiSoSau, heSoTienDien, tienNo, maPhong, ngayTao, tenNguoiChinhSua) {
                var form = document.getElementById("editHDDForm");
                form.style.display = form.style.display === 'none' ? 'block' : 'none';

                document.getElementById("edit_maHDD").value = maHDD;
                document.getElementById("edit_maHDD_hidden").value = maHDD;
                document.getElementById("edit_chiSoTruoc").value = chiSoTruoc;
                document.getElementById("edit_chiSoSau").value = chiSoSau;
                document.getElementById("edit_heSoTienDien").value = heSoTienDien;
                document.getElementById("edit_tienNo").value = tienNo === "true" ? "True" : "False";
                document.getElementById("edit_maPhong").value = maPhong;
                document.getElementById("edit_ngayTao").value = ngayTao;
                document.getElementById("edit_tenNguoiChinhSua_hidden").value = tenNguoiChinhSua;
                
                console.log("Edit form populated with:");
                console.log("maHDD:", maHDD);
                console.log("chiSoTruoc:", chiSoTruoc);
                console.log("chiSoSau:", chiSoSau);
                console.log("heSoTienDien:", heSoTienDien);
                console.log("tienNo:", tienNo);
                console.log("maPhong:", maPhong);
                console.log("ngayTao:", ngayTao);
            }

            function closeForm(formId) {
                document.getElementById(formId).style.display = 'none';
            }

            function showConfirm(deleteUrl) {
                Swal.fire({
                    title: 'Xác nhận xóa',
                    text: 'Bạn có chắc chắn muốn xóa hóa đơn này?',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Có, xóa nó!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = deleteUrl;
                    }
                });
            }

            function toggleDeleteSelectedButton() {
                var deleteSelectedButton = document.getElementById("deleteSelectedButton");
                var checkedItems = document.querySelectorAll(".select-item:checked");

                if (checkedItems.length > 0) {
                    deleteSelectedButton.style.display = 'block';
                } else {
                    deleteSelectedButton.style.display = 'none';
                }
            }

            function confirmDeleteSelected() {
                Swal.fire({
                    title: 'Xác nhận xóa',
                    text: 'Bạn có chắc chắn muốn xóa các hóa đơn đã chọn?',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Có, xóa chúng!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        document.getElementById("deleteSelectedForm").submit();
                    }
                });
            }

            document.addEventListener('DOMContentLoaded', function() {
                var addMaPhong = document.getElementById("add_maPhong");
                var addChiSoTruoc = document.getElementById("add_chiSoTruoc");

                addMaPhong.addEventListener('change', function() {
                    var maPhong = addMaPhong.value;
                    if (maPhong) {
                        fetch('${pageContext.request.contextPath}/hoadondien/get-chi-so-truoc?maPhong=' + maPhong)
                            .then(response => response.json())
                            .then(data => {
                                addChiSoTruoc.value = data;
                            })
                            .catch(() => {
                                addChiSoTruoc.value = '';
                            });
                    } else {
                        addChiSoTruoc.value = '';
                    }
                });
            });
        </script>

    </body>

</html>