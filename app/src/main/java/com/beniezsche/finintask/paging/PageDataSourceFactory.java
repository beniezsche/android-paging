package com.beniezsche.finintask.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.beniezsche.finintask.model.Repo;

public class PageDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Repo>> repoLiveDataSource = new MutableLiveData<>();
    @Override
    public DataSource<Integer,Repo> create() {


        RepoDataSource repoDataSource = new RepoDataSource();

        repoLiveDataSource.postValue(repoDataSource);

        return repoDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Repo>> getItemLiveDataSource() {
        return repoLiveDataSource;
    }
}
