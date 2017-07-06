package com.example.stefanmarvel.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stefanmarvel.R;
import com.example.stefanmarvel.adapters.ComicsAdapter;
import com.example.stefanmarvel.models.Comics;
import com.example.stefanmarvel.modules.MarvelModule;
import com.example.stefanmarvel.presenters.ComicsListPresenter;
import com.example.stefanmarvel.views.ComicsListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by stefanmay on 06/07/2017.
 */

public class ComicListFragment extends Fragment implements ComicsListView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ProgressDialog progressDialog;

    private Unbinder unbinder;
    private ComicsAdapter comicsAdapter;
    private ComicsListPresenter presenter;

    public static ComicListFragment getInstance(MarvelModule module) {
        ComicListFragment fragment = new ComicListFragment();

        fragment.presenter = new ComicsListPresenter(fragment, module);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comics_list, container, false);

        unbinder = ButterKnife.bind(this, view);

        comicsAdapter = new ComicsAdapter();
        recyclerView.setAdapter(comicsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog = ProgressDialog.show(getActivity(), getString(R.string.loading), "", true, false);
        presenter.getComicsList();
    }

    @Override
    public void setError(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.error);
        builder.setCancelable(false);
        builder.setMessage(error);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void setComics(Comics[] comics) {
        comicsAdapter.setComics(comics);
        comicsAdapter.notifyDataSetChanged();
        progressDialog.hide();
    }
}
