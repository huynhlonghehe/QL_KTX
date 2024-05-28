<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
	
	<base href="${pageContext.servletContext.contextPath}/">
  <meta charset="UTF-8" />
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport" />
  <title>Quản lý gửi xe &mdash; KTX</title>
  <link rel="icon" href="assets/sinhVien/assets/img/ptiticon.png" type="image/x-icon">

  <!-- General CSS Files -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
    integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous" />

  <!-- CSS Libraries -->
  <link href="assets/sinhVien/assets/js/tabulator-master/dist/css/tabulator.min.css" rel="stylesheet">
  <link href="assets/sinhVien/assets/js/tabulator-master/dist/css/bootstrap/tabulator_bootstrap4.min.css"
    rel="stylesheet">
  <script type="text/javascript" src="assets/sinhVien/assets/js/tabulator-master/dist/js/tabulator.min.js"></script>
  


  <!-- Template CSS -->
  	<link rel="stylesheet" href="assets/sinhVien/assets/css/style.css" />
    <link rel="stylesheet" href="assets/sinhVien/assets/css/components.css" />
    <link rel="stylesheet" href="assets/sinhVien/assets/css/custom.css" />


    <script src='assets/sinhVien/assets/js/page/fullcalendar/core/main.js'></script>
    <script src='assets/sinhVien/assets/js/page/fullcalendar/daygrid/main.js'></script>
</head>

<body class="sidebar-mini">
  <div id="app">
    <div class="main-wrapper">
      <div class="navbar-bg"></div>
      
      <%@include file="/WEB-INF/views/SinhVien/include/header.jsp"%>
        
        <%@include file="/WEB-INF/views/SinhVien/include/sidebar.jsp"%>

      <!-- Main Content -->
      <div class="main-content">
        <section class="section">
          <div class="section-header">
            <h1>Thông tin CSVC phòng</h1>
          </div>
          <div class="section-body">
            <div class="row">
              <div class="col-sm-12 col-md-12 col-xl-12">
                <div class="card">
                  <div class="card-header">
                    <h4>Danh sách cơ sở vật chất</h4>
                  </div>
                  <div class="card-body">
                    <div class="row">
                      <div class="col-12">
                        <span><h6>Tìm kiếm dữ liệu theo cột</h6></span>
                        <select id="filter-field">
                          <option></option>
                          <option value="facility_name">Tên CSVC</option>
                        </select>

                        <select id="filter-type">
                          <option value="like">like</option>
                          <option value="=">=</option>
                          <option value="<"><</option>
                          <option value="<="><=</option>
                          <option value=">">></option>
                          <option value=">=">>=</option>
                          <option value="!=">!=</option>
                        </select>

                        <input id="filter-value" type="text" placeholder="điền dữ liệu cần tìm">
                      </div>
                    </div>
                    <br>
                    <div id="facility-table"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
      </div>
      </section>
    </div>
    <div class="modal fade" id="student-table-modal" tabindex="-1" role="dialog" aria-labelledby="studentModal" aria-hidden="true">
      <div class="modal-dialog  modal-md modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="studentModal">Thông tin chi tiết</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="card">
              <div class="card author-box card-primary">
                <div class="card-body">
                  <div class="author-box-left">
                    <img alt="image" src="../assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">
                    <div class="clearfix"></div>
                  </div>
                  <div class="author-box-details">
                    <div class="author-box-name">
                      <form action="">
                        <div class="form-group">
                          <p><strong>Họ và tên: </strong></p>
                          <input type="text" class="form-control" value="" id="student-name">
                        </div>
                        <div class="form-group">
                          <p><strong>Phòng: </strong></p>
                          <input type="text" class="form-control" value="" id="student-room">
                        </div>
                        <div class="form-group">
                          <p><strong>Mã số sinh viên: </strong></p>
                          <input type="text" class="form-control" value="" id="student-id">
                        </div>
                        <div class="form-group">
                          <p><strong>Loại xe: </strong></p>
                          <input type="text" class="form-control" value="" id="type_of_vehicle">
                        </div>
                        <div class="form-group">
                          <p><strong>Biển xe: </strong></p>
                          <input type="text" class="form-control" value="" id="plate">
                        </div>
                        <div class="form-group">
                          <p><strong>Ngày đăng ký: </strong> </strong><span id="register_date"></span></p>
                        <p><strong>Ngày cập nhật: </strong><span id ="update_date"></span></p>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            <button type="button" class="btn btn-primary" id="change-student-button">Lưu lại</button>
          </div>
        </div>
      </div>
    </div>
  
    <div class="modal fade" id="add-student-table-modal" tabindex="-1" role="dialog" aria-labelledby="addStudentModal" aria-hidden="true">
      <div class="modal-dialog  modal-md modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="addStudentModal">Thêm sinh viên gửi xe</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="card">
              <div class="card card-primary">
                <div class="card-body">
                  <div class="">
                    <div class="e">
                      <form action="">
                        <div class="form-group">
                          <p><strong>Họ và tên: </strong></p>
                          <input type="text" class="form-control" value="" id="add-student-name">
                        </div>
                        <div class="form-group">
                          <p><strong>Phòng: </strong></p>
                          <input type="text" class="form-control" value="" id="add-student-room">
                        </div>
                        <div class="form-group">
                          <p><strong>Mã số sinh viên: </strong></p>
                          <input type="text" class="form-control" value="" id="add-student-id">
                        </div>
                        <div class="form-group">
                          <p><strong>Loại xe: </strong></p>
                          <input type="text" class="form-control" value="" id="add-type_of_vehicle">
                        </div>
                        <div class="form-group">
                          <p><strong>Biển xe: </strong></p>
                          <input type="text" class="form-control" value="" id="add-plate">
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            <button type="button" class="btn btn-primary" id="add-student-button">Thêm sinh viên</button>
          </div>
        </div>
      </div>
    </div>
  
    <footer class="main-footer">
      <div class="footer-left">
        Copyright &copy; 2018
        <div class="bullet"></div>
        Design By <a href="https://nauval.in/">Muhamad Nauval Azhar</a>
      </div>
      <div class="footer-right">
        2.3.0
      </div>
    </footer>
  </div>
  </div>

  
  <!-- General JS Scripts -->
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"
    integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.7.6/jquery.nicescroll.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
  <script src="assets/sinhVien/assets/js/stisla.js"></script>

  <!-- JS Libraies -->

  <!-- Template JS File -->
  <script src="assets/sinhVien/assets/js/scripts.js"></script>
  <script type="text/javascript" src="https://oss.sheetjs.com/sheetjs/xlsx.full.min.js"></script>

  <script src="assets/sinhVien/assets/js/co-so-vat-chat.js"></script>

  <!-- Page Specific JS File -->

</body>

</html>