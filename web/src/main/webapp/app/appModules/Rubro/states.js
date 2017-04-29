define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/Rubro/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("rubro", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/rubro"
		});
		/* Module states */
		$stateProvider.state("rubro.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "RubroIndexCtrl"
		});
        $stateProvider.state("rubro.create", {
            templateUrl: viewsPath + "/create.html",
            url: "/create",
            controller: "RubroCreateCtrl"
        });
	};
	return constructor;
});