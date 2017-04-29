define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("RubroTemplateService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
            var service = {};
            var url = REST_DIR + '/rubro';

            service.inicializarIndex = function(){
                angular.element('#spinner').show();
                return $http.get(url).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                });
            };

            service.create = function(rubro, file){
                angular.element('#spinner').show();

                return Upload.upload({
                    url: url,
                    fields: {rubro:rubro},
                    file: file
                });
            };

            service.checkNombreRubro = function(nombre){
                return $http.post(url + "/checkNombreRubro", nombre).success(function (data) {
                    return data;
                });
            };

			return service;
		}]);
	};
	return constructor;
});