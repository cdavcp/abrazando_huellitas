define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("SolicitudCreateCtrl", ["$state", "$stateParams", "$rootScope", "$scope", "SolicitudCreateService", function ($state, $stateParams, $root, $scope, service) {
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
            //$scope.evento.fechaEvento = new Date();
            $scope.titulo = "";


            function init() {
                if ($stateParams.lugar) {
                    $scope.evento.lugar = $stateParams.lugar;
                    $scope.titulo = $stateParams.lugar.nombre;
                }
                if ($stateParams.artista) {
                    $scope.evento.artista = $stateParams.artista;
                    $scope.titulo = $stateParams.artista.nombre;
                }

                //service.getTipoShow().success(function (data) {
                //    $scope.data.listaTipoShow = data;
                //});


                if ($root.userProfile.tipoUsuario.id == 1) {
                    $scope.evento.aceptadoPorArtista = true;
                    $scope.evento.aceptadoPorLugar = false;

                    service.getArtistaByUsuario($root.userProfile.id).success(function (data) {
                        $scope.artistas = data;
                    });


                }

                if ($root.userProfile.tipoUsuario.id == 2) {
                    $scope.evento.aceptadoPorArtista = false;
                    $scope.evento.aceptadoPorLugar = true;


                    service.getLugaresMByUsuario($root.userProfile.id).success(function (data) {
                        $scope.lugares = data;
                    });

                }

            };
            init();


            $scope.guardar = function () {

                //$scope.evento.fechaEvento.set
                service.create($scope.evento).success(function () {
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
                                state.go('solicitud.index');
                            }
                        }
                    },
                    closeButton: false
                });
            };


            $scope.cancelar = function () {

            };


        }]);

        module.controller("SolicitudEditCtrl", ["$state", "$stateParams", "$rootScope", "$scope", "SolicitudCreateService", function ($state, $stateParams, $root, $scope, service) {
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
            $scope.titulo = "";


            function init() {

                $scope.evento = $stateParams.evento;




            };
            init();


            //$scope.guardar = function () {
            //
            //    bootbox.dialog({
            //        message: "Solicitud enviada con éxito", title: "Confirmación", buttons: {
            //            OK: {
            //                label: "OK", className: "btn-primary", callback: function () {
            //                    $scope.data = {};
            //                    $state.go('solicitud.index');
            //                }
            //            }
            //        }
            //    })
            //
            //}

            $scope.guardar = function () {

                if ($root.userProfile.tipoUsuario.id == 1) {
                    $scope.evento.aceptadoPorArtista = true;
                    $scope.evento.aceptadoPorLugar = false;



                }

                if ($root.userProfile.tipoUsuario.id == 2) {
                    $scope.evento.aceptadoPorArtista = false;
                    $scope.evento.aceptadoPorLugar = true;


                }

                service.updateEvento($scope.evento).success(function () {
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
                    message: "Edición realizada con éxito",
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "OK", className: "btn-primary", callback: function () {
                                scope.data = {};
                                state.go('solicitud.index');
                            }
                        }
                    },
                    closeButton: false
                });
            };


            $scope.cancelar = function () {

            };


        }]);

        module.controller("SolicitudViewSCtrl", ["$state", "$stateParams", "$rootScope", "$scope", "SolicitudCreateService", function ($state, $stateParams, $root, $scope, service) {
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
            $scope.titulo = "";


            function init() {

                $scope.evento = $stateParams.evento;




            };
            init();


            //$scope.guardar = function () {
            //
            //    bootbox.dialog({
            //        message: "Solicitud enviada con éxito", title: "Confirmación", buttons: {
            //            OK: {
            //                label: "OK", className: "btn-primary", callback: function () {
            //                    $scope.data = {};
            //                    $state.go('solicitud.index');
            //                }
            //            }
            //        }
            //    })
            //
            //}

            $scope.guardar = function () {

                if ($root.userProfile.tipoUsuario.id == 1) {
                    $scope.evento.aceptadoPorArtista = true;
                    $scope.evento.aceptadoPorLugar = false;



                }

                if ($root.userProfile.tipoUsuario.id == 2) {
                    $scope.evento.aceptadoPorArtista = false;
                    $scope.evento.aceptadoPorLugar = true;


                }

                service.updateEvento($scope.evento).success(function () {
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
                    message: "Edición realizada con éxito",
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "OK", className: "btn-primary", callback: function () {
                                scope.data = {};
                                state.go('solicitud.index');
                            }
                        }
                    },
                    closeButton: false
                });
            };


            $scope.cancelar = function () {

            };


        }]);


        module.controller("SolicitudIndexCtrl", ["$state", "$stateParams", "$rootScope", "$scope", "SolicitudIndexService", function ($state, $stateParams, $root, $scope, service) {


            $scope.usuario = {};
            $scope.usuario.id = $root.userProfile.id;
            $scope.usuario.tipoUsuario = $root.userProfile.tipoUsuario;


            function init() {

                service.getSolicitudesByUsuario($scope.usuario).success(function (data) {
                    $scope.solicitudes = data;
                });
            };

            init();

            $scope.verSolicitud = function (item) {
                $state.go('solicitud.viewS', {evento: item});

            };

            $scope.verEvento = function (item) {
                $state.go('solicitud.view', {evento: item});

            };

            $scope.verArtista = function (item) {
                $state.go("artistas.view");

            };

            $scope.editarEvento = function (item) {
                $state.go('solicitud.edit', {evento: item});

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

        module.controller("SolicitudViewCtrl", ["$state", "$stateParams", "$rootScope", "$scope", "SolicitudIndexService", function ($state, $stateParams, $root, $scope, service) {


            $scope.usuario = {};
            $scope.usuario.id = $root.userProfile.id;
            $scope.usuario.tipoUsuario = $root.userProfile.tipoUsuario;
            $scope.evento = {};
            $scope.comentario = {};
            $scope.comentario.comentario = "";
            $scope.comentarios = [];
            $scope.comentario.usuario = {};
            $scope.comentario.evento = {};
            $scope.mg = {
                usuario: {},
                evento: {},
                id: ''
            };


            $scope.tieneMg = 'Me Gusta';


            angular.element(document).ready(function () {
                console.log("tabs");
                $('.nav-tabs a').click(function (e) {
                    e.preventDefault()
                    $(this).tab('show')
                })
            });


            $scope.meGusta = function () {

                service.createMegusta($scope.mg).success(function (data) {
                    $scope.mg.id = data.id;
                    $scope.mg.evento = angular.copy($stateParams.evento);
                    $scope.mg.usuario = angular.copy($scope.comentario.usuario);
                    if ($scope.mg.id) {
                        $scope.tieneMg = 'Ya no me Gusta';
                    }
                    else {
                        $scope.tieneMg = 'Me Gusta';
                    }

                });
            };

            $scope.comentar = function () {

                service.createComentario($scope.comentario).success(function () {
                    service.getComentariosByEvento($scope.evento.id).success(function (data) {
                        $scope.comentarios = data;
                    });

                })
            };

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
                $scope.comentario.evento = angular.copy($stateParams.evento);
                $scope.mg.evento = angular.copy($stateParams.evento);
                $scope.comentario.usuario = angular.copy($root.userProfile);

                delete $scope.comentario.usuario["name"];
                delete $scope.comentario.usuario["avatar"];
                delete $scope.comentario.usuario["validSession"];
                delete $scope.comentario.usuario["menuOptions"];

                $scope.mg.usuario = angular.copy($scope.comentario.usuario);


                service.getComentariosByEvento($scope.evento.id).success(function (data) {
                    $scope.comentarios = data;
                });

                service.findMG($scope.mg).success(function (data) {
                    $scope.mg.id = data.id;
                    $scope.mg.evento = angular.copy($stateParams.evento);
                    $scope.mg.usuario = angular.copy($scope.comentario.usuario);

                    if ($scope.mg.id) {
                        $scope.tieneMg = 'Ya no me Gusta';
                    }
                    else {
                        $scope.tieneMg = 'Me Gusta';
                    }
                });


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