define(["bootbox"], function(bootbox) {
    var constructor = {};
    constructor.init = function(module) {
        module.controller("ComisionCreateArchivoCtrl", ["$state", "$stateParams", "$scope", "ComisionTemplateService", "$rootScope", function($state, $stateParams, $scope, service, $rootScope) {
            $scope.form = {};


            function init() {
                service.inicializarCreateArchivo($rootScope.userProfile.id).success(function(data){
                    $scope.data = data;
                    $scope.data.disableNombreArchivo = true;
                    $scope.data.lote = {};
                    $scope.data.lote.productor = {};
                    $scope.data.lote.productor.id = $scope.data.productor.id;
                });
            };
            init();

            $scope.cancelar = function () {
                $scope.data = {};
                $state.go('comision.index');
            };

            $scope.guardar = function () {
                if($scope.data.file) {
                    service.procesarArchivo($scope.data.lote, $scope.data.file).success(function (data) {
                        if (data) {
                            $scope.data.idLote = data;
                            success($scope, $state);
                        } else {
                            errorLoteRepetido();
                        }

                    }).error(function () {
                            error();
                        });
                } else {
                    showErrors("Sleccione un archivo");
                }
            };

            $scope.$on("fileSelected", function (evt, files) {
                if(files[0])
                {
                    var validExtensions = [".csv"];
                    var extension = files[0].name.substring(files[0].name.lastIndexOf('.'));
                    if (validExtensions.indexOf(extension) < 0) {
                        showErrors("Tipo de archivo inválido, las extensiones válidas son: " + validExtensions.toString());
                    }
                    else {
                        $scope.data.file = files[0];
                        $scope.data.lote.nombre = $scope.data.file.name;
                    }
                }
            });
        }]);

        function success(scope, state) {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Registro realizado con éxito", title: "Confirmación", buttons: {
                    success: {
                        label: "OK", className: "btn-primary", callback: function () {
                            scope.data = {};
                            state.go('comision.index');
                        }
                    }
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
                },
                closeButton: false
            });
            $('.modal-body').css('word-wrap', 'break-word');
        };

        function errorLoteRepetido() {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Ya hay un lote procesado para el mes y la aseguradora seleccionados.", title: "Error", buttons: {
                    success: {label: "OK", className: "btn-primary"}
                }
            });
        };

        function error() {
            angular.element('#spinner').hide();
            bootbox.dialog({
                message: "Se ha producido un error al procesar las comisiones, por favor intentelo de nuevo.", title: "Error", buttons: {
                    success: {label: "OK", className: "btn-primary"}
                }
            });
        };
    };
    return constructor;
});