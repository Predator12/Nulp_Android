package com.example.predator.nulpandroid;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.predator.nulpandroid.Adapter.CharacterAdapter;
import com.example.predator.nulpandroid.Models.Hero;
import com.example.predator.nulpandroid.SQLite.SQLite;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Predator on 14.11.2018.
 */
public class FavouriteFragment extends Fragment implements CharacterAdapter.OnHeroCLickListener {
    private ArrayList<Hero> heroes;
    private CharacterAdapter mAdapter;
    @BindView(R.id.rvFavourite)
    RecyclerView mRvCharacters;
    @BindView(R.id.pullToRefreshFav)
    SwipeRefreshLayout mPullToRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, null);
        ButterKnife.bind(this, view);
        heroes = new ArrayList<>();

        mPullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFavouriteCharacters();
                mPullToRefresh.setRefreshing(false);
            }
        });
        mAdapter = new CharacterAdapter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvCharacters.setLayoutManager(layoutManager);
        mRvCharacters.setAdapter(mAdapter);
        mRvCharacters.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        getFavouriteCharacters();
        return view;
    }

    private void getFavouriteCharacters() {
        heroes.clear();
        String name, species, created, imgUrl;
        SQLite db = new SQLite(getActivity());
        Cursor cursor = db.getAll();
        while (cursor.moveToNext()) {
            name = cursor.getString(0);
            species = cursor.getString(1);
            created = cursor.getString(2);
            imgUrl = cursor.getString(3);
            Hero hero = new Hero(name, species, created, imgUrl);
            heroes.add(hero);
        }
        if (!(heroes.size() < 1)) {
            mRvCharacters.setAdapter(mAdapter);
        }
        mAdapter.update(heroes);
    }

    @Override
    public void onItemClick(Hero hero) {
        Bundle data = new Bundle();
        data.putString("name", hero.getName());
        data.putString("species", hero.getSpecies());
        data.putString("created", hero.getCreated());
        data.putString("url_to_img", hero.getImage());
        Fragment fragment = new HeroDetails();
        fragment.setArguments(data);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.addToBackStack(null);
        fragmentTransaction2.hide(FavouriteFragment.this);
        fragmentTransaction2.add(android.R.id.content, fragment);
        fragmentTransaction2.commit();
    }
}
