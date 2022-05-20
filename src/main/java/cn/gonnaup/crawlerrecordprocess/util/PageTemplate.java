package cn.gonnaup.crawlerrecordprocess.util;

import java.util.List;

/**
 * 分页数据返回模板
 * @author gonnaup
 * @version created at 2022/4/27 20:42
 */
public class PageTemplate<T> {

    private PageTemplate(List<T> content, long totalElements, int totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public static <T> PageTemplate<T> of(List<T> content, long totalElements, int totalPages) {
        return new PageTemplate<>(content, totalElements, totalPages);
    }

    private List<T> content;

    private long totalElements;

    private int totalPages;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
