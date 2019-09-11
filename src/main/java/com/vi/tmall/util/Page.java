package com.vi.tmall.util;

public class Page {
    //开始页数
    private int start;
    //每页显示个数
    private int count;
    //总记录数
    private int total;
    //参数
    private String param;
    //默认每页显示5条
    private static final int defaultCount = 5;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    //返回总记录数
    public int getTotal() {
        return total;
    }
    public Page() {
        count = defaultCount;
    }
    public String getParam() {
        return param;
    }
    public void setParam(String param) {
        this.param = param;
    }
    public Page(int start,int count) {
        this();
        this.start = start;
        this.count = count;
    }
    //是否有上一页
    public boolean isHasPrevious() {
        return !(start == 0);
    }
    //是否有下一页
    public boolean isHasNext() {
        return !(start == getLast());
    }
    //获取总页数
    public int getTotalPage() {
        int totalPage;
        if(total%count == 0)
            totalPage = total/count + 1;
        else
            totalPage = total/count;
        if(totalPage == 0)
            totalPage = 1;
        return totalPage;
    }


    //获取最后一页的第一条记录是第多少条记录
    public int getLast() {
        int last;
        if(total%count == 0)
            last = total - count;
        else
            last = total - total%count;
        return last<0?0:last;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", param='" + param + '\'' +
                '}';
    }
}
