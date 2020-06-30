package com.beniezsche.finintask.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.beniezsche.finintask.model.Repo;
import com.beniezsche.finintask.paging.PageDataSourceFactory;

public class RepoViewModel extends ViewModel {

    private LiveData<PagedList<Repo>> repoPagedList;
    private LiveData<PageKeyedDataSource<Integer, Repo>> liveDataSource;


    public RepoViewModel() {

        PageDataSourceFactory itemDataSourceFactory = new PageDataSourceFactory();


        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();


        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(2).build();


        repoPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)).build();
    }

    public LiveData<PagedList<Repo>> getRepoPagedList() {
        return repoPagedList;
    }

    public void setRepoPagedList(LiveData<PagedList<Repo>> repoPagedList) {
        this.repoPagedList = repoPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, Repo>> getLiveDataSource() {
        return liveDataSource;
    }

    public void setLiveDataSource(LiveData<PageKeyedDataSource<Integer, Repo>> liveDataSource) {
        this.liveDataSource = liveDataSource;
    }


}
