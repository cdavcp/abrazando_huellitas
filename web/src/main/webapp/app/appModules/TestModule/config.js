define(["TestModule/states", "TestModule/controllers/controllers"], function(states, controllers) {
		var module = angular.module("TestModule");
		controllers.init(module);
		module.config(["$stateProvider", function($stateProvider) {
			states.init($stateProvider);
		}]);

		return {};
});