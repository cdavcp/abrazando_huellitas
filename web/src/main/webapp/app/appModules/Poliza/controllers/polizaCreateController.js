define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("PolizaCreateCtrl", ["$state", "$stateParams", "$scope", "PolizaTemplateService", "$rootScope", function ($state, $stateParams, $scope, service, $rootScope) {
            $scope.form = {};

            function init() {
                var idUsuarioLogueado = $rootScope.userProfile.id;
                service.inicializar(idUsuarioLogueado).success(function (data) {
                    $scope.data = data;
                    $scope.data.VALID_CLASS = "has-success";
                    $scope.data.INVALID_CLASS = "has-error";
                    if ($stateParams.param) {
                        if ($stateParams.param.aseguradoraId) {
                            $scope.data.aseguradoraSeleccionadaId = $stateParams.param.aseguradoraId;
                            $scope.data.aseguradoraDisabled = true;
                        }
                        if ($stateParams.param.numeroPoliza) {
                            $scope.data.poliza.numero = $stateParams.param.numeroPoliza;
                            $scope.checkNumero();
                        }
                        if ($stateParams.param.solicitud) {
                            $scope.data.poliza.cliente = $stateParams.param.solicitud.cliente;
                            $scope.data.aseguradoraSeleccionadaId = $stateParams.param.solicitud.cobertura.aseguradora.id;
                            $scope.data.rubroSeleccionadoId = $stateParams.param.solicitud.cobertura.rubro.id;
                            $scope.padreCoberturaChanged();
                            $scope.data.poliza.cobertura = $stateParams.param.solicitud.cobertura;
                            $scope.data.disableCliente = true;
                        }
                        if ($stateParams.param.origenId) {
                            $scope.data.poliza.origen = {};
                            $scope.data.poliza.origen.id = $stateParams.param.origenId;
                            $scope.data.disableOrigen = true;
                        }
                    }
                });
            }

            init();

            $scope.padreCoberturaChanged = function () {
                if ($scope.data.aseguradoraSeleccionadaId && $scope.data.rubroSeleccionadoId) {
                    $scope.data.poliza.cobertura = {};
                    service.findCoberturas($scope.data.aseguradoraSeleccionadaId, $scope.data.rubroSeleccionadoId).success(function (data) {
                        $scope.data.listaCoberturas = data;
                    })
                }
            };

            $scope.checkNumero = function () {
                if ($scope.data.poliza.numero && $scope.data.poliza.numero != "") {
                    $scope.data.checkingName = true;
                    service.checkNumeroPoliza($scope.data.poliza.numero, $scope.data.aseguradoraSeleccionadaId).success(function (data) {
                        $scope.data.numeroNuevaPolizaValido = (data == "true");
                        $scope.data.claseNumeroPoliza = $scope.data.numeroNuevaPolizaValido ? $scope.data.VALID_CLASS : $scope.data.INVALID_CLASS;
                        $scope.data.checkingName = false;
                    })
                }
                else {
                    $scope.data.claseNumeroPoliza = $scope.data.INVALID_CLASS;
                }
            };

            $scope.cancelar = function () {
                if ($stateParams.param) {
                    $scope.data = {};
                    $state.go($stateParams.param.previousState);
                }
                else {
                    $scope.data = {};
                    $state.go('poliza.index');
                }
            };

            $scope.guardar = function () {
                service.create($scope.data.poliza).success(function () {
                    success($scope, $state, $stateParams.param);
                })
                    .error(function () {
                        error();
                    });
            }
        }]);

        function success(scope, state, param) {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Registro realizado con éxito",
                title: "Confirmación",
                buttons: {
                    success: {
                        label: "OK", className: "btn-primary", callback: function () {
                            if (param) {
                                var resp = {poliza: scope.data.poliza};
                                scope.data = {};
                                state.go(param.previousState, {param: resp});
                            } else {
                                scope.data = {};
                                state.go('poliza.index');
                            }
                        }
                    }
                },
                closeButton: false
            });
        }

        function error() {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Se ha producido un error al guardar la póliza, por favor inténtelo de nuevo.", title: "Error", buttons: {
                    success: {label: "OK", className: "btn-primary"}
                }
            });
        }
    };
    return constructor;
});