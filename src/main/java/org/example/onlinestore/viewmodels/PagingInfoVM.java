package org.example.onlinestore.viewmodels;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PagingInfoVM {
    private int totalItems;
    private int itemsPerPage;
    private int currentPage;

    private int totalPages;

    public PagingInfoVM(int currentPage, int totalItems, int itemsPerPage) {
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;
    }

    @Deprecated
    public int getTotalPages() {
        return (int) Math.ceil((double) totalPages / itemsPerPage);
    }
}

