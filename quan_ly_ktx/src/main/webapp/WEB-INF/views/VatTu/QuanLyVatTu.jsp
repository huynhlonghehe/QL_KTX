<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

    </head>

    <body>
        <%@include file="/WEB-INF/views/includes/header.jsp"%>
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="main_content">
            <h1 class="title">QUẢN LÝ VẬT TƯ</h1>
            <form id="deleteSelectedForm" action="${pageContext.request.contextPath}/vattu/delete-selected" method="POST">
                <table>
                    <thead>
                        <tr>
                            <th scope="col" class="checkbox-column"><input type="checkbox" id="selectAll"></th>
                            <th scope="col">Mã vật tư <a href="#" data-column="MAVT" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Tên vật tư <a href="#" data-column="TENVT" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Giá tiền <a href="#" data-column="GIATIEN" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Ngày tạo <a href="#" data-column="NGAYTAO" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Ngày sửa đổi <a href="#" data-column="NGAYSUADOI" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                            <th scope="col">Người sửa đổi</th>
                            <th scope="col">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="vattu" items="${listVatTu}">
                            <tr>
                                <td class="checkbox-column"><input type="checkbox" class="select-item" name="selectedItems" value="${vattu.maVT}"></td>
                                <td>${vattu.maVT}</td>
                                <td>${vattu.tenVT}</td>
                                <td>${vattu.giaTien}</td>
                                <td>${vattu.ngayTao != null ? vattu.ngayTao : 'N/A'}</td>
                                <td>${vattu.ngaySuaDoi != null ? vattu.ngaySuaDoi : 'N/A'}</td>
                                <td>${vattu.nguoiSuaDoiCuoi != null ? vattu.nguoiSuaDoiCuoi : 'N/A'}</td>
                                <td>
                                    <!-- Sửa button with tooltip and new style -->
                                    <a href="javascript:void(0);" class="fa-solid fa-pen-to-square" style="color: #63E6BE;" title="Sửa" onclick="toggleEditVatTuForm('${vattu.maVT}', '${vattu.tenVT}', '${vattu.giaTien}', '${sessionScope.USERNAME}')"></a>
                                    <!-- Xoá button with tooltip and new style -->
                                    <a href="javascript:void(0);" class="fa-solid fa-trash" style="color: #fa0000;" title="Xoá" onclick="showConfirm('${pageContext.request.contextPath}/vattu/delete-vat-tu/${vattu.maVT}'); return false;"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>

            <div class="search-and-buttons">
                <button type="button" class="btn btn-primary button_createTK" onclick="toggleCreateVatTuForm()">
                    <i class="fa-solid fa-building"></i> Thêm Vật Tư
                </button>
                <button type="button" class="btn btn-primary button_createTK" onclick="window.location.href='${pageContext.request.contextPath}/vattu/list'">
                    <i class="fa-solid fa-redo"></i> Reload
                </button>
                <button type="button" class="btn btn-primary button-createTK" id="deleteSelectedButton" style="display: none;" onclick="confirmDeleteSelected()">
                    <i class="fa-solid fa-trash-can"></i> Xóa các vật tư phòng đã lựa chọn
                </button>
            </div>


            <div class="search">
                <!-- Form tìm kiếm -->
                <form action="${pageContext.request.contextPath}/vattu/find" method="GET" id="searchForm">
                    <label for="maVT">Mã vật tư:</label>
                    <input type="text" id="maVT" name="maVT" placeholder="Nhập mã vật tư">

                    <label for="tenVT">Tên vật tư:</label>
                    <input type="text" id="tenVT" name="tenVT" placeholder="Nhập tên vật tư">

                    <label for="giaTien">Giá tiền:</label>
                    <input type="text" id="giaTien" name="giaTien" placeholder="Nhập giá tiền">

                    <label for="ngayTao">Ngày tạo:</label>
                    <input type="text" id="ngayTao" name="ngayTao" placeholder="Nhập ngày tạo">

                    <label for="ngaySuaDoi">Ngày sửa đổi:</label>
                    <input type="text" id="ngaySuaDoi" name="ngaySuaDoi" placeholder="Nhập ngày sửa đổi">

                    <label for="nguoiSuaDoi">Người sửa đổi:</label>
                    <input type="text" id="nguoiSuaDoi" name="nguoiSuaDoi" placeholder="Nhập người sửa đổi">

                    <!-- Thêm nút tìm kiếm -->
                    <button type="submit" class="btn btn-find">
                        <i class="fas fa-search"></i> Tìm kiếm
                    </button>
                </form>
            </div>



            <div id="createVatTuForm" style="display: none">
                <span class="close" onclick="closeForm('createVatTuForm')">&times;</span>
                <h2>Thêm Vật Tư</h2>
                <form action="${pageContext.request.contextPath}/vattu/add-vat-tu" method="POST" onsubmit="return validateCreateForm()">
                    <div class="form-group">
                        <label for="maKhu" class="form-label">Khu:</label>
                        <select id="maKhu" name="maKhu" class="form-select" required oninvalid="this.setCustomValidity('Vui lòng chọn khu')" oninput="this.setCustomValidity('')">
                            <option value="">Chọn khu</option>
                            <option value="A">A</option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                            <option value="D">D</option>
                            <option value="E">E</option>
                            <option value="F">F</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="maSo" class="form-label">Mã Vật Tư:</label>
                        <input type="text" class="form-control" id="maSo" name="maSo" placeholder="Nhập mã vật tư" required oninvalid="this.setCustomValidity('Vui lòng nhập mã vật tư')" oninput="this.setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label for="tenVT" class="form-label">Tên Vật Tư:</label>
                        <input type="text" class="form-control" id="tenVT" name="tenVT" placeholder="Nhập tên vật tư" required oninvalid="this.setCustomValidity('Vui lòng nhập tên vật tư')" oninput="this.setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label for="giaTien" class="form-label">Giá Tiền:</label>
                        <input type="number" class="form-control" id="giaTien" name="giaTien" placeholder="Nhập giá tiền" required oninvalid="this.setCustomValidity('Vui lòng nhập giá tiền')" oninput="this.setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-Them">Thêm</button>
                    </div>
                </form>
            </div>




            <div id="editVatTuForm" style="display: none">
                <span class="close" onclick="closeForm('editVatTuForm')">&times;</span>
                <h2>Sửa Thông Tin Vật Tư</h2>
                <form action="${pageContext.request.contextPath}/vattu/update-vat-tu/" method="POST" onsubmit="return validateEditForm()">
                    <div class="form-group">
                        <label for="edit_maVT">Mã vật tư:</label>
                        <input type="text" name="maVT" id="edit_maVT" readonly>
                        <span id="edit_maVT_display" style="display: block;"></span>
                    </div>
                    <div class="form-group">
                        <label for="edit_tenVT">Tên vật tư:</label>
                        <input type="text" class="form-control" name="tenVT" id="edit_tenVT" placeholder="Tên vật tư" required oninvalid="this.setCustomValidity('Vui lòng nhập tên vật tư')" oninput="this.setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label for="edit_giaTien">Giá tiền:</label>
                        <input type="number" class="form-control" name="giaTien" id="edit_giaTien" placeholder="Giá tiền" required oninvalid="this.setCustomValidity('Vui lòng nhập giá tiền')" oninput="this.setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="nguoiSuaDoiCuoi" id="edit_nguoiSuaDoiCuoi" readonly>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-them">Lưu</button>
                    </div>
                </form>
            </div>






        </div>

    </body>
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

        function closeForm(formId) {
            document.getElementById(formId).style.display = 'none';
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

        function toggleCreateVatTuForm() {
            var form = document.getElementById('createVatTuForm');
            if (form.style.display === 'none' || form.style.display === '') {
                form.style.display = 'block';
            } else {
                form.style.display = 'none';
            }
        }

        function toggleEditVatTuForm(maVT, tenVT, giaTien, nguoiSuaDoiCuoi) {
            // Điền thông tin vật tư vào form sửa
            document.getElementById("edit_maVT").value = maVT;
            document.getElementById("edit_tenVT").value = tenVT;
            document.getElementById("edit_giaTien").value = giaTien;
            document.getElementById("edit_nguoiSuaDoiCuoi").value = nguoiSuaDoiCuoi;
            // Hiển thị form sửa
            var form = document.getElementById('editVatTuForm');

            if (form.style.display === 'none' || form.style.display === '') {
                form.style.display = 'block';
            } else {
                form.style.display = 'none';
            }
        }

        document.addEventListener("DOMContentLoaded", function() {
            var sortIcons = document.querySelectorAll(".fa-sort");
            sortIcons.forEach(function(icon) {
                var link = icon.closest('a');
                var column = link.getAttribute('data-column');

                // Tải trạng thái sắp xếp đã lưu từ localStorage
                var savedMode = localStorage.getItem('sortMode-' + column);
                if (savedMode) {
                    link.setAttribute('data-mode', savedMode);
                    link.setAttribute('href', "http://localhost:8080/quan_ly_ktx/vattu/list/" + column + "/sort/" + savedMode);
                }

                icon.addEventListener("click", function(event) {
                    var currentMode = link.getAttribute('data-mode');
                    var newMode = currentMode === 'asc' ? 'desc' : 'asc';

                    // Cập nhật data-mode và href trên thẻ a
                    link.setAttribute('data-mode', newMode);
                    var newHref = "http://localhost:8080/quan_ly_ktx/vattu/list/" + column + "/sort/" + newMode;
                    link.setAttribute('href', newHref);

                    // Lưu trạng thái mới vào localStorage
                    localStorage.setItem('sortMode-' + column, newMode);

                    window.location.href = newHref; // Chuyển hướng người dùng
                });
            });
        });


        function generateMaVT() {
            var maKhu = document.getElementById("maKhu").value;
            var maSo = document.getElementById("maSo").value;
            var maVT = maKhu + maSo;
            return maVT;
        }


        function submitForm(formType) {
            event.preventDefault(); // Ngăn chặn hành động mặc định của sự kiện submit

            // Lấy dữ liệu từ form
            var maVT, tenVT, giaTien;
            if (formType === 'create') {
                maVT = generateMaVT(); // Thay thế generateMaVT() bằng hàm tạo mã vật tư phù hợp
                console.log(maVT);
                tenVT = document.getElementById("tenVT").value; // Thay thế id của input tên vật tư phù hợp
                giaTien = document.getElementById("giaTien").value; // Thay thế id của input giá tiền phù hợp
            } else if (formType === 'edit') {
                maVT = document.getElementById("edit_maVT").value; // Thay thế id của input mã vật tư phù hợp
                tenVT = document.getElementById("edit_tenVT").value; // Thay thế id của input tên vật tư phù hợp
                giaTien = document.getElementById("edit_giaTien").value; // Thay thế id của input giá tiền phù hợp
            }

            // Kiểm tra dữ liệu
            /*             if (maVT === '' || tenVT === '' || giaTien === '') {
                            showAlert('Error!', 'Vui lòng điền đầy đủ thông tin.', 'error');
                            return false;
                        } */


            // Xử lý submit tùy thuộc vào loại form
            if (formType === 'create') {
                // Thêm vật tư mới
                document.querySelector("#createVatTuForm form").submit();
            } else if (formType === 'edit') {
                // Sửa thông tin vật tư
                document.querySelector("#editVatTuForm form").submit();
            }
        }

        // Kiểm tra nếu có errorMessage thì hiển thị modal

        function showErrorModal(errorMessage) {
            var modal = document.getElementById("errorModal");
            var modalContent = modal.querySelector("#errorMessage");
            modalContent.innerText = errorMessage;
            modal.style.display = "block";
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
    </script>

</html>