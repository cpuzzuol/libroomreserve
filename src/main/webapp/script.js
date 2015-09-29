var myApp = angular.module('myApp',['ngRoute']);

myApp.controller('MainController', ['$scope', function($scope) {
    $scope.message = 'MAIN PAGE!';
}]);

scotchApp.controller('UserController', ['$scope', function($scope) {
    $scope.message = 'USER PAGE!';
}]);

scotchApp.controller('ContactController', ['$scope', function($scope) {
    $scope.message = 'CONTACT PAGE!';
}]);

// configure our routes
myApp.config(function($routeProvider) {
    $routeProvider
        // route for the home page
        .when('/', {
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
        });
        
        // use the HTML5 History API
        $locationProvider.html5Mode(true);
});


