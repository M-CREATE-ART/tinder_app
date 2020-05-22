<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/people-list.css">
</head>

<body>
<div class="custom-header">
    <div class="image-wrapper">
        <img src="${other.image}" class="user-pp"/>
        <p class="user-name">${other.fullname}</p>
    </div>
    <div class="header-btns">
        <p class="header-btn , minimize-btn"><i class="fa fa-window-minimize hide-chat-box hover text-center pt-1"></i>
        </p>
        <p class="header-btn , up-btn"><i class="fa fa-arrow-up text-center pt-1"></i></p>
        <p class="header-btn , escape-btn"><a href="/liked"><i class="fa fa-times hover text-center pt-1"></i></a></p>
    </div>
</div>
<div class="message-box">
    <#list messages as message>
        <#if message.senderId == me.id>
            <p class="message-right , message ">
                ${message.text}
            </p>
            <span style="font-size: 10px; font-style: italic" class="message-right float-left">${message.date}</span>
        <#else>
            <p class="message-left , message">
                ${message.text}
            </p>
            <span style="font-size: 10px; font-style: italic">${message.date}</span>
        </#if>
    </#list>
</div>
<div class="input-wrapper">
    <form method="post">
        <input type="text" name="message" class="border-0 send-message-field" placeholder=" Send message"/>
        <button class="btn btn-success , send-btn" type="submit" name="submit" value="send">Send</button>
    </form>
</div>
</body>

</html>