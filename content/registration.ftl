<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/style.css">
    <title>Document</title>
</head>
<body>

<div class="login-container">

    <form class="form-signin" method="post" enctype="multipart/form-data">
        <input required class="form-control input-custom-styles" type="text" placeholder="Enter your username" class="textbox" name="fullname">
        <input required class="form-control input-custom-styles" type="email" placeholder="Enter your email" class="textbox" name="email">
        <input required class="form-control input-custom-styles" type="password" placeholder="Enter your password" class="textbox" name="password">
        <input required class="form-control input-custom-styles" type="password" placeholder="Confirm your password" class="textbox" name="confPass">
        <input required class="form-control input-custom-styles" type="file"  placeholder="upload your photo" name="image">
        <button name = "submit" value="register" class="btn btn-lg btn-primary btn-block , login-submit-btn" type="submit">Sign up</button>
    </form>

</div>

</body>
</html>