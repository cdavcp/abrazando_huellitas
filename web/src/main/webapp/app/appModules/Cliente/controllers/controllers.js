define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("ClienteIndexCtrl", ["$state", "$stateParams", "$scope", "ClienteTemplateService", function ($state, $stateParams, $scope, service) {
            $scope.data = {};

            function init() {
                service.inicializarIndex().success(function (data) {
                    $scope.data.listaClientes = data;
                });
            };
            init();

            $scope.delete = function (cliente) {
                bootbox.dialog({
                    message: "Está seguro que desea eliminar al cliente: " + cliente.nombre + " " + cliente.apellido,
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-primary",
                            callback: function () {
                                service.delete(cliente).success(function () {
                                    angular.element('#spinner').hide();
                                    $scope.data.listaClientes.splice(getIndexFromId(cliente.id, $scope.data.listaClientes), 1);
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

            $scope.create = function () {
                $state.go('cliente.create');
            };

            $scope.editarClick = function (cliente) {
                $state.go('cliente.edit', {cliente: cliente});
            };

            $scope.verClick = function (cliente) {
                $state.go('cliente.view', {cliente: cliente});
            };
        }]);

        module.controller("ClienteCreateCtrl", ["$state", "$stateParams", "$scope", "ClienteTemplateService", function ($state, $stateParams, $scope, service) {

            function init() {
                service.inicializarCreate().success(function (data) {
                    $scope.data = {};
                    $scope.data.cliente = {};
                    $scope.data.logoValido = false;
                    $scope.data.listaTiposDocumento = data;
                    $scope.MAX_FILE_SIZE_BYTES = 100000;
                });
            };
            init();

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('cliente.index');
            };

            $scope.guardar = function () {
                service.exists($scope.data.cliente).success(function(existe){
                    if(existe == 'false'){
                        service.create($scope.data.cliente)
                            .success(function () {
                                success($scope, $state);
                            })
                            .error(function () {
                                error();
                            });
                    } else{
                        warning("Ya existe un cliente con el tipo y número de documento ingresados.")
                    }
                });

            };
        }]);

        module.controller("ClienteEditCtrl", ["$state", "$stateParams", "$scope", "ClienteTemplateService", function ($state, $stateParams, $scope, service) {

            $scope.data = {};

            function init() {
                $scope.data.cliente = $stateParams.cliente;
                service.inicializarCreate().success(function (data) {
                    $scope.data.listaTiposDocumento = data;
                });
            };
            init();

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('cliente.index');
            };

            $scope.guardar = function () {
                service.update($scope.data.cliente)
                    .success(function () {
                        success($scope, $state);
                    })
                    .error(function () {
                        error();
                    });
            }
        }]);

        module.controller("ClienteViewCtrl", ["$state", "$stateParams", "$scope", "ClienteTemplateService", function ($state, $stateParams, $scope, service) {
            $scope.data = {};
            $scope.data.viewStateFlag = true;

            function init() {
                $scope.data.cliente = $stateParams.cliente;
                service.inicializarCreate().success(function (data) {
                    $scope.data.listaTiposDocumento = data;
                });
            };
            init();

            $scope.volver = function () {
                $scope.data = {};
                $state.go('cliente.index');
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
                            state.go('cliente.index');
                        }
                    }
                },
                closeButton: false
            });
        };

        function warning(msj) {
            bootbox.dialog({
                message: msj, title: "Advertencia", buttons: {
                    success: {
                        label: "OK", className: "btn-primary"
                    }
                }
            });
        };

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