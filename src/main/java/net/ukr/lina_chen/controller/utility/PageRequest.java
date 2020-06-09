package net.ukr.lina_chen.controller.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.ukr.lina_chen.controller.utility.IConstants.MIN_QUANTITY_PAGES;
import static net.ukr.lina_chen.controller.utility.IConstants.PAGE_SIZE;

public class PageRequest<T> {
    private static final int SIZE = PAGE_SIZE;
    private final List<T> list;


    public PageRequest(List<T> list) {
        this.list = list;
    }

    private int getTotalPages() {
        return (int) Math.ceil(((double) list.size()) / SIZE);
    }

    public List<Integer> getPageNumbers() {
        return IntStream.rangeClosed(MIN_QUANTITY_PAGES, getTotalPages())
                .boxed()
                .collect(Collectors.toList());
    }

    public List<T> getPage(int pageNumber) {
        int start = pageNumber * SIZE;
        int end = Math.min((start + SIZE), list.size());
        if (start > list.size()) {
            return new ArrayList<>();
        }
        return list.subList(start, end);
    }
}
