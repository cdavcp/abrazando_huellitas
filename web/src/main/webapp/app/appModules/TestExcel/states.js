define([], function() {
	var constructor = {};
	var viewsPath = GLOBAL_DIR + "/views";
	constructor.init = function($stateProvider) {
		$stateProvider.state("testExcel", {
			templateUrl: viewsPath + "/testExcel.html",
			url: "/testExcel",
			controller: "ExcelCtrl"
		});
	};
	return constructor;
});