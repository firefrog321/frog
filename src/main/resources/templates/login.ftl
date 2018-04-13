<!doctype html>
<html lang="en">
<#include "header.ftl">
<link href="/css/signin.css" rel="stylesheet">

<body class="text-center">

<form class="form-signin" method="post" action="/login">
    <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt=""
         width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <h4>${message!}</h4>
    <label for="inputEmail" class="sr-only">Username</label>
    <input id="username" class="form-control" name="username" placeholder="Email address"
           required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" name="password"
           placeholder="Password" required>
    <#--<div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>-->
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2019</p>
</form>
</body>
</html>
