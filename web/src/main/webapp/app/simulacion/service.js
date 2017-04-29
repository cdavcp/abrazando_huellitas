define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("SimulacionService", ["$http",function($http) {
			var service = {};
            var url = REST_DIR + '/simulacion';

            service.inicializarIndex = function() {
                angular.element('#spinner').show();
                return $http.get(url).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.searchTiposCobertura = function() {
                return $http.get(url + "/buscarTiposCobertura");
            };

            service.searchCoberturas = function(rubroId, tipoCoberturaId, valorAuto) {
                params = {rubroId: rubroId, tipoCoberturaId: tipoCoberturaId};
                if(valorAuto){
                    params.valorAuto = valorAuto;
                }
                return $http.post(url + "/searchCoberturas", params).success(function (data) {
                    return data;
                });
            };

            service.buscarTiposDocumento = function() {
                angular.element('#spinner').show();
                return $http.get(url + "/getTiposDocumento").success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.ingresar = function(cliente) {
                angular.element('#spinner').show();
                return $http.post(url + "/ingresar", cliente).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.registrarNuevaCuenta = function(cliente) {
                angular.element('#spinner').show();
                return $http.post(url + "/registrarNuevaCuenta", cliente).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.enviarSolicitud = function(solicitud) {
                angular.element('#spinner').show();
                return $http.post(url + "/enviarSolicitud", solicitud).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.searchMarcasAuto = function() {
                return $http.get(url + "/searchMarcasAuto");
            };

            service.searchModelos = function(marca) {
                angular.element('#spinner').show();
                return $http.post(url + "/searchModelos", marca).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.searchAnios = function(marca,modelo) {
                angular.element('#spinner').show();
                return $http.post(url + "/searchAnios", {marca:marca,modelo:modelo}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.searchVersionesYPrecios = function(marca,modelo,anio) {
                angular.element('#spinner').show();
                return $http.post(url + "/searchVersionesYPrecios", {marca:marca,modelo:modelo,anio:anio}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

			return service;
		}]);
	};
	return constructor;
});