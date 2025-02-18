<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>クッキーの取得</title>
</head>
<body>
<h1>クッキーの取得</h1>
<% 
Cookie[] cs = request.getCookies();
String user = null;
if (cs != null) {
	for (Cookie c : cs) {
		if (c.getName().equals("user")) {
			out.println("<p>ユーザー名：" + c.getValue() + "</p>");
		}
	}
}
%>
</body>
</html>
