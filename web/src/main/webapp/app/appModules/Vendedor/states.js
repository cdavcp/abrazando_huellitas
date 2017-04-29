define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/Vendedor/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("vendedor", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/vendedor"
		});
		/* Module states */
		$stateProvider.state("vendedor.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "VendedorIndexCtrl"
		});
        $stateProvider.state("vendedor.create", {
            templateUrl: viewsPath + "/create.html",
            url: "/create",
            controller: "VendedorCreateCtrl"
        })
        $stateProvider.state("vendedor.edit", {
            templateUrl: viewsPath + "/edit.html",
            params:{vendedor: null},
            controller: "VendedorEditCtrl"
        })
        $stateProvider.state("vendedor.view", {
            templateUrl: viewsPath + "/edit.html",
            params:{vendedor: null},
            controller: "VendedorViewCtrl"
        })

    };
	return constructor;
});