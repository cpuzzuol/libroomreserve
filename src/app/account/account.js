angular.module('ngBoilerplate.account', ['ui.router', 'ngResource'])

.config(function($stateProvider){
    $stateProvider
        .state('login', {
           url: '/login',
           views: {
               'main': {
                   templateUrl: 'account/login.tpl.html',
                   controller: 'LoginCtrl'
               }
           },
           data: { pageTitle: 'Login' }
        })
        .state('register', {
          url: '/register',
          views: {
              'main': {
                  templateUrl: 'account/register.tpl.html',
                  controller: 'RegisterCtrl'
              }
          },
          data: { pageTitle: 'Register' }
        });
})

//switch $state if user logged in
.controller("LoginCtrl", function($scope, sessionService, $state){
    $scope.login = function() {
        sessionService.login($scope.account);
        $state.go("home");
    };
})
.controller("RegisterCtrl", function($scope, sessionService, $state, accountService){
    $scope.register = function(){
        accountService.register(
            $scope.account,
            function(returnedData){
                sessionService.login($scope.account);
                console.log($scope.account);
                $state.go("home");
            },
            function(){
                console.log($scope.account);
            });
    };
})
//a factory service is similar to a java @Bean
//returns a JSON object representing the session service
.factory('sessionService', function(){
    var session = {};
    session.login = function(data){
        localStorage.setItem("session", data);
        console.log("Logged in user: " + data.userName + " / " + data.password);
    };
    session.logout = function(){
        localStorage.removeItem("session");
        console.log("User has been logged out.");
    };
    session.isLoggedIn = function(){
        return localStorage.getItem("session") !== null;
    };
    return session;
})

.factory('accountService', function($resource){
    var service = {};
    service.register = function(account, success, failure){
        var Account = $resource("/libroomreserve/api/user");
        Account.save({}, account, success, failure);
    };
    return service;
});

