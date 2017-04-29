define([], function() {
	var constructor = {};
	constructor.init = function($futureStateProvider) {
		$futureStateProvider.futureState({
			stateName: "testState",
			type: "lazyLoad",
			module: "TestModule",
			url: "/test"
		});
	};
	return constructor;
});