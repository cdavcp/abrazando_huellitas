define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("BandejaSolicitudTemplateService", ["$http", "$resource", function($http, $resource) {
			var service = {};

            var url = REST_DIR + '/bandejaSolicitud';

            service.inicializarIndex = function(usuarioId){
                angular.element('#spinner').show();
                return $http.post(url + "/inicializarIndex", usuarioId).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getTiposDocumento = function(){
                angular.element('#spinner').show();
                return $http.get(url + "/getTiposDocumento").success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.getAseguradoras = function(){
                angular.element('#spinner').show();
                return $http.get(url + "/getAseguradoras").success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.descartar = function(solicitudId){
                angular.element('#spinner').show();
                return $http.post(url + "/descartar", solicitudId).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.checkNumero = function(numero, aseguradoraId){
                angular.element('#spinner').show();
                return $http.post(url + "/checkNumero", {numeroPoliza:numero, aseguradoraId:aseguradoraId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.update = function(solicitud, numeroPoliza, aseguradoraId){
                angular.element('#spinner').show();
                return $http.put(url, {solicitud: solicitud, numeroPoliza: numeroPoliza, aseguradoraId:aseguradoraId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });;
            };

			return service;
		}]);
	};
	return constructor;
});