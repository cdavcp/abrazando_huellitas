define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/BandejaAsignacion/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("bandejaAsignacion", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/bandejaAsignacion"
		});
		/* Module states */
		$stateProvider.state("bandejaAsignacion.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "BandejaAsignacionIndexCtrl"
		});
	};
	return constructor;
});