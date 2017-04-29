define(["BandejaAsignacion/states", "BandejaAsignacion/controllers/controllers", "BandejaAsignacion/services/services"], function(states, controllers, services) {
	var module = angular.module("BandejaAsignacion");
	controllers.init(module);
	services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});