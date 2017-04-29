define(["TestExcel/states", "TestExcel/controllers/controllers"], function(states, controllers) {
		var module = angular.module("TestExcel");
		controllers.init(module);
		module.config(["$stateProvider", function($stateProvider) {
			states.init($stateProvider);
		}]);

		return {};
});