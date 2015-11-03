angular.module('ngBoilerplate.admin', ['ngBoilerplate.editRooms', 'ui.router', 'ngResource'])

.config(function($stateProvider){
    $stateProvider
        .state('admin', {
            url: '/admin',
            views: {
                'main': {
                    templateUrl: 'admin/admin.tpl.html',
                    controller: 'AdminController'
                }
            },
            data: { pageTitle: "Administration Panel" }
        })
        .state('listRooms', {
            url: '/admin/listrooms',
            views: {
                'main': {
                    templateUrl: 'admin/rooms/listrooms.tpl.html',
                    controller: 'ListRoomsController'
                }
            },
            data: { pageTitle: "List Rooms" }
        })
        .state('editRoom', {
            //url: '/admin/editroom/:roomNumber',
            url: '/admin/editroom?roomNumber',
            views: {
                'main': {
                    templateUrl: 'admin/rooms/editroom.tpl.html',
                    controller: 'EditRoomController'
                }
            },
            data: { pageTitle: "Edit Room" }
        })
        .state('addRoom', {
            url: '/admin/addroom',
            views: {
                'main': {
                    templateUrl: 'admin/rooms/addroom.tpl.html',
                    controller: 'AddRoomController'
                }
            },
            data: { pageTitle: "New Room" }
        });
})

.factory('dashboardService', function($http, $resource){
    var dashboard = {};
    
    dashboard.totalUsers = function(){
        return $http.get("/libroomreserve/api/user")
                .then(function(response){
                    return response.data.userResources;
                });
    };
    
    return dashboard;
})
        
.controller('AdminController', function($scope, dashboardService){
    dashboardService.totalUsers().then(function(data){
        $scope.totalUsers = data.length;
    });
});


