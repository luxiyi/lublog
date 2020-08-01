package com.lublog.provider.dao;

import com.lublog.po.Plan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: java类作用描述PlanMapper
 * @Author: lxy
 * @time: 2020/7/29 1:34
 */
public interface PlanMapper {
    @Insert("insert into plan (planContent, planDetail, planValue, planYear, planMonth, planDay, planHour, planMinute,planSecond) value (#{param1},#{param2},#{param3},#{param4},#{param5},#{param6},#{param7},#{param8},#{param9})")
    void addPlan(String planContent, String planDetail, String planValue, int planYear, int planMonth, int planDay, int planHour, int planMinute, int planSecond);



    @Select("select * from plan where flag = 0")
    List<Plan> getAllPlans();
}
