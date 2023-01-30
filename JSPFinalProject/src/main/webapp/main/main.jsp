<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Template Name: Gravity
Author: <a href="https://www.os-templates.com/">OS Templates</a>
Author URI: https://www.os-templates.com/
Licence: Free to use under our free template licence terms
Licence URI: https://www.os-templates.com/template-terms
-->
<html>
<head>
<title>맛집&여행</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="../shadow/css/shadowbox.css">
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9965c727d3306713c47391be682e4be9&libraries=services"></script>
<link href="../css/layout.css" rel="stylesheet" type="text/css" media="all">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
<style type="text/css">
*{
   font-family: 'Gamja Flower', cursive;
}
</style>
<link href="../css/layout.css" rel="stylesheet" type="text/css" media="all">
</head>
<body id="top">
<jsp:include page="header.jsp"></jsp:include>
<!--  ############################Header############################### -->
<jsp:include page="${main_jsp }"></jsp:include>
<!--  ################################################################## -->
<jsp:include page="footer.jsp"></jsp:include>
<!--  ################################################################# -->
<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> 
<!-- JAVASCRIPTS --> 
<script src="../js/jquery.min.js"></script> 
<script src="../js/jquery.backtotop.js"></script> 
<script src="../js/jquery.mobilemenu.js"></script> 
<script src="../js/jquery.flexslider-min.js"></script>
</body>
</html>