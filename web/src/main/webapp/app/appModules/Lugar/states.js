define([], function() {
    var constructor = {};
    var viewsPath = MODULES_DIR + "/Lugar/views";
    constructor.init = function($stateProvider) {
        /* abstract-base-state definition to match with auto-futureState convention */
        $stateProvider.state("lugar", {
            abstract: true,
            template: "<div ui-view></div>",
            url: "/lugar"
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
        $stateProvider.state("lugar.create", {
            templateUrl: viewsPath + "/create.html",
            params:{persona: null},
            controller: "LugarCreateCtrl"
        });
        $stateProvider.state("lugar.menu", {
            templateUrl: viewsPath + "/localesmenu.html",
            params:{persona: null},
            controller: "LugarMenuCtrl"
        });





        $stateProvider.state("lugar.index", {
            templateUrl: viewsPath + "/index.html",
            params:{persona: null},
            controller: "LugarIndexCtrl"
        });

        $stateProvider.state("lugar.view", {
            templateUrl: viewsPath + "/view.html",
            params:{lugar: null},
            controller: "LugarViewCtrl"
        })
    };
    return constructor;
});
