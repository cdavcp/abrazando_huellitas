define(["Solicitud/states", "Solicitud/controllers/controllers", "Solicitud/services/services"], function(states, controllers, services) {
    var module = angular.module("Solicitud", ['ngFileUpload']);
    controllers.init(module);
    services.init(module);
    module.config(["$stateProvider", function($stateProvider) {
        states.init($stateProvider);
    }]);
    return {};
});