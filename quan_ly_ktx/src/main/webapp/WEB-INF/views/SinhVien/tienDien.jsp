<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.servletContext.contextPath}/">
  <meta charset="UTF-8" />
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport" />
  <title>Tiền điện &mdash; KTX</title>
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
  <link href='assets/sinhVien/assets/js/page/fullcalendar/core/main.css' rel='stylesheet' />
  <link href='assets/sinhVien/assets/js/page/fullcalendar/daygrid/main.css' rel='stylesheet' />


  <!-- Template CSS -->
  <link rel="stylesheet" href="assets/sinhVien/assets/css/style.css" />
  <link rel="stylesheet" href="assets/sinhVien/assets/css/components.css" />
  <link rel="stylesheet" href="assets/sinhVien/assets/css/customCalendar.css" />
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
            <h1>Theo dõi tiền trọ</h1>
          </div>
          <div class="section-body">
            <div class="row">
              <div class="col-12 col-lg-12 col-xl-5">
                <div class="card">
                  <div class="card-header">
                    <h4>Phòng trọ</h4>
                  </div>
                  <div class="card-body">
                    <div class="card">
                      <div class="card-body">
                        <div class="author-box-details" id="info-description">
                          <div class="author-box-name">
                            <p><strong>Phòng trọ: </strong><span id="student-room"></span></p>
                            <p><strong>Tiền trọ: </strong><span id="room-fee"></span>đ</p>
                            <p><strong>Hạn đóng tiền: </strong><span id="due-date">Mùng 1 hàng tháng</span></p>
                            <p><strong>Trạng thái: </strong><span id="fee-status"></span></p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                
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
              <div class="col-12 col-lg-12 col-xl-7">
                <!-- <div class="card">
                  <div class="card-header">
                    <h4>Các khoản phí trong tháng</h4>
                  </div>
                  <div class="card-body">
                    <div class="card">
                      <div class="card-body">
                        <div class="author-box-details" id="info-description">
                          <div class="author-box-name">
                            <h5>1. Phí lưu trú</h5>
                            <p><strong>- Phí lưu trú theo quy định Hà Nội: </strong><span
                                id="phi-luu-tru-qd">85,000</span>đ</p>
                            <p><strong>- Phí tiền nước: </strong><span id="phi-tien-nuoc">25,000</span>đ</p>
                            <p><strong>- Phí tiền điện: </strong><span id="phi-tien-dien">100,000</span>đ</p>
                            <p><strong>- Phí tiền internet: </strong><span id="phi-internet">50,000</span>đ</p>
                            <br>
                            <h5>2. Phí giữ xe</h5>
                            <p><strong>- Xe máy: </strong><span id="phi-giu-xe-may">30,000</span>đ</p>
                            <p><strong>- Xe đạp: </strong><span id="phi-giu-xe-dap">30,000</span>đ</p>
                            <br>
                            <hr>
                            <h5>TỔNG: <span id="tong-phi-luu-tru">290,000</span>đ</h5>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div> -->
                <div class="card">
                  <div class="card-header">
                    <h4>Lịch sử thanh toán tiền trọ</h4>
                  </div>
                  <div class="card-body">
                      <div class="row">
                        <div class="col-8">
                          <span>
                            <h6>Tìm kiếm dữ liệu theo cột</h6>
                          </span>
                          <select id="filter-field">
                            <option></option>
                            <option value="month">Tháng</option>
                            <option value="year">Năm</option>
                            <option value="status">Tình trạng nộp phí</option>
                          </select>

                          <select id="filter-type">
                          <option value="like">like</option>>
                          <option value="=">=</option>
                          <option value="<"><</option>
                          <option value="<="><=</option>
                          <option value=">">></option>
                          <option value=">=">>=</option>
                          <option value="!=">!=</option>
                          </select>

                          <input id="filter-value" type="text" placeholder="điền dữ liệu cần tìm">
                        </div>
                        <div class="col-4">
                         
                        </div>
                      </div>
                      <br>
                    <div id="table"></div>
                  </div>
                </div>
              </div>
            </div>

          </div>
      </div>
      </section>
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
  <script src="assets/sinhVien/assets/js/calendar.js"></script>
  <script src="assets/sinhVien/assets/js/theo-doi-tien-tro.js"></script>
  <!-- Page Specific JS File -->
</body>

</html>