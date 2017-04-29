define(["BandejaSolicitud/states", "BandejaSolicitud/controllers/controllers", "BandejaSolicitud/services/services"], function(states, controllers, services) {
	var module = angular.module("BandejaSolicitud");
	controllers.init(module);
	services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});