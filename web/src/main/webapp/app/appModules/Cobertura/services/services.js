define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("CoberturaTemplateService", ["$http", "$resource", function($http, $resource) {
			var service = {};
            var url = REST_DIR + '/cobertura';

            service.inicializarIndex = function(){
                angular.element('#spinner').show();
                return $http.get(url).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.create = function(cobertura){
                angular.element('#spinner').show();
                return $http.post(url,cobertura).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.update = function(cobertura){
                angular.element('#spinner').show();
                return $http.put(url, cobertura);
            };

            service.inicializarCreate = function(){
                angular.element('#spinner').show();
                return $http.get(url + "/inicializarCreate").success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.buscarRiesgos = function(rubroId){
                angular.element('#spinner').show();
                return $http.post(url + "/buscarRiesgos", rubroId).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.checkNombreRiesgo = function(riesgo){
                return $http.post(url + "/checkNombreRiesgo", riesgo).success(function (data) {
                    return data;
                });
            };

            service.checkNombreCobertura = function(riesgo){
                return $http.post(url + "/checkNombreCobertura", riesgo).success(function (data) {
                    return data;
                });
            };

            service.checkNombreCoberturaEdit = function(cobertura){
                return $http.post(url + "/checkNombreCoberturaEdit", cobertura).success(function (data) {
                    return data;
                });
            };

            service.createRiesgo = function(riesgo){
                return $http.post(url + "/createRiesgo", riesgo).success(function (data) {
                    return data;
                });
            };

            service.inicializarEdit = function(coberturaId){
                angular.element('#spinner').show();
                return $http.post(url + "/inicializarEdit", coberturaId).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

			return service;
		}]);
	};
	return constructor;
});