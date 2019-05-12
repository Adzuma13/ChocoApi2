package com.example.myapplication.allDeals.fragments.mvp;
import com.example.myapplication.allDeals.adapter.DealAdapter;
import com.example.myapplication.allDeals.rest.ChocoApi;
import com.example.myapplication.allDeals.rest.NetworkService;
import com.example.myapplication.allDeals.rest.entities.MainDeal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealModel implements DealContract.Model {

    DealPresenter presenter;

    public DealModel(DealPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDealList(int page) {
        NetworkService networkService = NetworkService.getInstance();
        ChocoApi chocoApi = networkService.getChocoApi();
        Call<MainDeal> call = chocoApi.getDealList(1,1,page);

        call.enqueue(new Callback<MainDeal>() {
            @Override
            public void onResponse(Call<MainDeal> call, Response<MainDeal> response) {
               presenter.loadingFinisher(response.body());
            }

            @Override
            public void onFailure(Call<MainDeal> call, Throwable t) {
                presenter.loadingFailed(t);
            }
        });
    }
}
