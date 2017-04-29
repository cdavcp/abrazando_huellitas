define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("ArtistaCreateCtrl", ["$state", "$stateParams","$rootScope" ,"$scope", "ArtistaCreateService", function ($state, $stateParams, $root,$scope, service) {
            $scope.data = {};
            $scope.ver = true;
            $scope.modificado = false;
            $scope.personaOriginal = {};
            $scope.artista={};
            $scope.artista.tipoShow = {};
            $scope.tags = {};
            $scope.tag = {};
            $scope.artista.tags = [];
            $scope.artista.usuario = {};


            function init() {

                service.getTipoShow().success(function (data) {
                    $scope.data.listaTipoShow = data;
                });

            };
            init();


            $scope.MyCtrl = function ($scope) {
                $scope.gPlace;

            };





            $scope.loadTags = function(query) {
                //return $http.get('/tags?query=' + query);
                console.log($scope.artista);
            };

            $scope.guardar = function () {

                $scope.artista.usuario.id = $root.userProfile.id;
                $scope.tags.forEach(function(tag) {
                   console.log(tag.text);
                   $scope.tag.nombre = tag.text;
                   $scope.artista.tags.push($scope.tag);
                });

                service.create($scope.artista).success(function () {
                   success($scope, $state);
                })
                   .error(function() {
                       error("Se ha producido un error al registrar el artista, por favor intentelo de nuevo.");
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
                                state.go('artistas.index');
                            }
                        }
                    },
                    closeButton: false
                });
            };

            $scope.cancelar = function () {

            };


        }]);


        module.controller("ArtistaEditCtrl", ["$state", "$stateParams","$rootScope" ,"$scope", "ArtistaCreateService", function ($state, $stateParams, $root,$scope, service) {
            $scope.data = {};
            $scope.ver = true;
            $scope.modificado = false;
            $scope.personaOriginal = {};
            $scope.artista={};
            $scope.artista.tipoShow = {};
            $scope.tags = [];
            $scope.tag = {
                text: ""
            };
            $scope.artista.tags = [];
            $scope.artista.usuario = {};


            function init() {
                $scope.artista = $stateParams.artista;
                if($scope.artista.tags){
                    $scope.artista.tags.forEach(function(tag) {
                        $scope.tag.text = tag.nombre;
                        $scope.tags.push($scope.tag);
                    });
                }
                service.getTipoShow().success(function (data) {
                    $scope.data.listaTipoShow = data;
                });

            };
            init();


            $scope.MyCtrl = function ($scope) {
                $scope.gPlace;

            };





            $scope.loadTags = function(query) {
                //return $http.get('/tags?query=' + query);
                console.log($scope.artista);
            };

            $scope.guardar = function () {

                $scope.artista.usuario.id = $root.userProfile.id;


                service.updateA($scope.artista).success(function () {
                    success($scope, $state);
                })
                    .error(function() {
                        error("Se ha producido un error al editar el artista, por favor intentelo de nuevo.");
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
                    message: "Editción realizada con éxito",
                    title: "Confirmación",
                    buttons: {
                        success: {
                            label: "OK", className: "btn-primary", callback: function () {
                                scope.data = {};
                                state.go('artistas.index');
                            }
                        }
                    },
                    closeButton: false
                });
            };

            $scope.cancelar = function () {

            };


        }]);


        module.controller("ArtistaIndexCtrl", ["$state", "$stateParams","$rootScope" ,"$scope", "ArtistaIndexService", function ($state, $stateParams, $root,$scope, service) {

            function init() {

                service.getArtistaByUsuario($root.userProfile.id).success(function (data) {
                    $scope.artistas = data;
                });
            };

            init();

            $scope.verArtista = function (item) {
                $state.go('artistas.view', {artista: item});

            };

            $scope.editarArtista = function (item) {
                $state.go('artistas.edit', {artista: item});

            };

            $scope.crearArtista = function () {
                $state.go("artistas.create");

            };


        }]);


        module.controller("ArtistaViewCtrl", ["$state", "$stateParams","$rootScope" ,"$scope", "ArtistaIndexService", function ($state, $stateParams, $root,$scope, service) {
            $scope.artista = {};
            $scope.seguidor = {};
            $scope.estilos = "";
            $scope.seguidorUsuario = {};
            $scope.test = {};
            $scope.sigue=false;
            $scope.botonSeguir="";
            $scope.seguidorUsuario.usuario = {};
            $scope.seguidorUsuario.usuario.id = "";

            //VARIABLES VOTACION
            $scope.votacionLugar = {};
            $scope.pintadaUna = false;
            $scope.pintadaDos = false;
            $scope.pintadaTres = false;
            $scope.pintadaCuatro = false;
            $scope.pintadaCinco = false;


                angular.element(document).ready(function() {
                    console.log("tabs");
                    $('.nav-tabs a').click(function (e) {
                        e.preventDefault()
                        $(this).tab('show')
                    })
                });

            function init() {
                delete $stateParams.artista["show"];
                $scope.artista = $stateParams.artista;
                $scope.estilos = "";
                $scope.seguidor.artista = $scope.artista;
                $scope.seguidor.usuario = angular.copy($root.userProfile);
                delete $scope.seguidor.usuario["name"];
                delete $scope.seguidor.usuario["avatar"];
                delete $scope.seguidor.usuario["validSession"];
                delete $scope.seguidor.usuario["menuOptions"];

                //COPIA DE USUARIO
                $scope.votacionLugar = angular.copy($scope.seguidor);
                $scope.votacionLugar.puntuacion = "";
                $scope.votacionLugar.id = "";

                if($scope.artista.tags){
                    $scope.artista.tags.forEach(function(tag) {
                        $scope.estilos +=  tag.nombre + ", ";
                    });
                }

                service.getEventosByArtista($scope.artista).success(function (data) {
                    $scope.eventos = data;
                });

                $scope.goToSolicitud = function () {
                    $state.go('solicitud.create', {artista: $stateParams.artista});

                };


                service.getEventosByTodosFiltros('Two of Us').success(function (data) {
                    $scope.eventos = data;
                });

                service.findSeguidor($scope.seguidor).success(function (data) {
                    $scope.seguidorUsuario = data;
                    if($scope.seguidorUsuario.id){
                        $scope.sigue = true;
                        $scope.botonSeguir = "Siguiendo";
                    }
                    else{
                        $scope.sigue = false;
                        $scope.botonSeguir = "Seguir";
                    }
                });

                service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                    $scope.votacionLugar.id = data.id;

                    if($scope.votacionLugar.id){

                        service.findVotacion($scope.votacionLugar.id).success(function (data) {
                            $scope.votacionLugar = data;

                            if($scope.votacionLugar.puntuacion == 1){
                                $scope.pintadaUna = true;

                            }
                            if($scope.votacionLugar.puntuacion == 2){
                                $scope.pintadaUna = true;
                                $scope.pintadaDos = true;
                            }
                            if($scope.votacionLugar.puntuacion == 3){
                                $scope.pintadaUna = true;
                                $scope.pintadaDos = true;
                                $scope.pintadaTres = true;
                            }
                            if($scope.votacionLugar.puntuacion == 4){
                                $scope.pintadaUna = true;
                                $scope.pintadaDos = true;
                                $scope.pintadaTres = true;
                                $scope.pintadaCuatro = true;
                            }
                            if($scope.votacionLugar.puntuacion == 5){
                                $scope.pintadaUna = true;
                                $scope.pintadaDos = true;
                                $scope.pintadaTres = true;
                                $scope.pintadaCuatro = true;
                                $scope.pintadaCinco = true;
                            }

                        });
                    }


                });

            };

            init();

            $scope.verEvento = function (evento) {
                $state.go('solicitud.view', {evento: evento});

            };

            $scope.seguir = function () {
                if(!$scope.sigue){
                    service.crearSeguidor($scope.seguidor).success(function (data) {
                        $scope.test = data;

                        service.findSeguidor($scope.seguidor).success(function (data) {
                            $scope.seguidorUsuario = data;
                            if($scope.seguidorUsuario.id){
                                $scope.sigue = true;
                                $scope.botonSeguir = "Siguiendo";
                            }
                            else{
                                $scope.sigue = false;
                                $scope.botonSeguir = "Seguir";
                            }

                        });

                    });

                }


                else{

                    service.deleteSeguidor($scope.seguidorUsuario).success(function (data) {
                        $scope.test = data;

                        service.findSeguidor($scope.seguidor).success(function (data) {
                            $scope.seguidorUsuario = data;
                            if($scope.seguidorUsuario.id){
                                $scope.sigue = true;
                                $scope.botonSeguir = "Siguiendo";
                            }
                            else{
                                $scope.sigue = false;
                                $scope.botonSeguir = "Seguir";
                            }

                        });

                    });

                }

            };


            $scope.unaEstrella = function () {
                $scope.votacionLugar.puntuacion = 1;
                $scope.pintadaUna = true;
                $scope.pintadaDos = false;
                $scope.pintadaTres = false;
                $scope.pintadaCuatro = false;
                $scope.pintadaCinco = false;

                if($scope.votacionLugar.id){
                    service.updatePuntuacionInteresado($scope.votacionLugar).success(function (data) {
                        service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                            $scope.votacionLugar.id = data.id;
                        });
                    });
                }

                else{

                    service.crearPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                        service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                            $scope.votacionLugar.id = data.id;
                        });
                    });
                }



            };

            $scope.dosEstrella = function () {
                $scope.votacionLugar.puntuacion = 2;
                $scope.pintadaUna = true;
                $scope.pintadaDos = true;
                $scope.pintadaTres = false;
                $scope.pintadaCuatro = false;
                $scope.pintadaCinco = false;

                if($scope.votacionLugar.id){
                    service.updatePuntuacionInteresado($scope.votacionLugar).success(function (data) {
                        service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                            $scope.votacionLugar.id = data.id;
                        });
                    });
                }

                else{

                    service.crearPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                        service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                            $scope.votacionLugar.id = data.id;
                        });
                    });
                }



            };

            $scope.tresEstrella = function () {
                $scope.votacionLugar.puntuacion = 3;
                $scope.pintadaUna = true;
                $scope.pintadaDos = true;
                $scope.pintadaTres = true;
                $scope.pintadaCuatro = false;
                $scope.pintadaCinco = false;

                if($scope.votacionLugar.id){
                    service.updatePuntuacionInteresado($scope.votacionLugar).success(function (data) {
                        service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                            $scope.votacionLugar.id = data.id;
                        });
                    });
                }

                else{

                    service.crearPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                        service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                            $scope.votacionLugar.id = data.id;
                        });
                    });
                }




            };

            $scope.cuatroEstrella = function () {
                $scope.votacionLugar.puntuacion = 4;
                $scope.pintadaUna = true;
                $scope.pintadaDos = true;
                $scope.pintadaTres = true;
                $scope.pintadaCuatro = true;
                $scope.pintadaCinco = false;

                if($scope.votacionLugar.id){
                    service.updatePuntuacionInteresado($scope.votacionLugar).success(function (data) {
                        service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                            $scope.votacionLugar.id = data.id;
                        });
                    });
                }

                else{

                    service.crearPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                        service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                            $scope.votacionLugar.id = data.id;
                        });
                    });
                }


            };

            $scope.cincoEstrella = function () {
                $scope.votacionLugar.puntuacion = 5;
                $scope.pintadaUna = true;
                $scope.pintadaDos = true;
                $scope.pintadaTres = true;
                $scope.pintadaCuatro = true;
                $scope.pintadaCinco = true;

                if($scope.votacionLugar.id){
                    service.updatePuntuacionInteresado($scope.votacionLugar).success(function (data) {
                        service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                            $scope.votacionLugar.id = data.id;
                        });
                    });
                }

                else{

                    service.crearPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                        service.findPuntuacionInteresado($scope.votacionLugar).success(function (data) {
                            $scope.votacionLugar.id = data.id;
                        });
                    });
                }


            };

            $scope.index = function () {
                $state.go("artistas.index");

            };

            $scope.actualizarSeguimiento = function () {

            };




        }]);

        module.controller("ArtistaMenuCtrl", ["$state", "$stateParams","$rootScope" ,"$scope", "ArtistaIndexService", function ($state, $stateParams, $root,$scope, service) {


            function init() {
                //$root.actualizarNotificaciones();
                $scope.cantEncontrada = '';

                $scope.listaTipoShow=[];
                $scope.artistaFilter ={
                    nombre: '',
                    lugarActual: '',
                    tipoShow: {}
                };
                $scope.artistaFilter.tipoShow.nombre='';
                $scope.genero='';
                service.getArtistaByUsuarioMenu($root.userProfile.id).success(function (data) {
                    $scope.artistas = data;

                    $scope.artistas.forEach(function(artista) {
                        artista.show=true;
                    });

                });

                service.getArtistasMg().success(function (data) {
                    $scope.artistasMg = data;
                });

                service.getTipoShow().success(function (data) {
                    $scope.listaTipoShow = data;
                });

            };

            init();


            $scope.verArtista = function (item) {
                $state.go('artistas.view', {artista: item});

            };

            $scope.queryBusqueda = function () {

                if($scope.artistaFilter.nombre==undefined){$scope.artistaFilter.nombre = "";}
                if($scope.artistaFilter.lugarActual==undefined){$scope.artistaFilter.lugarActual = "";}
                if($scope.artistaFilter.tipoShow.nombre==undefined){$scope.artistaFilter.tipoShow.nombre = "";}

                service.getArtistaByFilters($scope.artistaFilter).success(function (data) {
                    $scope.artistas = data;

                    if($scope.genero){

                        $scope.artistas.forEach(function(artista) {
                           artista.tags.forEach(function(tag) {
                               if(tag.nombre == $scope.genero){
                                   artista.show=true;
                               }
                            });

                        });
                    }
                    else{
                        $scope.artistas.forEach(function(artista) {
                            artista.show=true;
                        });
                    }


                });



            };



            $scope.index = function () {
                $state.go("artistas.index");

            };
            $scope.goLugarView = function () {
                $state.go("artistas.view");

            };



        }]);

    };
    return constructor;
});



angular.module('myApp2', ['ngTagsInput'])
    .controller('MyCtrl2', function($scope, $http) {
        $scope.tags = [
            { text: 'just' },
            { text: 'some' },
            { text: 'cool' },
            { text: 'tags' }
        ];
        $scope.loadTags = function(query) {
            return $http.get('/tags?query=' + query);
        };
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




//myApp.factory('myService', function() {});


