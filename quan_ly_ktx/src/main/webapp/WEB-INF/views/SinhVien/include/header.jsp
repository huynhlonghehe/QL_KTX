<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg main-navbar">
          <form class="form-inline mr-auto">
            <ul class="navbar-nav mr-3">
              <li>
                <!-- <a href="/quan_ly_ktx/sinhvien/index" data-toggle="sidebar" class="nav-link nav-link-lg"
                  ><i class="fas fa-bars"></i
                ></a> -->
                <i data-toggle="sidebar" class="fas fa-bars"></i>
              </li>
              <li>
                <a
                  href="#"
                  data-toggle="search"
                  class="nav-link nav-link-lg d-sm-none"
                  ><i class="fas fa-search"></i
                ></a>
              </li>
            </ul>
            <div class="search-element"></div>
          </form>
          <ul class="navbar-nav navbar-right">
            <li class="dropdown">
              <a
                href="#"
                data-toggle="dropdown"
                class="nav-link dropdown-toggle nav-link-lg nav-link-user"
              >
                <img
                  alt="image"
                  src="assets/sinhVien/assets/img/avatar/avatar-1.png"
                  class="rounded-circle mr-1"
                />
                <div class="d-sm-none d-lg-inline-block">
                  Lục<span class ="student-name"></span>
                </div>
              <div class="dropdown-menu dropdown-menu-right">
                <div class="dropdown-title">Hành động</div>
                <a href="" id="logout" class="dropdown-item has-icon text-danger">
                  <i  class="fas fa-sign-out-alt"></i> Đăng xuất
                </a>
              </div>
            </li>
          </ul>
        </nav>