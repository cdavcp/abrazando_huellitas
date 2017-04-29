define(["Artistas/states", "Artistas/controllers/controllers", "Artistas/services/services"], function(states, controllers, services) {
    var module = angular.module("Artistas", ['ngFileUpload']);
    controllers.init(module);
    services.init(module);
    module.config(["$stateProvider", function($stateProvider) {
        states.init($stateProvider);
    }]);
    return {};
});