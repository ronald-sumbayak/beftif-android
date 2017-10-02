package ra.sumbayak.beftif.main.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ra.sumbayak.beftif.R;
import ra.sumbayak.beftif.api.QRCallback;
import ra.sumbayak.beftif.api.models.News;
import ra.sumbayak.beftif.customs.ListRecyclerView.Host;
import ra.sumbayak.beftif.main.MainActivity;
import ra.sumbayak.beftif.main.NewsRecyclerView;
import ra.sumbayak.beftif.news.NewsActivity;
import retrofit2.Call;

public abstract class NewsFragment extends Fragment implements Host<News> {
    
    private static final int OPEN_NEWS_REQUEST = 1;
    
    @BindView (R.id.news_list)
    NewsRecyclerView newsList;
    
    private List<News> data;
    
    protected MainActivity context;
    protected String query;
    private boolean pending;
    
    @Override
    public void onAttach (Context context) {
        super.onAttach (context);
        this.context = (MainActivity) context;
        query = getArguments ().getString ("query");
        getApiInterface ().enqueue (new QRCallback<List<News>> () {
            @Override
            protected void onSuccessful (@NonNull List<News> body) {
                if (newsList != null)
                    setData (body);
                data = body;
            }
        });
    }
    
    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_page, container, false);
    }
    
    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        ButterKnife.bind (this, view);
        if (data != null) setData (data);
        newsList.setHost (this);
    }
    
    abstract protected Call<List<News>> getApiInterface ();
    
    protected void setData (List<News> data) {
        newsList.setData (data);
        for (int i = 0; i < data.size (); i++)
            newsList.getAdapter ().notifyItemInserted (i);
    }
    
    @Override
    public void onItemClick (News item) {
        if (pending)
            return;
        pending = true;
        Intent intent = new Intent (context, NewsActivity.class);
        intent.putExtra ("header", item.header);
        intent.putExtra ("category", item.category);
        intent.putExtra ("title", item.title);
        intent.putExtra ("date", item.date ());
        intent.putExtra ("content", item.content);
        intent.putExtra ("id", item.id);
        startActivityForResult (intent, OPEN_NEWS_REQUEST);
    }
    
    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == OPEN_NEWS_REQUEST)
            pending = false;
    }
}
