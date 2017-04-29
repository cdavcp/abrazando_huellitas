define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/Cobertura/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("cobertura", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/cobertura"
		});
		/* Module states */
		$stateProvider.state("cobertura.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "CoberturaIndexCtrl"
		});
        $stateProvider.state("cobertura.create", {
            templateUrl: viewsPath + "/create.html",
            params:{aseguradora: null},
            controller: "CoberturaCreateCtrl"
        });
        $stateProvider.state("cobertura.edit", {
            templateUrl: viewsPath + "/edit.html",
            params:{cobertura: null},
            controller: "CoberturaEditCtrl"
        });
        $stateProvider.state("cobertura.view", {
            templateUrl: viewsPath + "/edit.html",
            params:{cobertura: null},
            controller: "CoberturaViewCtrl"
        });
	};
	return constructor;
});