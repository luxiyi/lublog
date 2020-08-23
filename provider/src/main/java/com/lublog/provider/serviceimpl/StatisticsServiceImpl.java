package com.lublog.provider.serviceimpl;

import com.lublog.po.Statistics;
import com.lublog.provider.dao.StatisticsMapper;
import com.lublog.service.StatisticsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: java类作用描述StatisticsServiceImpl
 * @Author: lxy
 * @time: 2020/8/22 15:53
 */
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Statistics queryStatistics(int statisticsId) {
        return statisticsMapper.queryStatistics(statisticsId);
    }

    @Override
    public int queryBlogTotalNum() {
        return statisticsMapper.queryBlogTotalNum();
    }

    @Override
    public int queryBlogShowOrDeleteNum(int flag) {
        return statisticsMapper.queryBlogShowOrDeleteNum(flag);
    }


    @Override
    public int queryBlogLikesNum() {
        return statisticsMapper.queryBlogLikesNum();
    }

    @Override
    public int queryBlogCommentsNum() {
        return statisticsMapper.queryBlogCommentsNum();
    }

    @Override
    public void updateStatisticsNum(@Param("statistics")Statistics statistics, @Param("statisticsId")int statisticsId) {
        statisticsMapper.updateStatisticsNum(statistics, statisticsId);
    }
}
