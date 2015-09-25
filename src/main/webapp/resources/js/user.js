// declare the module
var userModule = angular.module('userApp', []);

// configure the module.
// in this example we will create a greeting filter
/*
userModule.controller('UserController', ['$scope','UserService','$timeout', 
  function($scope, UserService, $timeout){
    this.user = 'cpuzzuol';
    this.users = ['cpuzzuol', 'prady', 'asinger'];

    this.totalUsers = function totalUsers(users){
        if(typeof(users) == 'object'){
          return "There are " + users.length + " users in the system.";
        }
        return "Invalid users array!";
    
    $scope.users = UserService.getAllUsers();
  }
}]);

userModule.service('UserService', ['$http','$q', function($http, $q) {
  return {
            getAllUsers: function() {
                var deferred = $q.defer();

                $http.get('/user')
                    .then(function (response) {
                        if (response.status == 200) {
                            deferred.resolve(response.data);
                        }
                        else {
                            deferred.reject('Error retrieving user info');
                        }
                });

                return deferred.promise;
              }
        }
}]);
*/
userModule.config(['$routeProvider', '$locationProvider',
  function($routeProvider, $locationProvider){
    $routeProvider.
            when('/userbones', {
              templateUrl: 'public/user.html',
              controller: 'UserController'
            }).
            otherwise({
              redirectTo: '/'
            });
     $locationProvider.html5Mode(true);
  }]);
userModule.controller('UserController', function($scope){
  $scope.message = "You can turn your back on the bitter world";
});
