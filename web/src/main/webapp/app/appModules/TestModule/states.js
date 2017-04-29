define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/TestModule/views";
	constructor.init = function($stateProvider) {
		$stateProvider.state("test", {
			templateUrl: viewsPath + "/index.html",
			url: "/test",
			controller: "TestCtrl"
		});
		$stateProvider.state("test.nested", {
			templateUrl: viewsPath + "/index2.html",
			url: "/nested",
			controller: "TestCtrl"
		});
		$stateProvider.state("paramState", {
			templateUrl: viewsPath + "/index3.html",
			url: "/other/:id",
			controller: "TestCtrl"
		});
		$stateProvider.state("testModule", {
			templateUrl: viewsPath + "/index3.html",
			controller: "TestCtrl"
		});
	};
	return constructor;
});