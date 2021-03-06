<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="look.look"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="java.util.Iterator"%>
    <%@page import="config.*"%>
    <%@page import="file.*"%>
    <%@page import="java.text.SimpleDateFormat"%>
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
        #info{
        margin-bottom:5px;}
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
  
  
  
  <!-- 模态框（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
            <form action="" method="post" name="formhp" id="formhp">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" 
						aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="htmlh">
					
				</h4>
			</div>
			<div class="modal-body" id="htmlp">
				<p>设置密码：</p><center><input type="password" class="form-control" name="password" required="required" form="formhp" placeholder="请输入密码"></center>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" 
						data-dismiss="modal">取消
				</button>
				<button type="submit" class="btn-custom red" id="delbtn">
					确定
				</button>
			</div>
            </form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
  
  
  
  
  

        <%
        	config c=new config();
            c.config();
        %>
    
	<br><br>
<div class="container" style="min-width:450px;">
    <center>
    	<h3>临时文件中转</h3>
    	<p>文件上传后请尽快删除！本网站不保证任何文件的安全！</p>
    	<p>最大文件支持<%=c.max_size %>MB！</p>
    	<a href="https://www.myelf.club">@MY ELF</a>
    	<hr>
    </center>
    <br><div id="reinfo"><%if(null!=application.getAttribute("message")){%><%=application.getAttribute("message")%><%}application.setAttribute("message",null); %></div><br>
	<article>
		 <div class="row">
             <div class="col-md-12 upbox">
                 <form action="upfiles" method="post" enctype="multipart/form-data" class="text-center" id="upfiles" onSubmit="return check()">
                     <input type="file" name="files[]" id="demo-fileInput-4" multiple="multiple" required="required">
                     <button type="submit" class="btn-custom green" value="" name="password" onClick="check();">上传</button>
                     <input type="button" class="btn-custom orange" value="加密"  onClick="upfiles();">
                 </form>
             </div>
          </div>
	</article>
	<br>
	<div class="row">
		<form action="download" method="post" class="bs-example bs-example-form" onsubmit="return url();">
			<center id="info"><h3>离线下载</h3>(实验性功能,下载后名为“下载”,不支持文件夹下载,下载后请尽快删除！)</center>
				<div class="input-group col-xs-12">
					<input id="dowurl" type="text" class="form-control" name="dowlink"  required="required">
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit" value="下载" name="downame">
							下载
						</button>
					</span>
				</div><!-- /input-group -->
			</form>
	</div><!-- /.row -->
