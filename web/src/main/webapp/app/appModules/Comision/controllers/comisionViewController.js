define([], function () {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("ComisionViewCtrl", ["$state", "$stateParams", "$scope", "ComisionTemplateService", function ($state, $stateParams, $scope, service) {
            $scope.form = {};
            $scope.data = {};
            function init() {
                if($stateParams.param)
                {
                    var idLote = $stateParams.param.loteId;
                    service.inicializarView(idLote).success(function (data) {
                        $scope.data.lote = data;
                    });
                } else {
                    $state.go('comision.index');
                }
            }

            init();

            $scope.volver = function () {
                $scope.data = {};
                $state.go('comision.index');
            };
        }]);
    };
    return constructor;
});