<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アップロード完了</title>
</head>
<body>
	<h1>アップロード完了</h1>
	<p>以下のファイルをアップロードしました</p>
	<table border="1">
		<tr>
			<th>ファイルサイズ</th>
			<td><fmt:formatNumber value="${fileSize}" />byte</td>
		</tr>
		<tr>
			<th>ファイルの種類</th>
			<td><c:out value="${fileType}" /></td>
		</tr>
		<tr>
			<th>ファイル名</th>
			<td><c:out value="${fileName}" /></td>
		</tr>
	</table>
	<p>
		<a href="upload">戻る</a>
	</p>
</body>
</html>