define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {

        module.controller("NotificacionesIndexCtrl", ["$state", "$stateParams","$rootScope" ,"$scope", function ($state, $stateParams, $root,$scope) {

            function init() {

            };

            init();

            $scope.updateNotificacion = function (item) {
                item.leida = true;
                $root.updateNotificacion(item);
                //$root.actualizarNotificaciones();
                //$state.go('notificaciones.index', {persona: null});


            };



        }]);




    };
    return constructor;
});







//myApp.factory('myService', function() {});


