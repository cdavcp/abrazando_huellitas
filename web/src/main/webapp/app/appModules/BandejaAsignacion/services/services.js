define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("BandejaAsignacionTemplateService", ["$http", "$resource", function($http, $resource) {
			var service = {};
            var url = REST_DIR + '/bandejaAsignacion';

            service.inicializarIndex = function(){
                angular.element('#spinner').show();
                return $http.get(url).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.asignar = function(productores){
                angular.element('#spinner').show();
                return $http.post(url,{listaProductores: productores}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            return service;
		}]);
	};
	return constructor;
});