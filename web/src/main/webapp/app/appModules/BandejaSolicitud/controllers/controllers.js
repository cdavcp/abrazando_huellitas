define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("BandejaSolicitudIndexCtrl", ["$state", "$stateParams", "$scope", "BandejaSolicitudTemplateService", "$rootScope", function ($state, $stateParams, $scope, service, $rootScope) {

            function prepareSolicitudes() {
                $scope.data.solicitudesNuevas = [];
                $scope.data.solicitudesHistorial = [];
                angular.forEach($scope.data.listaSolicitudes, function (solicitud) {
                    if (solicitud.estado.id == $scope.ID_ESTADO_ASIGNADA) {
                        $scope.data.solicitudesNuevas.push(solicitud);
                    }
                    else {
                        $scope.data.solicitudesHistorial.push(solicitud);
                    }
                });
                $scope.data.solicitudesNuevasPaginated = $scope.data.solicitudesNuevas;
                $scope.data.solicitudesHistorialPaginated = $scope.data.solicitudesHistorial;
            };

            $scope.verClick = function (solicitud) {
                $state.go('bandejaSolicitud.view', {solicitud: solicitud});
            };

            $scope.venderClick = function (solicitud) {
                $scope.data.solicitud = solicitud;
            };

            $scope.ingresarPoliza = function () {
                angular.element(".modal-backdrop").remove();
                $state.go('bandejaSolicitud.ingresarPoliza', {solicitud: $scope.data.solicitud});
            };

            $scope.createPoliza = function () {
                var param = {previousState: 'bandejaSolicitud.index',
                    solicitud: $scope.data.solicitud,
                    origenId: $scope.ID_ORIGEN_SOLICITUD};
                $rootScope.bandejaSolicitudData = $scope.data;
                angular.element(".modal-backdrop").remove();
                $state.go('poliza.create', {param: param});
            };

            $scope.descartar = function () {
                service.descartar($scope.data.solicitud.id).success(function () {
                    success("Solicitud descartada con éxito.", true);
                });
            };

            $scope.actualizarSolicitud = function () {
                service.update($scope.data.solicitud, $scope.data.solicitud.poliza.numero, $scope.data.solicitud.poliza.cobertura.aseguradora.id).success(function () {
                    success("Solicitud actualizada con éxito.", true);
                });
            };

            function success(msj, reset) {
                angular.element('#spinner').hide();
                bootbox.dialog({
                    message: msj,
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "OK", className: "btn-primary", callback: function () {
                                if (reset) {
                                    delete $rootScope.bandejaSolicitudData;
                                    $scope.init();
                                }
                            }
                        }
                    },
                    closeButton: false
                });
            };

            $scope.solicitudClass = function (estadoId) {
                return estadoId == $scope.ID_ESTADO_VENDIDA ? "success" : "danger";
            };

            $scope.init = function () {
                $scope.ID_ESTADO_REGISTRADA = 1;
                $scope.ID_ESTADO_ASIGNADA = 2;
                $scope.ID_ESTADO_VENDIDA = 3;
                $scope.ID_ESTADO_NO_VENDIDA = 4;
                $scope.ID_ORIGEN_SOLICITUD = 1;

                if($rootScope.bandejaSolicitudData){
                    $scope.data =$rootScope.bandejaSolicitudData;
                    if(!$scope.data.solicitud)$scope.data.solicitud = {};
                    if($stateParams.param && $stateParams.param.poliza){
                        $scope.data.solicitud.poliza = $stateParams.param.poliza;
                        $scope.actualizarSolicitud();
                    }
                }
                else{
                    $scope.data = {};
                    service.inicializarIndex($scope.$root.userProfile.id).success(function (data) {
                        $scope.data.listaSolicitudes = data;
                        prepareSolicitudes();
                    });
                }
            };

            $scope.init();

        }]);

        module.controller("BandejaSolicitudViewCtrl", ["$state", "$stateParams", "$scope", "BandejaSolicitudTemplateService", function ($state, $stateParams, $scope, service) {

            function init() {
                $scope.data = {};
                $scope.data.solicitud = $stateParams.solicitud;
                $scope.data.viewStateFlag = true;
                service.getTiposDocumento().success(function (data) {
                    $scope.data.listaTiposDocumento = data;
                });
            };
            init();

            $scope.volver = function () {
                $scope.data = {};
                $state.go('bandejaSolicitud.index');
            };
        }]);

        module.controller("IngresarPolizaCtrl", ["$state", "$stateParams", "$scope", "BandejaSolicitudTemplateService", function ($state, $stateParams, $scope, service) {

            function init() {
                $scope.data = {};
                $scope.data.solicitud = $stateParams.solicitud;

                $scope.data.numeroValido = false;
                $scope.data.VALID_CLASS = "has-success";
                $scope.data.INVALID_CLASS = "has-error";

                service.getAseguradoras().success(function (data) {
                    $scope.data.listaAseguradoras = data;
                });
            };
            init();

            $scope.checkNumero = function () {
                if ($scope.data.numeroPoliza && $scope.data.numeroPoliza != "") {
                    $scope.data.checkingNumero = true;
                    service.checkNumero($scope.data.numeroPoliza, $scope.data.aseguradoraId).success(function (data) {
                        if (data) {
                            $scope.data.numeroValido = true;
                            $scope.data.claseNumeroPoliza = $scope.data.VALID_CLASS;
                            $scope.data.solicitud.poliza = data;
                        }
                        else {
                            $scope.data.claseNumeroPoliza = $scope.data.INVALID_CLASS;
                            $scope.data.numeroValido = false;
                        }
                        $scope.data.checkingNumero = false;
                    })
                }
                else {
                    $scope.data.claseNumeroPoliza = $scope.data.INVALID_CLASS;
                }
            };

            $scope.aseguradoraChanged = function(){
                $scope.data.numeroPoliza = null;
                $scope.data.numeroValido = false;
            };

            $scope.actualizarSolicitud = function () {
                service.update($scope.data.solicitud, $scope.data.numeroPoliza, $scope.data.aseguradoraId).success(function () {
                    success("Solicitud actualizada con éxito.", true);
                });
            };

            $scope.volver = function () {
                $scope.data = {};
                $state.go('bandejaSolicitud.index');
            };

            function success(msj, reset) {
                angular.element('#spinner').hide();
                bootbox.dialog({
                    message: msj, title: "Confirmación",
                    buttons: {
                        success: {
                            label: "OK", className: "btn-primary", callback: function () {
                                if (reset) {
                                    $state.go('bandejaSolicitud.index');
                                }
                            }
                        }
                    },
                    closeButton: false
                });
            };
        }]);

        function error(msj) {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: msj, title: "Advertencia", buttons: {
                    success: {label: "OK", className: "btn-primary"}
                }
            });
        };

    };
    return constructor;
});