angular.module('ngBoilerplate.reservations',['ui.router', 'ui.bootstrap', 'ngResource'])

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

.factory('reservationService', function($resource, $q, $http){
    var reservations = {};
    
    reservations.addReservation = function(reservation, success, failure){
        console.log("DOGGIE AFUERA!");
        console.log(reservation.room);
        
        var Reservation = $resource('/libroomreserve/api/reservation');
        Reservation.save({}, reservation, success, failure);
    };
    
    reservations.listRooms = function(success, failure){
        var RoomsList = $resource('/libroomreserve/api/room');
        RoomsList.get({}, success, failure);
    };
    
    reservations.fetchRoomObj = function(roomId){
        //var Room = $resource('/libroomreserve/api/room/67');
        //Room.get({roomId: room.roomId}, success, failure);
        return $http.get("/libroomreserve/api/room/" + roomId).then(function(response){
                return response;
        });
    };
    
    return reservations;
})

.controller('AddReservationController', function($scope, $state, $http, reservationService){
        $scope.reservation = {};
        
    //get all rooms
        reservationService.listRooms(
            function(data){
                $scope.roomsList = data.roomResources;
            },
            function(){
                $scope.roomsList = null;
            }
        );  
            
        //fetch user object of logged in user
        $http.get("/libroomreserve/api/user/1").then(
            function(resource){
                console.log(resource);
                $scope.reservation.user = resource.data;
            },
            function(){
                $scope.reservation.user = null;
            }
        );
        $scope.newReservation = function(){
            reservationService.fetchRoomObj($scope.reservation.room).then(function(roomObj){
                var finalReservation = {
                    room: roomObj.data,
                    user: $scope.reservation.user,
                    startTime: $scope.reservation.startTime,
                    endTime: $scope.reservation.endTime,
                    note: $scope.reservation.note
                };
                reservationService.addReservation(
                    finalReservation,
                    function(data){
                        console.log("Success! Data printing:");
                        $state.go("home");
                    },
                    function(data){
                        console.log("Failure! Data printing:");
                    }
                );
            });
            
        };
});


