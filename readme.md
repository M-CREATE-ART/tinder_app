## Step project Tinder

#### How to run it locally
The main idea of this project is:
- [registration](http://localhost:5000/reg)
- [login](http://localhost:5000/login)

If you want to test it, there are some test credentials
1. email - ***test@gmail.com*** and password - ***test***
2. email - ***sh1@gmail.com*** and password - ***123***

After login, it will redirect you to **users** page

- [users](http://localhost:5000/users)

Here you can like or dislike people and can see liked or disliked users

- [liked](http://localhost:5000/liked)
- [disliked](http://localhost:5000/disliked)

If you want to send message to any person to can click on name of him/her

- [messages](http://localhost:5000/messages)

if you want to logout from web page:

- [logout](http://localhost:5000/logout)

#### How to deploy it to heroku

1.`mvn clean install` *(in terminal run this command)*

2.`heroku login` *(it will open official heroku page for login)*

3.`heroku create` (project name) *(it will create project in heroku by name which you want)*

4.`git push heroku master` *(to push your code to heroku master)*

**Here our deployed heroku link**
- [heroku link](http://tindershefamaryam.herokuapp.com)

**Here register link**
- [register](http://tindershefamaryam.herokuapp.com)

#### Environment variables
- PORT (http port)

- URL (database url)

- USER (database username)

- PASS (database password)







