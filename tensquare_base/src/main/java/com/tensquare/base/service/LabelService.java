package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

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
        label.setId(idWorker.nextId()+"");
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

}
