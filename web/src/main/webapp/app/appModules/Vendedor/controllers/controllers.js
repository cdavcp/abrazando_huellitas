define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("VendedorIndexCtrl", ["$state", "$stateParams", "$scope", "VendedorTemplateService", function ($state, $stateParams, $scope, service) {

            $scope.data = {};

            function init() {
                service.inicializarIndex().success(function (data) {
                    $scope.data.listaVendedores = data;
                });
            };
            init();

            $scope.delete = function (vendedor) {
                bootbox.dialog({
                    message: "Está seguro que desea eliminar al vendedor: " + vendedor.nombre + " " + vendedor.apellido,
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-primary",
                            callback: function () {
                                service.delete(vendedor).success(function () {
                                    angular.element('#spinner').hide();
                                    $scope.data.listaVendedores.splice(getIndexFromId(vendedor.id, $scope.data.listaVendedores), 1);
                                })
                            }
                        },
                        cancel: {
                            label: "Cancelar",
                            className: "btn-default"
                        }
                    }
                });
            };

            $scope.create = function () {
                $state.go('vendedor.create');
            };

            $scope.editarClick = function (vendedor) {
                $state.go('vendedor.edit', {vendedor: vendedor});
            };

            $scope.verClick = function (vendedor) {
                $state.go('vendedor.view', {vendedor: vendedor});
            };
        }]);

        module.controller("VendedorCreateCtrl", ["$state", "$stateParams", "$scope", "VendedorTemplateService", function ($state, $stateParams, $scope, service) {

            $scope.data = {};
            $scope.data.vendedor = {};

            function init() {
                service.inicializarCreate().success(function (data) {
                    $scope.data.listaTiposDocumento = data;
                });
            };
            init();

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('vendedor.index');
            };

            $scope.guardar = function () {
                service.create($scope.data.vendedor).success(function () {
                    success($scope, $state);
                })
                    .error(function () {
                        error();
                    });
            };
        }]);

        module.controller("VendedorEditCtrl", ["$state", "$stateParams", "$scope", "VendedorTemplateService", function ($state, $stateParams, $scope, service) {

            $scope.data = {};

            function init() {
                $scope.data.vendedor = $stateParams.vendedor;
                service.inicializarCreate().success(function (data) {
                    $scope.data.listaTiposDocumento = data;
                });
            };
            init();

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('vendedor.index');
            };

            $scope.guardar = function () {
                service.update($scope.data.vendedor).success(function () {
                    success($scope, $state);
                })
                    .error(function () {
                        error();
                    });
            }
        }]);

        module.controller("VendedorViewCtrl", ["$state", "$stateParams", "$scope", "VendedorTemplateService", function ($state, $stateParams, $scope, service) {
            $scope.data = {};
            $scope.data.viewStateFlag = true;

            function init() {
                $scope.data.vendedor = $stateParams.vendedor;
                service.inicializarCreate().success(function (data) {
                    $scope.data.listaTiposDocumento = data;
                });
            };
            init();

            $scope.volver = function () {
                $scope.data = {};
                $state.go('vendedor.index');
            };
        }]);

        function success(scope, state) {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Registro realizado con éxito",
                title: "Confirmación",
                buttons: {
                    success: {
                        label: "OK", className: "btn-primary", callback: function () {
                            scope.data = {};
                            state.go('vendedor.index');
                        }
                    }
                },
                closeButton: false
            });
        }

        function error() {
            angular.element('#spinner').hide();
        }

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