define([], function() {
    var constructor = {};
    constructor.init = function(module) {
        module.service("SolicitudCreateService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
            var service = {};
            var url = REST_DIR + '/evento';
            var urlD = REST_DIR + '/artista';
            var urlL = REST_DIR + '/lugar';


            //service.getPersona = function(idUsuario){
            //    return $http.post(url + "/datosPersona", idUsuario).success(function (data) {
            //        angular.element('#spinner').hide();
            //        return data;
            //    })
            //};

            service.updateEvento = function(evento){
                angular.element('#spinner').show();
                return $http.post(url  + "/update",evento).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getArtistaByUsuario = function(usuario){
                angular.element('#spinner').show();
                return $http.post(urlD  + "/datosArtista",usuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getLugaresMByUsuario = function(usuario){
                angular.element('#spinner').show();
                return $http.post(urlL  + "/datosLugar",usuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };


            service.create = function(evento){
                angular.element('#spinner').show();
                return $http.post(url,evento).success(function (data) {
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


        module.service("SolicitudIndexService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
            var service = {};
            var url = REST_DIR + '/evento';
            var urlComentario = REST_DIR + '/comentario';



            //service.getPersona = function(idUsuario){
            //    return $http.post(url + "/datosPersona", idUsuario).success(function (data) {
            //        angular.element('#spinner').hide();
            //        return data;
            //    })
            //};

            service.getComentariosByEvento = function(evento){
                angular.element('#spinner').show();
                return $http.post(urlComentario  + "/findComentariosByEvento",evento).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.createMegusta = function(meGusta){
                angular.element('#spinner').show();
                return $http.post(url  + "/createMeGusta",meGusta).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.findMG = function(meGusta){
                angular.element('#spinner').show();
                return $http.post(url  + "/findMeGusta",meGusta).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };


            service.createComentario = function(comentario){
                angular.element('#spinner').show();
                return $http.post(urlComentario,comentario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getArtistaByUsuario = function(usuario){
                angular.element('#spinner').show();
                return $http.post(url  + "/datosArtista",usuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getSolicitudesByUsuario = function(usuario){
                angular.element('#spinner').show();
                return $http.post(url  + "/init",usuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.updateEvento = function(evento){
                angular.element('#spinner').show();
                return $http.post(url  + "/update",evento).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };


            return service;
        }]);
    };
    return constructor;
});
