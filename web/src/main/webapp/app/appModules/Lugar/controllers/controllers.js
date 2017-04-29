define(["bootbox"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {

        module.controller("LugarCreateCtrl", ["$state", "$stateParams","$rootScope" ,"$scope", "LugarCreateService", function ($state, $stateParams, $root,$scope, service) {
            $scope.data = {};
            $scope.ver = true;
            $scope.modificado = false;
            $scope.personaOriginal = {};
            $scope.lugar={};
            $scope.listaTipoShow = {};
            $scope.lugar.tipoShows = [];
            $scope.tipoShows = [];
            $scope.tipoShow = {};
            $scope.tags = {};
            $scope.tag = {};
            $scope.lugar.tags = [];
            $scope.lugar.usuario = {};


            function init() {

                service.getTipoShow().success(function (data) {
                    //$scope.data.tipoShow = data;
                    $scope.listaTipoShow = data;
                    //$scope.personaOriginal = data;
                });

            };
            init();


            $scope.MyCtrl = function ($scope) {
                $scope.gPlace;

            };

            $scope.loadTags = function(query) {
                //return $http.get('/tags?query=' + query);
                console.log($scope.lugar);
            };

            $scope.guardar = function () {

                $scope.lugar.usuario.id = $root.userProfile.id;
                /*$scope.tags.forEach(function(tag) {
                    console.log(tag.text);
                    $scope.tag.nombre = tag.text;
                    $scope.artista.tags.push($scope.tag);
                    //a b c
                });*/

                $scope.tipoShows.forEach(function(tipoShow) {
                    $scope.tipoShow.id = tipoShow.tipoShows.id;
                    $scope.lugar.tipoShows.push($scope.tipoShow);
                });

                //$scope.artista.tipoShow.id = 1;
                console.log($scope.lugar);
                service.create($scope.lugar).success(function () {
                    success($scope, $state);
                })
                    .error(function () {
                        error();
                    });
            }

            function error() {
                angular.element('#spinner').hide();
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
                                state.go('lugar.index');
                            }
                        }
                    },
                    closeButton: false
                });
            };

            $scope.cancelar = function () {

            };




        }]);

        module.controller("LugarIndexCtrl", ["$state", "$stateParams","$rootScope" ,"$scope", "LugarIndexService", function ($state, $stateParams, $root,$scope, service) {



            /*$scope.lugares = [{nombre:'La esquinita',ubicacion: 'Córdoba, Córdoba, Argentina', mail:'laesquinita@gmail.com', telefono:'3515455458' },

                {nombre:'Maria Maria',ubicacion: 'Córdoba, Córdoba, Argentina', mail:'mariamaria@gmail.com', telefono:'3516457854' }];
*/

            function init() {
                service.getLugarByUsuario($root.userProfile.id).success(function (data) {
                    $scope.lugar = data;
                });
            }

            init();

            $scope.verLugar = function (item) {
                $state.go('lugar.view', {lugar: item});

            };


            $scope.crearArtista = function () {
                $state.go("lugar.create");

            };


        }]);


        module.controller("LugarViewCtrl", ["$state", "$stateParams","$rootScope" ,"$scope", "LugarIndexService", function ($state, $stateParams, $root,$scope, service) {

            $scope.seguidor = {};
            $scope.seguidorUsuario = {};
            $scope.test = {};
            $scope.sigue=false;
            $scope.botonSeguir="";
            $scope.estilo= "";
            $scope.tipoS= "";
            $scope.tiposS = "";
            $scope.seguidorUsuario.usuario = {};
            $scope.seguidorUsuario.usuario.id = "";
            $scope.lugar = {};


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
                $scope.lugar = $stateParams.lugar;


                if($scope.lugar.tags){
                    $scope.lugar.tags.forEach(function(tag) {
                        $scope.estilo +=  tag.nombre + ", ";
                    });
                }
                if($scope.lugar.tipoShow){
                    $scope.lugar.tipoShow.forEach(function(tag) {
                        $scope.tipoS +=  tag.nombre + ", ";
                    });
                }

                $scope.estilos = "";
                $scope.seguidor.lugar = $scope.lugar;
                $scope.seguidor.usuario = angular.copy($root.userProfile);
                delete $scope.seguidor.usuario["name"];
                delete $scope.seguidor.usuario["avatar"];
                delete $scope.seguidor.usuario["validSession"];
                delete $scope.seguidor.usuario["menuOptions"];

                //COPIA DE USUARIO
                $scope.votacionLugar = angular.copy($scope.seguidor);
                $scope.votacionLugar.puntuacion = "";
                $scope.votacionLugar.id = "";

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



            $scope.goToSolicitud = function () {
                $state.go('solicitud.create', {lugar: $scope.lugar});

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
                $state.go("lugar.index");

            };


        }]);


        module.controller("LugarMenuCtrl", ["$state", "$stateParams","$rootScope" ,"$scope", "LugarIndexService", function ($state, $stateParams, $root,$scope, service) {


            function init() {

                $scope.lugarFilter ={
                    nombre: '',
                    ubicacion: ''
                };

                service.getLugarByUsuarioMenu($root.userProfile.id).success(function (data) {
                    $scope.lugares = data;
                });

            };

            init();


            $scope.verLugar = function (item) {
                $state.go('lugar.view', {lugar: item});

            };

            $scope.queryBusqueda = function () {

                if($scope.lugarFilter.nombre==undefined){$scope.lugarFilter.nombre = "";}
                if($scope.lugarFilter.ubicacion==undefined){$scope.lugarFilter.ubicacion = "";}

                service.getLugarByFilters($scope.lugarFilter).success(function (data) {
                    $scope.lugares = data;
                });

            };



            $scope.index = function () {
                $state.go("lugar.index");

            };
            $scope.goLugarView = function () {
                $state.go("lugar.view");

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
            //scope.gPlace.setTypes(locality,sublocality,country);


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




