<%@ page pageEncoding="UTF-8"%>
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
</body>
</html>