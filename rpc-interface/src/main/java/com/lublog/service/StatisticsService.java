package com.lublog.service;

import com.lublog.po.Statistics;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: StatisticsServicejava类作用描述
 * @Author: lxy
 * @time: 2020/8/22 15:52
 */
public interface StatisticsService {
    Statistics queryStatistics(int statisticsId);

    int queryBlogTotalNum();

    int queryBlogShowOrDeleteNum(int flag);

    int queryBlogLikesNum();

    int queryBlogCommentsNum();

    void updateStatisticsNum(@Param("statistics")Statistics statistics, @Param("statisticsId")int statisticsId);
}
