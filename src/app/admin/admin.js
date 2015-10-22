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
        .state('editRooms', {
            url: '/admin/editrooms',
            views: {
                'main': {
                    templateUrl: 'admin/editrooms/editrooms.tpl.html',
                    controller: 'EditRoomsController'
                }
            },
            data: { pageTitle: "Edit Rooms" }
        });
})
        
.controller('AdminController', function($scope){
    
});


