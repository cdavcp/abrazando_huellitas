define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("VendedorTemplateService", ["$http", "$resource", function($http, $resource) {
			var service = {};
            var url = REST_DIR + '/vendedor';

            service.inicializarIndex = function(){
                angular.element('#spinner').show();
                return $http.get(url).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.inicializarCreate = function(){
                angular.element('#spinner').show();
                return $http.get(url + "/create").success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.create = function(vendedor){
                angular.element('#spinner').show();
                return $http.post(url, vendedor);
            };

            service.update = function(vendedor){
                angular.element('#spinner').show();
                return $http.put(url, vendedor);
            };

            service.delete = function(vendedor){
                angular.element('#spinner').show();
                return $http.post(url + "/delete", vendedor);
            };

			return service;
		}]);
	};
	return constructor;
});