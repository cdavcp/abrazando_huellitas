define([], function() {
    var constructor = {};
    var viewsPath = MODULES_DIR + "/Reserva/views";
    constructor.init = function($stateProvider) {
        /* abstract-base-state definition to match with auto-futureState convention */
        $stateProvider.state("reserva", {
            abstract: true,
            template: "<div ui-view></div>",
            url: "/reserva"
        });
        /* Module states */
        //$stateProvider.state("usuario.index", {
        //templateUrl: viewsPath + "/index.html",
        //url: "/index",
        //controller: "ProductorIndexCtrl"
        //});
        //$stateProvider.state("usuario.create", {
        //    templateUrl: viewsPath + "/create.html",
        //    params:{usuario: null},
        //    controller: "ProductorCreateCtrl"
        //});
        $stateProvider.state("reserva.create", {
            templateUrl: viewsPath + "/create.html",
            params:{evento: null},
            controller: "ReservaCreateCtrl"
        });

        $stateProvider.state("reserva.reservasLugar", {
            templateUrl: viewsPath + "/reservasLugar.html",
            params:{evento: null},
            controller: "ReservaLugarCtrl"
        });

        $stateProvider.state("reserva.index", {
            templateUrl: viewsPath + "/index.html",
            params:{reserva: null},
            controller: "ReservaIndexCtrl"
        });

        //$stateProvider.state("solicitud.index", {
        //    templateUrl: viewsPath + "/index.html",
        //    params:{persona: null},
        //    controller: "SolicitudIndexCtrl"
        //});

        //$stateProvider.state("solicitud.view", {
        //    templateUrl: viewsPath + "/view.html",
        //    params:{evento: null},
        //    controller: "SolicitudViewCtrl"
        //});
        //$stateProvider.state("lugar.menu", {
        //    templateUrl: viewsPath + "/localesmenu.html",
        //    params:{persona: null},
        //    controller: "LugarMenuCtrl"
        //});

        //
        //
        //
        //
        //$stateProvider.state("lugar.index", {
        //    templateUrl: viewsPath + "/index.html",
        //    params:{persona: null},
        //    controller: "LugarIndexCtrl"
        //});
        //
        //$stateProvider.state("lugar.view", {
        //    templateUrl: viewsPath + "/view.html",
        //    params:{persona: null},
        //    controller: "LugarViewCtrl"
        //})
    };
    return constructor;
});

