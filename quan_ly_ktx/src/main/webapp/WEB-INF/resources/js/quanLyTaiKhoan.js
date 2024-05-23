/**
 * 
 */

 	function toggleCreateAccountForm(event) {
	    var form = document.getElementById('createAccountForm');
	    if (form.style.display === 'none' || form.style.display === '') {
	        form.style.display = 'block';
	    } else {
	        form.style.display = 'none';
	    }
	}
	
	document.addEventListener('DOMContentLoaded', function() {
	    var form = document.getElementById('createAccountForm');
	    form.addEventListener('click', function(event) {
	        event.stopPropagation(); // Ngăn chặn sự kiện lan truyền lên cấp cha
	    });
	});
	
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
	                confirmButtonText: 'Xác nhận xoá!',
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


