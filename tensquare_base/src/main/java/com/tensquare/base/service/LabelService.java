package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.base.service
 * @author:MartinKing
 * @createTime:2021/3/8 14:49
 * @version:1.0
 */
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * description 根据id查询标签
     * param
     * return
     * author MartinKing
     * createTime 2021/3/8 14:54
     **/
    public Label findLabelById(String labelId) {
        return labelDao.findById(labelId).get();
    }

    /**
     * description 新增一条标签
     * param
     * return
     * author MartinKing
     * createTime 2021/3/8 14:56
     **/
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * description 修改标签
     * param [label]
     * return void
     * author MartinKing
     * createTime 2021/3/8 14:58
     **/
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * description 删除标签
     * param
     * return
     * author MartinKing
     * createTime 2021/3/8 14:58
     **/
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }



    /**
     * description 条件查询，根据label查询，where labelname like %xxx% and state = x
     * param [label]
     * return java.util.List<com.tensquare.base.pojo.Label>
     * author MartinKing
     * createTime 2021/3/9 9:41
     **/
    public List<Label> searchLabel(Label label) {
        List<Label> labels = labelDao.findAll(new Specification<Label>() {
            List<Predicate> predicateList = new ArrayList<>();

            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(predicate);
                }
                if (label.getState() != null && "".equals(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    predicateList.add(predicate);
                }
                //给cb.add构建一个数组对象
                Predicate[] p = new Predicate[predicateList.size()];
                //将list对象转为数组对象
                p = predicateList.toArray(p);
                return cb.and(p);
            }
        });
        return labels;
    }

    public Page<Label> queryPage(Label label, int page, int size) {
        Specification<Label> specification = new Specification<Label>() {
            List<Predicate> predicateList = new ArrayList<>();

            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(predicate);
                }
                if (label.getState() != null && "".equals(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    predicateList.add(predicate);
                }
                //给cb.add构建一个数组对象
                Predicate[] p = new Predicate[predicateList.size()];
                //将list对象转为数组对象
                p = predicateList.toArray(p);
                return cb.and(p);
            }
        };
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Label> labelPage = labelDao.findAll(specification, pageable);
        System.out.println(labelPage.getContent());
        return labelPage;
    }
}
