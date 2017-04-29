define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.controller("TestCtrl", ["$state", "$stateParams", "$scope", function($state, $stateParams, $scope) {
			$scope.success = "Test property";
			$scope.id = $stateParams.id;
		}]);
	};
	return constructor;
});