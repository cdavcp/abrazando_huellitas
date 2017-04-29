define([], function() {
	var constructor = {};
	var modules = [];

	constructor.ready = function($lazyLoad) {
		setModulesConfig($lazyLoad);
	};

	constructor.registerModule = function(name, options) {
		registerModule(name, options);
	};

	/* registerModule(name, [options])
		* name: nombre del modulo
		* options (optional): objeto con la configuracion del modulo. Posibles propiedades:
			- customStates: en caso de no seguir la convencion de los futureStates indicar "true" (default: "false")
			- files: en caso de no seguir la convencion, indicar donde estan los archivos (debe ser un array)
			- dependencies: si tiene dependencias externas indicar como array
			- reconfig: si deberia lanzarse el reconfig del modulo o no
			- rerun: si deberia lanzarse el rerun del modulo o no
	*/
	function registerModule(name, options) {
		options = options || {};
		options.name = options.name || name;
		options.files = options.files || [MODULES_DIR + "/" + name + "/config.js"];
		modules.push(options);
	};
	function getStateName(name) {
		var parsedName = "";
		if (name.indexOf("Module") != -1) {
			var holder = name.split("Module")[0];
			parsedName =  holder[0].toLowerCase() + holder.slice(1);
		} else {
			parsedName = name[0].toLowerCase() + name.slice(1);
		}
		return parsedName;
	};
	function setModulesConfig($lazyLoad) {
		modules.forEach(function(module) {
			if (!module.customStates) {
				futureStateProvider.futureState({
					stateName: getStateName(module.name),
					url: "/" + getStateName(module.name),
					type: "lazyLoad",
					module: module.name
				});
			} else {
				//TODO: poner setModuleConfig en el success del getScript
				var customStatesDir = MODULES_DIR + "/" + module.name + "/states/customStates.js";
				$.getScript(customStatesDir)
					.success(function() { console.info("customStates for module: " + module.name + " loaded") })
					.error(function () { console.warn("Error loading customStates for module: " + module.name) })
			}
			$lazyLoad.setModuleConfig(module);
		});
	};
	return constructor;
});