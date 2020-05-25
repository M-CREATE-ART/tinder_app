<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
        integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body style="background-color: #f5f5f5;">
    <img src="image/pictuers/cloud-left.png" class="cloud-left"/>

    <div class="col-4 ">
        <div class="user-name">
            <h3 class="mb-0 text-truncated">${user.fullname}</h3>
        </div>
        <div class="card , user-photo-card">
            <div class="card-body">
                <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <img src="${user.image}" alt="" class="mx-auto rounded-circle img-fluid">

                        <br>
                    </div>

                    <!--/col-->
                </div>
                <!--/row-->
            </div>

            <!--/card-block-->
        </div>
        <form method="post" class="btn-wrapper">
            <button name="btn" value="dislike" type="submit" class="btn btn-outline-danger , btn-dislike">Dislike</button>
            <button name="btn" value="like" type="submit" class="btn btn-outline-success , btn-like ">Like</button>
        </form>
        <a href="/liked" class="btn btn-outline-info btn-lg col-6 float-left">People you liked</a>
        <a href="/disliked" class="btn btn-outline-danger btn-lg col-6 float-right">People you disliked</a>
        <a href="/logout" class="btn btn-danger btn-lg col-12 float-right">Log out</a>

    </div>
    <img src="image/pictuers/cloud-right.png" class="cloud-right"/>

</body>

</html>