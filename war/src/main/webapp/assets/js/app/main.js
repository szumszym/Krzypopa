(function (Includer, Utils) {
    Utils.initDashboard();

    Includer.includeFrom(['#menu', '#top-menu']);
    Includer.includeOnStart();

})(App.Components.Includer, App.Utils);