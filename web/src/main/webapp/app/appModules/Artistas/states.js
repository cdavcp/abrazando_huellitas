define([], function() {
    var constructor = {};
    var viewsPath = MODULES_DIR + "/Artistas/views";
    constructor.init = function($stateProvider) {
        /* abstract-base-state definition to match with auto-futureState convention */
        $stateProvider.state("artistas", {
            abstract: true,
            template: "<div ui-view></div>",
            url: "/artistas"
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
        $stateProvider.state("artistas.create", {
            templateUrl: viewsPath + "/create.html",
            params:{persona: null},
            controller: "ArtistaCreateCtrl"
        });

        $stateProvider.state("artistas.index", {
            templateUrl: viewsPath + "/index.html",
            params:{persona: null},
            url: '/index',
            controller: "ArtistaIndexCtrl"
        });

        $stateProvider.state("artistas.menu", {
            templateUrl: viewsPath + "/artistasmenu.html",
            params:{persona: null},
            controller: "ArtistaMenuCtrl"
        });

        $stateProvider.state("artistas.view", {
            url: '/view',
            templateUrl: viewsPath + "/view.html",
            params:{artista: null},
            controller: "ArtistaViewCtrl"
        })

        $stateProvider.state("artistas.edit", {
            url: '/edit',
            templateUrl: viewsPath + "/edit.html",
            params:{artista: null},
            controller: "ArtistaEditCtrl"
        })
    };
    return constructor;
});
