App.Components.Includer = (function ($) {
    var _default = {

    };
    var generateSelectorsString = function (menuIds) {
        var menuSelectors = '';
        for (var i = 0; i < menuIds.length; i++) {
            menuSelectors += menuIds[i] + ', ';
        }
        menuSelectors = menuSelectors.slice(0, -2);
        return menuSelectors;
    };
    var _contextElemId = "";
    var _currentPageUrl = "";

    return {
        includeFrom: function (menuIds) {
            var menuSelectors = generateSelectorsString(menuIds);
            $(menuSelectors).on('click', 'a[data-url]', function () {
                var url = $(this).data('url');
                var placement = $(this).data('placement');
                $(placement).load(url);
                _currentPageUrl = url;
                _contextElemId = placement;
            });
        },
        includeOnStart: function () {
            var $contextElem = $('div[data-default]');
            if ($contextElem.length) {
                var contextId = $contextElem.attr('id');
                var defaultContext = $contextElem.data('default');
                $("#" + contextId).load(defaultContext);
                _currentPageUrl = defaultContext;
                _contextElemId = '#' + contextId;
            }
        },
        refresh: function () {
            $(_contextElemId).load(_currentPageUrl);
        },
        load: function (url, placementId) {
            $('#' + placementId).load(url);
        }
    }

})(jQuery);