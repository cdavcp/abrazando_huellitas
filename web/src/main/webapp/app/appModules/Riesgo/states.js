define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/Riesgo/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("riesgo", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/riesgo"
		});
		/* Module states */
		$stateProvider.state("riesgo.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "RiesgoIndexCtrl"
		});
        $stateProvider.state("riesgo.create", {
            templateUrl: viewsPath + "/create.html",
            url: "/create",
            controller: "RiesgoCreateCtrl"
        });
        $stateProvider.state("riesgo.edit", {
            templateUrl: viewsPath + "/edit.html",
            params:{riesgo: null},
            controller: "RiesgoEditCtrl"
        });
        $stateProvider.state("riesgo.view", {
            templateUrl: viewsPath + "/edit.html",
            params:{riesgo: null},
            controller: "RiesgoViewCtrl"
        });
	};
	return constructor;
});