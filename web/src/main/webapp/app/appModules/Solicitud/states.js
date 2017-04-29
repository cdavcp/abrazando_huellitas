define([], function() {
    var constructor = {};
    var viewsPath = MODULES_DIR + "/Solicitud/views";
    constructor.init = function($stateProvider) {
        /* abstract-base-state definition to match with auto-futureState convention */
        $stateProvider.state("solicitud", {
            abstract: true,
            template: "<div ui-view></div>",
            url: "/solicitud"
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
        $stateProvider.state("solicitud.create", {
            templateUrl: viewsPath + "/create.html",
            params:{lugar: null, artista: null},
            controller: "SolicitudCreateCtrl"
        });

        $stateProvider.state("solicitud.index", {
            templateUrl: viewsPath + "/index.html",
            params:{persona: null},
            controller: "SolicitudIndexCtrl"
        });

        $stateProvider.state("solicitud.view", {
            templateUrl: viewsPath + "/view.html",
            params:{evento: null},
            controller: "SolicitudViewCtrl"
        });

        $stateProvider.state("solicitud.viewS", {
            templateUrl: viewsPath + "/viewSolicitud.html",
            params:{evento: null},
            controller: "SolicitudViewSCtrl"
        });

        $stateProvider.state("solicitud.edit", {
            templateUrl: viewsPath + "/edit.html",
            params:{evento: null},
            controller: "SolicitudEditCtrl"
        });
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
