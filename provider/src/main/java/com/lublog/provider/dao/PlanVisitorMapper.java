package com.lublog.provider.dao;

import com.lublog.po.PlanVisitor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Description: PlanVisitorMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/8/15 20:44
 */
public interface PlanVisitorMapper {
    @Select("select * from t_planVisitor where plan_visitor_name = #{param1} and flag = 0")
    PlanVisitor queryPlanVisitor(String planVisitorName);

    @Insert("insert into t_planVisitor (plan_visitor_count, plan_visitor_name) values (#{param1}, #{param2})")
    void addPlanVisitor(int planVisitorCount, String planVisitorName);

    @Update("update t_planVisitor set plan_visitor_count = plan_visitor_count + 1 where plan_visitor_name = #{param2} and flag = 0")
    void updatePlanVisitor(String planVisitorName);
}
