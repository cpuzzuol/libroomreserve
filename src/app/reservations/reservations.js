angular.module('ngBoilerplate.reservations',['ui.router', 'ngResource'])

.config(function($stateProvider){
    $stateProvider
        .state('addReservation',{
            url: '/addreservation',
            views: {
                'main': {
                    templateUrl: 'reservations/addreservation.tpl.html',
                    controller: 'AddReservationController'
                }
            },
            data: { pageTitle: 'New Reservation' }
        });
})
.controller('AddReservationController', function($scope, $http){
    $scope.hello = "Let's reserve a new room, shall we?";
});


