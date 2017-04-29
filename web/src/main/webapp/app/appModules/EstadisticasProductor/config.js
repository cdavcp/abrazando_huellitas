define(["EstadisticasProductor/states", "EstadisticasProductor/controllers/controllers", "EstadisticasProductor/services/services"], function(states, controllers, services) {
	var module = angular.module("EstadisticasProductor");
	controllers.init(module);
	services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});