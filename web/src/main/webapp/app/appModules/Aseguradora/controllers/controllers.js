define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("AseguradoraIndexCtrl", ["$state", "$stateParams", "$scope", "AseguradoraTemplateService", function ($state, $stateParams, $scope, service) {

            $scope.data = {};

            function init() {
                service.inicializarIndex().success(function (data) {
                    $scope.data.listaAseguradoras = data;
                });
            };

            init();

            $scope.create = function () {
                $state.go('aseguradora.create');
            };

            $scope.editarClick = function (aseguradora) {
                $state.go('aseguradora.edit', {aseguradora: aseguradora});
            };

            $scope.verClick = function (aseguradora) {
                $state.go('aseguradora.view', {aseguradora: aseguradora});
            };
        }]);

        module.controller("AseguradoraCreateCtrl", ["$state", "$stateParams", "$scope", "AseguradoraTemplateService", function ($state, $stateParams, $scope, service) {

            function init() {
                $scope.data = {};
                $scope.data.logoValido = false;
                $scope.data.aseguradora = {};
                //$scope.data.aseguradora.parametroImportacion = {};
                $scope.MAX_FILE_SIZE_BYTES = 100000;
            };
            init();

            $scope.$on("fileSelected", function (evt, files) {
                $scope.data.file = files[0];
                if($scope.data.file.size > $scope.MAX_FILE_SIZE_BYTES || $scope.data.file.name.indexOf(".jpg")==-1){
                    $scope.data.logoValido = false;
                }
                else{
                    $scope.data.logoValido = true;
                }
            });

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('aseguradora.index');
            };

            $scope.guardar = function () {
                paramValidMsj = null;
                if($scope.data.aseguradora.parametroImportacion){
                    paramValidMsj = validarParametroImportacion($scope.data.aseguradora.parametroImportacion);
                }
                if(!paramValidMsj){
                    if(!$scope.data.file || ($scope.data.file && $scope.data.logoValido)) {
                        service.create($scope.data.aseguradora, $scope.data.file)
                            .success(function (aseguradoraGuardada) {
                                success($scope, $state, aseguradoraGuardada);
                            })
                            .error(function () {
                                error();
                            });
                    }
                    else{
                        warning("La foto de perfil debe ser un archivo JPG con un tamaño no mayor a 100KB.");
                    }
                }
                else{
                    warning(paramValidMsj);
                }
            };

        }]);

        module.controller("AseguradoraEditCtrl", ["$state", "$stateParams", "$scope", "AseguradoraTemplateService", function ($state, $stateParams, $scope, service) {

            $scope.data = {};

            function init() {
                $scope.data.aseguradora = $stateParams.aseguradora;
            };
            init();

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('aseguradora.index');
            };

            $scope.guardar = function () {
                service.update($scope.data.aseguradora).success(function (aseguradoraGuardada) {
                    success($scope, $state, aseguradoraGuardada);
                })
                    .error(function () {
                        error();
                    });
            };
        }]);

        module.controller("AseguradoraViewCtrl", ["$state", "$stateParams", "$scope", "AseguradoraTemplateService", function ($state, $stateParams, $scope, service) {
            $scope.data = {};
            $scope.data.viewStateFlag = true;

            function init() {
                $scope.data.aseguradora = $stateParams.aseguradora;
            };
            init();

            $scope.volver = function () {
                $scope.data = {};
                $state.go('aseguradora.index');
            };
        }]);

        function success(scope, state, aseguradora) {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Registro realizado con éxito",
                title: "Confirmación",
                buttons: {
                    ok: {
                        label: "OK", className: "btn-primary", callback: function () {
                            scope.data = {};
                            state.go('aseguradora.index');
                        }
                    },
                    agregarCobertura: {
                        label: "Agregar cobertura", className: "btn-primary", callback: function () {
                            state.go('cobertura.create', {aseguradora:aseguradora});
                        }
                    }
                },
                closeButton: false
            });
        };

        function warning(msj) {
            bootbox.dialog({
                message: msj, title: "Advertencia",
                buttons: {
                    success: {
                        label: "OK", className: "btn-primary"
                    }
                },
                closeButton: false
            });
        };

        function error() {
            angular.element('#spinner').hide();
        };

        function validarParametroImportacion(parametroImportacion){
            fila = parametroImportacion.filaInicio && parametroImportacion.filaInicio != "";
            comision = parametroImportacion.nroColumnaComision && parametroImportacion.nroColumnaComision != "";
            fecha =  parametroImportacion.nroColumnaFech &&  parametroImportacion.nroColumnaFech != "";
            poliza = parametroImportacion.nroColumnaNroPoliza && parametroImportacion.nroColumnaNroPoliza != "";

            if( (fila || comision || fecha || poliza) && (!fila || !comision || !fecha || !poliza) )
            {
                return "Si desea ingresar parámetros de importación, debe completar todos los datos."
            }
            else if(
                (fila && comision && fecha && poliza)
                &&
                (parametroImportacion.nroColumnaComision == parametroImportacion.nroColumnaFech ||
                parametroImportacion.nroColumnaComision == parametroImportacion.nroColumnaNroPoliza ||
                parametroImportacion.nroColumnaFech == parametroImportacion.nroColumnaNroPoliza)  )
            {
                return "Los números de columna no pueden ser idénticos."
            }
        };
    };
    return constructor;
});