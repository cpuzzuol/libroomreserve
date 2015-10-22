angular.module('ngBoilerplate.editRooms', ['ui.router', 'ngResource'])

.factory('roomService', function($http){
    var rooms = {};
    
    rooms.list = function(data){
        //return $http.get('/libroomreserve/api/rooms');
        console.log(data);
    };    
    return rooms;
})

.controller('EditRoomsController', function($scope, roomService){
   $scope.listRooms = function(){
       roomService.list("Bishes, you cra!");
   };
});
