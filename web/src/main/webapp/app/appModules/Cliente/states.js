define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/Cliente/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("cliente", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/cliente"
		});
		/* Module states */
		$stateProvider.state("cliente.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "ClienteIndexCtrl"
		});
        $stateProvider.state("cliente.create", {
            templateUrl: viewsPath + "/create.html",
            url: "/create",
            controller: "ClienteCreateCtrl"
        })
        $stateProvider.state("cliente.edit", {
            templateUrl: viewsPath + "/edit.html",
            params:{cliente: null},
            controller: "ClienteEditCtrl"
        })
        $stateProvider.state("cliente.view", {
            templateUrl: viewsPath + "/edit.html",
            params:{cliente: null},
            controller: "ClienteViewCtrl"
        })
	};
	return constructor;
});