package ra.sumbayak.beftif.news;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ra.sumbayak.beftif.R;
import ra.sumbayak.beftif.api.ApiInterface;
import ra.sumbayak.beftif.api.QRCallback;
import ra.sumbayak.beftif.api.models.News;

public class NewsActivity extends AppCompatActivity {
    
    @BindView (R.id.header)
    SimpleDraweeView header;
    
    @BindView (R.id.category)
    TextView category;
    
    @BindView (R.id.title)
    TextView title;
    
    @BindView (R.id.date)
    TextView date;
    
    @BindView (R.id.content)
    TextView content;
    
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_news);
        ButterKnife.bind (this);
        header.setImageURI (Uri.parse (getIntent ().getStringExtra ("header")));
        category.setText (getIntent ().getStringExtra ("category"));
        title.setText (getIntent ().getStringExtra ("title"));
        date.setText (getIntent ().getStringExtra ("date"));
        content.setText (getIntent ().getStringExtra ("content"));
        
        new Handler ().postDelayed (new Runnable () {
            @Override
            public void run () {
                ApiInterface.Builder
                    .build (NewsActivity.this)
                    .retrieveNews (getIntent ().getIntExtra ("id", 0))
                    .enqueue (new QRCallback<News> () {
                        @Override
                        protected void onSuccessful (@NonNull News body) {
                            
                        }
                    });
            }
        }, 5120);
    }
}
