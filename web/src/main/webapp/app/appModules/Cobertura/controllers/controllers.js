define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("CoberturaIndexCtrl", ["$state", "$stateParams", "$scope", "CoberturaTemplateService", function ($state, $stateParams, $scope, service) {
            $scope.data = {};

            function init() {
                service.inicializarIndex().success(function (data) {
                    $scope.data.listaCoberturas = data;
                    angular.forEach($scope.data.listaCoberturas, function(item){
                        if(item.porcentajePrecio)
                            item.porcentajePrecioStr = item.porcentajePrecio + ' %';
                    });
                });
            };

            init();

            $scope.create = function () {
                $state.go('cobertura.create');
            };

            $scope.editarClick = function (cobertura) {
                $state.go('cobertura.edit', {cobertura: cobertura});
            };

            $scope.verClick = function (cobertura) {
                $state.go('cobertura.view', {cobertura: cobertura});
            };

        }]);

        module.controller("CoberturaCreateCtrl", ["$state", "$stateParams", "$scope", "CoberturaTemplateService", function ($state, $stateParams, $scope, service) {
            $scope.form = {};
            $scope.RUBRO_AUTO_ID = 1;

            function init() {
                service.inicializarCreate().success(function (data) {
                    $scope.data = data;
                    $scope.data.nombreNoValido = true;
                    $scope.data.checkingName = false;
                    $scope.data.precioFijoChecked = true;
                    $scope.data.cobertura = {};
                    $scope.data.VALID_CLASS = "has-success";
                    $scope.data.INVALID_CLASS = "has-error";
                    $scope.data.disableAseguradora = false;
                    if($stateParams.aseguradora){
                        $scope.data.cobertura.aseguradora = $stateParams.aseguradora;
                        $scope.data.disableAseguradora = true;
                    }
                });
            };
            init();

            $scope.rubroSelected = function () {
                service.buscarRiesgos($scope.data.cobertura.rubro.id).success(function (data) {
                    $scope.data.listaRiesgos = data;
                    $scope.data.cobertura.riesgos = [];
                });
                $scope.data.precioFijoChecked =  ($scope.data.cobertura.rubro.id != $scope.RUBRO_AUTO_ID);
            };

            $scope.precioChanged = function () {
                if ($scope.data.precioFijoChecked) {
                    $scope.data.cobertura.porcentajePrecio = null;
                }
                else {
                    $scope.data.cobertura.precio = null;
                }
            };

            $scope.agregarRiesgo = function (idx) {
                $scope.data.cobertura.riesgos.push($scope.data.listaRiesgos[idx]);
                $scope.data.listaRiesgos.splice(idx, 1);
            };

            $scope.quitarRiesgo = function (idx) {
                $scope.data.listaRiesgos.push($scope.data.cobertura.riesgos[idx]);
                $scope.data.cobertura.riesgos.splice(idx, 1);
            };

            $scope.nuevoRiesgo = function () {
                $scope.data.nuevoRiesgo = {};
                $scope.data.nombreNuevoRiesgoValido = true;
            };

            $scope.checkNameCobertura = function(){
                if($scope.data.cobertura.nombre && $scope.data.cobertura.nombre != ""){
                    $scope.data.checkingName = true;
                    service.checkNombreCobertura($scope.data.cobertura).success(function (data) {
                        $scope.data.nombreValido = (data == "true");
                        $scope.data.claseNombreCobertura = $scope.data.nombreValido ? $scope.data.VALID_CLASS : $scope.data.INVALID_CLASS;
                        $scope.data.checkingName = false;
                        $scope.form.coberturaCreateForm.nombre.$invalid = !$scope.data.nombreValido;
                    })
                }
                else{
                    $scope.data.claseNombreCobertura = $scope.data.INVALID_CLASS;
                }
            };

            $scope.checkName = function () {
                if ($scope.data.nuevoRiesgo.nombre && $scope.data.nuevoRiesgo.nombre != "") {
                    $scope.data.checkingName = true;
                    $scope.data.nuevoRiesgo.rubro = $scope.data.cobertura.rubro;
                    service.checkNombreRiesgo($scope.data.nuevoRiesgo).success(function (data) {
                        $scope.data.nombreNuevoRiesgoValido = (data == "true");
                        $scope.data.claseNombreRiesgo = $scope.data.nombreNuevoRiesgoValido ? $scope.data.VALID_CLASS : $scope.data.INVALID_CLASS;
                        $scope.data.checkingName = false;
                    })
                }
                else {
                    $scope.data.claseNombreRiesgo = $scope.data.INVALID_CLASS;
                }
            };

            $scope.createRiesgo = function () {
                $scope.data.nuevoRiesgo.rubro = $scope.data.cobertura.rubro;
                service.createRiesgo($scope.data.nuevoRiesgo).success(function (riesgoGuardado) {
                    $scope.data.cobertura.riesgos.push(riesgoGuardado);
                    success($scope, $state, true);
                })
                    .error(function () {
                        error();
                    });
            };

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('cobertura.index');
            };

            $scope.guardar = function () {
                service.create($scope.data.cobertura).success(function () {
                    success($scope, $state);
                })
                    .error(function () {
                        error();
                    });
            };
        }]);

        module.controller("CoberturaEditCtrl", ["$state", "$stateParams", "$scope", "CoberturaTemplateService", function ($state, $stateParams, $scope, service) {

            function init() {
                $scope.form = {};
                service.inicializarEdit($stateParams.cobertura.id).success(function (data) {
                    $scope.data = data;
                    $scope.data.nombreValido = true;
                    $scope.data.precioFijoChecked = $scope.data.cobertura.precio ? true : false;
                    $scope.data.VALID_CLASS = "has-success";
                    $scope.data.INVALID_CLASS = "has-error";
                });
            };
            init();

            $scope.rubroSelected = function () {
                service.buscarRiesgos($scope.data.cobertura.rubro.id).success(function (data) {
                    $scope.data.listaRiesgos = data;
                });
            };

            $scope.precioChanged = function () {
                if ($scope.data.precioFijoChecked) {
                    $scope.data.cobertura.porcentajePrecio = null;
                }
                else {
                    $scope.data.cobertura.precio = null;
                }
            };

            $scope.agregarRiesgo = function (idx) {
                if($scope.data.cobertura.riesgos == null)
                {
                    $scope.data.cobertura.riesgos = [];
                }
                $scope.data.cobertura.riesgos.push($scope.data.listaRiesgos[idx]);
                $scope.data.listaRiesgos.splice(idx, 1);
            };

            $scope.quitarRiesgo = function (idx) {
                $scope.data.listaRiesgos.push($scope.data.cobertura.riesgos[idx]);
                $scope.data.cobertura.riesgos.splice(idx, 1);
            };

            $scope.nuevoRiesgo = function () {
                $scope.data.nuevoRiesgo = {};
                $scope.data.nombreNuevoRiesgoValido = true;
            };

            $scope.checkNameCoberturaEdit = function(){
                if($scope.data.cobertura.nombre && $scope.data.cobertura.nombre != ""){
                    $scope.data.checkingName = true;
                    service.checkNombreCoberturaEdit($scope.data.cobertura).success(function (data) {
                        $scope.data.nombreValido = (data == "true");
                        $scope.data.claseNombreCobertura = $scope.data.nombreValido ? $scope.data.VALID_CLASS : $scope.data.INVALID_CLASS;
                        $scope.data.checkingName = false;
                        $scope.form.coberturaCreateForm.nombre.$invalid = !$scope.data.nombreValido;
                    })
                }
                else{
                    $scope.data.claseNombreCobertura = $scope.data.INVALID_CLASS;
                }
            };

            $scope.checkName = function () {
                if ($scope.data.nuevoRiesgo.nombre && $scope.data.nuevoRiesgo.nombre != "") {
                    $scope.data.checkingName = true;
                    $scope.data.nuevoRiesgo.rubro = $scope.data.cobertura.rubro;
                    service.checkNombreRiesgo($scope.data.nuevoRiesgo).success(function (data) {
                        $scope.data.nombreNuevoRiesgoValido = (data == "true");
                        $scope.data.claseNombreRiesgo = $scope.data.nombreNuevoRiesgoValido ? $scope.data.VALID_CLASS : $scope.data.INVALID_CLASS;
                        $scope.data.checkingName = false;
                    })
                }
                else {
                    $scope.data.claseNombreRiesgo = $scope.data.INVALID_CLASS;
                }
            };

            $scope.createRiesgo = function () {
                $scope.data.nuevoRiesgo.rubro = $scope.data.cobertura.rubro;
                service.createRiesgo($scope.data.nuevoRiesgo).success(function (riesgoGuardado) {
                    if($scope.data.cobertura.riesgos == null)
                    {
                        $scope.data.cobertura.riesgos = [];
                    }
                    $scope.data.cobertura.riesgos.push(riesgoGuardado);
                    success($scope, $state, true);
                })
                    .error(function () {
                        error();
                    });
            };

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('cobertura.index');
            };

            $scope.guardar = function () {
                service.update($scope.data.cobertura).success(function () {
                    success($scope, $state);
                })
                    .error(function () {
                        error();
                    });
            };

        }]);

        module.controller("CoberturaViewCtrl", ["$state", "$stateParams", "$scope", "CoberturaTemplateService", function ($state, $stateParams, $scope, service) {

            function init() {
                service.inicializarEdit($stateParams.cobertura.id).success(function (data) {
                    $scope.data = data;
                    $scope.data.viewStateFlag = true;
                    $scope.data.precioFijoChecked = $scope.data.cobertura.precio ? true : false;
                    $scope.data.VALID_CLASS = "has-success";
                    $scope.data.INVALID_CLASS = "has-error";
                });
            };
            init();

            $scope.guardar = function () {
                delete $scope.data.cobertura.rubro;
                service.create($scope.data.cobertura).success(function () {
                    success($scope, $state);
                })
                    .error(function () {
                        error();
                    });
            };

            $scope.volver = function () {
                $scope.data = {};
                $state.go('cobertura.index');
            };
        }]);

        function success(scope, state, stay) {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Registro realizado con éxito", title: "Confirmación",
                buttons: {
                    success: {
                        label: "OK", className: "btn-primary", callback: function () {
                            if(!stay){
                                scope.data = {};
                                state.go('cobertura.index');
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

    };
    return constructor;
});