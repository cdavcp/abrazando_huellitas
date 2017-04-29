define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/EstadisticasProductor/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("estadisticasProductor", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/estadisticasProductor"
		});
		/* Module states */
		$stateProvider.state("estadisticasProductor.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "EstadisticasProductorIndexCtrl"
		});
	};
	return constructor;
});