package com.lublog.provider.dao;

import com.lublog.po.Plan;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description: java类作用描述PlanMapper
 * @Author: lxy
 * @time: 2020/7/29 1:34
 */
public interface PlanMapper {
    @Insert("insert into plan (planContent, planDetail, planValue, planYear, planMonth, planDay, planHour, planMinute,planSecond,tagName) value (#{param1},#{param2},#{param3},#{param4},#{param5},#{param6},#{param7},#{param8},#{param9},#{param10})")
    void addPlan(String planContent, String planDetail, String planValue, int planYear, int planMonth, int planDay, int planHour, int planMinute, int planSecond, String tagName);



    @Select("select * from plan where flag = 0 and tagName = #{param1}")
    List<Plan> getTagPlans(String tagName);

    @Select("select * from plan where flag = 0")
    List<Plan> getAllPlans();

    @Select("select * from plan where flag = 0 and planId = #{param1}")
    Plan getOnePlan(Integer planId);

    @Update("update plan set doPlanDetail = #{param1} where flag = 0 and planId = #{param2} ")
    void updateDoDetailPlan(String doPlanDetail, Integer planId);

    @Update("update plan set flag = 1 where flag = 0 and planId = #{param1}")
    void deletePlan(Integer planId);
}
