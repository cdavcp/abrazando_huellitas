define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("ReservaCreateCtrl", ["$state", "$stateParams", "$rootScope", "$scope", "ReservaCreateService", function ($state, $stateParams, $root, $scope, service) {
            $scope.data = {};
            $scope.ver = true;
            $scope.modificado = false;
            $scope.personaOriginal = {};
            $scope.artista = {};
            $scope.evento = {};
            $scope.evento.lugar = {};
            $scope.evento.artista = {};
            $scope.artista.tipoShow = {};
            $scope.tags = {};
            $scope.tag = {};
            $scope.artista.usuario = {};
            $scope.evento.aceptadoPorArtista = false;
            $scope.evento.aceptadoPorLugar = false;
            $scope.evento.fechaEmision = new Date();
            $scope.reserva = {
                evento: {
                    cupoEvento: ''
                },
                cantidad: '',
                usuario: {},
                nombre: ''
            };


            function init() {

                $scope.reserva.evento = $stateParams.evento;

                $scope.reserva.usuario = angular.copy($root.userProfile);
                delete $scope.reserva.usuario["name"];
                delete $scope.reserva.usuario["avatar"];
                delete $scope.reserva.usuario["validSession"];
                delete $scope.reserva.usuario["menuOptions"];

            };
            init();


            $scope.guardar = function () {

                $scope.reserva.evento.cupoEvento = $scope.reserva.evento.cupoEvento - $scope.reserva.cantidad;

                service.create($scope.reserva).success(function () {
                    success($scope, $state);
                })
                    .error(function () {
                        error("Se ha producido un error al registrar la solicitud, por favor intentelo de nuevo.");
                    });
            }

            function error(e) {
                angular.element('#spinner').hide();
                bootbox.dialog({
                    message: e,
                    title: "Error",
                    buttons: {
                        success: {label: "OK", className: "btn-primary"}
                    },
                    closeButton: false
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
                                state.go('dashboard');
                            }
                        }
                    },
                    closeButton: false
                });
            };


            $scope.cancelar = function () {

            };


        }]);


        module.controller("ReservaIndexCtrl", ["$state", "$stateParams", "$rootScope", "$scope", "ReservaIndexService", function ($state, $stateParams, $root, $scope, service) {


            $scope.usuarioConsulta = angular.copy($root.userProfile);
            delete $scope.usuarioConsulta["name"];
            delete $scope.usuarioConsulta["avatar"];
            delete $scope.usuarioConsulta["validSession"];
            delete $scope.usuarioConsulta["menuOptions"];


            function init() {

                service.getReservasByUsuario($scope.usuarioConsulta).success(function (data) {
                    $scope.reservas = data;
                });
            };

            init();

            $scope.verArtista = function (item) {
                $state.go("artistas.view");

            };





            $scope.aceptarSolicitud = function (item) {

                if ($root.userProfile.tipoUsuario.id == 1) {
                    item.aceptadoPorArtista = true;

                    service.updateEvento(item).success(function (data) {
                        init();
                    });
                }

                if ($root.userProfile.tipoUsuario.id == 2) {
                    item.aceptadoPorLugar = true;

                    service.updateEvento(item).success(function (data) {
                        init();
                    });
                }


            };


            $scope.crearArtista = function () {
                $state.go("artistas.create");

            };


        }]);
        module.controller("ReservaLugarCtrl", ["$state", "$stateParams", "$rootScope", "$scope", "ReservaIndexService", function ($state, $stateParams, $root, $scope, service) {


            $scope.lugares = [];
            $scope.reservasVer = [];
            $scope.solicitudes = [];
            $scope.lugar = {};
            $scope.lugar.id = "";
            $scope.verReservas = false;
            $scope.eventoVer= {};
            $scope.imp= false;
            $scope.usuarioConsulta = angular.copy($root.userProfile);
            delete $scope.usuarioConsulta["name"];
            delete $scope.usuarioConsulta["avatar"];
            delete $scope.usuarioConsulta["validSession"];
            delete $scope.usuarioConsulta["menuOptions"];


            function init() {



                service.getLugaresByUsuario($root.userProfile.id).success(function (data) {
                    $scope.lugares = data;
                });

                service.getSolicitudesByUsuarioReservas($scope.usuarioConsulta).success(function (data) {
                    $scope.solicitudes = data;
                });
            };

            init();

            //$scope.verArtista = function (item) {
            //    $state.go("artistas.view");
            //
            //};

            //
            //$scope.aceptarSolicitud = function (item) {
            //
            //    if($root.userProfile.tipoUsuario.id == 1){
            //        item.aceptadoPorArtista=true;
            //
            //        service.updateEvento(item).success(function (data) {
            //            init();
            //        });
            //    }
            //
            //    if($root.userProfile.tipoUsuario.id == 2){
            //        item.aceptadoPorLugar=true;
            //
            //        service.updateEvento(item).success(function (data) {
            //            init();
            //        });
            //    }
            //
            //
            //
            //};


            $scope.verReservasVer = function (item) {
                $scope.verReservas = true;
                $scope.eventoVer = angular.copy(item);

                service.getReservasByEvento($scope.eventoVer).success(function (data) {
                    $scope.reservasVer = data;
                });

            };

            $scope.volverReservas = function () {
                $scope.verReservas = false;
                $scope.eventoVer={};


            };

            $scope.verificarSeleccion = function (item) {
                item.asistio = true;
                service.update(item).success(function (data) {
                });


            };

            $scope.imprimirReserva = function () {
                $scope.imp=true;

                if ($scope.imp){
                    setTimeout(function () {
                        window.print();
                        $scope.imp=false;

                    }, 900);

                }



            };


            $scope.guardarAsistencia = function () {
               console.log()


            };



            //$scope.imprimir = function () {
            //
            //
            //};

            $scope.selectLugar = function () {
                if($scope.lugar.id){

                    service.findEventosByL($scope.lugar).success(function (data) {
                        $scope.solicitudes = data;
                    });

                }
                else{
                    service.getSolicitudesByUsuarioReservas($scope.usuarioConsulta).success(function (data) {
                        $scope.solicitudes = data;
                    });

                }

            };


        }]);

        module.controller("ReservaViewCtrl", ["$state", "$stateParams", "$rootScope", "$scope", "ReservaIndexService", function ($state, $stateParams, $root, $scope, service) {


            $scope.usuario = {};
            $scope.usuario.id = $root.userProfile.id;
            $scope.usuario.tipoUsuario = $root.userProfile.tipoUsuario;
            $scope.evento = {};


            angular.element(document).ready(function () {
                console.log("tabs");
                $('.nav-tabs a').click(function (e) {
                    e.preventDefault()
                    $(this).tab('show')
                })
            });


            $scope.verArtista = function () {
                $state.go('artistas.view', {artista: $scope.evento.artista});

            };

            $scope.reservar = function () {
                $state.go('reserva.create', {evento: $scope.evento});

            };

            $scope.verLugar = function () {
                $state.go('lugar.view', {lugar: $scope.evento.lugar});

            };


            function init() {

                $scope.evento = $stateParams.evento;

            };

            init();

            $scope.verEvento = function (item) {
                $state.go("solicitud.view");

            };

            $scope.crearArtista = function () {
                $state.go("artistas.create");

            };


        }]);


    };
    return constructor;
});
