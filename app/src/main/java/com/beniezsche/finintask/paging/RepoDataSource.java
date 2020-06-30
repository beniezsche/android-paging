package com.beniezsche.finintask.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.beniezsche.finintask.model.Page;
import com.beniezsche.finintask.model.Repo;
import com.beniezsche.finintask.retrofit2.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoDataSource extends PageKeyedDataSource<Integer, Repo> {


    private static final int FIRST_PAGE = 1;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Repo> callback) {

        RetrofitClient.getInstance()
                .getApi().getPage(FIRST_PAGE)
                .enqueue(new Callback<Page>() {
                    @Override
                    public void onResponse(Call<Page> call, Response<Page> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getData(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Page> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repo> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Repo> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getPage(params.key)
                .enqueue(new Callback<Page>() {
                    @Override
                    public void onResponse(Call<Page> call, Response<Page> response) {

                        if (response.body() != null) {

                            callback.onResult(response.body().getData(), null);
                        }
                    }

                    @Override
                    public void onFailure(Call<Page> call, Throwable t) {

                    }
                });

    }
}
