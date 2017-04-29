define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("PolizaTemplateService", ["$http", "$resource", function($http, $resource) {
			var service = {};
            var url = REST_DIR + '/poliza';

            service.inicializar = function(idUsuarioLogueado){
                angular.element('#spinner').show();
                return $http.post(url + "/init", idUsuarioLogueado).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.inicializarView = function(idPoliza){
                angular.element('#spinner').show();
                return $http.post(url + "/view", idPoliza).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.create = function(cliente){
                angular.element('#spinner').show();
                return $http.post(url, cliente);
            };

            service.findCoberturas = function(idAseguradora, idRubro){
                angular.element('#spinner').show();
                var param = {idAseguradora: idAseguradora, idRubro : idRubro};
                return $http.post(url + "/findCoberturas", param).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.checkNumeroPoliza = function(numeroPoliza, idAseguradora){
                var param = {poliza: {numero:numeroPoliza}, idAseguradora:idAseguradora};
                return $http.post(url + "/checkNumeroPoliza", param).success(function (data) {
                    return data;
                });
            };

            service.consultar = function(filtros){
                angular.element('#spinner').show();
                return $http.post(url + "/consultar", filtros).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.darDeBaja = function(idPoliza){
                angular.element('#spinner').show();
                return $http.post(url + "/eliminar", idPoliza).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };
			return service;
		}]);
	};
	return constructor;
});