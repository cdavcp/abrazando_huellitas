define(["simulacion.states", "simulacion.controllers", "simulacion.services", "simulacion.modules", "fileReader", "moment", "lazyLoad", "jsZip", "excelReader", "ui-router", "angular-route", "ui-router-extras"], function(moduleStates, controller, service, applicationModules, fileReader) {

	var app = angular.module("sigep.simulacion", ['ui.router', 'oc.lazyLoad', "ct.ui.router.extras"]);
    service.init(app);
    controller.init(app);
    fileReader.init(app);

	app.config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', "$futureStateProvider", 
	   function ($stateProvider,   $urlRouterProvider,   $ocLazyLoadProvider,   $futureStateProvider) {
		moduleStates.init($stateProvider);
		$urlRouterProvider.otherwise(function() {
			console.warn("The requested url/state isnt defined.");
			return "/";
		});
		$ocLazyLoadProvider.config({
			debug: true,
			jsLoader: requirejs
		});
		var lazyLoadStateFactory = ["$rootScope", "$state", "$q", "$ocLazyLoad", "futureState", function ($root, $state, $q, $ocLazyLoad, futureState) {
			var deferred = $q.defer();
			if (!futureState.module) {
				console.log("The futureState doesnt provide a module to do lazyLoad. Provide a module");
				deferred.reject("Missing futureState's module property");
				return;
			}

            var moduleConfig = $ocLazyLoad.getModuleConfig(futureState.module);
            createModule(futureState.module, moduleConfig.dependencies);
            $ocLazyLoad.load(futureState.module).then(function success() { deferred.resolve(undefined); }, function error() { deferred.reject("Error loading future state"); });

			return deferred.promise;
		}];
        $futureStateProvider.stateFactory('lazyLoad', lazyLoadStateFactory);
        window.futureStateProvider = $futureStateProvider;
	}]);

	app.run(["$rootScope", "$ocLazyLoad", "$state", function($root, $lazyLoad, $state) {
		applicationModules.init($lazyLoad);
	}]);

	return {};
});