define(["Cliente/states", "Cliente/controllers/controllers", "Cliente/services/services"], function(states, controllers, services) {
	var module = angular.module("Cliente", ['ngFileUpload']);
	controllers.init(module);
	services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});