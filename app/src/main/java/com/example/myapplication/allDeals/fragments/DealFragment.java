package com.example.myapplication.allDeals.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.allDeals.adapter.DealAdapter;
import com.example.myapplication.allDeals.adapter.ReachEndList;
import com.example.myapplication.allDeals.fragments.mvp.DealContract;
import com.example.myapplication.allDeals.fragments.mvp.DealModel;
import com.example.myapplication.allDeals.fragments.mvp.DealPresenter;
import com.example.myapplication.allDeals.rest.entities.Deal;

import java.util.List;

public class DealFragment extends Fragment implements DealContract.View {
    private View view;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private int currentPage = 1;
    private boolean isLoading = false;
    private DealAdapter dealAdapter;

    private DealContract.Presenter dealPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_deal, container, false);
        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        progressBar = view.findViewById(R.id.progressBar);

        dealAdapter = new DealAdapter();
        recyclerView.setAdapter(dealAdapter);


        dealPresenter = new DealPresenter(this);
            dealAdapter.setReachEndList(new ReachEndList() {
                @Override
                public void OnReachEnd() {
                    if(!isLoading){
                        isLoading = true;
                        currentPage++;
                        dealPresenter.load(currentPage);

                    }
                }
            });
        dealPresenter.load(1);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void addNewDeals(List<Deal> newDeal) {
        dealAdapter.addDealList(newDeal);
        isLoading = false;
    }

    @Override
    public void showError(Throwable t) {
        Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
