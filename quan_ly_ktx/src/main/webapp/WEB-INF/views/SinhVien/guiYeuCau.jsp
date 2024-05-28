<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.servletContext.contextPath}/">
  <meta charset="UTF-8" />
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport" />
  <title>Gửi yêu cầu &mdash; KTX</title>
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
            <h1>Gửi yêu cầu</h1>
          </div>
          <div class="section-body">
            <div class="row">
              <div class="col-12">
                <div class="card">
                  <form id="form-gui-yeu-cau" action="" method="" class="needs-validation" novalidate="true">
                    <div class="card-header">
                      <h4>Form gửi phản hồi</h4>
                    </div>
                    <div class="card-body">
                      <div class="form-group">
                        <label>Tiêu đề</label>
                        <input id="title_yeu_cau" type="text" class="form-control" required="">
                        <div class="invalid-feedback">
                          Vui lòng điền tiêu đề của bạn.
                        </div>
                      </div>
                      <div class="form-group mb-0">
                        <label>Nội dung</label>
                        <textarea id="content_yeu_cau" class="form-control" required=""></textarea>
                        <div class="invalid-feedback">
                          Vui lòng điền nội dung yêu cầu của bạn.
                        </div>
                      </div>
                    </div>
                    <div class="card-footer text-right">
                      <div class="button">
                        <a id="submit-yeu-cau" href="#" class="btn btn-primary btn-md btn-icon icon-right" tabindex="4">Gửi yêu cầu</a>
                        <button type="reset" class="btn btn-secondary">Reset</button>
                      </div>
                      
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>

        <%-- <%@include file="/WEB-INF/views/SinhVien/include/footer.jsp"%> --%>
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
  <script src="assets/sinhVien/assets/js/gui-yeu-cau.js"></script>
  
  <!-- <script src="//cdn.ckeditor.com/4.21.0/standard/ckeditor.js"></script>
  <script>
    	CKEDITOR.replace('content_yeu_cau');
	</script> -->
	
	<script src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>
	<script>
    ClassicEditor
        .create( document.querySelector( '#content_yeu_cau' ) )
        .catch( error => {
            console.error( error );
        } );
</script>
	
</body>

</html>