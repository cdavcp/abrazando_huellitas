define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/Productor/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("productor", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/productor"
		});
		/* Module states */
		$stateProvider.state("productor.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "ProductorIndexCtrl"
		});
        $stateProvider.state("productor.create", {
            templateUrl: viewsPath + "/create.html",
            params:{usuario: null},
            controller: "ProductorCreateCtrl"
        });
        $stateProvider.state("productor.edit", {
            templateUrl: viewsPath + "/edit.html",
            params:{productor: null},
            controller: "ProductorEditCtrl"
        });
        $stateProvider.state("productor.view", {
            templateUrl: viewsPath + "/edit.html",
            params:{productor: null},
            controller: "ProductorViewCtrl"
        })
	};
	return constructor;
});