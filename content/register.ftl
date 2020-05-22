<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">
    <link href="css/css-reset.css" rel="stylesheet"
    <link
            href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,300;0,400;1,200;1,300;1,400&display=swap"
            rel="stylesheet">
    <title>Register Page</title>

    <!-- Bootstrap core CSS -->

    <link href="css/bootstrap.min.css" rel="stylesheet">


    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="text-center , login-custom-styles ">
<div>
    <form class="form-signin, register-form" method="post" enctype="multipart/form-data">
        <div class="form-header">
            <a href="/login" class="signUp-link">
                <p class="">Login?</p>
            </a>
        </div>
        <input placeholder="Email" name="email" type="email" id="inputEmail" class="form-control , email" required
               autofocus>
        <input placeholder="Username" name="fullname" type="text" class="form-control , email" required autofocus>
        <input name="password" type="password" id="inputPassword" class="form-control , input-field "
               placeholder="Password" required>
        <input name="confPass" type="password" id="inputPassword" class="form-control , input-field "
               placeholder="Confirm password" required>
        <label for="file-upload" class="custom-file-upload">choose profile picture</label>
        <input required name="image" value="image" type="file" id="file-upload" placeholder="upload your photo"
               class="custom-file-input"/>
        <img class="user-profie"/>
        <button name="submit" value="register" class="submit-btn" type="submit">Sign up</button>
    </form>
</div>
<div class="branding">
    <img src="image/pictuers/bubble-7.png" class="bubble-7"/>
    <p class="register-page-brand-logo">Tinder</p>
</div>
</body>

</html>