function categoryDropdown() {
    $.ajax({
        url:"/admin/listArticleCategory",
        type:"get",
        dataType:"json",
        success:function (data) {
            var content = "";
            for (var i = 0; i < data.length; i++) {
                var po = data[i];
                content += "<a href='blogCategories?categoryId="+po.categoryid+"'>" + po.categoryname + "</a>";
            }
            $("#categoryDropdown").html(content);
        }
    });
}
categoryDropdown();


function tagDropdown() {
    $.ajax({
        url:"/admin/listArticleTag",
        type:"get",
        dataType:"json",
        success:function (data) {
            var content = "";
            for (var i = 0; i < data.length; i++) {
                var po = data[i];
                content += "<a href='blogTags?tagId="+po.tagid+"'>" + po.tagname + "</a>";
            }
            $("#tagDropdown").html(content);
        }
    });
}
tagDropdown();