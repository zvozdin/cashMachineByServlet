package ua.com.training.controller;

public class Page {

    private Integer offset;
    private Integer size;
    private Integer currentPageNum;

    public Page(Integer currentPageNum, Integer size){
        this.currentPageNum = currentPageNum;
        this.size = size;
        this.offset = currentPageNum * size - size; // important
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getCurrentPageNum() {
        return currentPageNum;
    }
}
