define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("ClienteTemplateService", ["$http", "$resource", "Upload", function($http) {
			var service = {};
            var url = REST_DIR + '/cliente';

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

            service.create = function(cliente){
                angular.element('#spinner').show();
                return $http.post(url,cliente).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.exists = function(cliente){
                angular.element('#spinner').show();
                return $http.post(url + '/exists', cliente).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.update = function(cliente){
                angular.element('#spinner').show();
                return $http.put(url, cliente);
            };

            service.delete = function(cliente){
                angular.element('#spinner').show();
                return $http.post(url + "/delete", cliente);
            };
			return service;
		}]);
	};
	return constructor;
});