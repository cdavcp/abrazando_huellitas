window.BASE_URL = "http://" + window.location.host;
window.BASE_URL += window.location.pathname.indexOf("sigep-web") != -1 ? "/sigep-web" : "";
window.APP_DIR = BASE_URL + "/app";
window.GLOBAL_DIR = APP_DIR + "/global";
window.MODULES_DIR = APP_DIR + "/appModules";
window.REST_DIR = BASE_URL + "/rest";

window.createModule = function(moduleName, dependencies) {
	var module;
	try {
		module = angular.module(moduleName);
	} catch(e) {
		module = angular.module(moduleName, dependencies || []);
	}
	return module;
};

window.lazyLoad = function(moduleName, injectionName) {
	var name = injectionName || "module";
	return { 
		name : ['$ocLazyLoad', function ($ocLazyLoad) {
			return $ocLazyLoad.load(moduleName);
		}]
	}
};

var libsURL = BASE_URL + "/libs/";
requirejs.config({
	baseUrl: MODULES_DIR + "/",
	paths : {
		/* Application core */
		config: APP_DIR + "/config",
		applicationStates: APP_DIR + "/states",
		applicationControllers: APP_DIR + "/controller",
		applicationModules: APP_DIR + "/applicationModules",
		factorizator: libsURL + "factorizator",
		fileReader: GLOBAL_DIR + "/directives/fileReader",
		menuItem: GLOBAL_DIR + "/directives/menuItem",
		pagination: GLOBAL_DIR + "/directives/pagination",
		GlobalModule: GLOBAL_DIR + "/moduleConfig",
		dateInterceptor: APP_DIR + "/dateInterceptor",

        /* Simulacion core */
        "simulacion.config": APP_DIR + "/simulacion/config",
        "simulacion.states": APP_DIR + "/simulacion/states",
        "simulacion.controllers": APP_DIR + "/simulacion/controller",
        "simulacion.services": APP_DIR + "/simulacion/service",
        "simulacion.modules": APP_DIR + "/simulacion/applicationModules",

		/* Libraries */
		jquery: libsURL + "jquery",
		bootstrap: libsURL + "bootstrap/bootstrap.min",
        jasny: libsURL + "jasny-bootstrap/jasny-bootstrap.min",
		moment: libsURL + "moment",
		jsZip: libsURL + "jszip",
		excelReader: libsURL + "xlsx",
		bootbox: libsURL + "bootbox",
        select2: libsURL + "select2",
        wizard: libsURL + "wizard/jquery.wizard",
        simulacionWizard: libsURL + "wizard/wizard",
        draganddrop: libsURL + "draganddrop",
        googleCharts: libsURL + "ng-google-chart",

		/* Angular libs */
		angular: libsURL + "angular/angular",
		"angular-route": libsURL + "angular/angular-route.min",
		"angular-moment": libsURL + "angular/angular-moment",
		"angular-cookies": libsURL + "angular/angular-cookies.min",
		"angular-resource": libsURL + "angular/angular-resource.min",
		"ui-router": libsURL + "angular/angular-ui-router",
		"ui-router-extras": libsURL + "angular/ct-ui-router-extras",
		lazyLoad: libsURL + "angular/ocLazyLoad",
        "angular-select2": libsURL + "angular/angular-select2",
        "ui-bootstrap": libsURL + "angular/ui-bootstrap"
	},
	shim: {
        select2: { deps: ["jquery"], exports: "select2" },
        "angular-select2": { deps: ["select2"], exports: "angular-select2" },
		"excelReader": { deps: ["jsZip"], exports: "excelReader"},
		bootstrap: { deps: ["jquery"], exports: "bootstrap"},
        wizard: { deps: ["bootstrap"], exports: "wizard" },
		bootbox: { deps: ["bootstrap"], exports: "bootbox"}
	}
});