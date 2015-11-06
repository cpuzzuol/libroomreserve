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
        
        var packagedUser = $http.get("/libroomreserve/api/room/" + reservation.room).then(function(response){
                console.log("success http!");
                console.log(response.data);
                return response.data;
        });
        
        /*
        function(){
                    return this.fetchRoomObj(
                            reservation.room, 
                            function(data){
                                console.log("fetch room ran!");
                                console.log(data);
                                return data;
                            }, 
                            function(){console.log("fetch room FAILED!");}
                        );*/
        
        var preparedReservation = {
            room: packagedUser,
            user: reservation.user,
            startTime: reservation.startTime,
            endTime: reservation.endTime,
            note: reservation.note
        };
        console.log(preparedReservation);
        var Reservation = $resource('/libroomreserve/api/reservation');
        Reservation.save({}, preparedReservation, success, failure);
    };
    
    reservations.listRooms = function(success, failure){
        var RoomsList = $resource('/libroomreserve/api/room');
        RoomsList.get({}, success, failure);
    };
    
    reservations.fetchRoomObj = function(room){
        //var Room = $resource('/libroomreserve/api/room/67');
        //Room.get({roomId: room.roomId}, success, failure);
        $http.get("/libroomreserve/api/room/" + room).then(function(response){
                console.log("success http!");
                console.log(response.data);
                finalRoom = response;
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
            //fetch room object based on submitted roomId
            /*
            $http.get("/libroomreserve/api/room/" + $scope.reservation.room).then(
                function(resource){
                    console.log("FRENCH PIANO!");
                    console.log(resource.data);
                    $scope.reservation.room = resource.data;
                },
                function(){
                    $scope.reservation.room = null;
                }
            );
            */
            
            reservationService.addReservation(
                $scope.reservation,
                function(data){
                    console.log("Success! Data printing:");
                    $state.go("home");
                },
                function(data){
                    console.log("Failure! Data printing:");
                }
            );
        };
});


