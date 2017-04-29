define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/Poliza/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("poliza", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/poliza"
		});
		/* Module states */
		$stateProvider.state("poliza.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "PolizaIndexCtrl"
		});
        $stateProvider.state("poliza.create", {
            templateUrl: viewsPath + "/create.html",
            url: "/create",
            params:{param: null},
            controller: "PolizaCreateCtrl"
        });
        $stateProvider.state("poliza.view", {
            templateUrl: viewsPath + "/view.html",
            url: "/view",
            params:{param: null},
            controller: "PolizaViewCtrl"
        });
	};
	return constructor;
});