package com.example.bijay.expensemanagement.Views.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bijay.expensemanagement.Adapter.PersonsRecyclerAdapter;
import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.PersonSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPersonsFragment extends Fragment {

    private static final String TAG = ViewPersonsFragment.class.getSimpleName();

    private RecyclerView recyclerView;

    public ViewPersonsFragment() {
        Log.d(TAG, "[ViewPersonsFragment] constructor initialization is done");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_persons, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        Log.d(TAG, "[onCreateView] Inflate the layout for this fragment is done");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showPeople();
        Log.d(TAG, "[onActivityCreated] called, calling [showPeople] method");
    }

    private void showPeople() {
        PersonSqliteDatabaseAdapter personSqliteDatabaseAdapter = new PersonSqliteDatabaseAdapter(getContext());

        //Calling the custom recycler view adapter i.e. MyRecyclerAdapter
        PersonsRecyclerAdapter recyclerAdapter = new PersonsRecyclerAdapter(getActivity(), personSqliteDatabaseAdapter.getPersons());
        recyclerView.setAdapter(recyclerAdapter);

        //Creating the layout of the data to be displayed in the recycler view i.e. in linear with vertical arrangement
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Setting default animation of items in the recycler view. Note: it's default and it's not required to add.
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Log.d(TAG, "[showPeople] People fetched successfully");
    }
}
