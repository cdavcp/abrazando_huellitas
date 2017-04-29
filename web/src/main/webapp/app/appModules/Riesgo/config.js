define(["Riesgo/states", "Riesgo/controllers/controllers", "Riesgo/services/services"], function(states, controllers, services) {
	var module = angular.module("Riesgo");
	controllers.init(module);
	services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});