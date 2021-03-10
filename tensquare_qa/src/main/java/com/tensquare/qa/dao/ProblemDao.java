package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * description最新回答列表
     * param
     * return
     * author MartinKing
     * createTime 2021/3/9 17:55
     **/
    @Query(value = "SELECT * FROM `tb_problem` INNER JOIN tb_pl ON tb_pl.problemid = tb_problem.id WHERE tb_pl.labelid = ? ORDER BY replytime DESC",nativeQuery = true)
    Page<Problem> newList(String labelId, Pageable pageable);

    /**
     * description 热门回答列表
     * param
     * return
     * author MartinKing
     * createTime 2021/3/9 17:56
     **/
    @Query(value = "SELECT * FROM `tb_problem` INNER JOIN tb_pl ON tb_pl.problemid = tb_problem.id WHERE tb_pl.labelid = ? ORDER BY reply DESC",nativeQuery = true)
    Page<Problem> hotList(String labelId, Pageable pageable);

    /**
     * description 等待回答列表
     * param
     * return
     * author MartinKing
     * createTime 2021/3/9 17:56
     **/
    @Query(value = "SELECT * FROM `tb_problem` INNER JOIN tb_pl ON tb_pl.problemid = tb_problem.id WHERE tb_pl.labelid = ? AND tb_problem.reply=0 ORDER BY tb_problem.createtime DESC",nativeQuery = true)
    Page<Problem> waitList(String labelId, Pageable pageable);
}
