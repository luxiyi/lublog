
function addPlan() {
    var palnYear = document.getElementById("year").value;
    var palnMonth = document.getElementById("month").value - 1;
    var palnDay = document.getElementById("day").value;
    var palnHour = document.getElementById("hour").value;
    var palnMinute = document.getElementById("minute").value;
    var palnSecond = document.getElementById("second").value;
    var planThreeMonthContent = document.getElementById("planThreeMonthContent").value;
    var tagName = $("#tagName").val();
    var planDetail = $("#planDetail").val();
    planDetail=planDetail.replace(/\n/g,'<br />');
alert(tagName)
    var planValue = $("#planValue").val();
    planValue=planValue.replace(/\n/g,'<br />');
    if (typeof (palnYear) == undefined || palnYear == null || palnYear == ""
        || typeof (palnMonth) == undefined || palnMonth == null || palnMonth == ""
        || typeof (palnDay) == undefined || palnDay == null || palnDay == ""
        || typeof (palnHour) == undefined || palnHour == null || palnHour == ""
        || typeof (palnMinute) == undefined || palnMinute == null || palnMinute == ""
        || typeof (palnSecond) == undefined || palnSecond == null || palnSecond == "") {
        alert("请输入计划目标日期");
        return;
    }
    if (typeof (planThreeMonthContent) == undefined || planThreeMonthContent == null || planThreeMonthContent == "") {
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
            "planContent":planThreeMonthContent,
            "planDetail":planDetail,
            "planValue":planValue,
            "planYear": palnYear,
            "planMonth": palnMonth,
            "planDay": palnDay,
            "planHour": palnHour,
            "planMinute": palnMinute,
            "planSecond": palnSecond,
            "tagName": tagName
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
        url:"/admin/showTagPlans",
        type:"get",
        data:{
            tagName:"近期计划"
        },
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
                            + "<td style='width: 500px;line-height: 40px'><a href='/justDoIt?planId=" +po.planId + "' style='text-decoration: none;color: #d43f3a'>" + po.planContent + "</a></td>"
                            +"<td style='line-height: 40px'>" + po.planYear + "年" + planMonth + "月" + po.planDay + "日</td>"
                            +"<td style='line-height: 40px'>" + planStatus + "</td>"
                            +"<td style='line-height: 17px'><button class='btn btn-success' type='submit' onclick='showPlan("
                            + po.planId
                            + ")'>完成，点击!</button>&nbsp;"
                            + "<button class='btn btn-warning'> <a href='/creatDoPlan?planId="+ po.planId +"'style='text-decoration: none;color: white'>每天执行</a></button>&nbsp;"
                            + "<button class='btn btn-danger' type='submit' onclick='deletePlan("
                            + po.planId
                            + ")'>删除任务</button>"
                            + "</td>"
                            +"</tr>";
                }
                $("#showLastPlan").html(content);
            }
        }
    });
}
showLastPlan();



function showThisYearPlan() {
    $.ajax({
        url:"/admin/showTagPlans",
        type:"get",
        data:{
            tagName:"今年计划"
        },
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
                        + "<td style='width: 500px;line-height: 40px'><a href='/justDoIt?planId=" +po.planId + "' style='text-decoration: none;color: #d43f3a'>" + po.planContent + "</a></td>"
                        +"<td style='line-height: 40px'>" + po.planYear + "年" + planMonth + "月" + po.planDay + "日</td>"
                        +"<td style='line-height: 40px'>" + planStatus + "</td>"
                        +"<td style='line-height: 17px'><button class='btn btn-success' type='submit' onclick='showPlan("
                        + po.planId
                        + ")'>完成，点击!</button>&nbsp;"
                        + "<button class='btn btn-warning'> <a href='/creatDoPlan?planId="+ po.planId +"'style='text-decoration: none;color: white'>每天执行</a></button>&nbsp;"
                        + "<button class='btn btn-danger' type='submit' onclick='deletePlan("
                        + po.planId
                        + ")'>删除任务</button>"
                        + "</td>"
                        +"</tr>";
                }
                $("#showThisYearPlan").html(content);
            }
        }
    });
}
showThisYearPlan();


function deletePlan(planId) {
    var status = confirm("是否删除该计划？")
    if (!status){
        return false;
    }
    $.ajax({
       url:"/admin/deletePlan",
       type:"PUT",
        data:{
           planId:planId
        },
        dataType:"text",
        success:function (data) {
            if (data == "删除成功！"){
                alert(data);
                window.location.reload();
            } else {
                alert(data);
            }
        },
        error:function () {
            alert("系统错误，请找帅气的管理员！")
        }
    });
}

function addPlanQuestion() {
    $.ajax({
        url:"/admin/addPlanQuestion",
        type:"post",
        data:{
            "planFirstQuestion":$("#plan-first-question").val(),
            "planSecondQuestion":$("#plan-second-question").val(),
            "planThirdQuestion":$("#plan-third-question").val(),
            "planFirstAnswer":$("#plan-first-answer").val(),
            "planSecondAnswer":$("#plan-second-answer").val()
        },
        dataType:"text",
        success:function (data) {
            if (data == "设置问题答案成功!") {
                alert(data);
                window.location.reload();
            } else if (data == "已设置问题，不用再添加了!"){
                alert(data);
            } else {
                alert(data);
            }
        }
    });
}

function queryPlanQuestion() {
    $.ajax({
        url:"/queryPlanQuestion",
        type:"get",
        dataType:"json",
        success:function (data) {
                $("#plan-first-question").val(data.planFirstQuestion);
                $("#plan-second-question").val(data.planSecondQuestion);
                $("#plan-third-question").val(data.planThirdQuestion);
                $("#plan-first-answer").val(data.planFirstAnswer);
                $("#plan-second-answer").val(data.planSecondAnswer);
        }
    });
}
queryPlanQuestion();