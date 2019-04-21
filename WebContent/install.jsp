<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>安装</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h2>安装</h2>
  <form action="install" method="post">
    <div class="form-group">
      <label for="uppath">上传目录*：</label>
      <input type="text" class="form-control" name="uppath" value="upload" placeholder="如：upload" required="required">
    </div>
    <div class="form-group">
      <label for="max_size">上传文件大小(MB)*：</label>
      <input type="number" class="form-control" name="max_size" value="200" placeholder="如：200" required="required">
    </div>
    <div class="form-group">
      <label for="pwd">输入域名*：</label>
      <input type="text" class="form-control" name="link" placeholder="如：www.myelf.club" required="required">
    </div>
    <div class="form-group">
      <label for="pwd">管理员账号*：</label>
      <input type="text" class="form-control" name="admin" placeholder="请输入字母或数字！！！" required="required">
    </div>
    <div class="form-group">
      <label for="pwd">密码*：</label>
      <input type="password" class="form-control" name="password" placeholder="请输入字母或数字！！！" required="required">
    </div>
    <div class="form-check">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" required="required">readme
      </label>
    </div>
    <button type="submit" class="btn btn-primary">提交</button>
  </form>
</div>


<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>