package com.example.predator.nulpandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.predator.nulpandroid.Adapter.CharacterAdapter;
import com.example.predator.nulpandroid.Models.Result;
import com.example.predator.nulpandroid.Retrofit.ApiUtils;
import com.example.predator.nulpandroid.Retrofit.Service;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Predator on 08.11.2018.
 */
public class HomeFragment extends Fragment {
    private Service mApiService;
    private CharacterAdapter mAdapter = new CharacterAdapter();
    @BindView(R.id.rv_info)
    RecyclerView mRvGames;
    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout mPullToRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        init(view);
        return view;
    }
    public void getCharacters() {
        mApiService.getCharacters().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    mAdapter.update(response.body().getResults());
                    Log.i("Api Json", json);
                } else {
                    Log.e("Error", "News don't load");
                    Toast.makeText(getActivity(), "error loading from API",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
                Toast.makeText(getActivity(), "error loading from API",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void init(View view) {
        mApiService = ApiUtils.getSOService();
        ButterKnife.bind(this, view);
        getCharacters();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvGames.setLayoutManager(layoutManager);
        mRvGames.setAdapter(mAdapter);
        mPullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCharacters();
                mPullToRefresh.setRefreshing(false);
            }
        });
    }

}
