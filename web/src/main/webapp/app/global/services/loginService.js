angular.module("GlobalModule", ['ngFileUpload'])
    .service("LoginService", ["$http", "$cookieStore", "$q", "Upload", function ($http, $cookies, $q, Upload) {
        var service = {};
        var session = $cookies.get("sigep_session") || "";
        var url = REST_DIR + "/usuario";
        var urlPersona = REST_DIR + "/persona";
        var urlEvento = REST_DIR + "/evento";
        var urlNotifica = REST_DIR + "/notificacion";
        var urlArtista = REST_DIR + '/artista';


        service.checkLogin = function () {
            var defer = $q.defer();
            session = $cookies.get("sigep_session") || "";
            if (session) {
                $http({
                    method: "POST",
                    url: url + "/checkSession",
                    headers : { 'Content-Type' : 'application/x-www-form-urlencoded'},
                    data: "session="+session                      
                    }).then(function success(response) { return defer.resolve(response.data); },
                        function error() { return defer.reject("Sesión inválida.");
                    });
            } else {
                defer.reject("No hay sesión iniciada.");
            }
            return defer.promise;
        };

        service.getEventosProximos = function(){
            angular.element('#spinner').show();
            return $http.get(urlEvento + "/eventosProximos").success(function (data) {
                angular.element('#spinner').hide();
                return data;
            });
        };

        service.getTipoShow = function(){
            angular.element('#spinner').show();
            return $http.get(urlArtista + "/tiposShow").success(function (data) {
                angular.element('#spinner').hide();
                return data;
            });
        };

        service.getEventosByTodosFiltros = function(query){
            angular.element('#spinner').show();
            return $http.post(urlEvento  + "/todoFiltros",query).success(function (data) {
                angular.element('#spinner').hide();
                return data;
            });
        };

        service.getEventosMg = function(){
            angular.element('#spinner').show();
            return $http.get(urlEvento + "/eventosMg").success(function (data) {
                angular.element('#spinner').hide();
                return data;
            });
        };




        service.login = function (user) {
            return $http.post(url, user);
        };

        service.logout = function (user) {
            return $http.post(url + "/logout", user);
        };

        service.register = function(persona, file){
            angular.element('#spinner').show();
            return Upload.upload({
                url: urlPersona + "/register",
                fields: {persona:persona},
                file: file
            });
        };

        service.updateNotificaciones = function(notificacion){
            angular.element('#spinner').show();
            return $http.post(urlNotifica  + "/update",notificacion).success(function (data) {
                angular.element('#spinner').hide();
                return data;
            });
        };

        service.changePassword = function(newPass, previousPass, usuarioId){
            var param = {newPass: newPass, previousPass: previousPass, usuarioId: usuarioId};
            return $http.post(url + "/changePass", param);
        };

        service.inicializarCreate = function(){
            angular.element('#spinner').show();
            return $http.get(url + "/create").success(function (data) {
                angular.element('#spinner').hide();
                return data;
            });
        };

        service.checkNombreUsuario = function(nombreUsuario){
            return $http.post(url + "/checkNombreUsuairo", nombreUsuario).success(function (data) {
                return data;
            });
        };
        return service;
    }]);