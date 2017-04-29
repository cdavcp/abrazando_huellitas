define(["Aseguradora/states", "Aseguradora/controllers/controllers", "Aseguradora/services/services"], function(states, controllers, services) {
	var module = angular.module("Aseguradora", ['ngFileUpload']);
	controllers.init(module);
	services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});