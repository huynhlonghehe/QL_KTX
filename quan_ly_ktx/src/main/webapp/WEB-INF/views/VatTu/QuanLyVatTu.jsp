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


    </head>

    <body>
        <%@include file="/WEB-INF/views/includes/header.jsp"%>
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="main_content">
            <h1 class="title">Quản Lý vật tư</h1>
            <table>
                <thead>
                    <tr>
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
                            <td>${vattu.maVT}</td>
                            <td>${vattu.tenVT}</td>
                            <td>${vattu.giaTien}</td>
                            <td>${vattu.ngayTao != null ? vattu.ngayTao : 'N/A'}</td>
                            <td>${vattu.ngaySuaDoi != null ? vattu.ngaySuaDoi : 'N/A'}</td>
                            <td>${vattu.nguoiSuaDoiCuoi != null ? vattu.nguoiSuaDoiCuoi : 'N/A'}</td>
                            <td>
                                <!-- Sửa button with tooltip and new style -->
                                <a href="javascript:void(0);" class="fa-solid fa-pen-to-square" style="color: #63E6BE;" title="Sửa" onclick="toggleEditVatTuForm('${vattu.maVT}', '${vattu.tenVT}', '${vattu.giaTien}', '${username}')"></a>

                                <!-- Xoá button with tooltip and new style -->
                                <a href="javascript:void(0);" class="fa-solid fa-trash" style="color: #fa0000;" title="Xoá" onclick="deleteVatTu('${vattu.maVT}')"></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


            <div class="search-and-buttons">
                <button type="button" class="btn btn-primary button_createTK" onclick="toggleCreateVatTuForm()">Thêm Vật Tư</button>
                <button type="button" class="btn btn-primary button_createTK" onclick="window.location.href='http://localhost:8080/quan_ly_ktx/vattu/list'">Reload</button>
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
                    <button type="submit" class="btn btn-find">Tìm kiếm</button>
                </form>
            </div>



            <div id="createVatTuForm" style="display: none">
                <span class="close" onclick="closeForm('createVatTuForm')">&times;</span>
                <h2>Thêm Vật Tư</h2>
                <form action="${pageContext.request.contextPath}/vattu/add-vat-tu" method="POST">
                    <div class="form-group">
                        <label for="maKhu" class="form-label">Khu:</label>
                        <select id="maKhu" name="maKhu" class="form-select">
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
                        <input type="text" class="form-control" id="maSo" name="maSo" placeholder="Nhập mã vật tư">
                    </div>
                    <div class="form-group">
                        <label for="tenVT" class="form-label">Tên Vật Tư:</label>
                        <input type="text" class="form-control" id="tenVT" name="tenVT" placeholder="Nhập tên vật tư">
                    </div>
                    <div class="form-group">
                        <label for="giaTien" class="form-label">Giá Tiền:</label>
                        <input type="number" class="form-control" id="giaTien" name="giaTien" placeholder="Nhập giá tiền">
                    </div>
                    <!-- Thêm vào trong form thêm vật tư -->
                    <div class="form-group">
                        <button type="submit" class="btn btn-Them" onclick="validateAndSubmitForm(); ">Thêm</button>
                    </div>
                </form>
            </div>


            <!-- Sử dụng span để lưu mã vật tư, mặc định ẩn đi -->
            <div id="editVatTuForm" style="display: none">
                <span class="close" onclick="closeForm('editVatTuForm')">&times;</span>
                <h2>Sửa Thông Tin Vật Tư</h2>
                <form action="${pageContext.request.contextPath}/vattu/update-vat-tu/" method="POST">
                    <div class="form-group">
                        <label for="edit_maVT">Mã vật tư:</label>
                        <!-- Input readonly để hiển thị giá trị edit_maVT -->
                        <input type="text" name="maVT" id="edit_maVT" readonly>
                        <!-- Hiển thị giá trị edit_maVT -->
                        <span id="edit_maVT_display" style="display: block;"></span>

                    </div>
                    <div class="form-group">
                        <label for="edit_tenVT">Tên vật tư:</label>
                        <input type="text" class="form-control" name="tenVT" id="edit_tenVT" placeholder="Tên vật tư">
                    </div>
                    <div class="form-group">
                        <label for="edit_giaTien">Giá tiền:</label>
                        <input type="number" class="form-control" name="giaTien" id="edit_giaTien" placeholder="Giá tiền">
                    </div>
                    <div class="form-group">
                        <!-- Input ẩn để truyền giá trị edit_maVT  <label for="edit_nguoiSuaDoiCuoi">Lần cuối sửa đổi bởi:</label>-->
                        <input type="hidden" name="nguoiSuaDoiCuoi" id="edit_nguoiSuaDoiCuoi" readonly>
                        <!-- Hiển thị giá trị edit_maVT <span id="edit_nguoiSuaDoiCuoi_display" style="display: block;"></span> -->
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-them">Lưu</button>
                    </div>
                </form>
            </div>





        </div>

        <div id="errorModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <span class="close">&times;</span>
                <div class="modal-header">
                    <h2>Xuất hiện lỗi !!!</h2>
                </div>
                <div class="modal-body">
                    <p id="errorMessage"></p>
                </div>
            </div>
        </div>

        <div id="confirmModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <span class="close" onclick="closeForm('confirmModal')">&times;</span>
                <div class="modal-header">
                    <h2>Thông báo</h2>
                </div>
                <div class="modal-body">
                    <p id="message"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="closeConfirmModalButton">Close</button>
                    <button type="button" class="btn btn-primary" id="saveChangesConfirmModalButton">Save changes</button>
                </div>
            </div>
        </div>



    </body>
    <script>
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
            if (maVT === '' || tenVT === '' || giaTien === '') {
                showAlert('Error!', 'Vui lòng điền đầy đủ thông tin.', 'error');
                return false;
            }

            // Xử lý submit tùy thuộc vào loại form
            if (formType === 'create') {
                // Thêm vật tư mới
                document.querySelector("#createVatTuForm form").submit();
            } else if (formType === 'edit') {
                // Sửa thông tin vật tư
                document.querySelector("#editVatTuForm form").submit();
            }
        }



        function deleteVatTu(maVT) {
            // Sử dụng Ajax để kiểm tra ràng buộc khóa ngoại trước khi xóa bản ghi
            $.ajax({
                type: "GET",
                url: "${pageContext.request.contextPath}/vattu/check-foreign-key/" + maVT,
                success: function(response) {
                    console.log("Response từ server:", response); // In ra giá trị response để kiểm tra
                    if (response === "OK") {
                        // Hiển thị modal xác nhận
                        showConfirmModal("Bạn có chắc chắn muốn xóa bản ghi này không?", function(confirmed) {
                            console.log("confirm từ server:", confirmed);
                            if (confirmed) {
                                // Nếu người dùng đã xác nhận, thực hiện chuyển hướng đến URL xóa bản ghi
                                window.location.href = "${pageContext.request.contextPath}/vattu/delete-vat-tu/" + maVT;
                            }
                        });
                    } else {
                        // Nếu có tham chiếu, hiển thị modal báo lỗi
                        showErrorModal("Không thể xóa vật tư này vì có ít nhất một phòng đang sử dụng.");
                    }
                },
                error: function(xhr, status, error) {
                    showErrorModal("Đã xảy ra lỗi khi kiểm tra ràng buộc khóa ngoại.");
                }
            });
        }


        // Kiểm tra nếu có errorMessage thì hiển thị modal

        function showErrorModal(errorMessage) {
            var modal = document.getElementById("errorModal");
            var modalContent = modal.querySelector("#errorMessage");
            modalContent.innerText = errorMessage;
            modal.style.display = "block";
        }
        //Hiển thị confirmModal và kiểm tra lựa chọn 
        function showConfirmModal(message, callback) {
            var modal = document.getElementById("confirmModal");
            var modalContent = modal.querySelector("#message");
            modalContent.innerText = message;
            modal.style.display = "block";

            // Lấy phần tử nút "Save changes" trong modal content
            var saveChangesButton = document.getElementById("saveChangesConfirmModalButton");
            // Lắng nghe sự kiện click cho nút "Save changes"
            var closeModalButton = document.getElementById("closeModalButton");
            saveChangesButton.addEventListener("click", function() {
                modal.style.display = "none"; // Ẩn modal
                if (callback) {
                    callback(true); // Gọi callback với giá trị true (đã xác nhận)
                }
            });

            // Lấy phần tử nút "Close" trong modal content
            var closeModalButton = document.getElementById("closeConfirmModalButton");
            // Lắng nghe sự kiện click cho nút "Close"
            closeModalButton.addEventListener("click", function() {
                modal.style.display = "none"; // Ẩn modal
                if (callback) {
                    callback(false); // Gọi callback với giá trị false (đã từ chối)
                }
            });
        }


        //Ẩn cửa sổ modal khi người dùng nhấp vào nút đóng
        document.getElementById("errorModal").querySelector(".close").onclick = function() {
            var modal = document.getElementById("errorModal");
            modal.style.animation = "disappearModal 0.5s ease forwards"; // Áp dụng hiệu ứng biến mất
            // Ẩn modal sau khi hoàn thành hiệu ứng
            setTimeout(function() {
                modal.style.display = "none";
                // Thiết lập lại trạng thái của modal
                modal.style.animation = ""; // Xóa hiệu ứng animation
                document.getElementById("errorMessage").innerText = ""; // Xóa thông báo lỗi
            }, 500); // Thời gian của keyframes disappearModal
        }

        // Đóng cửa sổ modal khi người dùng nhấp vào bất kỳ đâu ngoài cửa sổ modal
        window.onclick = function(event) {
            var modal = document.getElementById("errorModal");
            if (event.target == modal) {
                modal.style.animation = "disappearModal 0.5s ease forwards"; // Áp dụng hiệu ứng biến mất
                // Ẩn modal sau khi hoàn thành hiệu ứng
                setTimeout(function() {
                    modal.style.display = "none";
                    // Thiết lập lại trạng thái của modal
                    modal.style.animation = ""; // Xóa hiệu ứng animation
                    document.getElementById("errorMessage").innerText = ""; // Xóa thông báo lỗi
                }, 500); // Thời gian của keyframes disappearModal
            }
        }
        //đóng các modal
        function closeForm(formId) {
            var form = document.getElementById(formId);
            form.style.animation = "disappearModal 0,1s";
            setTimeout(function() {
                form.style.display = "none";
            }, 100);
        }
        //Lấy phần tử nút đóng modal
        var closeModalButton = document.getElementById("closeModalButton");

        // Thêm sự kiện lắng nghe cho nút đóng modal
        closeModalButton.addEventListener("click", function() {
            // Lấy phần tử modal
            var modal = document.getElementById("confirmModal");
            // Ẩn modal
            modal.style.display = "none";
        });
    </script>

</html>