define(["Poliza/states", "Poliza/controllers/polizaCreateController", "Poliza/controllers/polizaIndexController", "Poliza/controllers/polizaViewController", "Poliza/services/polizaService"], function(states, createController, indexController, viewController, service) {
	var module = angular.module("Poliza");
	createController.init(module);
    indexController.init(module);
    viewController.init(module);
	service.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});