<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員管理</title>
</head>
<body>
	<h1>会員一覧</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>名前</th>
			<th>年齢</th>
			<th>住所</th>
			<th>会員種別</th>
			<th>登録日</th>
		</tr>
		<c:forEach items="${memberList}" var="member">
			<tr>
				<td><c:out value="${member.id}" /></td>
				<td><c:out value="${member.name}" /></td>
				<td><c:out value="${member.age}" /></td>
				<td><c:out value="${member.address}" /></td>
				<td><c:out value="${member.typeName}" /></td>
				<td><c:out value="${member.created}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>