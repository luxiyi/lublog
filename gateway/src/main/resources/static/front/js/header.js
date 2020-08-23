function categoryDropdown() {
    $.ajax({
        url:"/listBlogCategory",
        type:"get",
        dataType:"json",
        success:function (data) {
            var content = "";
            for (var i = 0; i < data.length; i++) {
                var po = data[i];
                content += "<a href='blogCategories?categoryId="+po.categoryId+"'>" + po.categoryName + "</a>";
            }
            $("#categoryDropdown").html(content);
        }
    });
}
categoryDropdown();


function tagDropdown() {
    $.ajax({
        url:"/listBlogTag",
        type:"get",
        dataType:"json",
        success:function (data) {
            var content = "";
            for (var i = 0; i < data.length; i++) {
                var po = data[i];
                content += "<a href='blogTags?tagId="+po.tagId+"'>" + po.tagName + "</a>";
            }
            $("#tagDropdown").html(content);
        }
    });
}
tagDropdown();

function queryPlanQuestion() {
    $.ajax({
        url:"/queryPlanQuestion",
        type:"get",
        dataType:"json",
        success:function (data) {
            $("#plan-first-question").val(data.planFirstQuestion);
            $("#plan-second-question").val(data.planSecondQuestion);
            $("#plan-third-question").val(data.planThirdQuestion);
        }
    });
}
queryPlanQuestion();


//添加plan访客
function addOrUpdatePlanVisitor() {
    $.ajax({
        url:"/addOrUpdatePlanVisitor",
        type:"post",
        data:{
            "planVisitorCount": 1,
            "planFirstAnswer":$("#plan-first-answer").val(),
            "planSecondAnswer":$("#plan-second-answer").val(),
            "planThirdAnswer":$("#plan-third-answer").val()
        },
        dataType:"text",
        success:function (data) {
            if (data == "操作成功"){
                window.location.href = "/plan";
            } else {
                alert(data)
            }
        }
    });
}