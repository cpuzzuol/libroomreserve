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
        var Reservation = $resource('/libroomreserve/api/reservation');
        Reservation.save({}, reservation, success, failure);
    };
    
    reservations.listRooms = function(success, failure){
        var RoomsList = $resource('/libroomreserve/api/room');
        RoomsList.get({}, success, failure);
    };
    
    //This function has been replaced by fetchRoomUserObjs (LEAVE FOR REFERENCE)
    /*
    reservations.fetchRoomObj = function(roomId){
        return $http.get("/libroomreserve/api/room/" + roomId).then(
            function(response){
                return response.data;
            },
            function(httpError){
                //translate the error
                throw httpError.status + " : " +
                        httpError.data;
            }
        );
    }; */
    
    //uses $q to process multiple asynchronous calls
    reservations.fetchRoomUserObjs = function(roomId, userId){
        var deferred = $q.defer();
        var urlCalls = [];
        
        //call to room API
        urlCalls.push($http.get("/libroomreserve/api/room/" + roomId));
        //call to user API
        urlCalls.push($http.get("/libroomreserve/api/user/" + userId));
        
        //execute callbacks once they are completely finished
        $q.all(urlCalls).then(
            //success
            function(results){
                console.log("SUCCESS Q-ALL. Results: ");
                deferred.resolve(results);
            },
            //error
            function(errors){
                console.log("REJECTED Q-ALL. Errors: ");
                deferred.reject(errors);
            },
            //update
            function(updates){
                console.log("RESPONSE Q-ALL. Updates: ");
                deferred.update(updates);
            }
        );
        return deferred.promise;
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
            reservationService.fetchRoomUserObjs($scope.reservation.room, 1).then(
                function(roomAndUserObjs){
                    var finalReservation = {
                        room: roomAndUserObjs[0].data, //room object returned in promise
                        user: roomAndUserObjs[1].data, //user object returned in promise
                        startTime: $scope.reservation.startTime,
                        endTime: $scope.reservation.endTime,
                        note: $scope.reservation.note
                    };
                    reservationService.addReservation(
                        finalReservation,
                        function(data){
                            $state.go("home");
                        },
                        function(data){
                            $scope.error = true;
                        }
                    );
                }
            );
        };
});


