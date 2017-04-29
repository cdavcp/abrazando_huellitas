define(["Rubro/states", "Rubro/controllers/controllers", "Rubro/services/services"], function(states, controllers, services) {
	var module = angular.module("Rubro", ['ngFileUpload']);
	controllers.init(module);
	services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});