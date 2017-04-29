define([], function() {
    var constructor = {};
    constructor.init = function(module) {
        module.service("ArtistaCreateService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
            var service = {};
            var url = REST_DIR + '/artista';




            //service.getPersona = function(idUsuario){
            //    return $http.post(url + "/datosPersona", idUsuario).success(function (data) {
            //        angular.element('#spinner').hide();
            //        return data;
            //    })
            //};

            service.create = function(artista){
                angular.element('#spinner').show();
                return $http.post(url,artista).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getTipoShow = function(){
                angular.element('#spinner').show();
                return $http.get(url + "/tiposShow").success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.updateA = function(artista){
                angular.element('#spinner').show();
                return $http.post(url+ "/update",artista).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };



            return service;
        }]);


        module.service("ArtistaIndexService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
            var service = {};
            var url = REST_DIR + '/artista';
            var urlEvento = REST_DIR + '/evento';
            var urlSeguidores = REST_DIR + '/seguidoresArtista';
            var urlVotacion = REST_DIR + '/votacionArtista';

            service.getTipoShow = function(){
                angular.element('#spinner').show();
                return $http.get(url + "/tiposShow").success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };


            //service.getPersona = function(idUsuario){
            //    return $http.post(url + "/datosPersona", idUsuario).success(function (data) {
            //        angular.element('#spinner').hide();
            //        return data;
            //    })
            //};

            service.getArtistaByUsuario = function(usuario){
                angular.element('#spinner').show();
                return $http.post(url  + "/datosArtista",usuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };



            service.getEventosByArtista = function(artista){
                angular.element('#spinner').show();
                return $http.post(urlEvento  + "/artistas",artista).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };


           service.getEventosByTodosFiltros = function(query){
                angular.element('#spinner').show();
                return $http.post(urlEvento  + "/todoFiltros",query).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };


            service.crearSeguidor = function(seguidor){
                angular.element('#spinner').show();
                return $http.post(urlSeguidores,seguidor).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.findSeguidor = function(seguidor){
                angular.element('#spinner').show();
                return $http.post(urlSeguidores + "/consultarSeguidor",seguidor).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.deleteSeguidor = function(seguidor){
                angular.element('#spinner').show();
                return $http.post(urlSeguidores + "/borrarSeguidor",seguidor).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };




            service.findPuntuacionInteresado = function(seguidor){
                angular.element('#spinner').show();
                return $http.post(urlVotacion + "/consultarVotacionArtistaInteresado",seguidor).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.findVotacion = function(seguidor){
                angular.element('#spinner').show();
                return $http.post(urlVotacion + "/datosVotacion",seguidor).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.crearPuntuacionInteresado = function(seguidor){
                angular.element('#spinner').show();
                return $http.post(urlVotacion,seguidor).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.updatePuntuacionInteresado = function(seguidor){
                angular.element('#spinner').show();
                return $http.post(urlVotacion  + "/updateVotacionArtistaInteresado" ,seguidor).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };


            service.getArtistaByUsuarioMenu = function(usuario){
                angular.element('#spinner').show();
                return $http.post(url  + "/datosLugarMenu",usuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getArtistaByFilters = function(artista){
                angular.element('#spinner').show();
                return $http.post(url  + "/artistaByFilters",artista).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };


            service.getArtistasMg = function(){
                angular.element('#spinner').show();
                return $http.get(url + "/artistaMg").success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };




            return service;
        }]);
    };
    return constructor;
});
