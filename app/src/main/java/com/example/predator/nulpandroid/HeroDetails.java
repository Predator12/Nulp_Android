package com.example.predator.nulpandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.predator.nulpandroid.SQLite.SQLite;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vova0199 on 14.11.2018.
 */
public class HeroDetails extends Fragment {
    private SQLite sqLite;
    private String name;
    private String species;
    private String created;
    private String urlToImg;
    @BindView(R.id.button_delete)
    public Button mDelete;
    @BindView(R.id.button_save)
    public Button mSave;
    @BindView(R.id.name)
    public TextView mName;
    @BindView(R.id.species)
    public TextView mSpecies;
    @BindView(R.id.created)
    public TextView mCreated;
    @BindView(R.id.imageView)
    public ImageView mImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero, null);
        ButterKnife.bind(this, view);
        sqLite = new SQLite(getActivity());
        getInfo();
        return view;
    }

    private void getInfo() {
        name = getArguments().getString("name");
        species = getArguments().getString("species");
        created = getArguments().getString("created");
        urlToImg = getArguments().getString("url_to_img");
        setInfo(name, species, created, urlToImg);
    }

    @OnClick({R.id.button_save, R.id.button_delete})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                sqLite.insert(name, species, created, urlToImg);
                Toast.makeText(getActivity(), "Збережено до улюблених", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_delete:
                sqLite.delete(name);
                Toast.makeText(getActivity(), "Видалено з улюблених", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setInfo(String name, String species, String created, String urlToImg) {
        mName.setText(name);
        mSpecies.setText(species);
        mCreated.setText(created);
        Picasso.get().load(urlToImg).into(mImage);
    }
}
