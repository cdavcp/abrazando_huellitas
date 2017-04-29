define([], function() {
    var constructor = {};
    constructor.init = function(module) {
        module.directive("menuItem", [function () {
            var directiveConf = {};
            directiveConf.restrict = "E";
            directiveConf.replace = true;
            directiveConf.templateUrl = GLOBAL_DIR + "/views/menuItem-template.html";
            directiveConf.scope = {
                descriptor: "="
            };
            directiveConf.link = function(scope) {
                if (scope.descriptor.subOptions) {
                    scope.descriptor.hasMenu = true;
                }
            };
            return directiveConf;
        }]);
    };
    return constructor;
});