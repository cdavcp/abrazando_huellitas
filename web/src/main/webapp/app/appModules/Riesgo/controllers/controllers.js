define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("RiesgoIndexCtrl", ["$state", "$stateParams", "$scope", "RiesgoTemplateService", function ($state, $stateParams, $scope, service) {

            function init() {
                service.inicializarIndex().success(function (data) {
                    $scope.data = {};
                    $scope.data.listaRiesgos = data;
                });
            };

            init();

            $scope.create = function () {
                $state.go('riesgo.create');
            };

            $scope.editarClick = function (riesgo) {
                $state.go('riesgo.edit', {riesgo: riesgo});
            };

            $scope.verCoberturas = function (riesgo) {
                service.buscarCoberturasDelRiesgo(riesgo.id).success(function (data) {
                    if (data && data.length != 0) {
                        $scope.data.riesgoSeleccionado = riesgo;
                        $scope.data.coberturasDelRiesgo = data;
                        angular.element("#modalCoberturasDelRiesgo").modal("show");
                    } else {
                        showMsj("No hay coberturas que incluyan el riesgo seleccionado.");
                    }
                });
            };

            $scope.delete = function (riesgo) {
                bootbox.dialog({
                    message: '¿Está seguro que desea eliminar el riesgo: "' + riesgo.nombre + '"? Se eliminará de todas las coberturas en las que esté incluido.',
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-primary",
                            callback: $scope.cbk = function () {
                                service.delete(riesgo).success(function () {
                                    angular.element('#spinner').hide();
                                    $scope.data.listaRiesgos.splice(getIndexFromId(riesgo.id, $scope.data.listaRiesgos), 1);
                                });
                            }
                        },
                        cancel: {
                            label: "Cancelar",
                            className: "btn-default"
                        }
                    }
                });
            };

        }]);


        module.controller("RiesgoCreateCtrl", ["$state", "$stateParams", "$scope", "RiesgoTemplateService", function ($state, $stateParams, $scope, service) {

            $scope.form = {};
            function init() {
                service.inicializarCreate().success(function (data) {
                    $scope.data = {};
                    $scope.data.listaRubros = data;
                    $scope.data.riesgo = {};
                    $scope.data.VALID_CLASS = "has-success";
                    $scope.data.INVALID_CLASS = "has-error";
                    $scope.data.nombreNuevoRiesgoValido = true;
                });
            };
            init();

            $scope.rubroSelected = function () {

                $scope.data.riesgo.nombre = "";
                $scope.data.nombreNuevoRiesgoValido = true;
                $scope.data.claseNombreRiesgo = "";

                if($scope.data.riesgo.rubro){
                    service.buscarCoberturasDelRubro($scope.data.riesgo.rubro.id).success(function (data) {
                        $scope.data.listaCoberturas = data;
                        $scope.data.coberturasPorAsociar = [];
                    });
                }
            };

            $scope.checkName = function () {
                if ($scope.data.riesgo.nombre && $scope.data.riesgo.nombre != "") {
                    $scope.data.checkingName = true;
                    service.checkNombreRiesgo($scope.data.riesgo).success(function (data) {
                        $scope.data.nombreNuevoRiesgoValido = (data == "true");
                        $scope.data.claseNombreRiesgo = $scope.data.nombreNuevoRiesgoValido ? $scope.data.VALID_CLASS : $scope.data.INVALID_CLASS;
                        $scope.data.checkingName = false;
                    })
                }
                else {
                    $scope.data.claseNombreRiesgo = $scope.data.INVALID_CLASS;
                }
            };

            $scope.agregarCobertura = function (idx) {
                $scope.data.coberturasPorAsociar.push($scope.data.listaCoberturas[idx]);
                $scope.data.listaCoberturas.splice(idx, 1);
            };

            $scope.quitarCobertura = function (idx) {
                $scope.data.listaCoberturas.push($scope.data.coberturasPorAsociar[idx]);
                $scope.data.coberturasPorAsociar.splice(idx, 1);
            };

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('riesgo.index');
            };

            $scope.guardar = function () {
                var dto = {riesgo: $scope.data.riesgo, coberturas: $scope.data.coberturasPorAsociar};
                service.create(dto).success(function () {
                    success($scope, $state);
                })
                    .error(function () {
                        error();
                    });
            };
        }]);

        module.controller("RiesgoEditCtrl", ["$state", "$stateParams", "$scope", "RiesgoTemplateService", function ($state, $stateParams, $scope, service) {

            $scope.form = {};
            function init() {
                $scope.data = {};
                $scope.data.riesgo = $stateParams.riesgo;
                $scope.data.VALID_CLASS = "has-success";
                $scope.data.INVALID_CLASS = "has-error";
                $scope.data.nombreOriginal = $scope.data.riesgo.nombre;
                $scope.data.nombreNuevoRiesgoValido = true;
            };
            init();

            $scope.checkName = function () {
                if($scope.data.riesgo.nombre == $scope.data.nombreOriginal){
                    $scope.data.nombreNuevoRiesgoValido = true;
                }
                else if ($scope.data.riesgo.nombre && $scope.data.riesgo.nombre != "") {
                    $scope.data.checkingName = true;
                    service.checkNombreRiesgo($scope.data.riesgo).success(function (data) {
                        $scope.data.nombreNuevoRiesgoValido = (data == "true");
                        $scope.data.claseNombreRiesgo = $scope.data.nombreNuevoRiesgoValido ? $scope.data.VALID_CLASS : $scope.data.INVALID_CLASS;
                        $scope.data.checkingName = false;
                    })
                }
                else {
                    $scope.data.claseNombreRiesgo = $scope.data.INVALID_CLASS;
                }
            };

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('riesgo.index');
            };

            $scope.guardar = function () {
                service.update($scope.data.riesgo).success(function () {
                    success($scope, $state);
                })
                    .error(function () {
                        error();
                    });
            };

        }]);

        function success(scope, state, stay) {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Registro realizado con éxito",
                title: "Confirmación",
                buttons: {
                    success: {
                        label: "OK", className: "btn-primary", callback: function () {
                            if (!stay) {
                                scope.data = {};
                                state.go('riesgo.index');
                            }
                        }
                    }
                },
                closeButton: false
            });
        };

        function error() {
            angular.element('#spinner').hide();
        };

        function showMsj(msj) {
            angular.element('#spinner').hide();
            bootbox.dialog({
                title: "Advertencia",
                message: msj,
                buttons: {
                    success: {
                        label: "OK", className: "btn-primary"
                    }
                }
            });
        };

        function getIndexFromId(itemId, lista){
            for(i=0;i<lista.length;i++){
                if(lista[i].id == itemId){
                    return i;
                }
            }
        }
    };
    return constructor;
});