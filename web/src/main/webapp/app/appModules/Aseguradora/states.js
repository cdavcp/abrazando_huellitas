define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/Aseguradora/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("aseguradora", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/aseguradora"
		});
		/* Module states */
		$stateProvider.state("aseguradora.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "AseguradoraIndexCtrl"
		});
        $stateProvider.state("aseguradora.create", {
            templateUrl: viewsPath + "/create.html",
            url: "/create",
            controller: "AseguradoraCreateCtrl"
        });
        $stateProvider.state("aseguradora.edit", {
            templateUrl: viewsPath + "/edit.html",
            params:{aseguradora: null},
            controller: "AseguradoraEditCtrl"
        });
        $stateProvider.state("aseguradora.view", {
            templateUrl: viewsPath + "/edit.html",
            params:{aseguradora: null},
            controller: "AseguradoraViewCtrl"
        });
	};
	return constructor;
});