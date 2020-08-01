function getAllPlans() {
    $.ajax({
        url: "getAllPlans",
        type: "get",
        dataType: 'json',
        success: function (data) {
            var content = "";
            for (var i = 0; i < data.length; i++) {
                var po = data[i];
                var planYear = po.planYear;
                var planMonth = po.planMonth;
                var planRealMonth = planMonth + 1;
                var planDay = po.planDay;
                var planHour = po.planHour;
                var planMinute = po.planMinute;
                var planSecond = po.planSecond;
                var time=new Date(planYear,planMonth,planDay,planHour,planMinute,planSecond).getTime();
                //获取当前时间
                var nowTime = Date.now();
                var timeDiff = (time - nowTime) / 1000; //difference btw target date and current date, in seconds
                var oneMin = 60; //1 minute in seconds
                var oneHour = 60 * 60; //1 hour in seconds
                var oneDay = 60 * 60 * 24; //1 day in seconds
                var totalDay = Math.floor(timeDiff / oneDay);
                var totalHour = Math.floor((timeDiff - totalDay * oneDay) / oneHour);
                var totalMin = Math.floor((timeDiff - totalDay * oneDay - totalHour * oneHour) / oneMin);
                var totalSec = Math.floor((timeDiff - totalDay * oneDay - totalHour * oneHour - totalMin * oneMin));
                content += "<tr style='border-bottom: #d43f3a 1px solid'>"
                        + "<td class='time-down-td'>" + po.planContent +"</td>"
                        + "<td class='time-down-td'>" + planYear + " <span>年</span> " + planRealMonth + " <span>月</span> " + planDay + " <span>日</span></td>"
                        + "<td class='time-down-td'>" + totalDay + " <span>天</span> " + totalHour + " <span>时</span> " + totalMin + " <span>分</span> " + totalSec + " <span>秒</span></td>"
                        + "<td class='time-down-td-planDetail'>" + po.planDetail + " </td>"
                        + "<td class='time-down-td-planValue'>" + po.planValue + " </td>"
                        + "<td class='time-down-td-last'></td>"
                        + "</tr>";
            }
            $("#planCountDown").html(content);
            setTimeout("getAllPlans()", 1000);
    }
});

}
getAllPlans();