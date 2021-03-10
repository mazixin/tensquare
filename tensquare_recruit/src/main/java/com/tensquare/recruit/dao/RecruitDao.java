package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    /**
     * description 查询最新职位列表（按照创建日期降序排列）
     * param [state]
     * return java.util.List<com.tensquare.recruit.pojo.Recruit>
     * author MartinKing
     * createTime 2021/3/9 15:22
     **/
    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);

    /**
     * description 查询最新职位列表（按照日期降序排列，查询6个。状态不为0）
     * param [state]
     * return java.util.List<com.tensquare.recruit.pojo.Recruit>
     * author MartinKing
     * createTime 2021/3/9 15:45
     **/
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);

}
