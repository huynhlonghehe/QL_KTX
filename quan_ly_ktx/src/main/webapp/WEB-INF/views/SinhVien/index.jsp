<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<base href="${pageContext.servletContext.contextPath}/">

 <meta charset="UTF-8" />
    <meta
      content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no"
      name="viewport"
    />
    <title>Thông tin &mdash; KTX</title>
    
    <link rel="icon" href="assets/sinhVien/assets/img/ptiticon.png" type="image/x-icon">
    <!-- General CSS Files -->
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
      crossorigin="anonymous"
    />

   
    <link  rel='stylesheet' href="assets/sinhVien/assets/js/page/fullcalendar/core/main.css"/>
    <link rel='stylesheet' href="assets/sinhVien/assets/js/page/fullcalendar/daygrid/main.css"  />
    
	<!-- Template CSS -->
    <link rel="stylesheet" href="assets/sinhVien/assets/css/style.css" />
    <link rel="stylesheet" href="assets/sinhVien/assets/css/components.css" />
    <link rel="stylesheet" href="assets/sinhVien/assets/css/custom.css" />
    <link rel="stylesheet" href="assets/sinhVien/assets/css/customCalendar.css" />

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
              <h1>Thông tin</h1>
            </div>
            <div class="section-body">
              <div class="row">
                <div class="col-12 col-md-12 col-lg-7">
                  <div class="card">
                    <div class="card-header">
                      <h4>Thông tin sinh viên</h4>
                      <div class="card-header-action">
                    </div>
                    </div>
                  <div class="card-body">
                    <div class="card author-box card-primary">
                      <div class="card-body">
                        <div class="author-box-left">
                         
                           <img alt="image" src="assets/sinhVien/assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">                        
                        
                          
                          <div class="clearfix"></div>
                        </div>
                        <div class="author-box-details">
                          <div class="">
                            <p><strong>Họ và tên: </strong><span class="student-name"></span></p>
                            <p><strong>Mã số sinh viên: </strong><span id="student-id"></span></p>
                            <p><strong>Ngày sinh: </strong><span id="student-birth"></span></p>
                            <p><strong>Địa chỉ: </strong><span id="student-address"></span></p>
                            <p><strong>Thông tin liên lạc: </strong><span id="student-contact"></span></p>
                            <p><strong>Ngày đăng ký: </strong> </strong><span id="student-register-date"></span></p>
                            <p><strong>Ngày cập nhật: </strong> </strong><span id="student-update-date"></span></p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  </div>
                  <div class="card">
                    <div class="card-header">
                      <h4>Phòng trọ</h4>
                      <div class="card-header-action">
                        <a href="sinhvien/phong-tro" class="btn btn-primary btn-round">Chi tiết...</a>
                    </div>
                    </div>
                  <div class="card-body">
                    <div class="card author-box">
                      <div class="card-body">
                        <div class="" id="info-description">
                          <div class="">
                            <p><strong>Phòng trọ: </strong><span id="student-room"></span></p>
                            <p><strong>Tiền trọ: </strong><span id="room-fee"></span>đ</p>
                            <p><strong>Hạn đóng tiền: </strong><span id="due-date">Mùng 6 hàng tháng</span></p>
                            <p><strong>Trạng thái: </strong><span id="fee-status"></span></p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  </div>
                </div>
                <div class="col-12 col-md-12 col-lg-5">
                  <div class="card">
                    <div class="card-header">
                      <h4>Lịch</h4>
                    </div>
                    <div class="card-body">
                      <div class="fc-overflow">
                        <div id="calendar" class="fc-overflow"></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
                  
              </div>
            </div>
          </section>
        </div>
        
        <%@include file="/WEB-INF/views/SinhVien/include/footer.jsp"%>
        
      </div>
    </div>

    <!-- General JS Scripts -->
    <script
      src="https://code.jquery.com/jquery-3.3.1.min.js"
      integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.7.6/jquery.nicescroll.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <script src="assets/sinhVien/assets/js/stisla.js"></script>

    <!-- JS Libraies -->

    <!-- Template JS File -->
    
    <script src="assets/sinhVien/assets/js/scripts.js"></script>
    <script src="assets/sinhVien/assets/js/calendar.js"></script>


    <!-- Page Specific JS File -->
  </body>
</html>


