define(['bootbox'], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("ComisionEditCtrl", ["$state", "$stateParams", "$scope", "ComisionTemplateService", "$rootScope", function ($state, $stateParams, $scope, service, $rootScope) {
            $scope.form = {};

            function init() {
                if ($rootScope.comisionData) {
                    $scope.data = $rootScope.comisionData;
                    if($stateParams.param && $stateParams.param.poliza)
                    {
                        $scope.data.listaPolizas.push($stateParams.param.poliza);
                        $scope.data.comision.numeroPoliza = $stateParams.param.poliza.numero;
                    }
                }
                else {
                    service.inicializarEdit($stateParams.param.loteId).success(function (data) {
                        $scope.data = data;
                        $scope.data.disableNumeroFila = true;
                        $scope.data.comision = {};
                        $scope.data.listaNuevasComisiones = [];
                        $scope.data.listaComisionesBorradas = [];
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
                service.procesarActualizacion($scope.data.lote, $scope.data.listaNuevasComisiones, $scope.data.listaComisionesBorradas).success(function () {
                    success($scope, $state, $rootScope);
                })
                    .error(function () {
                        error();
                    });
            };

            $scope.agregar = function () {
                $scope.data.listaNuevasComisiones.push($scope.data.comision);
                $scope.data.comision = {};
            };

            $scope.eliminar = function(item, index){
                $scope.data.lote.comisionesIncompletas.splice(index, 1);
                $scope.data.listaComisionesBorradas.push(item.id);
            };

            $scope.corregir = function (item, index) {
                if($scope.data.comision.id) {
                    showErrors("Ya se encuentra seleccionada una comisión para corregir.");
                } else{
                    $scope.data.lote.comisionesIncompletas.splice(index, 1);
                    $scope.data.comision = item;
                }
            };

            $scope.deshacerCorreccion = function(item, index){
                if($scope.data.comision.id) {
                    showErrors("Ya se encuentra seleccionada una comisión para corregir.");
                } else {
                    $scope.data.listaNuevasComisiones.splice(index, 1);
                    $scope.data.comision = item;
                }
            };

            $scope.nuevaPoliza = function () {
                $rootScope.comisionData = $scope.data;
                var param = {previousState: 'comision.edit', aseguradoraId: $scope.data.lote.aseguradora.id, numeroPoliza: $scope.data.comision.numeroPoliza};
                $state.go('poliza.create', {param: param});
            };

            $scope.tieneVariosErrores = function(item){
                return item.error.indexOf(',') != -1 || item.error.indexOf('.') != -1;
            };
        }]);

        function success(scope, state, root) {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Registro realizado con éxito",
                title: "Confirmación",
                buttons: {
                    success: {
                        label: "OK", className: "btn-primary", callback: function () {
                            root.comisionData = undefined;
                            scope.data = {};
                            state.go('comision.index');
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

        function showErrors(message) {
            bootbox.dialog({
                message: message,
                title: '<span class="glyphicon glyphicon-warning-sign"></span> Advertencia',
                buttons: {
                    'btn-error-dialog-ok': {
                        label: 'OK',
                        className: 'btn-primary'
                    }
                }
            });
            $('.modal-body').css('word-wrap', 'break-word');
        };
    };
    return constructor;
});