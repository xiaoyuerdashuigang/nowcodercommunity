package com.pudongdevelopmentbank.entity;

/***
 *@Description 封装分页相关的信息
 *@Author:Lihuiming
 *@Date:2022/6/7
 */
public class Page {

    // 当前页码
    private int current = 1;
    // 显示上限,每页显示的条数
    private int limit = 10;
    // 数据总数，用于计算总的页数
    private int rows;
    // 查询路径,比如说点击第一页、第二页
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     *
     * @return
     */
    public int getOffset() {
        return (current - 1) * limit;
    }

    /**
     * 获取总的页数
     *
     * @return
     */
    public int getTotal() {
        if (rows % limit == 0) {
            return rows / limit;
        }
        return rows / limit + 1;
    }

    /**
     * 获得起始页
     *
     * @return
     */
    public int getFrom() {
        int from = current - 2;
        return from <1 ? 1 : from;
    }

    /**
     * 获得结束页
     *
     * @return
     */
    public int getTo() {
        int total = getTotal();
        int to = current + 2;
        return to > total ? total : to;
    }
}
