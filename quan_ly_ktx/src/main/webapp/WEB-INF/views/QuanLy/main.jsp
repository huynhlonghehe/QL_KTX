<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<style>
    <%@include file="/WEB-INF/resources/css/QuanLy_CSS/main_quanLy.css"%>
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        $(".func").click(function() {
            $(".menu").toggleClass("collapsed"); // Thêm hoặc loại bỏ class "collapsed" để điều chỉnh kích thước
        });
    });
</script>

</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>



</body>
</html>
