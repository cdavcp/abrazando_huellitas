define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("ProductorIndexCtrl", ["$state", "$stateParams", "$scope", "ProductorTemplateService", function ($state, $stateParams, $scope, service) {
            $scope.data = {};

            function init() {
                service.inicializarIndex().success(function (data) {
                    $scope.data.listaProductores = data;
                });
            };
            init();

            $scope.delete = function (productor) {
                bootbox.dialog({
                    message: "Está seguro que desea dar de baja al productor: " + productor.nombre + " " + productor.apellido,
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-primary",
                            callback: function () {
                                service.delete(productor).success(function () {
                                    angular.element('#spinner').hide();
                                    $scope.data.listaProductores.splice(getIndexFromId(productor.id, $scope.data.listaProductores), 1);
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

            $scope.editarClick = function (productor) {
                $state.go('productor.edit', {productor: productor});
            };

            $scope.verClick = function (productor) {
                $state.go('productor.view', {productor: productor});
            };
        }]);

        module.controller("ProductorCreateCtrl", ["$state", "$stateParams", "$rootScope", "$scope", "ProductorTemplateService", function ($state, $stateParams, $root, $scope, service) {
            function init() {
                service.inicializarCreate().success(function (data) {
                    $scope.data = data;
                    $scope.data.productor = {};
                    $scope.data.productor.usuario = {};
                    $scope.data.productor.vendedores = [];
                    $scope.data.usuario = $stateParams.usuario;
                });
            };
            init();

            $scope.quitarVendedor = function (item) {
                $scope.data.listaVendedores.push(item);
                $scope.data.productor.vendedores.splice(getIndexFromId(item.id, $scope.data.productor.vendedores), 1);
            };

            $scope.agregarVendedor = function (item) {
                $scope.data.listaVendedores.splice(getIndexFromId(item.id, $scope.data.listaVendedores), 1);
                $scope.data.productor.vendedores.push(item);
            };

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('productor.index');
            };

            $scope.guardar = function () {
                $scope.data.productor.usuario = $scope.data.usuario;
                service.create($scope.data.productor).success(function () {
                    success($scope, $state);
                })
                    .error(function () {
                        error();
                    });
            }
        }]);

        module.controller("ProductorViewCtrl", ["$state", "$stateParams", "$scope", "ProductorTemplateService", function ($state, $stateParams, $scope, service) {
            $scope.data = {};


            function init() {
                $scope.data.productor = $stateParams.productor;
                service.inicializarView($scope.data.productor.id).success(function (data) {
                    $scope.data = data;
                    $scope.data.viewStateFlag = true;
                });
            };
            init();

            $scope.volver = function () {
                $scope.data = {};
                $state.go('productor.index');
            };
        }]);

        module.controller("ProductorEditCtrl", ["$state", "$stateParams", "$scope", "ProductorTemplateService", "$rootScope", function ($state, $stateParams, $scope, service, $rootScope) {
            $scope.data = {};


            function init() {
                angular.element('#spinner').show();
                var id_Productor = "";
                var id_Usuario = "";
                if (!$stateParams.productor) {
                    id_Usuario = $rootScope.userProfile.id;
                } else {
                    id_Productor = $stateParams.productor.id;
                }
                service.inicializarEdit(id_Productor, id_Usuario).success(function (data) {
                    $scope.data = data;
                    //$rootScope.userProfile.avatar = $scope.data.productor.foto;
                    $scope.data.viewStateFlag = false;
                });
            };
            init();

            $scope.$on("fileSelected", function (evt, files) {
                $scope.data.file = files[0];
                if ($scope.data.file.size > $scope.MAX_FILE_SIZE_BYTES || $scope.data.file.name.indexOf(".jpg") == -1) {
                    $scope.data.logoValido = false;
                }
                else {
                    $scope.data.logoValido = true;
                }
            });

            $scope.quitarVendedor = function (item) {
                $scope.data.listaVendedores.push(item);
                $scope.data.productor.vendedores.splice(getIndexFromId(item.id, $scope.data.productor.vendedores), 1);
            };

            $scope.agregarVendedor = function (item) {
                $scope.data.listaVendedores.splice(getIndexFromId(item.id,  $scope.data.listaVendedores), 1);
                $scope.data.productor.vendedores.push(item);
            };

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('productor.index');
            };

            $scope.guardar = function () {
                if (!$scope.data.file || ($scope.data.file && $scope.data.logoValido)) {
                    service.update($scope.data.productor, $scope.data.file)
                        .success(function () {
                            if($rootScope.userProfile.tipoUsuario.id == 2){
                                service.inicializarEdit($scope.data.productor.id, $rootScope.userProfile.id).success(function (data) {
                                    $rootScope.userProfile.avatar = data.productor.foto;
                                    successPrincipal($scope, $state);
                                });
                            } else {
                                success($scope, $state);
                            }
                        })
                        .error(function () {
                            error();
                        });
                }
                else {
                    warning("La foto de perfil debe ser un archivo JPG con un tamaño no mayor a 100KB.");
                }
            };

        }]);

        function warning(msj) {
            bootbox.dialog({
                message: msj, title: "Advertencia", buttons: {
                    success: {
                        label: "OK", className: "btn-primary"
                    }
                }
            });
        };

        function successPrincipal(scope, state){
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Registro realizado con éxito", title: "Confirmación", buttons: {
                    success: {
                        label: "OK", className: "btn-primary", callback: function () {
                            scope.data = {};
                            state.go('dashboard');
                        }
                    }
                }
            });
        }

        function success(scope, state) {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Registro realizado con éxito",
                title: "Confirmación",
                buttons: {
                    success: {
                        label: "OK", className: "btn-primary", callback: function () {
                            scope.data = {};
                            state.go('productor.index');
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