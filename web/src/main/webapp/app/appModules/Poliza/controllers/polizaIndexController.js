define(['bootbox'], function(bootbox) {
    var constructor = {};
    constructor.init = function(module) {        
        module.controller("PolizaIndexCtrl", ["$state", "$stateParams", "$scope", "PolizaTemplateService","$rootScope", "$modal", function($state, $stateParams, $scope, service, $rootScope, $modal) {
            $scope.ESTADOS_MODAL_TEMPLATE = MODULES_DIR.concat("/Poliza/views/estadosModal.html");
            $scope.LABEL_ESTADOS = "Pólizas activas";

            function init() {
                var idUsuarioLogueado = $rootScope.userProfile.id;
                service.inicializar(idUsuarioLogueado).success(function (data) {
                    $scope.data = data;
                    $scope.data.filtros = {};
                    $scope.data.filtros.productorId = $scope.data.poliza.productor.id;
                    $scope.data.estados = [null, true, true, true, true, false];
                });
            };
            init();

            $scope.consultar = function(){
                $scope.data.filtros.estados = [];
                for(var i = 1; i <= 5; i++){
                    if($scope.data.estados[i] == true){
                        $scope.data.filtros.estados.push(i);
                    }
                }

                service.consultar($scope.data.filtros).success(function (data) {
                    $scope.data.listaPolizas = data;
                });
            };

            $scope.delete = function(cliente, index){
                $scope.data.polizasPaginated.splice(index, 1);
            };

            $scope.create = function () {
                $state.go('poliza.create');
            };

            $scope.view = function (id) {
                var param = {idPoliza: id};
                $state.go('poliza.view', {param: param});
            };

            $scope.deleteClick = function (id) {
                bootbox.dialog({
                    message: "La póliza se dará de baja de forma permanente. ¿Está seguro que desea continuar?", title: "Confirmación", buttons: {
                        success: {
                            label: "Aceptar", className: "btn-primary", callback: function () {
                                service.darDeBaja(id).success(function(){
                                    success();
                                }).error(function(){
                                    error();
                                });
                            }
                        },
                        cancel: {label: "Cancelar", className: "btn-primary"}}
                });
            };


            $scope.soloActivasClick = function(){
                $scope.data.SOLO_ACTIVAS = true;
                $scope.data.DADAS_BAJA = false;
                $scope.data.PERSONALIZADO = false;
                $scope.LABEL_ESTADOS = "Pólizas activas";
                $scope.data.estados[1] = true;
                $scope.data.estados[2] = true;
                $scope.data.estados[3] = true;
                $scope.data.estados[4] = true;
                $scope.data.estados[5] = false;
            };

            $scope.soloDadasBajaClick = function(){
                $scope.data.SOLO_ACTIVAS = false;
                $scope.data.DADAS_BAJA = true;
                $scope.data.PERSONALIZADO = false;
                $scope.LABEL_ESTADOS = "Pólizas dadas de baja";
                $scope.data.estados[1] = false;
                $scope.data.estados[2] = false;
                $scope.data.estados[3] = false;
                $scope.data.estados[4] = false;
                $scope.data.estados[5] = true;
            };

            $scope.personalizadoClick = function(){
                $scope.data.SOLO_ACTIVAS = false;
                $scope.data.DADAS_BAJA = false;
                $scope.data.PERSONALIZADO = true;
                $scope.LABEL_ESTADOS = "Perzonalizado";
            };

            $scope.setRowClass = function (idEstado) {
                switch (idEstado) {
                    case $scope.data.idEstadoNueva:
                        return 'info';
                        break;
                    case $scope.data.idEstadoAlDia:
                        return 'success';
                        break;
                    case $scope.data.idEstadoAtrasada:
                        return 'warning';
                        break;
                    case $scope.data.idEstadoMorosa:
                        return 'danger';
                        break;
                    default:
                        return 'active';
                }
            };

            function success() {
                angular.element('#spinner').hide();
                bootbox.dialog({
                    message: "La póliza se ha dado de baja existosamente.",
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "OK", className: "btn-primary", callback: function () {
                                init();
                            }
                        }
                    },
                    closeButton: false
                });
            };

            function error() {
                angular.element('#spinner').hide();
                bootbox.dialog({
                    message: "Se ha producido un error al eliminar la póliza, por favor inténtelo de nuevo", title: "Error", buttons: {
                        success: {
                            label: "OK", className: "btn-primary"
                        }
                    }
                });
            };            

        }]);
        module.controller("PolizaEstadosCtrl", ["$scope", function($scope) {
            this.parent = $scope.$parent;
        }]);
    };
    return constructor;
});