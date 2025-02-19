<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訪問回数の表示</title>
</head>
<body>
	<h2>あなたの訪問回数は${visitCount}回目です！</h2>

	<form action="visit" method="post">
		<input type="submit" value="リセット" />
	</form>
</body>
</html>