<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="/WEB-INF/resources/css/includes/menu.css"%>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
<!-- Vertical menu -->
<!-- <div class="vertical-menu">
  <a href="http://localhost:8080/quan_ly_ktx/quanly/QLTaiKhoan">Quản lý tài khoản</a>
  <a href="http://localhost:8080/quan_ly_ktx/quanly/QLSinhVien">Quản lý sinh viên</a>
  <a href="#">Menu 3</a>
  <a href="#">Menu 4</a>
  <a href="#">Menu 5</a>
  <a href="#">Menu 6</a>
  
</div> -->

<div class="menu">
	<ul class="accordion-menu">
	  <li>
	    <div class="dropdownlink"><i class="fa-solid fa-user" style="color: #B197FC;"></i> Quản lý tài khoản
	      <i class="fa fa-chevron-down" aria-hidden="true"></i>
	    </div>
	    <ul class="submenuItems">
	      <li><a href="${pageContext.request.contextPath}/quanly/QLTaiKhoan">Thông tin tài khoản</a></li>
	      <li><a href="#">History book 2</a></li>
	      <li><a href="#">History book 3</a></li>
	    </ul>
	  </li>
	  <li>
	    <div class="dropdownlink"><i class="fa fa-paper-plane" aria-hidden="true"></i> Quản lý sinh viên
	      <i class="fa fa-chevron-down" aria-hidden="true"></i>
	    </div>
	    <ul class="submenuItems">
	      <li><a href="${pageContext.request.contextPath}/quanly/QLSinhVien">Thông tin sinh viên</a></li>
	      <li><a href="${pageContext.request.contextPath}/quanly/QuanLySinhVien/SinhVienViPham">Sinh viên vi phạm</a></li>
	      <li><a href="#">Thống kê sinh viên</a></li>
	    </ul>
	  </li>
	  <li>
	    <div class="dropdownlink"><i class="fa-solid fa-file-contract" aria-hidden="true"></i> Quản lý hợp đồng
	      <i class="fa fa-chevron-down" aria-hidden="true"></i>
	    </div>
	    <ul class="submenuItems">
	      <li><a href="${pageContext.request.contextPath}/quanly/QLHopDong">Thông tin hợp đồng</a></li>
	      <li><a href="#">Fantasy book 2</a></li>
	      <li><a href="#">Fantasy book 3</a></li>
	    </ul>
	  </li>
	  <li>
	    <div class="dropdownlink"><i class="fa-solid fa-screwdriver-wrench" aria-hidden="true"></i> Quản lý vật tư
	      <i class="fa fa-chevron-down" aria-hidden="true"></i>
	    </div>
	    <ul class="submenuItems">
	      <li><a href="${pageContext.request.contextPath}/vattu/list">Thông tin vật tư</a></li>
	      <li><a href="#">Fantasy book 2</a></li>
	      <li><a href="#">Fantasy book 3</a></li>
	    </ul>
	  </li>
	  <li>
	    <div class="dropdownlink"><i class="fa-solid fa-house" aria-hidden="true"></i> Quản lý phòng
	      <i class="fa fa-chevron-down" aria-hidden="true"></i>
	    </div>
	    <ul class="submenuItems">
	      <li><a href="${pageContext.request.contextPath}/phong/list">Thông tin phòng</a></li>
	      <li><a href="#">Action book 2</a></li>
	      <li><a href="#">Action book 3</a></li>
	    </ul>
	  </li>
	  	  <li>
	    <div class="dropdownlink"><i class="fa-solid fa-business-time" aria-hidden="true"></i> Quản lý vật tư phòng
	      <i class="fa fa-chevron-down" aria-hidden="true"></i>
	    </div>
	    <ul class="submenuItems">
	      <li><a href="${pageContext.request.contextPath}/vtphong/list">Thông tin vật tư - phòng</a></li>
	      <li><a href="#">Action book 2</a></li>
	      <li><a href="#">Action book 3</a></li>
	    </ul>
	  </li>
	</ul>
</div>



<script type="text/javascript">
$(function() {
	  var Accordion = function(el, multiple) {
	    this.el = el || {};
	    // more then one submenu open?
	    this.multiple = multiple || false;
	    
	    var dropdownlink = this.el.find('.dropdownlink');
	    dropdownlink.on('click',
	                    { el: this.el, multiple: this.multiple },
	                    this.dropdown);
	  };
	  
	  Accordion.prototype.dropdown = function(e) {
	    var $el = e.data.el,
	        $this = $(this),
	        //this is the ul.submenuItems
	        $next = $this.next();
	    
	    $next.slideToggle();
	    $this.parent().toggleClass('open');
	    
	    if(!e.data.multiple) {
	      //show only one menu at the same time
	      $el.find('.submenuItems').not($next).slideUp().parent().removeClass('open');
	    }
	  }
	  
	  var accordion = new Accordion($('.accordion-menu'), false);
	})
</script>