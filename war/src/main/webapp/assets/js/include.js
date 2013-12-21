$(function () {
    $('#menu, #top-menu').on('click', 'a[data-url]', function () {
        var url = $(this).data('url');
        var placement = $(this).data('placement');
        $(placement).load(url);
    });

    var $contextElem = $('div[data-default]');
    if ($contextElem.length) {
        var contextId = $contextElem.attr('id');
        var defaultContext = $contextElem.data('default');
        $("#" + contextId).load(defaultContext);
    }
});

