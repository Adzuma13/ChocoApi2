package com.example.myapplication.allDeals.fragments.mvp;

import com.example.myapplication.allDeals.rest.entities.Deal;
import com.example.myapplication.allDeals.rest.entities.MainDeal;

import java.util.List;

public interface DealContract {
    interface View{
        void showLoading();
        void hideLoading();

        void addNewDeals(List<Deal> newDeal);
        void showError(Throwable t);

    }

    interface Presenter{
        void load(int page);
        void loadingFinisher(MainDeal mainDeal);
        void loadingFailed(Throwable t);

    }

    interface Model{
        void getDealList(int page);
    }
}
