define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/Usuario/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("usuario", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/usuario"
		});
		/* Module states */
        //$stateProvider.state("usuario.index", {
			//templateUrl: viewsPath + "/index.html",
			//url: "/index",
			//controller: "ProductorIndexCtrl"
        //});
        //$stateProvider.state("usuario.create", {
        //    templateUrl: viewsPath + "/create.html",
        //    params:{usuario: null},
        //    controller: "ProductorCreateCtrl"
        //});
        $stateProvider.state("usuario.edit", {
            templateUrl: viewsPath + "/edit.html",
            params:{persona: null},
            controller: "UsuarioIndexCtrl"
        });
        //$stateProvider.state("usuario.view", {
        //    templateUrl: viewsPath + "/edit.html",
        //    params:{productor: null},
        //    controller: "ProductorViewCtrl"
        //})
	};
	return constructor;
});