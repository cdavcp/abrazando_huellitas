define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/Comision/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("comision", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/comision"
		});
		/* Module states */
		$stateProvider.state("comision.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "ComisionIndexCtrl"
		});
        $stateProvider.state("comision.createA", {
            templateUrl: viewsPath + "/createArchivo.html",
            url: "/createA",
            controller: "ComisionCreateArchivoCtrl"
        });
        $stateProvider.state("comision.createM", {
            templateUrl: viewsPath + "/createManual.html",
            params: {param : null},
            controller: "ComisionCreateManualCtrl"
        });
        $stateProvider.state("comision.edit", {
            templateUrl: viewsPath + "/edit.html",
            params: {param : null},
            controller: "ComisionEditCtrl"
        });
        $stateProvider.state("comision.view", {
            templateUrl: viewsPath + "/view.html",
            params: {param : null},
            controller: "ComisionViewCtrl"
        });
	};
	return constructor;
});