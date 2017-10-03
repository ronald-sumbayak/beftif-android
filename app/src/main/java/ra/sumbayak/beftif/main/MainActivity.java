package ra.sumbayak.beftif.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.gson.JsonObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ra.sumbayak.beftif.R;
import ra.sumbayak.beftif.SingleSourceTabLayout;
import ra.sumbayak.beftif.api.ApiInterface;
import ra.sumbayak.beftif.api.QRCallback;
import ra.sumbayak.beftif.api.models.Category;
import ra.sumbayak.beftif.api.models.News;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    
    @BindView (R.id.toolbar)
    Toolbar toolbar;
    
    @BindView (R.id.main_tab)
    SingleSourceTabLayout<News> tabLayout;
    
    @BindView (R.id.viewpager)
    ViewPager viewpager;
    
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        final Call<JsonObject> test = ApiInterface.Builder
            .build (this)
            .test ();
            
        test.clone ().enqueue (new QRCallback<JsonObject> () {
            @Override
            protected void onSuccessful (@NonNull JsonObject body) {
                ApiInterface.Builder
                    .build (MainActivity.this)
                    .categories ()
                    .enqueue (new QRCallback<List<Category>> () {
                        @Override
                        protected void onSuccessful (@NonNull List<Category> body) {
                            ButterKnife.bind (MainActivity.this);
                            setSupportActionBar (toolbar);
                            for (Category category : body)
                                tabLayout.addTab (category.name);
                            tabLayout.activate (viewpager);
                        }
                    });
                
            }

            @Override
            protected void onFailure () {
                test.clone ().enqueue (this);
            }
        });
    }
}
