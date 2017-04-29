define(["Cobertura/states", "Cobertura/controllers/controllers", "Cobertura/services/services"], function(states, controllers, services) {
	var module = angular.module("Cobertura");
	controllers.init(module);
	services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});