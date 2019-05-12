package com.example.myapplication.allDeals.fragments.mvp;
import com.example.myapplication.allDeals.rest.entities.MainDeal;

public class DealPresenter implements DealContract.Presenter {
    private DealContract.Model model;
    private DealContract.View view;

    public DealPresenter(DealContract.View view) {
        this.model = new DealModel(this);
        this.view = view;
    }

    @Override
    public void load(int page) {
        view.showLoading();
        model.getDealList(page);
    }

    @Override
    public void loadingFinisher(MainDeal mainDeal) {
        view.hideLoading();
        view.addNewDeals(mainDeal.getResultList());
    }

    @Override
    public void loadingFailed(Throwable t) {
        view.showError(t);
    }
}
