App.Utils = (function ($) {
    var _default = {

    };
    var initMetisPlugin = function () {
        var pluginName = "metisMenu",
            defaults = {
                toggle: true
            };

        function Plugin(element, options) {
            this.element = element;
            this.settings = $.extend({}, defaults, options);
            this._defaults = defaults;
            this._name = pluginName;
            this.init();
        }

        Plugin.prototype = {
            init: function () {

                var $this = $(this.element),
                    $toggle = this.settings.toggle;

                $this.find('li.active').has('ul').children('ul').addClass('collapse in');
                $this.find('li').not('.active').has('ul').children('ul').addClass('collapse');

                $this.find('li').has('ul').children('a').on('click', function (e) {
                    e.preventDefault();

                    $(this).parent('li').toggleClass('active').children('ul').collapse('toggle');

                    if ($toggle) {
                        $(this).parent('li').siblings().removeClass('active').children('ul.in').collapse('hide');
                    }
                });
            }
        };

        $.fn[ pluginName ] = function (options) {
            return this.each(function () {
                if (!$.data(this, "plugin_" + pluginName)) {
                    $.data(this, "plugin_" + pluginName, new Plugin(this, options));
                }
            });
        };
    };
    var initDashboard = function () {
        "use strict";

        var currentdate = new Date();
        var datetime = "" + currentdate.getDate() + "/"
            + (currentdate.getMonth() + 1) + "/"
            + currentdate.getFullYear() + " @ "
            + currentdate.getHours() + ":"
            + currentdate.getMinutes() + ":"
            + currentdate.getSeconds();
        $('.current-date').text(datetime);


        $('a[href=#]').on('click', function (e) {
            e.preventDefault();
        });


        $('a[data-toggle=tooltip]').tooltip();
        $('a[data-tooltip=tooltip]').tooltip();


        $('.minimize-box').on('click', function (e) {
            e.preventDefault();
            var $icon = $(this).children('i');
            if ($icon.hasClass('icon-chevron-down')) {
                $icon.removeClass('icon-chevron-down').addClass('icon-chevron-up');
            } else if ($icon.hasClass('icon-chevron-up')) {
                $icon.removeClass('icon-chevron-up').addClass('icon-chevron-down');
            }
        });
        $('.close-box').click(function () {
            $(this).closest('.box').hide('slow');
        });

        $('#changeSidebarPos').on('click', function (e) {
            $('body').toggleClass('hide-sidebar');
        });

        $('li.accordion-group > a').on('click', function (e) {
            $(this).children('span').children('i').toggleClass('icon-angle-down');
        });

        $('#menu').metisMenu();
    };


    return {
        initDashboard: function () {
            initMetisPlugin();
            initDashboard();
        },
        initLoginForm: function () {
            $('.list-inline li > a').click(function () {
                var activeForm = $(this).attr('href') + ' > form';
                //console.log(activeForm);
                $(activeForm).addClass('magictime swap');
                //set timer to 1 seconds, after that, unload the magic animation
                setTimeout(function () {
                    $(activeForm).removeClass('magictime swap');
                }, 1000);
            });
        },
        activeCheckbox: function (id) {
            if ($(id).val() == "true") {
                $(id).prop('checked', true);
            } else {
                $(id).prop('checked', false);
            }
            $(id).change(function () {
                var $this = $(this);
                $this.val($this.prop('checked'));
            });
        },
        refresh: function (waitTime, fnArray) {
            //call on start
            for (var i = 0; i < fnArray.length; i++) {
                fnArray[i]["fn"].apply(this, fnArray[i]["arg"]);
                fnArray[i]["fn"].apply(this, fnArray[i]["arg"]);
            }

            var timeout = null;
            $(document).on('mousemove', function () {
                if (timeout !== null) {
                    clearTimeout(timeout);
                }
                timeout = setTimeout(function () {
                    for (var i = 0; i < fnArray.length; i++) {
                        fnArray[i]["fn"].apply(this, fnArray[i]["arg"]);
                    }
                }, waitTime);
            });
        },
        setTodayTo: function (selector, plusDays) {
            var plus = plusDays || 0;
            var today = new Date();
            today.setDate(today.getDate() + plus);
            var day = today.getDate();
            if (day < 10) day = '0' + day;
            var month = today.getMonth() + 1;
            if (month < 10) month = '0' + month;
            var year = today.getFullYear();

            $(selector).val(year + '-' + month + '-' + day);
        }
    }

})(jQuery);