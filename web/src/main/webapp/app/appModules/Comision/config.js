define(["Comision/states", "Comision/controllers/comisionController", "Comision/services/comisionService", "Comision/controllers/comisionCreateArchivoController", "Comision/controllers/comisionCreateManualController", "Comision/controllers/comisionEditController", "Comision/controllers/comisionViewController"], function(states, indexController, services, createArchivoController, createManualController, comisionEditController, comisionViewController) {
	var module = angular.module("Comision", ['ngFileUpload']);
	indexController.init(module);
	createArchivoController.init(module);
    createManualController.init(module);
    comisionEditController.init(module);
    comisionViewController.init(module);
    services.init(module);
	module.config(["$stateProvider", function($stateProvider) {
		states.init($stateProvider);
	}]);
	return {};
});