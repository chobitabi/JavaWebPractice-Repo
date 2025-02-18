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
		<p>
			<label for=""><input type="checkbox" name="rememberMe" value="true"/>ログイン情報を記憶する</label>
		</p>
		<input type="submit" value="ログイン" />
	</form>
</body>
</html>
