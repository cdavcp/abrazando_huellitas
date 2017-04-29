define([], function() {
	var constructor = {};
	var viewsPath = MODULES_DIR + "/BandejaSolicitud/views";
	constructor.init = function($stateProvider) {
		/* abstract-base-state definition to match with auto-futureState convention */
		$stateProvider.state("bandejaSolicitud", {
			abstract: true,
			template: "<div ui-view></div>",
			url: "/bandejaSolicitud"
		});
		/* Module states */
		$stateProvider.state("bandejaSolicitud.index", {
			templateUrl: viewsPath + "/index.html",
			url: "/index",
			controller: "BandejaSolicitudIndexCtrl",
            params:{param: null}
		});

        $stateProvider.state("bandejaSolicitud.view", {
            templateUrl: viewsPath + "/view.html",
            params:{solicitud: null},
            controller: "BandejaSolicitudViewCtrl"
        });

        $stateProvider.state("bandejaSolicitud.ingresarPoliza", {
            templateUrl: viewsPath + "/ingresarPoliza.html",
            params:{solicitud: null},
            controller: "IngresarPolizaCtrl"
        });
	};
	return constructor;
});