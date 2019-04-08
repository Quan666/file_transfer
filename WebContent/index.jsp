<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="look.look"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="java.util.Iterator"%>
<!doctype html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>文件上传</title>
	<link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/default.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.filer.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.filer-dragdropbox-theme.css">
    <link rel="stylesheet" type="text/css" href="css/tomorrow.css">
    <link rel="stylesheet" type="text/css" href="css/custom.css">
	<!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
	<br><br>
    <center>
    	<h3>临时文件中转</h3>
    	<p>文件上传后请尽快删除！本网站不保证任何文件的安全！</p>
    	<p>最大文件支持200MB！</p>
    	<a href="https://www.myelf.club">@MY ELF</a>
    	<hr>
    </center>
    <br><br>
	<article class="container">
		 <div class="row">
             <div class="col-md-12 upbox">
                 <form action="up" method="post" enctype="multipart/form-data" class="text-center">
                     <input type="file" name="files[]" id="demo-fileInput-4" multiple="multiple">
                     <input type="submit" class="btn-custom green" value="上传">
                 </form>
             </div>
          </div>
	</article>
	<%look all=new look(); 
	ArrayList<String> str=new ArrayList<String>(); 
	str=all.look();
	Iterator<String> astr=str.iterator();
	%>
	<div class="container">      
      <table class="table table-hover">
      <h2>文件列表</h2>
        <thead>
          <tr>
            <th width="90%">文件名</th>
            <th style="text-align: center" width="5%"></th>
            <th width="5%"></th>
          </tr>
        </thead>
        <tbody>
        <%while(astr.hasNext()){String ss=astr.next();%>
          <tr>
            <td style="font-size: 16px;padding-top: 15px"><%=ss %></td>
            <td style="text-align: center"><a href="<%="../upload/"+ss%>" download style="text-align: center" class="btn-custom green">下载</a></td>
            <td style="text-align: center">
            	<form action="delete" method="post" class="text-center">
                <button type="submit" name="filename" class="btn-custom red" style="text-align: center" value="<%=ss%>">删除</button>
                </form>
            </td>
          </tr>
          <%} %>
        </tbody>
      </table>
    </div>
	<script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
	<script>window.jQuery || document.write('<script src="js/jquery-2.1.1.min.js"><\/script>')</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/jquery.filer.min.js" type="text/javascript"></script>
    <script src="js/prettify.js" type="text/javascript"></script>
    <script src="js/scripts.js" type="text/javascript"></script>
    <script src="js/custom.js" type="text/javascript"></script>
</body>
</html>