package ra.sumbayak.beftif.main.fragments;

import java.util.List;

import ra.sumbayak.beftif.api.ApiInterface;
import ra.sumbayak.beftif.api.models.News;
import retrofit2.Call;

public class CategoryFragment extends NewsFragment {
    
    @Override
    protected Call<List<News>> getApiInterface () {
        return ApiInterface.Builder
            .build (context)
            .newsByCategory (query);
    }
}
