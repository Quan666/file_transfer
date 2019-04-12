<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
  <h2>安装</h2>
  <form>
    <div class="form-group">
      <label for="uppath">上传目录*：</label>
      <input type="text" class="form-control" id="uppath" placeholder="请输入绝对地址！！！">
    </div>
    <div class="form-group">
      <label for="pwd">回收站地址*：</label>
      <input type="text" class="form-control" id="repath" placeholder="请输入绝对地址！！！" >
    </div>
    <div class="form-check">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox"> Remember me
      </label>
    </div>
    <button type="submit" class="btn btn-primary">提交</button>
  </form>
</div>
</body>
</html>