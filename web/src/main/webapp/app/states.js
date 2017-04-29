define([], function () {
    var constructor = {};
    constructor.init = function ($stateProvider) {
        $stateProvider
            .state('index', {
                url: '/',
                controller: 'MainCtrl',
                templateUrl: GLOBAL_DIR + "/views/main.html"
            })
            .state('login', {
                url: '/login',
                controller: 'LoginCtrl',
                templateUrl: GLOBAL_DIR + "/views/login.html"
            })
            .state('register', {
                url: '/register',
                controller: 'LoginCtrl',
                templateUrl: GLOBAL_DIR + "/views/register.html"
            })
            .state('changePass', {
                url: '/changePass',
                controller:'LoginCtrl',
                templateUrl:GLOBAL_DIR + "/views/changePassword.html"
            })
            .state('dashboard', {
                url: '/dashboard',
                controller: 'MainCtrl',
                templateUrl: GLOBAL_DIR + "/views/main.html"
            })
    };
    return constructor;
});