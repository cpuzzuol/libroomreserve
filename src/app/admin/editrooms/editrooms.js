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
                    console.log("There is already a room " + rooms[0].roomNumber + " in the system!");
                    success(rooms[0]); 
                } else {
                    console.log("No matching rooms found for " + room.roomNumber);
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
      //alert("Deleted you sumbitch!");  
      var Room = $resource("/libroomreserve/api/room/:roomId", {roomId: '@roomId'});
      var data = Room.get(
                {roomId: roomId},
                function(){
                    data.$delete();
                }
            );
      
     /*
        return $resource("/libroomreserve/api/room/:roomId", 
                {},
                {
                    'delete': {method: 'DELETE', params: {roomId: roomId}}
                });
      */
    };
    
    return rooms;
})

.controller('ListRoomsController', function($scope, $http, roomService){
    $http.get('/libroomreserve/api/room').then(function(results){
        $scope.rooms = results.data.roomResources;
        //console.log(results);
    });
})

.controller('EditRoomController', function($scope, $http, $stateParams, roomService){
    $http.get('/libroomreserve/api/room?roomNumber=' + $stateParams.roomNumber).then(function(results){
        $scope.room = results.data.roomResources[0];
        //console.log($scope.room);
    });
    
    $scope.deleteRoom = function(){
        roomService.deleteRoom(1);
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
