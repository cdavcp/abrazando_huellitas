define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("RiesgoTemplateService", ["$http", "$resource", function($http, $resource) {
			var service = {};
            var url = REST_DIR + '/riesgo';

            service.inicializarIndex = function(){
                angular.element('#spinner').show();
                return $http.get(url).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.create = function(riesgo){
                angular.element('#spinner').show();
                return $http.post(url,riesgo).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.update = function(riesgo){
                angular.element('#spinner').show();
                return $http.put(url, riesgo);
            };

            service.inicializarCreate = function(){
                angular.element('#spinner').show();
                return $http.get(url + "/inicializarCreate").success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.buscarCoberturasDelRubro = function(rubroId){
                angular.element('#spinner').show();
                return $http.post(url + "/buscarCoberturasDelRubro", rubroId).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.buscarCoberturasDelRiesgo = function(riesgoId){
                angular.element('#spinner').show();
                return $http.post(url + "/buscarCoberturasDelRiesgo", riesgoId).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.checkNombreRiesgo = function(riesgo){
                return $http.post(url + "/checkNombreRiesgo", riesgo).success(function (data) {
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

            service.delete = function(riesgo){
                angular.element('#spinner').show();
                return $http.post(url + "/delete", riesgo);
            };

            return service;

		}]);
	};
	return constructor;
});