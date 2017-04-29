define(["Usuario/states", "Usuario/controllers/controllers", "Usuario/services/services"], function(states, controllers, services) {
	var module = angular.module("Usuario", ['ngFileUpload']);
	controllers.init(module);
	services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});