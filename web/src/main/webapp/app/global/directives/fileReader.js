define([], function() {
    var constructor = {};
    constructor.init = function(module) {
        module.directive("filereader", [function () {
            var directiveConf = {};
            directiveConf.restrict = "A";
            directiveConf.link = function (scope, element, attrs) {
                element.on("change", function (e) {
                    scope.$root.$broadcast("fileSelected", e.target.files);
                });
            }
            return directiveConf;
        }]);
    };
    return constructor;
});