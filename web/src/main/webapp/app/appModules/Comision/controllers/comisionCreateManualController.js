define(['bootbox'], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("ComisionCreateManualCtrl", ["$state", "$stateParams", "$scope", "ComisionTemplateService", "$rootScope", function ($state, $stateParams, $scope, service, $rootScope) {
            $scope.form = {};

            function init() {
                if ($rootScope.comisionData) {
                    $scope.data = $rootScope.comisionData;
                    if($stateParams.param){
                        $scope.data.listaPolizas.push($stateParams.param.poliza);
                        $scope.data.comision.numeroPoliza = $stateParams.param.poliza.numero;
                    }
                }
                else {
                    service.inicializarCreateManual($rootScope.userProfile.id).success(function (data) {
                        $scope.data = data;
                        $scope.data.lote = {};
                        $scope.data.lote.productor = {};
                        $scope.data.lote.productor.id = $scope.data.productor.id;
                        $scope.data.comision = {};
                        $scope.data.listaNuevasComisiones = [];
                    });
                }
            };
            init();

            $scope.cancelar = function () {
                $rootScope.comisionData = undefined;
                $scope.data = {};
                $state.go('comision.index');
            };

            $scope.guardar = function () {
                service.procesarManual($scope.data.lote, $scope.data.listaNuevasComisiones).success(function (response) {
                    if(response == true || response == 'true'){
                        success();
                    } else {
                        errorLoteRepetido();
                    }
                })
                    .error(function () {
                        error();
                    });
            };

            $scope.agregar = function () {
                $scope.data.listaNuevasComisiones.push($scope.data.comision);
                $scope.data.comision = {};
            };

            $scope.quitar = function (item, index) {
                $scope.data.listaNuevasComisiones.splice(index, 1);
                $scope.data.comision = item;
            };

            $scope.nuevaPoliza = function () {
                $rootScope.comisionData = $scope.data;
                var param = {previousState: 'comision.createM', aseguradoraId: $scope.data.lote.aseguradora.id};
                $state.go('poliza.create', {param: param});
            };

            $scope.padrePolizaChanged = function () {
                if ($scope.data.lote.aseguradora.id) {
                    service.findPolizas($scope.data.lote.aseguradora.id, $scope.data.lote.productor.id).success(function (data) {
                        $scope.data.listaPolizas = data;
                    })
                }
            };

            $scope.checkNumeroFila = function () {
                var flag = false;
                angular.forEach($scope.data.listaNuevasComisiones, function (com) {
                    if(!flag)
                    {
                        if (com.numeroFila == $scope.data.comision.numeroFila) {
                            $scope.data.numeroYaAgregado = true;
                            flag = true;
                        }
                        else {
                            $scope.data.numeroYaAgregado = false;
                        }
                    }
                });
            };

            function success() {
                angular.element('#spinner').hide();
                bootbox.dialog({
                    message: "Registro realizado con éxito",
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "OK", className: "btn-primary", callback: function () {
                                $rootScope.comisionData = undefined;
                                $scope.data = {};
                                $state.go('comision.index');
                            }
                        }
                    },
                    closeButton: false
                });
            };

            function error() {
                angular.element('#spinner').hide();
                bootbox.dialog({
                    message: "Se ha producido un error al procesar las comisiones, por favor inténtelo de nuevo.", title: "Error", buttons: {
                        success: {label: "OK", className: "btn-primary"}
                    }
                });
            };

            function errorLoteRepetido() {
                angular.element('#spinner').hide();
                bootbox.dialog({
                    message: "Ya hay un lote procesado para el mes y la aseguradora seleccionados.", title: "Error", buttons: {
                        success: {label: "OK", className: "btn-primary"}
                    }
                });
            };

        }]);
    };
    return constructor;
});