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
    service.userExists = function(account, success, failure){
        var Account = $resource("/libroomreserve/api/user");
        var data = Account.get(
            {userName: account.userName}, 
            function(){
                //see if an exsiting user was found through the API call
                var accounts = data.userResources;
                if(accounts.length !== 0){
                    console.log(accounts.length + " user account found for " + account.UserName + ". Logging in...");
                    success(accounts[0]); 
                } else {
                    failure(account);
                }
            },
            failure
        );
    };
    return service;
})

//switch $state if user logged in
.controller("LoginCtrl", function($scope, sessionService, $state, accountService){
    $scope.login = function() {
        accountService.userExists(
                $scope.account, 
                function(account){
                    sessionService.login(account);
                    $state.go("home");
                }, 
                function(account){
                    console.log("The user " + account.userName + " was not found.");
                }
        );
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
});