define(["Lugar/states", "Lugar/controllers/controllers", "Lugar/services/services"], function(states, controllers, services) {
    var module = angular.module("Lugar", ['ngFileUpload']);
    controllers.init(module);
    services.init(module);
    module.config(["$stateProvider", function($stateProvider) {
        states.init($stateProvider);
    }]);
    return {};
});