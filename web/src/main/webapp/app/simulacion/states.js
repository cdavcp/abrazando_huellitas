define([], function() {
	var constructor = {};

	constructor.init = function($stateProvider) {
        $stateProvider.state("simulacion", {
            url: "/",
            controller: "SimulacionCtrl"
        });
	};
	return constructor;
});