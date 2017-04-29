define(["Productor/states", "Productor/controllers/controllers", "Productor/services/services"], function(states, controllers, services) {
	var module = angular.module("Productor", ['ngFileUpload']);
	controllers.init(module);
	services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});