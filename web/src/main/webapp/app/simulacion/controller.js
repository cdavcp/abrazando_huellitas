define(["bootbox", "wizard"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("SimulacionCtrl", ["$q", "$rootScope", "$state", "$scope", "SimulacionService", function ($q, $root, $state, $scope, service) {

            $scope.init = function () {
                $scope.data = {};
                $scope.data.pestanaActual = 0;
                $scope.data.RUBRO_AUTO_ID = 1;

                $scope.data.cliente = {};
                buscarTiposDocumento();

                service.inicializarIndex().then(function (response) {
                    $scope.data.listaRubros = response.data;
                    var largoColumna = response.data.length != 0 ? Math.round((12 / response.data.length)) : 12;
                    $scope.data.rubroColumnClass = "col-sm-" + largoColumna;
                });

            };
            $scope.init();

            $(document).ready(onDomReady);

            function searchTiposCobertura() {
                if (!$scope.data.listaTiposCobertura) {
                    return service.searchTiposCobertura().success(function (data) {
                        var largoColumna = data.length != 0 ? Math.round((12 / data.length)) : 12;
                        $scope.data.tipoCoberturaColumnClass = "col-sm-" + largoColumna;
                        $scope.data.listaTiposCobertura = data;
                    });
                } else {
                    var defer = $q.defer();
                    defer.resolve(true);
                    return defer.promise;
                }
            };

            //$scope.login = function () {
            //    $scope.data.cliente = {};
            //    buscarTiposDocumento();
            //    angular.element("#modalLogin").modal("show");
            //};

            //$scope.ingresar = function () {
            //    service.ingresar($scope.data.cliente).success(function (data) {
            //        if (data) {
            //            $scope.data.cliente = data;
            //            $scope.data.clienteLogueado = true;
            //        }
            //        else {
            //            warning("No se ha encontrado un usuario activo con los datos ingresados.");
            //        }
            //    });
            //};

            $scope.enviarSolicitud = function () {
                angular.element("#modalNuevoCliente").modal("show");
            };

            $scope.enviar = function (form) {
                service.registrarNuevaCuenta($scope.data.cliente).success(function (data) {
                    $scope.data.cliente = data;
                    solicitud = {cliente: $scope.data.cliente, cobertura: $scope.data.cobertura, observaciones: $scope.data.observaciones};
                    service.enviarSolicitud(solicitud).success(function () {
                        success("Solicitud registrada con éxito. Relájese y aguarde que uno de nuestros productores se contacte con usted.", true);
                        form.$setPristine();
                    });
                });
            };

            function buscarTiposDocumento() {
                if (!$scope.data.listaTiposDocumento) {
                    service.buscarTiposDocumento().success(function (data) {
                        $scope.data.listaTiposDocumento = data;
                    });
                }
            };

            $scope.next = function () {
                goToPanelTop();
                switch ($scope.data.pestanaActual) {
                    case 0:
                        validarPestanaSeleccionarRubro();
                        break;
                    case 1:
                        validarPestanaPreferencias();
                        break;
                    case 2:
                        validarPestanaCoberturas()
                        break;

                }
            };

            function validarPestanaSeleccionarRubro() {
                if ($scope.data.rubro) {
                    angular.element("#spinner").show();
                    angular.element("#btn-nextValid").trigger("click");
                    $scope.data.pestanaActual++;
                    searchTiposCobertura().then(onSearchTiposCoberturaDone);
                } else {
                    angular.element("#txt-seleccionaRubro").addClass('animated tada text-warning ').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                        $(this).removeClass('animated tada text-warning');
                    });
                }

                function onSearchTiposCoberturaDone() {
                    if ($scope.data.rubro.id == $scope.data.RUBRO_AUTO_ID) {
                        $scope.data.mostrarDatosAuto = true;
                        $scope.data.auto = {};
                        searchMarcasAuto().then(onAllRequestDone);
                    } else {
                        onAllRequestDone();
                        $scope.data.mostrarTiposCobertura = true;
                    }
                };
            };

            function validarPestanaPreferencias() {
                if ($scope.data.tipoCobertura) {
                    angular.element("#btn-nextValid").trigger("click");
                    $scope.data.pestanaActual++;
                    searchCoberturas($scope.data.rubro.id, 
                                     $scope.data.tipoCobertura.id, 
                                     $scope.data.auto ? $scope.data.auto.versionPrecio.precio : undefined
                                     ).then(onAllRequestDone);
                } else {
                    angular.element("#txt-seleccionaTipoCobertura").addClass('animated tada text-warning ').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                        $(this).removeClass('animated tada text-warning');
                    });
                }
            };

            function validarPestanaCoberturas() {
                if ($scope.data.cobertura) {
                    angular.element("#btn-nextValid").trigger("click");
                    $scope.data.pestanaActual++;
                    //searchCoberturas($scope.data.rubro.id, $scope.data.tipoCobertura.id);
                } else {
                    goToPanelTop();
                    angular.element("#txt-seleccionaCobertura").addClass('animated tada text-warning ').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                        $(this).removeClass('animated tada text-warning');
                    });
                }
            };

            $scope.previous = function () {
                $scope.data.pestanaActual--;
            };

            function onAllRequestDone() { angular.element("#spinner").hide(); };

            $scope.rubroSelected = function (item) {
                if ($scope.data.rubro != item) {
                    $scope.data.rubro = item;
                    $(".rubroChoice.choice")
                        .removeClass("active")
                        .find("input[value='" + item.id + "']")
                        .parents(".choice").addClass("active");
                    resetAtRubroChange();
                }
            };

            function resetAtRubroChange() {
                $scope.data.tipoCobertura = null;
                $scope.data.auto = null;
                $scope.data.cobertura = null;
                $(".tipoCoberturaChoice.choice").removeClass("active");
                $scope.data.mostrarDatosAuto = false;
                $scope.data.mostrarTiposCobertura = false;
            };

            $scope.tipoCoberturaSelected = function (item) {
                $scope.data.tipoCobertura = item;
                $(".tipoCoberturaChoice.choice")
                    .removeClass("active")
                    .find("input[value='" + item.id + "']")
                    .parents(".choice").addClass("active");
            };

            //$scope.showRiesgosIncluidos = function(item){
            //    $scope.data.coberturaAux = item;
            //    angular.element("#modalRiesgos").modal("show");
            //};

            function searchCoberturas(rubroId, tipoCoberturaId, valorAuto) {
                return service.searchCoberturas(rubroId, tipoCoberturaId, valorAuto).success(function (data) {
                    $scope.data.resultadoSimulacion = data;
                });
            };

            function goToPanelTop(){
                $('html, body').animate({
                    scrollTop: $("#panel_cotizar").offset().top
                }, 150);
            };


            //**********RUBRO AUTOMOVIL********************

            function searchMarcasAuto() {
                if (!$scope.data.listaMarcasAuto) {
                    return service.searchMarcasAuto()
                        .success(function (data) {
                            $scope.data.listaMarcasAuto = data;
                        })
                        .error(function () {
                            warning("Se ha producido un error obteniendo el listado. Por favor, inténtelo de nuevo.");
                            angular.element('#spinner').hide();
                        });
                } else {
                    var defer = $q.defer();
                    defer.resolve(true);
                    return defer.promise;
                }
            };

            $scope.marcaSelected = function () {
                if ($scope.data.auto.marca) {
                    $scope.data.auto.anio = null;
                    $scope.data.auto.versionPrecio = null;
                    service.searchModelos($scope.data.auto.marca.value)
                        .success(function (data) {
                            $scope.data.listaModelos = data;
                        })
                        .error(function () {
                            warning("Se ha producido un error obteniendo el listado. Por favor, inténtelo de nuevo.");
                            angular.element('#spinner').hide();
                        });
                }
                //else{
                //    $scope.data.listaModelos = null;
                //}
            };

            $scope.modeloSelected = function () {
                if ($scope.data.auto.modelo) {
                    $scope.data.auto.anioPrecio = null;
                    service.searchAnios($scope.data.auto.marca.value, $scope.data.auto.modelo.key)
                        .success(function (data) {
                            $scope.data.listaAnios = data;
                        })
                        .error(function () {
                            warning("Se ha producido un error obteniendo el listado. Por favor, inténtelo de nuevo.");
                            angular.element('#spinner').hide();
                        });
                }
                //else{
                //    $scope.data.listaModelos = null;
                //}
            };

            $scope.anioSelected = function () {
                if ($scope.data.auto.anio) {
                    service.searchVersionesYPrecios($scope.data.auto.marca.value, $scope.data.auto.modelo.key, $scope.data.auto.anio.key)
                        .success(function (data) {
                        $scope.data.listaResults = data;
                    })
                        .error(function () {
                            warning("Se ha producido un error obteniendo el listado. Por favor, inténtelo de nuevo.");
                            angular.element('#spinner').hide();
                        });
                }
                //else{
                //    $scope.data.listaModelos = null;
                //}
            };


            //*********************WIZARD***************************

            //TODO: desnegrear -.- (aunque no esta tan negro pero bue)
            function onDomReady() {
                $("#btn_cotizar").click(function () {
                    $('html, body').animate({
                        scrollTop: $("#panel_cotizar").offset().top
                    }, 1000);
                });
                $('#wizard').bootstrapWizard({
                    //onTabChange: $scope.callMe,
                    'tabClass': 'nav nav-pills', 'nextSelector': '.btn-next-valid', 'previousSelector': '.btn-previous',
                    onInit: function (tab, navigation, index) {
                        var $total = navigation.find('li').length;
                        $width = 100 / $total;
                        $display_width = $(document).width();
                        if ($display_width < 400 && $total > 3) {
                            $width = 50;
                        }
                        navigation.find('li').css('width', $width + '%');

                    },
                    onTabClick: function (tab, navigation, index) {
                        return false;
                    },
                    onTabShow: function (tab, navigation, index) {
                        var $total = navigation.find('li').length;
                        var $current = index + 1;
                        var wizard = navigation.closest('.wizard-card');
                        if ($current >= $total) {
                            $(wizard).find('.btn-next').hide();
                            $(wizard).find('.btn-finish').show();
                        } else {
                            $(wizard).find('.btn-next').show();
                            $(wizard).find('.btn-finish').hide();
                        }
                    }

                });

                $('[data-toggle="wizard-radio"]').click(function (event) {
                    wizard = $(this).closest('.wizard-card');
                    wizard.find('[data-toggle="wizard-radio"]').removeClass('active');
                    $(this).addClass('active');
                    $(wizard).find('[type="radio"]').removeAttr('checked');
                    $(this).find('[type="radio"]').attr('checked', 'true');
                });
            };

            function success(msj, reset) {
                bootbox.dialog({
                    message: msj, title: "Confirmación", buttons: {
                        ok: {
                            label: "OK", className: "btn btn-fill btn-warning btn-wd btn-sm", callback: function () {
                                if (reset) {
                                    $('#wizard').bootstrapWizard("show", 0); //todos los metodos del wizard los encontras en: http://www.opensource.org/licenses/mit-license.php
                                    $scope.init();
                                    $scope.$apply();
                                    $('html, body').animate({
                                        scrollTop: $("#large-header").offset().top
                                    }, 700);
                                }
                            }
                        }
                    }
                });
            };

            function warning(msj) {
                bootbox.dialog({
                    message: msj, title: "Advertencia", buttons: {
                        ok: {
                            label: "OK", className: "btn btn-fill btn-warning btn-wd btn-sm", callback: function(){
                                $('#wizard').bootstrapWizard("show", 0); //todos los metodos del wizard los encontras en: http://www.opensource.org/licenses/mit-license.php
                                $scope.init();
                                $scope.$apply();
                                $('html, body').animate({
                                    scrollTop: $("#large-header").offset().top
                                }, 700);
                            }
                        }
                    }
                });
            };

        }]);




    };
    return constructor;
});