define(['bootbox'], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("ComisionIndexCtrl", ["$state", "$stateParams", "$scope", "ComisionTemplateService", "$rootScope", function ($state, $stateParams, $scope, service, $rootScope) {

            function init() {
                service.inicializarIndex($rootScope.userProfile.id).success(function (data) {
                    $scope.data = data;
                });
            }

            init();

            $scope.setRowClass = function (idEstado) {
                switch (idEstado) {
                    case $scope.data.idEstadoRechazado:
                        return 'danger';
                        break;
                    case $scope.data.idEstadoProcesado:
                        return 'success';
                        break;
                    case $scope.data.idEstadoProcesadoParcial:
                        return 'warning';
                        break;
                    default:
                        return 'active';
                }
            };

            $scope.createArchivo = function () {
                $state.go('comision.createA');
            };

            $scope.createManual = function () {
                $state.go('comision.createM');
            };

            $scope.editarClick = function (item) {
                var param = {loteId: item.id};
                $state.go('comision.edit', {param: param});
            };

            $scope.verClick = function(item){
                var param = {loteId: item.id};
                $state.go('comision.view', {param: param});
            };

            $scope.deleteClick = function (item) {
                bootbox.dialog({
                    message: "Si elimina el lote también se eliminaran todas las comisiones importadas ¿Está seguro que desea continuar?",
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-primary",
                            callback: function () {
                                service.delete(item.id).success(function () {
                                    $scope.data.listaLotes.splice(getIndexFromId(item.id, $scope.data.listaLotes), 1);
                                    angular.element('#spinner').hide();
                                })
                                    .error(function () {
                                        error();
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

        function success() {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Registro realizado con éxito",
                title: "Confirmación",
                buttons: {
                    success: {
                        label: "OK", className: "btn-primary"
                    }
                },
                closeButton: false
            });
        }

        function error() {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Se ha producido un error al eliminar el lote, por favor intentelo de nuevo.", title: "Error", buttons: {
                    success: {label: "OK", className: "btn-primary"}
                }
            });
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