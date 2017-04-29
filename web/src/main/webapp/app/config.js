define(["applicationStates", "applicationControllers", "applicationModules", "fileReader", "menuItem", "dateInterceptor",
	"moment", "lazyLoad", "jsZip", "excelReader", "ui-router", "angular-route", "angular-cookies", "angular-resource", "ui-router-extras", "GlobalModule", "angular-select2", "ui-bootstrap"], function(moduleStates, controller, applicationModules, fileReader, menuItem, dateInterceptor) {

	var app = angular.module("sigep", ['ui.router', 'oc.lazyLoad', "ngCookies", "ngResource", "ct.ui.router.extras", "GlobalModule", 'rt.select2', 'ui.bootstrap']);
	controller.init(app);
	fileReader.init(app);
	menuItem.init(app);
	dateInterceptor.init(app);
	
	/* Config */
	app.config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', "$futureStateProvider", "$httpProvider", 
	   function ($stateProvider,   $urlRouterProvider,   $ocLazyLoadProvider,   $futureStateProvider, $httpProvider) {
		moduleStates.init($stateProvider);
		$urlRouterProvider.otherwise(function() {
			console.warn("The requested url/state isnt defined.");
			return "/";
		});

		$ocLazyLoadProvider.config({
			debug: true,
			jsLoader: requirejs
		});
		var lazyLoadStateFactory = ["LoginService", "$rootScope", "$state", "$q", "$ocLazyLoad", "futureState", function (loginService, $root, $state, $q, $ocLazyLoad, futureState) {
			//TODO: evitar duplicacion de codigo
			$root.onLoginSuccess = function(response) {
				if(!response){
                    $state.go("login");
                } else{
                    $root.userProfile = response;
                    $root.userProfile.name = response.nombre;
                    $root.userProfile.avatar = response.foto;
                    $root.userProfile.validSession = true;
                    $root.userProfile.menuOptions = response.tipoUsuario.permisos;
                    if ($root.inLazyLoadCheck) {
                        //TODO: fix that -.-
                        $root.inLazyLoadCheck = false;
                    } else {
                        $state.go("dashboard");
                    }
                }
			};
			$root.onLoginFail = function(reason) {
				console.warn(reason);
				$state.go("login");
			};
			var deferred = $q.defer();
			if (!futureState.module) {
				console.log("The futureState doesnt provide a module to do lazyLoad. Provide a module");
				deferred.reject("Missing futureState's module property");
				return;
			}
			//Login interceptor
			if (!$root.userProfile || !$root.userProfile.validSession) {
				$root.inLazyLoadCheck = true;
				loginService.checkLogin().then($root.onLoginSuccess, $root.onLoginFail).then(function() {
					if (!$root.inLazyLoadCheck) {
						var moduleConfig = $ocLazyLoad.getModuleConfig(futureState.module);
		            	createModule(futureState.module, moduleConfig.dependencies);
	            		$ocLazyLoad.load(futureState.module).then(function success() { deferred.resolve(undefined); }, function error() { deferred.reject("Error loading future state"); });
					}
				});
        	} else {
        		var moduleConfig = $ocLazyLoad.getModuleConfig(futureState.module);
	            createModule(futureState.module, moduleConfig.dependencies);
	            $ocLazyLoad.load(futureState.module).then(function success() { deferred.resolve(undefined); }, function error() { deferred.reject("Error loading future state"); });
        	}
			//end of login interceptor
			return deferred.promise;
		}];

        $futureStateProvider.stateFactory('lazyLoad', lazyLoadStateFactory);
        window.futureStateProvider = $futureStateProvider;

        $httpProvider.interceptors.push('dateInterceptor');
	}]);

	app.run(["$rootScope", "$ocLazyLoad", "$state", "LoginService", function($root, $lazyLoad, $state, loginService) {
		$root.executeHeader = function() { $.getScript(BASE_URL + "/libs/" + "headerScript.js"); };
		$root.executeBody = function() { $.getScript(BASE_URL + "/libs/"  + "bodyScript.js"); };
        $root.imprimirVar=false;
		$root.userProfile = { };
		$root.userProfile.menuOptions = [
            { title: "Ivent", state: "index", icon: "fa fa-play"},

			//TODO: eliminar
            { title: "Eventos", state: "login", icon: "fa fa-calendar"},
			{ title: "Artistas", state: "login", icon: "fa fa-music"},
            { title: "Lugares", state: "login", icon: "fa fa-home"},
            { title: "Ubicacion", state: "login", icon: "fa fa-map-marker"},
            { title: "Noticias", state: "login", icon: "fa fa-newspaper-o"}
        ];

        //TODO: evitar duplicacion de codigo
        $root.onLoginSuccess = function(response) {
            if(!response){
                $state.go("login");
            } else{
                $root.userProfile = response;
                $root.userProfile.name = response.nombre;
                $root.userProfile.avatar = response.foto;
                $root.userProfile.validSession = true;
                $root.userProfile.menuOptions = response.tipoUsuario.permisos;
                if (!$root.inLazyLoadCheck) {
                    $state.go("dashboard");
                }
            }
        };
        $root.onLoginFail = function(reason) {
        	console.warn(reason);
        	$state.go("login");
        };





        $root.loginGo = function() {

            $state.go("login");
        };

        $root.registerGo = function() {

            $state.go("usuario.edit");
        };


        $root.logout = function(){
            var usuario = {id:$root.userProfile.id, nombre:$root.userProfile.nombre, pass: $root.userProfile.pass, sessionId: $root.userProfile.sessionId, tipoUsuario:$root.userProfile.tipoUsuario};
            loginService.logout(usuario).success(function(){
                $root.userProfile = {};


                $root.userProfile.menuOptions = [
                    { title: "Ivent", state: "index", icon: "fa fa-play"},

                    //TODO: eliminar
                    { title: "Eventos", state: "login", icon: "fa fa-calendar"},
                    { title: "Artistas", state: "login", icon: "fa fa-music"},
                    { title: "Lugares", state: "login", icon: "fa fa-home"},
                    { title: "Ubicación", state: "login", icon: "fa fa-map-marker"},
                    { title: "Noticias", state: "login", icon: "fa fa-newspaper-o"}
                ];
                $state.go("login");
            });
        };
		applicationModules.init($lazyLoad);
	}]);

	return {};
});