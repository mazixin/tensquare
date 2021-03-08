package entity;

import java.util.List;

/**
 * @description:分页类
 * @projectName:tensquare_parent
 * @see:entity
 * @author:MartinKing
 * @createTime:2021/3/8 10:25
 * @version:1.0
 */
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
