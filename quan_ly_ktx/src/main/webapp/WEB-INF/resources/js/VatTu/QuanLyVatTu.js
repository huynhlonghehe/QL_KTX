function toggleCreateVatTuForm() {
    var form = document.getElementById('createVatTuForm');
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

            window.location.href = newHref;  // Chuyển hướng người dùng
        });
    });
});



function validateAndSubmitForm() {
    // Ngăn chặn hành động mặc định của sự kiện submit
    event.preventDefault();

    // Kiểm tra các điều kiện cần thiết trước khi gửi biểu mẫu
    var maSo = document.getElementById("maSo").value;
    var tenVT = document.getElementById("tenVT").value;
    var giaTien = document.getElementById("giaTien").value;

    if (maSo === '' || tenVT === '' || giaTien === '') {
        showErrorModal("Vui lòng điền đầy đủ thông tin.");
        return false; // Ngăn chặn việc gửi biểu mẫu nếu có lỗi
    }
    
    // Sử dụng Ajax để kiểm tra mã vật tư tồn tại hay không
    $.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/vattu/invalidInput/" + maSo,
        success: function(response) {
            // Nếu mã vật tư không tồn tại, gửi biểu mẫu
            document.querySelector("#createVatTuForm form").submit();
        },
        error: function(xhr, status, error) {
            // Hiển thị thông báo lỗi nếu mã vật tư đã tồn tại
            showErrorModal("Mã vật tư đã tồn tại, vui lòng chọn mã khác.");
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

// Ẩn cửa sổ modal khi người dùng nhấp vào nút đóng
document.getElementById("errorModal").querySelector(".close").onclick = function() {
    var modal = document.getElementById("errorModal");
    modal.style.display = "none";
}

// Đóng cửa sổ modal khi người dùng nhấp vào bất kỳ đâu ngoài cửa sổ modal
window.onclick = function(event) {
    var modal = document.getElementById("errorModal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
}