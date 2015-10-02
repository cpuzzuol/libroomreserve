var roomResApp = angular.module('roomResApp',['ngRoute']);

roomResApp.controller('MainController', ['$scope', function($scope) {
    $scope.message = 'MAIN PAGE!';
}]);

roomResApp.controller('UserController', ['$scope', '$http', function($scope, $http) {
    $scope.getUsersList = function() {
      $http.get('http://localhost:8080/libroomreserve/user')
      .success(function(response) {$scope.users = response;})
      .error(function(response) {console.log(response)});
    }
    // selected users for deletion
    $scope.selectedUsersForDeletion = [];
      
    $scope.userAdd = function(){ 
      $http.post('http://localhost:8080/libroomreserve/user', $scope.userName);
      
      //reset scope variables after push
      $scope.userId = '';
      $scope.userName = '';
      
      $scope.getUsersList(); 
    }
    $scope.userEdit = function(){
      var index = getSelectedIndex($scope.userId);
      $scope.users[index].userName = $scope.userName;
      
      //put requires a User object (on the spring side)...be sure to include $scope.user[index]
      $http.put('http://localhost:8080/libroomreserve/user/' + $scope.userId, $scope.users[index]);
    }
    $scope.userSelect = function(id){
      var index = getSelectedIndex(id);
      var user = $scope.users[index];
      $scope.userId = user.userId;
      $scope.userName = user.userName;
    }  
    //use with single delete button on individual records
    $scope.userDelete = function(id){
      var result = confirm("Are you sure?");
      if(result===true){
        $scope.selectedUsersForDeletion.push(id);
        $scope.selectedUsersForDeletion.forEach(deleteUser);
      }
    }
    //use with checkboxes for multi-delete option
    $scope.usersDelete = function(ids){
      var result = confirm("Are you sure?");
      if(result===true){
        ids.forEach(deleteUser);
      }
    }
    
    // toggle checkbox selection for a given user
    $scope.toggleSelection = function toggleSelection(userId) {
      var idx = $scope.selectedUsersForDeletion.indexOf(userId); //returns -1 if not currently in the index (e.g. not selected)
      // is currently selected
      if (idx > -1) {
        $scope.selectedUsersForDeletion.splice(idx, 1);
      }
      // is newly selected
      else {
        $scope.selectedUsersForDeletion.push(userId);
      }
    };
    
    function deleteUser(value, index, array){
      var index = getSelectedIndex(value);
      $scope.users.splice(index, 1);

      $http.delete('http://localhost:8080/libroomreserve/user', {params: {userId: value}});
    }
    
    //find the userId that was selected for deletion
    function getSelectedIndex(id){
      for(var i=0; i < $scope.users.length; i++){
        if($scope.users[i].userId == id){
          return i;
        }
      }
      return -1;
    }
  }]);

roomResApp.controller('ContactController', ['$scope', function($scope) {
    $scope.message = 'CONTACT PAGE!';
}]);

// configure our routes
roomResApp.config(function($routeProvider, $locationProvider) {
    $routeProvider
        // route for the home page
        .when('/home', {
            templateUrl : 'pages/home.html',
            controller  : 'MainController'
        })
        // route for the about page
        .when('/users', {
            templateUrl : 'pages/user.html',
            controller  : 'UserController'
        })
        // route for the contact page
        .when('/contact', {
            templateUrl : 'pages/contact.html',
            controller  : 'ContactController'
        })
        .otherwise({
          redirectTo: '/home'
        });
        
        // use the HTML5 History API
        $locationProvider.html5Mode(true);
});



