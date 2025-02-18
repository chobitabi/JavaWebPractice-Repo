<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<p>${message}</p>
	<form action="login" method="post">
		<p>
			ユーザー名： <input type="text" name="username" />
		</p>
		<p>
			パスワード： <input type="password" name="password" />
		</p>
		<input type="submit" value="ログイン" />
	</form>
</body>
</html>
