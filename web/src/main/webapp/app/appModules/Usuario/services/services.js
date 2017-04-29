define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("UsuarioTemplateService", ["$http", "$resource", "Upload", function($http, $resource, Upload) {
            var service = {};
            var url = REST_DIR + '/usuario';
            var urlPersona = REST_DIR + '/persona';

            //service.inicializarIndex = function(){
            //    angular.element('#spinner').show();
            //    return $http.get(url).success(function (data) {
            //        angular.element('#spinner').hide();
            //        return data;
            //    });
            //};
            //
            //
            //service.inicializarEdit = function(productor, idUsuario){
            //    var param = {idProductorStr: productor, idUsuarioStr : idUsuario};
            //    return $http.post(url + "/edit", param).success(function (data) {
            //        angular.element('#spinner').hide();
            //        return data;
            //    })
            //};
            //
            //service.inicializarView = function(idProductor){
            //    return $http.post(url + "/view", idProductor).success(function (data) {
            //        angular.element('#spinner').hide();
            //        return data;
            //    })
            //};

            service.getPersona = function(idUsuario){
                return $http.post(urlPersona + "/datosPersona", idUsuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            //service.create = function(productor){
            //    angular.element('#spinner').show();
            //    return $http.post(url, productor);
            //};
            //
            //service.update = function(productor, file){
            //    angular.element('#spinner').show();
            //    return Upload.upload({
            //        url: url + "/update",
            //        fields: {productor:productor},
            //        file: file
            //    });
            //};
            //
            //service.delete = function(productor){
            //    angular.element('#spinner').show();
            //    return $http.post(url + "/delete", productor);
            //};

            return service;
		}]);
	};
	return constructor;
});