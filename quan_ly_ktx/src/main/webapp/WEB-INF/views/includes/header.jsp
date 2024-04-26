<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="/WEB-INF/resources/css/includes/header.css"%>
</style>
<div id="header">
    <ul id="nav">
        <li class="ptit"><a href="">Quản lý kí túc xá</a></li>
        <li class="func" id="toggleMenu"><a href="#">Chức năng</a></li>
        <li class="user">
            <a href="">Xin chào, ${username}!</a>
            <ul class="subnav">
                <li><a href="">Chức năng 1</a></li>
                <li><a href="">Chức năng 2</a></li>
                <li><a href="">Chức năng 3</a></li>
            </ul>
        </li>
    </ul>
</div>
