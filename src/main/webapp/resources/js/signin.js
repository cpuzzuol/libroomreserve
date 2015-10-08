var signinApp = angular.module('signinApp',[]);

signinApp.controller('SigninController', ['$rootScope','$scope','$http','$location', function($rootScope, $scope, $http, $location) {
    $scope.credentials = {}; //new scope object named "credentials"
    $scope.login = function(){
      authenticate($scope.credentials, function(){
        if($rootScope.authenticated){
          $location.path("/home");
          $scope.error = false;
        } else {
          $location.path("/signin");
          $scope.error = true;
        }
      });
    };
    //the authentication function
    var authenticate = function(credentials, callback){
      var headers = credentials ? {authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
      /*$http.get('http://localhost:8080/libroomreserve/user', {headers : headers})
              .success(function(data){
                if(data.userName){
                  $rootScope.authenticated = true;
                  console.log("AUTHENTICATED!");
                } else {
                  $rootScope.authenticated = false;
                  console.log("NOT AUTHENTICATED (successful function call, though!)");
                }
                callback && callback();
              }).error(function(data){
                $rootScope.authenticated = false;
                callback && callback();
              });*/
    };
    
    //run the authenticate() function
    //authenticate();
}]);
