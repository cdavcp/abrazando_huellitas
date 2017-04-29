angular.module("GlobalModule")
.service("ExcelService", ["$q", function($q) {
	var service = {};

	service.read = function(resource) {
		var defer = $q.defer();
		if (resource instanceof String) {
			var ajax = new XMLHttpRequest();
			ajax.open("GET", resource, true);
			ajax.responseType = "arraybuffer";
			ajax.onerror = function(e) {
				return $q.reject("Error parsing the excel file");
			};
			ajax.onload = function(e) {
				var arraybuffer = ajax.response;
				var data = new Uint8Array(arraybuffer);
				var arr = new Array();
				for(var i = 0; i != data.length; ++i) {
					arr[i] = String.fromCharCode(data[i]);
				}
				var bstr = arr.join("");  
				var workbook = XLSX.read(bstr, {type:"binary"});
				return $q.resolve(workbook);
			};
		} else if (resource instanceof File) {
			//var file = resource[0];
			var file = resource;
			var reader = new FileReader();
			var name = file.name;
			reader.onerror = function() {
				return defer.reject("Error reading the excel file.");
			};
			reader.onload = function(e) {
				var data = e.target.result;
				var workbook = XLSX.read(data, {type: 'binary'});
				return defer.resolve(workbook);
			};
			reader.readAsBinaryString(file);
		}
		return defer.promise;
	};

	return service;
}]);