$(document).ready(function () {
    "use strict";
    // toat popup js
    $.toast({
        heading: '今日推送',
        text: '欢迎来到这个主题不知道是什么的网站,自己随便看看吧',
        position: 'top-right',
        loaderBg: '#fff',
        icon: 'warning',
        hideAfter: 3500,
        stack: 6
    })


    //ct-visits
    new Chartist.Line('#ct-visits', {
        labels: ['2013', '2014', '2015', '2016', '2017', '2018', '2019', '2020'],
        series: [
            [2, 4, 3, 7, 2, 5, 2, 4],
            [5, 2, 9, 4, 6, 7, 6, 5]
        ]
    }, {
        top: 0,
        low: 1,
        showPoint: true,
        fullWidth: true,
        plugins: [
            Chartist.plugins.tooltip()
        ],
        axisY: {
            labelInterpolationFnc: function (value) {
                return (value / 1) + 'k';
            }
        },
        showArea: true
    });
    // counter
    $(".counter").counterUp({
        delay: 100,
        time: 1200
    });

    var sparklineLogin = function () {
        $('#sparklinedash').sparkline([0, 5, 6, 10, 9, 12, 4, 9], {
            type: 'bar',
            height: '30',
            barWidth: '4',
            resize: true,
            barSpacing: '5',
            barColor: '#7ace4c'
        });
        $('#sparklinedash2').sparkline([0, 5, 6, 10, 9, 12, 4, 9], {
            type: 'bar',
            height: '30',
            barWidth: '4',
            resize: true,
            barSpacing: '5',
            barColor: '#7460ee'
        });
        $('#sparklinedash3').sparkline([0, 5, 6, 10, 9, 12, 4, 9], {
            type: 'bar',
            height: '30',
            barWidth: '4',
            resize: true,
            barSpacing: '5',
            barColor: '#11a0f8'
        });
        $('#sparklinedash4').sparkline([0, 5, 6, 10, 9, 12, 4, 9], {
            type: 'bar',
            height: '30',
            barWidth: '4',
            resize: true,
            barSpacing: '5',
            barColor: '#f33155'
        });
    }
    var sparkResize;
    $(window).on("resize", function (e) {
        clearTimeout(sparkResize);
        sparkResize = setTimeout(sparklineLogin, 500);
    });
    sparklineLogin();
});