</div>

	
	<%look all=new look(); 
	ArrayList<file> str=new ArrayList<file>(); 
	str=all.look();
	Iterator<file> astr=str.iterator();
	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:MM");
	%>
	<div class="container table-responsive" style="min-width:450px;">      
      <table class="table table-hover table-condensed">
      <h2>文件列表</h2>
        <thead>
          <tr>
            <th width="60%">文件名</th>
            <th width="10%"></th>
            <th width="10%"></th>
            <th style="text-align: center" width="5%"></th>
            <th width="5%"></th>
          </tr>
        </thead>
        <tbody>
        <%while(astr.hasNext()){file ss=astr.next();%>
          <tr>
            <td style="font-size: 16px;padding-top: 12px"><a href="#" data-toggle="modal" data-target="#myModal" class="qrcode" title="点击获取二维码"><%=ss.name%></a></td>
            <td style="font-size: 12px;padding-top: 15px"><%=ss.size%></td>
            <td style="font-size: 12px;padding-top: 15px"><%=ft.format(ss.time)%></td>
            <td style="text-align: center"><a href="<%="../upload/"+ss.name%>" download style="text-align: center" class="btn-custom green">下载</a></td>
            <td style="text-align: center">
                <button  class="btn-custom red" style="text-align: center;" onclick="deles('<%=ss.name%>',<%=ss.password%>)" data-toggle="modal" data-target="#myModal2">删除</button>
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
    	function file_size() {
       	 	var f = document.querySelector("input[type=file]").files;
       	 
        	//上次修改时间
       		//alert(f[0].lastModifiedDate);
        	//名称
       	 	//alert(f[0].name);
        	//大小 字节
       		//alert(f[0].size);
        	var str="";
        	var tag=0;
        	for(var i=0;i<f.length;i++){
        		if(f[i].size>=<%=c.max_size%>*1024*1024){
        			str=str+f[i].name+" ,";
        			tag++;
        		}
        	}
        	str=str.substring(0,str.length-1);
        	
        	//类型
      		//alert(f[0].type);
        	if(tag>0){
    			var htmlp="<div class=\"alert alert-danger alert-dismissable\">\r\n" + 
				"	<button type=\"button\" class=\"close\" data-dismiss=\"alert\"\r\n" + 
				"			aria-hidden=\"true\">\r\n" + 
				"		&times;\r\n" + 
				"	</button>\r\n" + 
				"	<center>文件“"+str+"”超过<%=c.max_size%>MB!无法上传！</center>\r\n" + 
				"</div>";
    			document.getElementById("reinfo").innerHTML=htmlp;
    			htmlp="";
    			return false;
    		}
        	return true;
    	 }

    	function isPasswd(){  
    		var s=document.getElementById("pw").value;
    		var patrn=/^(\w){3,10}$/;  //3-10个字母、数字或下划线
    		if (!patrn.exec(s)) {
    			alert("密码不规范！请输入3-10个字母、数字或下划线！");
    			document.getElementById("pw").value="";
    			return false;
    		}
    		$('#myModal2').modal('hide');
    		document.getElementById("formhp").submit();
    		return true;
    		
    	}
        
        function deles(filename,ptag){
        	var htmlh="删除文件“"+filename+"”";
        	var htmlp="";
        	if(ptag==0){
        		htmlp="<center><h4 style=\"color:red\">确定永久删除文件？</h4></center>";
        	}
        	else {
        		htmlp="<input type=\"password\" class=\"form-control\" name=\"password\" required=\"required\" placeholder=\"请输入密码\">"
        	}
        	document.getElementById("htmlh").innerHTML=htmlh;
        	document.getElementById("htmlp").innerHTML=htmlp;
            document.getElementById("delbtn").value=filename;
            document.getElementById("delbtn").setAttribute('name','filename');
            document.formhp.action="delete";
            $('#myModal2').modal('hide');
        }
        
        function check(){
            if(document.querySelector("input[type=file]").files.length==0){
                alert("请选择文件！");
                return false;
            }
            if(!file_size())return false;
            //document.getElementById("upfiles").submit();
            
        }
        
        
        function upfiles(){
            if(document.querySelector("input[type=file]").files.length==0){
                alert("请选择文件！");
                return false;
            }
            if(!file_size())return false;
            else if(!document.querySelector("input[type=file]").files.length==0){
                var htmlh="上传文件:";
                var htmlp="";
                htmlp="<p>设置密码(3-10个字母、数字或下划线)：</p><center><input type=\"password\" onchange=\"isPasswd()\" id=\"pw\" class=\"form-control\" name=\"password\" required=\"required\" form=\"formhp\" placeholder=\"请输入密码\"></center>"
                document.getElementById("htmlh").innerHTML=htmlh;
                document.getElementById("htmlp").innerHTML=htmlp;
                document.getElementById("formhp").setAttribute('onsubmit',' return isPasswd();');
                document.formhp.setAttribute('enctype','multipart/form-data');
                document.getElementById("demo-fileInput-4").setAttribute('form','formhp');
                document.formhp.action="upfiles";
                $('#myModal2').modal('show');
            }
        }
        
        
      //判断是否为网址并将其中的中文转码
        function ChangeUrl(str){
            var patt = new RegExp("http[s]?://");
            if(patt.test(str)){
                str=encodeURI(str);
            };
            return str;
        }
        function url(){
            var url=document.getElementById("dowurl").value;
            url=ChangeUrl(url);
            document.getElementById("dowurl").value=url;
            return true;
        }
        var qrcode = new QRCode('put', {
            text: '<%=c.link%>',
            width: 180,
            height: 180,
            colorDark: '#000000',
            colorLight: '#ffffff',
            correctLevel: QRCode.CorrectLevel.H
        });
        $(document).ready(function(){
            
          $(".qrcode").click(function(){
            
            var a=$(this).text();
            var link="https://<%=c.link%>/<%=c.uppath%>/"+a;
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