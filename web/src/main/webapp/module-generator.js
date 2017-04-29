var fs = require("fs");

var PATH = "app/appModules/";
var directories = ["controllers", "services", "views", "states"];
console.info("-- SiGeP module generator --");
var args = parseArgs();
if (!args) {
	return;
}

function getParsedName(name) {
	var parsedName = "";
	if (name.indexOf("Module") != -1) {
		var holder = name.split("Module")[0];
		parsedName =  holder[0].toLowerCase() + holder.slice(1);
	} else {
		parsedName = name[0].toLowerCase() + name.slice(1);
	}
	return parsedName;
};

var parsedName = getParsedName(args.name);
var capitalized = parsedName[0].toUpperCase() + parsedName.slice(1);

var templateContent = {};
templateContent["/controllers/controllers.js"] = 'define([], function() {\n\tvar constructor = {};\n\tconstructor.init = function(module) {\n\t\tmodule.controller("' + capitalized + 'IndexCtrl", ["$state", "$stateParams", "$scope", function($state, $stateParams, $scope) {\n\t\t}]);\n\t};\n\treturn constructor;\n});';
templateContent["/services/services.js"] = 'define([], function() {\n\tvar constructor = {};\n\tconstructor.init = function(module) {\n\t\tmodule.service("' + capitalized + 'TemplateService", ["$http", "$resource", function($http, $resource) {\n\t\t\tvar service = {};\n\t\t\treturn service;\n\t\t}]);\n\t};\n\treturn constructor;\n});';
templateContent["/states/customStates.js"] = '/* use futureStateProvider.futureState(...) to create customFuture states */';
templateContent["/views/index.html"] = '<div ui-view>Module ' + args.name + ' was created successfully</div>';

templateContent["/config.js"] = 'define(["' + args.name + '/states", "' + args.name + '/controllers/controllers", "' + args.name + '/services/services"], function(states, controllers, services) {\n\tvar module = angular.module("' + args.name + '");\n\tcontrollers.init(module);\n\tservices.init(module);\n\tmodule.config(["$stateProvider", function($stateProvider) {\n\t\tstates.init($stateProvider);\n\t}]);\n\treturn {};\n});';
templateContent["/states.js"] = 'define([], function() {\n\tvar constructor = {};\n\tvar viewsPath = MODULES_DIR + "/' + args.name + '/views";\n\tconstructor.init = function($stateProvider) {\n\t\t/* abstract-base-state definition to match with auto-futureState convention */\n\t\t$stateProvider.state("' + parsedName  + '", {\n\t\t\tabstract: true,\n\t\t\ttemplate: "<div ui-view></div>",\n\t\t\turl: "/' + parsedName + '"\n\t\t});\n\t\t/* Module states */\n\t\t$stateProvider.state("' + parsedName + '.index", {\n\t\t\ttemplateUrl: viewsPath + "/index.html",\n\t\t\turl: "/index",\n\t\t\tcontroller: "' + capitalized + 'IndexCtrl"\n\t\t});\n\t};\n\treturn constructor;\n});';

var baseModuleDir = PATH + args.name;
if(fs.existsSync(baseModuleDir)){
	console.error("WARNING: The module directory exists. Exiting to avoid overwritting...");
	return;
}
console.log("Making scaffolding for: " + args.name);

fs.mkdir(baseModuleDir, function() {
	console.log(args.name + " directory created.");
	console.info("Creating module directories...");
	directories.forEach(function(dir) {
		console.info("\t*Creating " + dir + " directory.");
		fs.mkdirSync(baseModuleDir + "/" + dir);
	});
	createBasicFiles();
	console.log(args.name + " module created");
});

function createBasicFiles() {
	console.info("Creating basic module files...");
	Object.keys(templateContent).forEach(function(key) {
		console.info("\t*Creating " + key + " file.");
		fs.writeFileSync(baseModuleDir + "/" + key, templateContent[key]);
	});
};

function parseArgs() {
	/*Arguments
	name=MODULE_NAME
	*/
	var args = {};
	if (process.argv.length < 3) {
		showHelp();
		return undefined;
	} else {
		if (process.argv[2].toLowerCase() === "-h") {
			showHelp();
			return undefined;
		} else {
			args.name = process.argv[2];
		}
	}
	return args;
};

function showHelp() {
	console.log("\tUsage:");
	console.log("\t\tnode module-generator.js [-h] MODULE_NAME");
	console.log("\tArguments: ");
	console.log("\t\t-h : show this help (optional)");
	console.log("\t\tMODULES_NAME : create a module with MODULE_NAME (required)");
	console.log("");
};