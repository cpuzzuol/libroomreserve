angular.module('ngBoilerplate.editRooms', ['ui.router', 'ngResource'])

.factory('roomService', function($http, $resource){
    var rooms = {}; 
    
    rooms.addRoom = function(room, success, failure){
        var Room = $resource('/libroomreserve/api/room');
        Room.save({}, room, success, failure);
    };
    rooms.roomExists = function(room, success, failure){
        var Room = $resource("/libroomreserve/api/room");
        var data = Room.get(
            {roomNumber: room.roomNumber}, 
            function(){
                //see if an exsiting user was found through the API call
                var rooms = data.roomResources;
                if(rooms.length !== 0){
                    success(rooms[0]); 
                } else {
                    failure(rooms);
                }
            },
            failure
        );
    };
    rooms.extractNewRoomId = function(href){
        //match any numbers at the end of the href argument
        var newId = href.match(/\d+$/);
        return newId;
    };
    rooms.deleteRoom = function(roomId, success, failure){
      var Room = $resource("/libroomreserve/api/room/:roomId", {roomId: '@roomId'});
      var data = Room.remove({roomId: roomId}, {}, success, failure);
    };
    
    return rooms;
})

.controller('ListRoomsController', function($scope, $http, roomService){
    $http.get('/libroomreserve/api/room').then(function(results){
        $scope.rooms = results.data.roomResources;
        //console.log(results);
    });
})

.controller('EditRoomController', function($scope, $http, $state, $stateParams, roomService){
    $http.get('/libroomreserve/api/room?roomNumber=' + $stateParams.roomNumber)
        .then(
            //promise success
            function(results){
                $scope.room = results.data.roomResources[0];
                $scope.roomId = roomService.extractNewRoomId($scope.room.links[0].href);
                $scope.exists = true;
            }, 
            //promise failure
            function(){
                $scope.exists = false;
            }
        );
    
    $scope.deleteRoom = function(){
        roomService.deleteRoom(
                $scope.roomId,
                function(){
                   $state.go("listRooms");
                },
                function(){
                   $scope.notDeleted = true;
                }
        );
    };
})

.controller('AddRoomController', function($scope, $state, $stateParams, roomService){
    $scope.newRoom = function(){
        //add the room only if the function fails to find a room with the same name
        roomService.roomExists(
            $scope.room,
            function(room){
                $scope.error = true;
                $scope.existingRoomNumber = room.roomNumber;
            },
            function(room){
                roomService.addRoom(
                    $scope.room,
                    function(room){
                        var newRoomId = roomService.extractNewRoomId(room.links[0].href); //run the regex match
                        //$state.go('editRoom', {roomId:newRoomId});
                        var newRoomNumber = room.roomNumber;
                        $state.go('editRoom', {roomNumber:newRoomNumber});
                    },
                    function(room){
                        //console.log("Room does not exist, but the addRoom function failed!");
                    }
                );
                $scope.error = false;
            }
        );
        
    };
});
