<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ファイルアップロード</title>
</head>
<body>
	<h1>ファイルアップロード</h1>

	<p>画像ファイルをアップロードしてください</p>
	<form action="" method="post" enctype="multipart/form-data">
		<p>
			ファイル：<input type="file" name="upfile" />
		</p>
		<p>
			<input type="submit" value="アップロード">
		</p>
	</form>

	<h2>画像一覧</h2>
	<c:forEach var="file" items="${fileList}">
	<img src="uploads/<c:out value="${file.name}" />" height="200">
	</c:forEach>
</body>
</html>