define(["Notificaciones/states", "Notificaciones/controllers/controllers", "Notificaciones/services/services"], function(states, controllers, services) {
    var module = angular.module("Notificaciones", ['ngFileUpload']);
    controllers.init(module);
    services.init(module);
    module.config(["$stateProvider", function($stateProvider) {
        states.init($stateProvider);
    }]);
    return {};
});