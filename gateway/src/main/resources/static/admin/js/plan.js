
function addPlan() {
    var palnYear = document.getElementById("year").value;
    var palnMonth = document.getElementById("month").value - 1;
    var palnDay = document.getElementById("day").value;
    var palnHour = document.getElementById("hour").value;
    var palnMinute = document.getElementById("minute").value;
    var palnSecond = document.getElementById("second").value;
    var planContent = document.getElementById("planContent").value;
    var planDetail = $("#planDetail").val();
    planDetail=planDetail.replace(/\n/g,'<br />');
    // $("#planDetail").val(planDetail);

    var planValue = $("#planValue").val();
    planValue=planValue.replace(/\n/g,'<br />');
    // var planDetail = document.getElementById("planDetail").value;
    // var planValue = document.getElementById("planValue").value;
    alert(palnYear);
    if (typeof (palnYear) == undefined || palnYear == null || palnYear == ""
        || typeof (palnMonth) == undefined || palnMonth == null || palnMonth == ""
        || typeof (palnDay) == undefined || palnDay == null || palnDay == ""
        || typeof (palnHour) == undefined || palnHour == null || palnHour == ""
        || typeof (palnMinute) == undefined || palnMinute == null || palnMinute == ""
        || typeof (palnSecond) == undefined || palnSecond == null || palnSecond == "") {
        alert("请输入计划目标日期");
        return;
    }
    if (typeof (planContent) == undefined || planContent == null || planContent == "") {
        alert("请输入计划目标");
        return;
    }

    if (typeof (planDetail) == undefined || planDetail == null || planDetail == "") {
        alert("请输入计划目标详细内容");
        return;
    }

    if (typeof (planValue) == undefined || planValue == null || planValue == "") {
        alert("请输入计划目标意义价值");
        return;
    }

    $.ajax({
        url: "/admin/addPlan",
        type: "post",
        data: {
            "planContent":planContent,
            "planDetail":planDetail,
            "planValue":planValue,
            "planYear": palnYear,
            "planMonth": palnMonth,
            "planDay": palnDay,
            "planHour": palnHour,
            "planMinute": palnMinute,
            "planSecond": palnSecond
        },
        dataType: "text",
        success: function (data) {
            if (data == "增加计划成功，一定要完成") {
                alert(data);
                window.location.reload();
            } else {
                alert(data);
            }
        },
        error: function () {
            alert("系统问题，请联系帅气的管理员");
        }
    });

}

function showLastPlan() {
    $.ajax({
        url:"/admin/showLastPlan",
        type:"get",
        dataType:"json",
        success: function (data) {
            if (data.msg == "请先登录") {
                alert(data.msg);
                window.location.href = "/loginPage";
            } else {
                var content = "";
                for (var i = 0; i < data.length; i++) {
                    var po = data[i];
                    var planStatus;
                    var planMonth = po.planMonth + 1;
                    if (po.planStatus == 0) {
                        planStatus = "未进行";
                    } else if(po.planStatus == 1) {
                        planStatus = "进行中";
                    } else if(po.planStatus == 2) {
                        planStatus = "已完成";
                    } else if(po.planStatus == 3) {
                        planStatus = "未完成";
                    } else {
                        planStatus = "未知错误";
                    }
                    content += "<tr>"
                            + "<td style='width: 500px;line-height: 40px'><a href='/justDoIt?planId=" +po.planId + "'>" + po.planContent + "</a></td>"
                            +"<td style='line-height: 40px'>" + po.planYear + "年" + planMonth + "月" + po.planDay + "日</td>"
                            +"<td style='line-height: 40px'>" + planStatus + "</td>"
                            +"<td style='line-height: 17px'><button class='btn btn-danger' type='submit' onclick='showPlan("
                            + po.planId
                            + ")'>完成，点击!</button>&nbsp;"
                            + "<button class='btn btn-default' type='submit' onclick='showPlan("
                            + po.planId
                            + ")'>删除任务</button></td>"
                            +"</tr>";
                }
                $("#showLastPlan").html(content);
            }
        }
    });
}
showLastPlan();


function showLastComment() {
    $.ajax({
        url:"/admin/showLastComment",
        type:"get",
        dataType:"json",
        success: function (data) {
            if (data.msg == "请先登录") {
                alert(data.msg);
                window.location.href = "/loginPage";
            } else {
                var arr = data.commentShows;
                var content = "";
                for (var i = 0; i < arr.length; i++) {
                    var po = arr[i];
                    content += " <tr>"
                        + "<td><div  style='text-align: left'>"
                        + po.observer
                        + "&nbsp;&nbsp;&nbsp;"
                        + po.commentdate
                        + "&nbsp;&nbsp;&nbsp;评论了&nbsp;&nbsp;&nbsp;"
                        + po.commenter+" ：</div><div style='padding-right: 800px'>"
                        + "“"
                        + po.commentcontent
                        + "”</div></td>"
                        + "</tr>";
                }
                $("#lastCommentList").html(content);
            }
        }
    });
}
showLastComment();