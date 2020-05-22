<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
        integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/people-list.css">
</head>

<body>

    <div class="container">
        <img src="image/pictuers/cloud-header.png" class="cloud-header"/>
        <img src="image/pictuers/fish.png" class="fish" />
        <img src="image/pictuers/iland.png" class="island" />
        <div class="row">

            <div clas='cutom-table-container'>
                <div class="heading-container">
                    <h2 class="heading">You've matched with</h2>
                </div>

                <div class="panel panel-default user_panel , custom-table  ">

                    <div class="panel-body">
                        <div class="table-container">
                            <table class="table-users table , user-card " border="0">
                                <tbody>
                                <#list users as user>
                                    <tr>
                                        <td width="10">
                                            <div class="avatar-img , user-img">
                                                <img class="img-circle" src="${user.image}" />  
                                            </div>

                                        </td>
                                        <td class="align-middle">
                                            <form method="post">
                                                <button name="message" type="submit" value="${user.email}"
                                                    class="user-name-btn">
                                                    ${user.fullname}
                                                </button>
                                            </form>

                                        </td>
                                        <td class="align-middle">
                                            ${user.email}
                                        </td>
                                        <td class="align-middle">
                                            ${user.lastLogin}
                                        </td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                            <a href="/users" class="btn btn-primary btn-lg col-12 float-right">Find out more users</a>
                            <a href="/logout" class="btn btn-danger btn-lg col-12 float-right">Log out</a>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>

</html>