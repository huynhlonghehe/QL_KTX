<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.servletContext.contextPath}/">
  <meta charset="UTF-8" />
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport" />
  <title>Cán vụ KTX &mdash; KTX</title>
  <link rel="icon" href="assets/sinhVien/assets/img/ptiticon.png" type="image/x-icon">

  <!-- General CSS Files -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
    integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous" />

  <!-- CSS Libraries -->

  <!-- Template CSS -->
  <link rel="stylesheet" href="assets/sinhVien/assets/css/style.css" />
  <link rel="stylesheet" href="assets/sinhVien/assets/css/components.css" />
  <link rel="stylesheet" href="assets/sinhVien/assets/css/custom.css" />
  <link rel="stylesheet" href="assets/sinhVien/assets/css/customCalendar.css" />


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
            <h1>Danh sách cán vụ KTX</h1>
          </div>
          <div class="section-body">
            <div class="row">
              <div class="col-12 ">
                <div class="card">
                  <div class="card-header">
                    <h4>Lãnh đạo đơn vị</h4>
                  </div>
                  <div class="card-body row">
                    <div class="card author-box card-primary col-sm-12 col-md-12 col-lg-6 col-xl-4">
                      <div class="card-body">
                        <div class="author-box-left">
                          <img alt="image" src="assets/sinhVien/assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">
                          <div class="clearfix"></div>
                        </div>
                        <div class="author-box-details">
                          <div class="author-box-name">
                            <a href="#">Nguyễn Văn A</a>
                          </div>
                          <div class="author-box-job">Trưởng Ban</div>
                          <div class="author-box-description">
                            <span><strong>Phòng: </strong>203 - B3</span>
                            <br>
                            <span><strong>Email: </strong>daylamotcaimail@hust.edu.vn</span>
                            <br>
                            <span><strong>SĐT: </strong>0382212381</span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="card author-box card-primary col-sm-12 col-md-12 col-lg-6 col-xl-4">
                      <div class="card-body">
                        <div class="author-box-left">
                          <img alt="image" src="assets/sinhVien/assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">
                          <div class="clearfix"></div>
                        </div>
                        <div class="author-box-details">
                          <div class="author-box-name">
                            <a href="#">Nguyễn Văn B</a>
                          </div>
                          <div class="author-box-job">Phó Ban</div>
                          <div class="author-box-description">
                            <span><strong>Phòng: </strong>201 - B3</span>
                            <br>
                            <span><strong>Email: </strong>daylamotcaimail5@hust.edu.vn</span>
                            <br>
                            <span><strong>SĐT: </strong>0382214432</span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="card author-box card-primary col-sm-12 col-md-12 col-lg-6 col-xl-4">
                      <div class="card-body">
                        <div class="author-box-left">
                          <img alt="image" src="assets/sinhVien/assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">
                          <div class="clearfix"></div>
                        </div>
                        <div class="author-box-details">
                          <div class="author-box-name">
                            <a href="#">Nguyễn Văn C</a>
                          </div>
                          <div class="author-box-job">Phó Ban</div>
                          <div class="author-box-description">
                            <span><strong>Phòng: </strong>201 - B3</span>
                            <br>
                            <span><strong>Email: </strong>daylamotcaimail1@hust.edu.vn</span>
                            <br>
                            <span><strong>SĐT: </strong>038222233333</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <br>
                </div>
                <!-- <div class="card">
                  <div class="card-header">
                    <h4>Tổ hành chính</h4>
                  </div>
                  <div class="card-body row">
                    <div class="card author-box card-primary col-sm-12 col-md-12 col-lg-6 col-xl-4">
                      <div class="card-body">
                        <div class="author-box-left">
                          <img alt="image" src="assets/sinhVien/assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">
                          <div class="clearfix"></div>
                        </div>
                        <div class="author-box-details">
                          <div class="author-box-name">
                            <a href="#">Trần Quang A</a>
                          </div>
                          <div class="author-box-job">Trưởng Ban</div>
                          <div class="author-box-description">
                            <span><strong>Phòng: </strong>202 - B3</span>
                            <br>
                            <span><strong>Email: </strong>daylamotcaimail@hust.edu.vn</span>
                            <br>
                            <span><strong>SĐT: </strong>0333221234</span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="card author-box card-primary col-sm-12 col-md-12 col-lg-6 col-xl-4">
                      <div class="card-body">
                        <div class="author-box-left">
                          <img alt="image" src="assets/sinhVien/assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">
                          <div class="clearfix"></div>
                        </div>
                        <div class="author-box-details">
                          <div class="author-box-name">
                            <a href="#">Phạm Văn A</a>
                          </div>
                          <div class="author-box-job">Phó Ban</div>
                          <div class="author-box-description">
                            <span><strong>Phòng: </strong>202 - B3</span>
                            <br>
                            <span><strong>Email: </strong>daylamotcaimail5@hust.edu.vn</span>
                            <br>
                            <span><strong>SĐT: </strong>038998132</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <br>
                </div>
                <div class="card">
                  <div class="card-header">
                    <h4>Tổ vệ sinh môi trường</h4>
                  </div>
                  <div class="card-body row">
                    <div class="card author-box card-primary col-sm-12 col-md-12 col-lg-6 col-xl-4">
                      <div class="card-body">
                        <div class="author-box-left">
                          <img alt="image" src="assets/sinhVien/assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">
                          <div class="clearfix"></div>
                        </div>
                        <div class="author-box-details">
                          <div class="author-box-name">
                            <a href="#">Nguyễn Quang A</a>
                          </div>
                          <div class="author-box-job">Trưởng Ban</div>
                          <div class="author-box-description">
                            <span><strong>Phòng: </strong>205 - B3</span>
                            <br>
                            <span><strong>Email: </strong>daylamotcaimail@hust.edu.vn</span>
                            <br>
                            <span><strong>SĐT: </strong>0333221234</span>
                          </div>
                          <div class="w-100 d-sm-none"></div>
                        </div>
                      </div>
                    </div>
                    <div class="card author-box card-primary col-sm-12 col-md-12 col-lg-6 col-xl-4">
                      <div class="card-body">
                        <div class="author-box-left">
                          <img alt="image" src="assets/sinhVien/assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">
                          <div class="clearfix"></div>
                        </div>
                        <div class="author-box-details">
                          <div class="author-box-name">
                            <a href="#">Phạm Văn D</a>
                          </div>
                          <div class="author-box-job">Phó Ban</div>
                          <div class="author-box-description">
                            <span><strong>Phòng: </strong>205 - B3</span>
                            <br>
                            <span><strong>Email: </strong>daylamotcaimail5@hust.edu.vn</span>
                            <br>
                            <span><strong>SĐT: </strong>038998132</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <br>
                </div> -->
                <div class="card">
                  <div class="card-header">
                    <h4>Tổ bảo vệ</h4>
                  </div>
                  <div class="card-body row">
                    <div class="card author-box card-primary col-sm-12 col-md-12 col-lg-6 col-xl-4">
                      <div class="card-body">
                        <div class="author-box-left">
                          <img alt="image" src="assets/sinhVien/assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">
                          <div class="clearfix"></div>
                        </div>
                        <div class="author-box-details">
                          <div class="author-box-name">
                            <a href="#">Nguyễn Thị V</a>
                          </div>
                          <div class="author-box-job">Trưởng Ban</div>
                          <div class="author-box-description">
                            <span><strong>Phòng: </strong>206 - B3</span>
                            <br>
                            <span><strong>Email: </strong>daylamotcaimail@hust.edu.vn</span>
                            <br>
                            <span><strong>SĐT: </strong>0333221234</span>
                          </div>
                          <div class="w-100 d-sm-none"></div>
                        </div>
                      </div>
                    </div>
                    <div class="card author-box card-primary col-sm-12 col-md-12 col-lg-6 col-xl-4">
                      <div class="card-body">
                        <div class="author-box-left">
                          <img alt="image" src="assets/sinhVien/assets/img/avatar/avatar-1.png" class="rounded-circle author-box-picture">
                          <div class="clearfix"></div>
                        </div>
                        <div class="author-box-details">
                          <div class="author-box-name">
                            <a href="#">Mai Văn A</a>
                          </div>
                          <div class="author-box-job">Phó Ban</div>
                          <div class="author-box-description">
                            <span><strong>Phòng: </strong>206 - B3</span>
                            <br>
                            <span><strong>Email: </strong>daylamotcaimail5@hust.edu.vn</span>
                            <br>
                            <span><strong>SĐT: </strong>038998132</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <br>
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


  <!-- Page Specific JS File -->
</body>

</html>