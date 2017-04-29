define([], function() {
    var constructor = {};
    constructor.init = function(module) {
        module.service("LugarCreateService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
            var service = {};
            var url = REST_DIR + '/lugar';


            //service.getPersona = function(idUsuario){
            //    return $http.post(url + "/datosPersona", idUsuario).success(function (data) {
            //        angular.element('#spinner').hide();
            //        return data;
            //    })
            //};

            service.create = function(lugar){
                angular.element('#spinner').show();
                return $http.post(url,lugar).success(function (data) {
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

            return service;
        }]);


        module.service("LugarIndexService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
            var service = {};
            var url = REST_DIR + '/lugar';
            var urlSeguidores = REST_DIR + '/seguidoresLugar';
            var urlVotacion = REST_DIR + '/votacionLugar';

            service.getLugarByUsuario = function(usuario){
                angular.element('#spinner').show();
                return $http.post(url  + "/datosLugar",usuario).success(function (data) {
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


            service.findPuntuacionInteresado = function(seguidor){
                angular.element('#spinner').show();
                return $http.post(urlVotacion + "/consultarVotacionLugarInteresado",seguidor).success(function (data) {
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
                return $http.post(urlVotacion  + "/updateVotacionLugarInteresado" ,seguidor).success(function (data) {
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


            service.getLugarByUsuarioMenu = function(usuario){
                angular.element('#spinner').show();
                return $http.post(url  + "/datosLugarMenu",usuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getLugarByFilters = function(lugar){
                angular.element('#spinner').show();
                return $http.post(url  + "/lugarByFilters",lugar).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };



            return service;
        }]);
    };
    return constructor;
});
