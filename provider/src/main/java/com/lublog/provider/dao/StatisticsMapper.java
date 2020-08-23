package com.lublog.provider.dao;

import com.lublog.po.Statistics;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @Description: StatisticsMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/8/22 15:44
 */
public interface StatisticsMapper {
    @Select("select * from t_statistics where statistics_id = #{param1} and flag = 0")
    Statistics queryStatistics(int statisticsId);

    @UpdateProvider(type = DynamicProvider.class,method = "updateStatisticsNum")
    void updateStatisticsNum(@Param("statistics") Statistics statistics, @Param("statisticsId") int statisticsId);

    @Select("select count(blog_id) from t_blogContent")
    int queryBlogTotalNum();

    @Select("select count(blog_id) from t_blogContent where flag = #{param1}")
    int queryBlogShowOrDeleteNum(int flag);

    @Select("SELECT SUM(likes) FROM t_blogContent where flag = 0")
    int queryBlogLikesNum();

    @Select("SELECT SUM(comment_count) FROM t_blogContent where flag = 0")
    int queryBlogCommentsNum();

}
