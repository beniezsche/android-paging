package com.beniezsche.finintask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;


import com.beniezsche.finintask.adapter.RepoAdapter;
import com.beniezsche.finintask.model.Repo;
import com.beniezsche.finintask.viewmodel.RepoViewModel;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private static final String LOG_TAG = "CheckNetworkStatus";
    private NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, filter);


    }

    public void init(){

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setHasFixedSize(true);


        RepoViewModel repoViewModel = ViewModelProviders.of(this).get(RepoViewModel.class);

        //creating the Adapter
        final RepoAdapter adapter = new RepoAdapter(this);


        //observing the itemPagedList from view model
        repoViewModel.getRepoPagedList().observe(this, new Observer<PagedList<Repo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Repo> items) {

                //in case of any changes
                //submitting the items to adapter
                adapter.submitList(items);
            }
        });

        //setting the adapter
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {


            if(isNetworkAvailable(context)){
                Toast.makeText(context,"You are connected to the internet",Toast.LENGTH_SHORT).show();
                init();
            }
            else {

                Toast.makeText(context,"You are not connected to the internet",Toast.LENGTH_SHORT).show();
            }

        }

        private boolean isNetworkAvailable(Context context) {

            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivity.getActiveNetworkInfo() != null && connectivity.getActiveNetworkInfo().isConnected();
        }
    }





}
