define([], function() {
    var constructor = {};
    constructor.init = function(module) {
        module.service("ReservaCreateService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
            var service = {};
            var url = REST_DIR + '/reserva';
            var urlD = REST_DIR + '/artista';


            //service.getPersona = function(idUsuario){
            //    return $http.post(url + "/datosPersona", idUsuario).success(function (data) {
            //        angular.element('#spinner').hide();
            //        return data;
            //    })
            //};

            service.getArtistaByUsuario = function(usuario){
                angular.element('#spinner').show();
                return $http.post(urlD  + "/datosArtista",usuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };




            service.create = function(reserva){
                angular.element('#spinner').show();
                return $http.post(url,reserva).success(function (data) {
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


        module.service("ReservaIndexService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
            var service = {};
            var url = REST_DIR + '/reserva';
            var urlLugar = REST_DIR + '/lugar';
            var urlEvento = REST_DIR + '/evento';


            //service.getPersona = function(idUsuario){
            //    return $http.post(url + "/datosPersona", idUsuario).success(function (data) {
            //        angular.element('#spinner').hide();
            //        return data;
            //    })
            //};
            service.update = function(reserva){
                angular.element('#spinner').show();
                return $http.post(url  + "/update",reserva).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getSolicitudesByUsuarioReservas = function(usuario){
                angular.element('#spinner').show();
                return $http.post(urlEvento  + "/init",usuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.findEventosByL = function(lugar){
                angular.element('#spinner').show();
                return $http.post(urlEvento  + "/lugares",lugar).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getReservasByEvento = function(evento){
                angular.element('#spinner').show();
                return $http.post(url  + "/findByEvento",evento).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getReservasByUsuario = function(usuario){
                angular.element('#spinner').show();
                return $http.post(url  + "/reservasByUsuario",usuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getLugaresByUsuario = function(usuario){
                angular.element('#spinner').show();
                return $http.post(urlLugar  + "/datosLugar",usuario).success(function (data) {
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

