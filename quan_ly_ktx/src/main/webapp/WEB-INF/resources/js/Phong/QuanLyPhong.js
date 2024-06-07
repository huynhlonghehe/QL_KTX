
        //tải lại trang
        function reloadPage() {
            // Lấy URL gốc của trang (không có tham số sort)
            var originalURL = window.location.origin + window.location.pathname;
            // Thực hiện reload về URL gốc
            window.location.href = originalURL;
        }

        //mở form thêm vật tư
        function toggleCreatePhongForm() {
            var form = document.getElementById('createPhongForm');
            if (form.style.display === 'none' || form.style.display === '') {
                form.style.display = 'block';
            } else {
                form.style.display = 'none';
            }
        }


        //mở form sửa vật tư
        function toggleEditPhongForm(maPhong, tinhTrang, sucChua, maLoaiPhong, nguoiSuaDoiCuoi) {
            // Điền thông tin vật tư vào form sửa
            document.getElementById("edit_maPhong").value = maPhong;
            document.getElementById("edit_maKhu").value = maPhong.substring(0, 1);
            document.getElementById("edit_tinhTrang").value = tinhTrang;
            document.getElementById("edit_sucChua").value = sucChua;
            document.getElementById("edit_maLoaiPhong").value = maLoaiPhong;
            document.getElementById("edit_nguoiSuaDoiCuoi").value = nguoiSuaDoiCuoi;

            // Hiển thị form sửa
            var form = document.getElementById('editPhongForm');

            if (form.style.display === 'none' || form.style.display === '') {
                form.style.display = 'block';
            } else {
                form.style.display = 'none';
            }
        }
        //sort list bằng DAO
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

     // Hàm xử lý submit cho form tìm kiếm
		function submitSearchForm() {
		    var selectedColumn = document.getElementById("tenBangDuocChon").value;
		    var searchValue = document.getElementById("giaTriTimKiem").value;
		
		    // Kiểm tra nếu input rỗng
		    if (searchValue.trim() === "") {
		        // Hiển thị modal thông báo lỗi
		        showErrorModal("Vui lòng nhập giá trị cần tìm kiếm.");
		        return; // Dừng việc gửi form nếu input rỗng
		    }
		
		    // Tạo URL tìm kiếm
		    var url = "${pageContext.request.contextPath}/phong/list/" + encodeURIComponent(selectedColumn) + "/find/" + encodeURIComponent(searchValue);
		
		    // Cập nhật action của form
		    document.getElementById("searchForm").action = url;
		
		    // Submit form
		    document.getElementById("searchForm").submit();
		}





        
        //tạo maPhong từ maKhu và maSo
        function generateMaPhong() {
            var maKhu = document.getElementById("maKhu").value;
            var maSo = document.getElementById("maSo").value;
            var maPhong = maKhu + maSo;
            return maPhong;
        }

        //Hàm xử lý submit cho cả hai form
        function submitForm(formType) {
            event.preventDefault(); // Ngăn chặn hành động mặc định của sự kiện submit

            // Lấy dữ liệu từ form
            var maPhong, tinhTrang, sucChua, maLoaiPhong;
            if (formType === 'create') {
                maPhong = generateMaPhong();
                tinhTrang = document.getElementById("tinhTrang").value;
                sucChua = document.getElementById("sucChua").value;
                maLoaiPhong = document.getElementById("maLoaiPhong").value;
            } else if (formType === 'edit') {
                maPhong = document.getElementById("edit_maPhong").value;
                tinhTrang = document.getElementById("edit_tinhTrang").value;
                sucChua = document.getElementById("edit_sucChua").value;
                maLoaiPhong = document.getElementById("edit_maLoaiPhong").value;
            }

            // Kiểm tra dữ liệu
            if (maPhong === '' || tinhTrang === '' || sucChua === '' || maLoaiPhong === '') {
                showErrorModal("Vui lòng điền đầy đủ thông tin.");
                return false;
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

        // Hàm xử lý submit cho form thêm phòng
        function submitCreateForm(maPhong, tinhTrang, sucChua, maLoaiPhong) {
            // Kiểm tra mã phòng có tồn tại không
            $.ajax({
                type: "GET",
                url: "${pageContext.request.contextPath}/phong/invalidInput/" + maPhong,
                success: function(response) {
                    if (response === "OK") {
                        // Mã phòng hợp lệ, gửi biểu mẫu
                        document.querySelector("#createPhongForm form").submit();
                    } else {
                        showErrorModal("Mã phòng đã tồn tại, vui lòng chọn mã khác.");
                    }
                },
                error: function(xhr, status, error) {
                    showErrorModal("Đã xảy ra lỗi khi kiểm tra mã phòng.");
                }
            });
        }

        // Hàm xử lý submit cho form sửa thông tin phòng
        function submitEditForm(maPhong, tinhTrang, sucChua, maLoaiPhong) {
            // Gửi biểu mẫu sửa thông tin phòng
            document.querySelector("#editPhongForm form").submit();
        }

        //kiểm tra các liên kết của phòng đang muốn xóa
        function deletePhong(maPhong) {
            // Sử dụng Ajax để kiểm tra ràng buộc khóa ngoại trước khi xóa bản ghi
            $.ajax({
                type: "GET",
                url: "${pageContext.request.contextPath}/phong/check-foreign-key/" + maPhong,
                success: function(response) {
                    console.log("Response từ server:", response); // In ra giá trị response để kiểm tra
                    if (response === "OK") {
                        // Hiển thị modal xác nhận
                        showConfirmModal("Bạn có chắc chắn muốn xóa bản ghi này không?", function(confirmed) {
                            console.log("confirm từ server:", confirmed);
                            if (confirmed) {
                                // Nếu người dùng đã xác nhận, thực hiện chuyển hướng đến URL xóa bản ghi
                                window.location.href = "${pageContext.request.contextPath}/phong/delete-phong/" + maPhong;
                            }
                        });
                    } else {
                        // Nếu có tham chiếu, hiển thị modal báo lỗi
                        showErrorModal("Không thể xóa phòng này vì vẫn còn vật tư đang được sử dụng trong phòng.");
                    }
                },
                error: function(xhr, status, error) {
                    showErrorModal("Đã xảy ra lỗi khi kiểm tra ràng buộc khóa ngoại.");
                }
            });
        }


        // Kiểm tra nếu có errorMessage thì hiển thị modal
/* 
        function showErrorModal(errorMessage) {
            var modal = document.getElementById("errorModal");
            var modalContent = modal.querySelector("#errorMessage");
            modalContent.innerText = errorMessage;
            modal.style.display = "block";
        } */
        
        document.addEventListener('DOMContentLoaded', function() {
            var notiMessage = "${notiMessage}";
            var successMessage = "${successMessage}";

            if (notiMessage) {
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
