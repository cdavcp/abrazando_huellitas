define(["Vendedor/states", "Vendedor/controllers/controllers", "Vendedor/services/services"], function(states, controllers, services) {
	var module = angular.module("Vendedor");
	controllers.init(module);
    services.init(module);

	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});