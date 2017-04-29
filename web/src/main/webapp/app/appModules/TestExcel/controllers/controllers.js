define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.controller("ExcelCtrl", ["$scope", "ExcelService", function($scope, excelService) {
			$scope.obj = {};
			$scope.obj.messages = [];
			$scope.$on("fileSelected", function(e, files) {
				$scope.obj.messages.push("Reading " + files[0].name);
				excelService.read(files[0]).then(
					function success(workbook) {
						$scope.obj.messages.push("Excel parsed");
						var sheetNames = workbook.SheetNames;
						console.log("Sheets names: ", sheetNames);
						var testSheet = sheetNames[0];
						var jsonSheet = XLSX.utils.sheet_to_json(workbook.Sheets[testSheet]);
						$scope.obj.messages.push(jsonSheet);
						console.log("JSON sheet: ", jsonSheet);
					}, function error() {
						$scope.obj.messages.push("Error reading excel file");
					});
			});
		}]);
	};
	return constructor;
});