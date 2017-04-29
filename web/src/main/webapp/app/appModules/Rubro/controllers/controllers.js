define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("RubroIndexCtrl", ["$state", "$stateParams", "$scope", "RubroTemplateService", "$window", function ($state, $stateParams, $scope, service, $window) {

            function init() {
                $scope.data = {};
                service.inicializarIndex().success(function (data) {
                    $scope.data.listaRubros = data;
                });
            };

            function getUrl(path){
                var urlCreator = window.URL || window.webkitURL;
               return urlCreator.createObjectURL(new File(path));
            }

            init();

            $scope.create = function () {
                $state.go('rubro.create');
            };

        }]);

        module.controller("RubroCreateCtrl", ["$state", "$stateParams", "$scope", "RubroTemplateService", function ($state, $stateParams, $scope, service) {
            $scope.form = {};

            function init() {
                $scope.MAX_FILE_SIZE_BYTES = 100000;
                $scope.data = {};
                $scope.data.rubro = {};
                $scope.data.logoValido = false;
                $scope.data.nombreNoValido = true;
                $scope.data.checkingName = false;
                $scope.data.VALID_CLASS = "has-success";
                $scope.data.INVALID_CLASS = "has-error";
            };
            init();

            $scope.$on("fileSelected", function (evt, files) {
                $scope.data.file = files[0];
                if($scope.data.file.size > $scope.MAX_FILE_SIZE_BYTES || $scope.data.file.name.indexOf(".jpg")==-1){
                    $scope.data.logoValido = false;
                }
                else{
                    $scope.data.logoValido = true;
                }
            });

            $scope.checkName = function(){
                if($scope.data.rubro.nombre && $scope.data.rubro.nombre != ""){
                    $scope.data.checkingName = true;
                    service.checkNombreRubro($scope.data.rubro.nombre).success(function (data) {
                        $scope.data.nombreValido = (data == "true");
                        $scope.data.claseNombreRubro = $scope.data.nombreValido ? $scope.data.VALID_CLASS : $scope.data.INVALID_CLASS;
                        $scope.data.checkingName = false;
                    })
                }
                else{
                    $scope.data.claseNombreRubro = $scope.data.INVALID_CLASS;
                }
            };

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('rubro.index');
            };

            $scope.guardar = function () {
                if(!$scope.data.file || ($scope.data.file && $scope.data.logoValido)){
                    service.create($scope.data.rubro, $scope.data.file).success(function () {
                        success($scope, $state);
                    })
                        .error(function () {
                            error();
                        });
                }
                else{
                    warning("El logo debe ser un archivo JPG con un tamaño no mayor a 100KB.");
                }
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
                            state.go('rubro.index');
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
        };

    };
    return constructor;
});