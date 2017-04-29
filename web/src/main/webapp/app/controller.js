define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("MainCtrl", ["LoginService", "$rootScope", "$scope", "$state", function (loginService, $root, $scope, $state) {

            $scope.genero ="";
            function init() {

                $scope.eventoFilter ={
                    lugar: {
                        nombre: '',
                        ubicacion: ''
                    },
                    artista: {
                        nombre: '',
                        tipoShow: {
                            nombre: ''
                        }
                    }
                };

                loginService.getTipoShow().success(function (data) {
                    $scope.listaTipoShow = data;
                });

                loginService.getEventosProximos().success(function (data) {
                    $scope.eventosProximos = data;
                });

                loginService.getEventosMg().success(function (data) {
                    $scope.eventosMg = data;
                });
                loginService.getEventosByTodosFiltros($scope.eventoFilter).success(function (data) {
                    $scope.eventoByFilters = data;
                });



            };

            $scope.queryBusqueda = function () {

                if($scope.eventoFilter.lugar.nombre==undefined){$scope.eventoFilter.lugar.nombre = "";}
                if($scope.eventoFilter.lugar.ubicacion==undefined){$scope.eventoFilter.lugar.ubicacion = "";}
                if($scope.eventoFilter.artista.nombre==undefined){$scope.eventoFilter.artista.nombre = "";}
                //if($scope.eventoFilter.lugarActual==undefined){$scope.eventoFilter.lugarActual = "";}
                if($scope.eventoFilter.artista.tipoShow.nombre==undefined){$scope.eventoFilter.artista.tipoShow.nombre = "";}

                loginService.getEventosByTodosFiltros($scope.eventoFilter).success(function (data) {
                    $scope.eventoByFilters = data;

                    //if($scope.genero){
                    //
                    //    $scope.eventoByFilters.forEach(function(evento) {
                    //
                    //        if(evento.artista.tags.length){ evento.artista.tags.forEach(function(tag) {
                    //            if(tag.nombre == $scope.genero){
                    //                evento.show=true;
                    //            }
                    //        });}
                    //
                    //        if(evento.lugar.tags.length){ evento.lugar.tags.forEach(function(tag) {
                    //            if(tag.nombre == $scope.genero){
                    //                evento.show=true;
                    //            }
                    //        });}
                    //
                    //
                    //    });
                    //}
                    //else{
                    //    $scope.eventoByFilters.forEach(function(evento) {
                    //        evento.show=true;
                    //    });
                    //}


                });



            };




            init();

            if (!$root.userProfile || !$root.userProfile.validSession) {
                loginService.checkLogin().then($root.onLoginSuccess, $root.onLoginFail);
            }

            $root.verEvento = function (item) {
                delete item["show"];
                $state.go('solicitud.view', {evento: item});

            };


        }]);

        module.controller("DashboardCtrl", ["LoginService", "$rootScope", "$scope", function ($root, $scope, service) {
            $scope.data = {};


            //angular.module('ui.bootstrap.demo').controller('CarouselDemoCtrl', function ($scope) {

            function init() {

                service.getEventosProximos().success(function (data) {
                    $scope.eventos = data;
                });
            };

            init();

            $scope.verArtista = function (item) {
                $state.go('artistas.view', {artista: item});

            };

            $scope.crearArtista = function () {
                $state.go("artistas.create");

            };

        }]);

        module.controller("LoginCtrl", ["$state", "$scope", "$rootScope", "LoginService", "$cookieStore", function ($state, $scope, $root, loginService, $cookies) {
            $scope.data = {};
            $scope.data.ID_USUARIO_PRODUCTOR = 2;
            $scope.form = {};
            $scope.data.user = {};
            $scope.invalidLogin = false;
            $root.cantidadNotificaciones = 0;
            //$root.imprimirVar = false;

            //PRUEBA



            $root.verNotificaciones = function () {
                $root.actualizarNotificaciones();
                $state.go('notificaciones.index', {persona: null});


            };


            $root.actualizarNotificaciones = function () {
                $root.usr = {
                    nombre: '',
                    pass: ''
                };
                $root.cantidadNotificaciones = 0;

                if ($root.userProfile.nombre) {
                    $root.usr.nombre = angular.copy($root.userProfile.nombre);
                    $root.usr.pass = angular.copy($root.userProfile.pass);

                    loginService.login($root.usr).success(function success(usuario) {
                        if (usuario) {
                            $root.userProfile.notificaciones = usuario.notificaciones;
                            if ($root.userProfile.notificaciones != null) {
                                console.log("llega a size > 0");
                                $root.userProfile.notificaciones.forEach(function (notificacion) {
                                    if (!notificacion.leida) {
                                        //console.log("llega a no leida");
                                        $root.cantidadNotificaciones = $root.cantidadNotificaciones + 1;
                                    }

                                });
                            }
                        }
                    });
                }
            };

            $root.updateNotificacion = function (item) {
                $root.usrT = {
                };
                if ($root.userProfile.nombre) {

                    //$root.usrT = angular.copy($root.userProfile);
                    //
                    //delete $root.usrT["name"];
                    //delete $root.usrT["avatar"];
                    //delete $root.usrT["validSession"];
                    //delete $root.usrT["menuOptions"];
                    loginService.updateNotificaciones(item).success(function success(data) {
                        $root.actualizarNotificaciones();
                    });
                }
            };

            //$scope.$watch(function () {
            //    if ($root.userProfile.nombre) {
            //        $root.cantidadNotificaciones = 0;
            //
            //
            //    }
            //});
            //


            init();
            function init() {
                if ($state.$current == "register") {
                    loginService.inicializarCreate().success(function (data) {
                        $scope.data.listaTiposUsuario = data;
                        $scope.data.nombreNuevoUsuarioValido = true;
                        $scope.data.VALID_CLASS = "has-success";
                        $scope.data.INVALID_CLASS = "has-error";
                        $scope.data.checkingName = false;
                        $scope.MAX_FILE_SIZE_BYTES = 100000;
                    });
                }
            }

            $scope.registrarUsuario = function () {
                $state.go("register");

            }

            $scope.volverLogin = function () {
                $state.go("login");

            }

            $scope.login = function () {
                angular.element('#spinner').show();
                loginService.login($scope.data.user).success(function success(usuario) {
                    if (usuario) {
                        angular.element('#spinner').hide();
                        $cookies.put("sigep_session", usuario.sessionId);
                        $root.userProfile = usuario;
                        $root.userProfile.name = usuario.nombre;
                        $root.userProfile.avatar = usuario.foto;
                        $root.userProfile.validSession = true;
                        $root.userProfile.menuOptions = usuario.tipoUsuario.permisos;
                        $root.actualizarNotificaciones();
                        $state.go("dashboard");
                    }
                    else {
                        angular.element('#spinner').hide();
                        $scope.invalidLogin = true;
                    }
                })
                    .error(function error() {
                        bootbox.alert("Se ha producido un error al iniciar sesión, por favor intentelo de nuevo.");
                        angular.element('#spinner').hide();
                    });
            };

            $scope.$on("fileSelected", function (evt, files) {
                $scope.data.file = files[0];
                if ($scope.data.file.size > $scope.MAX_FILE_SIZE_BYTES || $scope.data.file.name.indexOf(".jpg") == -1) {
                    $scope.data.logoValido = false;
                }
                else {
                    $scope.data.logoValido = true;
                }
            });

            //$scope.register = function(){
            //    loginService.register($scope.data.persona).success(function(){
            //        bootbox.dialog({
            //            message: "Usuario registrado con éxito", title: "Confirmación", buttons: {
            //                OK: {
            //                    llabel: "OK", className: "btn-primary", callback: function () {
            //                        $scope.data = {};
            //                        $state.go('dashboard');
            //                    }
            //                }
            //            }
            //        })
            //    }).error(function(){
            //        bootbox.dialog({
            //            message: "Se ha producido un error, por favor inténtelo de nuevo.", title: "Error", buttons: {
            //                OK: {
            //                    llabel: "OK", className: "btn-primary", callback: function () {
            //                        $scope.data = {};
            //                    }
            //                }
            //            }
            //        })
            //    })
            //};

            $scope.register = function () {
                if (!$scope.data.file || ($scope.data.file && $scope.data.logoValido)) {
                    loginService.register($scope.data.persona, $scope.data.file).success(function () {
                        bootbox.dialog({
                            message: "Usuario registrado con éxito", title: "Confirmación", buttons: {
                                OK: {
                                    label: "OK", className: "btn-primary", callback: function () {
                                        $scope.data = {};
                                        $state.go('dashboard');
                                    }
                                }
                            }
                        })
                    }).error(function () {
                        bootbox.dialog({
                            message: "Se ha producido un error, por favor inténtelo de nuevo.",
                            title: "Error",
                            buttons: {
                                OK: {
                                    llabel: "OK", className: "btn-primary", callback: function () {
                                        $scope.data = {};
                                    }
                                }
                            }
                        })
                    })
                }
                else {
                    bootbox.dialog({
                        message: "La foto de perfil debe ser un archivo JPG con un tamaño no mayor a 100KB.",
                        title: "Error",
                        buttons: {
                            success: {label: "OK", className: "btn-primary"}
                        }
                    });
                }
            };

            $scope.siguiente = function () {
                var user = $scope.data.user;
                $state.go("productor.create", {usuario: user});
            }

            $scope.checkName = function () {
                if ($scope.data.user.nombre && $scope.data.user.nombre != "") {
                    $scope.data.checkingName = true;
                    loginService.checkNombreUsuario($scope.data.user.nombre).success(function (data) {
                        $scope.data.nombreNuevoUsuarioValido = (data == "true");
                        $scope.data.claseNombreUsuario = $scope.data.nombreNuevoUsuarioValido ? $scope.data.VALID_CLASS : $scope.data.INVALID_CLASS;
                        $scope.data.checkingName = false;
                    })
                }
                else {
                    $scope.data.claseNombreUsuario = $scope.data.INVALID_CLASS;
                }
            };

            $scope.changePassword = function () {
                angular.element('#spinner').show();
                loginService.changePassword($scope.data.newPass, $scope.data.previousPass, $root.userProfile.id).success(function (data) {
                    if (data == true || data == 'true') {
                        angular.element('#spinner').hide();
                        $root.userProfile.pass = $scope.data.newPass;
                        bootbox.dialog({
                            message: "Contraseña modificada con éxito", title: "Confirmación", buttons: {
                                success: {
                                    label: "OK", className: "btn-primary"
                                }
                            }
                        })
                    } else {
                        angular.element('#spinner').hide();
                        bootbox.dialog({
                            message: "Contraseña anterior incorrecta", title: "Error", buttons: {
                                success: {label: "OK", className: "btn-primary"}
                            }
                        });
                    }

                }).error(function (error) {
                    angular.element('#spinner').hide();
                    bootbox.dialog({
                        message: error, title: "Error", buttons: {
                            success: {label: "OK", className: "btn-primary"}
                        }
                    });
                });
            };
        }]);
    };
    return constructor;
});



var myApp = angular.module('myApp', []);

myApp.directive('googleplace', function() {

    return {
        require: 'ngModel',
        link: function(scope, element, attrs, model) {
            var options = {
                types: [],
                componentRestrictions: {}
            };
            scope.gPlace = new google.maps.places.Autocomplete(element[0], options);

            google.maps.event.addListener(scope.gPlace, 'place_changed', function() {
                scope.$apply(function() {
                    model.$setViewValue(element.val());

                    var prueba = scope.gPlace.getPlace();
                    var prueba2 = scope.gPlace.getBounds();

                });
            });
        }
    };
});
