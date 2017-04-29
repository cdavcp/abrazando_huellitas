define([], function() {
	function transformRequest(request) {
		if (!request) { return; }
		for(var prop in request) {
			if (angular.isObject( request[prop] )) {
				transformRequest( request[prop] );
			} else if (angular.isArray( request[prop] )) {
				request[prop].forEach(function(item) {
					if (angular.isArray( item )) {
						transformRequest({ array: item });
					} else if (angular.isObject( item )) {
						transformRequest(item);
					} else {
						console.warn("The content of transformRequest|isArray| wasnt an array or object");
					}
				});
			} else if (prop.toLowerCase().indexOf("fecha") != -1 && !angular.isDate(request[prop]) ) {
				request[prop] = transformDate(request[prop], true);
			}
		}
		return request;
	};

	function transformResponse(data) {
		if (!data) { return; }
		for(var prop in data) {
			if (angular.isObject(data[prop])) {
				transformResponse(data[prop]);
			} else if (angular.isArray(data[prop])) {
				data[prop].forEach(function(item) {
					if (angular.isArray(item)) {
						transformResponse({ array: item });
					} else if (angular.isObject(item)) {
						transformResponse(item);
					} else {
						console.warn("The content of transformResponse|isArray| wasnt an array or object");
					}
				});
			} else if (prop.toLowerCase().indexOf("fecha") != -1) {
				data[prop] = transformDate(data[prop]);
			}
		}
	};
	function transformDate(obj) {
		console.info("Interceptor: Transforming a string date prop");
		return obj ? new Date(obj) : null;
	};

	function requestInterceptor(config) { if (config) { transformRequest(config.data || config.params); } return config; };
	function responseInterceptor(response) {
		if (!response) { return; }
		var matchRegex = response.config.url.match(/^(.*?(\brest\b)[^$]*)$/);
		var htmlRegex = response.config.url.match(/^(.*?(\b.html\b)[^$]*)$/);
		if (response && response.data && !htmlRegex && matchRegex) { transformResponse(response.data); }
        return response;
	};
	function requestErrorInterceptor(rejection) { console.info("Request error: ", rejection); };
	function responseErrorInterceptor(rejection) { console.info("Response error: ", rejection); };

	function init(module) {
		var constructor = {	request: requestInterceptor, response: responseInterceptor, requestError: requestErrorInterceptor, responseError: responseErrorInterceptor
		};
		module.factory("dateInterceptor", function() { return constructor; });
	};
	var interceptorFn = { init: init };
	return interceptorFn;
});