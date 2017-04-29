define(["draganddrop", "bootbox"], function (draganddrop, bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("BandejaAsignacionIndexCtrl", ["$state", "$stateParams", "$scope", "BandejaAsignacionTemplateService", function ($state, $stateParams, $scope, service) {

            $scope.deleteSolicitud = function deleteSolicitud(idx, productor) {
                $scope.data.listaSolicitudesAux.push(productor.solicitudes[idx]);
                for(i = 0; i<$scope.data.listaProductoresAux.length;i++){
                    if($scope.data.listaProductoresAux[i].id == productor.id){
                        $scope.data.listaProductoresAux[i].solicitudes.splice(idx,1);
                        break;
                    }
                }
                $scope.data.asignando = false;
            };

            $scope.init = function() {
                $scope.data = {};
                service.inicializarIndex().success(function (data) {
                    $scope.data.listaSolicitudesAux = data.listaSolicitudes;
                    $scope.data.listaProductoresAux = data.listaProductores;
                });
            };

            $scope.init();

            $scope.asignar = function () {
                service.asignar($scope.data.listaProductoresAux).success(function () {
                    success();
                });
            };

            function success(scope) {
                angular.element('#spinner').hide();
                bootbox.dialog({
                    message: "Proceso de asignación realizado con éxito",
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "OK", className: "btn-primary", callback: function () {
                                $scope.init()
                            }
                        }
                    },
                    closeButton: false
                });
            };

            $scope.claseProductores = function(){
                return $scope.data.listaSolicitudesAux && $scope.data.listaSolicitudesAux.length != 0 ? "col-md-6" : "col-md-12";
            };

            $scope.asignacionAleatoria = function(){
                angular.forEach($scope.data.listaSolicitudesAux, function(solicitud){
                    productor = $scope.data.listaProductoresAux[getRandomInt(0,$scope.data.listaProductoresAux.length)];
                    if(!productor.solicitudes){
                        productor.solicitudes = [];
                    }
                    productor.solicitudes.push(solicitud);
                });
                $scope.data.listaSolicitudesAux = [];
                $scope.data.asignando = true;
            };

            function getRandomInt(min, max) {
                return Math.floor(Math.random() * (max - min + 1)) + min;
            };

            $scope.endDrag = function (index) {
                $scope.data.listaSolicitudesAux.splice(index, 1);
                if($scope.data.listaSolicitudesAux.length == 0){
                    $scope.data.asignando = true;
                }
            };


            //***************DRAGANDDROP******************
            $scope.Drop2Controller = function ($scope, $http) {
                $scope.itemsDrop = [];
                $scope.insertedIndex = -1;
                $scope.inserted = null;

                $scope.endDrop = function (data) {
                    $scope.itemsDrop.push(angular.copy(data));
                    if (!$scope.productor.solicitudes) {
                        $scope.productor.solicitudes = [];
                    }
                    $scope.productor.solicitudes.push(data);
                };

                $scope.mouseLeave = function (evt, data) {
                    $scope.insertedIndex = -1;
                };

                $scope.mouseMove = function (evt, data) {
                    var $ite = angular.element(evt.target);
                    while ($ite.length > 0 && $ite.attr("data-count") == null) {
                        $ite = $ite.parent()
                    }
                    if ($ite.length > 0 && $scope.insertedIndex == $ite.attr("data-count")) {
                        return;
                    }
                    if ($scope.itemsDrop.length == 0) {
                        $scope.inserted = angular.copy(data.data);
                        $scope.insertedIndex = 0;
                    } else if ($ite.length > 0) {
                        console.log($ite.attr("data-count"));
                        $scope.inserted = angular.copy(data.data);
                        $scope.insertedIndex = $ite.attr("data-count");
                    }
                };

            };

        }]);
    };
    return constructor;
});