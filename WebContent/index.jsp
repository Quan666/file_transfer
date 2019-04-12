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
    <style type="text/css">
        .link_p{
            margin-top: 10px;
        }
    
    </style>
</head>
<body>
    
  <!-- 模态框 -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <!-- 模态框头部 -->
        <div class="modal-header">
          <h4 class="modal-title text-center">扫码下载</h4>
        </div>
   
        <!-- 模态框主体 -->
        <div class="modal-body">
            <center><div id="put"></div></center>
              <div class="input-group link_p">
			      <input type="text" id="foo" class="form-control" value="">
			      <span class="input-group-btn">
			        <button class="btn btn-default" type="button" data-clipboard-action="copy" data-clipboard-target="#foo">复制</button>
			      </span>
			 </div><!-- /input-group -->
        </div>
      </div>
    </div>
  </div>
    
    
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
            <td style="font-size: 16px;padding-top: 15px"><a href="#" data-toggle="modal" data-target="#myModal" class="qrcode" title="点击获取二维码"><%=ss %></a></td>
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
    <script type="text/javascript" src="./QRCode_files/qrcode.min.js"></script>
    <script src="./QRCode_files/modernizr-2.8.3.js" type="text/javascript"></script>
        <script src="js/clipboard.min.js"></script>
    <script type="text/javascript">
        //判断是否为网址并将其中的中文转码
        function ChangeUrl(str){
            var patt = new RegExp("http[s]?://");
            if(patt.test(str)){
                str=encodeURI(str);
            };
            return str;
        }
        var qrcode = new QRCode('put', {
            text: 'https://www.myelf.club',
            width: 180,
            height: 180,
            colorDark: '#000000',
            colorLight: '#ffffff',
            correctLevel: QRCode.CorrectLevel.H
        });
        $(document).ready(function(){
          $(".qrcode").click(function(){
            
            var a=$(this).text();
            var id=a;
            var link="https://pic.myelf.club/upload/"+a;
            $("#foo").attr("value",link);
            
            qrcode.clear();
            qrcode.makeCode(ChangeUrl(link));
          });
            
        });
    var clipboard = new Clipboard('.btn');

    clipboard.on('success', function(e) {
    	
        console.log(e);
    });

    clipboard.on('error', function(e) {
        console.log(e);
    }); 
    </script>
</body>
</html>