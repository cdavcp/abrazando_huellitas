define([], function () {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("PolizaViewCtrl", ["$state", "$stateParams", "$scope", "PolizaTemplateService", "$rootScope", function ($state, $stateParams, $scope, service, $rootScope) {
            $scope.form = {};
            $scope.data = {};
            $scope.data.ESTADOS = {1:'Nueva', 2:'Al Dia', 3:'Atrasada', 4:'Morosa', 5:'Dada de baja'};
            $scope.data.ESTILOS = {1:'text-primary', 2:'text-success', 3:'text-warning', 4:'text-danger', 5:''};
            function init() {
                if($stateParams.param)
                {
                    var idPoliza = $stateParams.param.idPoliza;
                    service.inicializarView(idPoliza).success(function (data) {
                        $scope.data.poliza = data;
                    });
                } else {
                    $state.go('poliza.index');
                }
            }

            init();

            $scope.volver = function () {
                $scope.data = {};
                $state.go('poliza.index');
            };
        }]);
    };
    return constructor;
});