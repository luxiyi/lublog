package com.lublog.provider.dao;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lublog.po.Statistics;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Description: java类作用描述DynamicProvider
 * @Author: lxy
 * @time: 2020/8/23 13:13
 */
@Slf4j
public class DynamicProvider {
    private static final String TABLE_NAME = "t_statistics";

    public String updateStatisticsNum(@Param("statistics") Statistics statistics, @Param("statisticsId")int statisticsId) {
        SQL sql = new SQL().UPDATE(TABLE_NAME);
        if (statistics.getPageViewNum() != 0) {
            sql.SET("page_view_num = " + statistics.getPageViewNum() +"");
        }
        if (statistics.getBlogLikesNum() != 0) {
            sql.SET("blog_likes_num = " + statistics.getBlogLikesNum() +"");
        }
        if (statistics.getBlogCommentsNum() != 0) {
            sql.SET("blog_comments_num = " + statistics.getBlogCommentsNum() +"");
        }
        if (statistics.getBlogTotalNum() != 0) {
            sql.SET("blog_total_num = " + statistics.getBlogTotalNum() +"");
        }
        if (statistics.getBlogShowNum() != 0) {
            sql.SET("blog_show_num = " + statistics.getBlogShowNum() +"");
        }
        if (statistics.getBlogDeleteNum() != 0) {
            sql.SET("blog_delete_num = " + statistics.getBlogDeleteNum() +"");
        }
        sql.WHERE("statistics_id = "+ statisticsId +" and flag = 0");
        log.info("sql = {}", sql.toString());
        return sql.toString();
    }
}
