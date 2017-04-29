define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("AseguradoraTemplateService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
			var service = {};
            var url = REST_DIR + '/aseguradora';

            service.inicializarIndex = function(){
                angular.element('#spinner').show();
                return $http.get(url).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.create = function(aseguradora, file){
                angular.element('#spinner').show();

                return Upload.upload({
                    url: url,
                    fields: {aseguradora:aseguradora},
                    file: file
                });
            };

            service.update = function(aseguradora){
                angular.element('#spinner').show();
                return $http.put(url,aseguradora).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

			return service;
		}]);
	};
	return constructor;
});