define(["Reserva/states", "Reserva/controllers/controllers", "Reserva/services/services"], function(states, controllers, services) {
    var module = angular.module("Reserva", ['ngFileUpload']);
    controllers.init(module);
    services.init(module);
    module.config(["$stateProvider", function($stateProvider) {
        states.init($stateProvider);
    }]);
    return {};
});
