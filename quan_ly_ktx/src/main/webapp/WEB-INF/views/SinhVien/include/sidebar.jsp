<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="main-sidebar">
          <aside id="sidebar-wrapper">
            <div class="sidebar-brand">
              <a href="sinhvien/index">Ký túc xá HVCS</a>
            </div>
            <div class="sidebar-brand sidebar-brand-sm">
              <a href="sinhvien/index">KTX</a>
            </div>
            <ul class="sidebar-menu">
              <li class="menu-header">Tổng quan</li>
              <li class="nav-item dropdown active">
                <a href="sinhvien/index" class="nav-link"
                  ><i class="fas fa-info"></i><span>Thông tin</span></a
                >
              </li>
              <li class="nav-item dropdown">
                <a href="sinhvien/thong-bao" class="nav-link"
                  ><i class="fas fa-file-alt"></i><span>Thông báo</span></a
                >
              </li>
              <li class="menu-header">Thông tin phòng trọ</li>
              <li class="nav-item dropdown">
                <a href="sinhvien/phong-tro" class="nav-link"
                  ><i class="fas fa-user-friends"></i> <span>Phòng trọ</span></a
                >
              </li>
              <li class="nav-item dropdown">
                <a href="sinhvien/csvc" class="nav-link"
                  ><i class="fas fa-couch"></i> <span>Cơ sở vật chất</span></a
                >
              </li>
              <li class="nav-item dropdown">
                <a href="sinhvien/tien-dien" class="nav-link"
                  ><i class="fas fa-dollar-sign"></i> <span>Theo dõi tiền điện</span></a
                >
              </li>
              <li class="menu-header">Hỗ trợ</li>
              <li class="nav-item dropdown">
                <a href="sinhvien/gui-yeu-cau" class="nav-link"
                  ><i class="fas fa-paper-plane"></i> <span>Gửi yêu cầu</span></a
                >
              </li>
              <li class="nav-item dropdown">
                <a href="sinhvien/yeu-cau-da-gui" class="nav-link"
                  ><i class="far fa-file-alt"></i> <span>Yêu cầu đã gửi</span></a
                >
              </li>
              <li class="menu-header">Khác</li>
              <li class="nav-item dropdown">
                <a href="sinhvien/thong-tin-can-vu" class="nav-link"
                  ><i class="far fa-user"></i> <span>Thông tin cán vụ</span></a
                >
              </li>
          </aside>
        </div>
        
        
<!--     <script>
  var path = window.location.pathname;
  var page = path.split("/").pop();
  var links = document.querySelectorAll(".nav-item dropdown");

  for (var i = 0; i < links.length; i++) {
    if (links[i].getAttribute("href").indexOf(page) !== -1) {
      links[i].classList.add("active");
    }
  }
</script> -->