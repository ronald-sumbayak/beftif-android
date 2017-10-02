package ra.sumbayak.beftif.main;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import ra.sumbayak.beftif.R;
import ra.sumbayak.beftif.api.models.News;
import ra.sumbayak.beftif.customs.ListRecyclerView;

public class NewsRecyclerView extends ListRecyclerView<News> {
    
    public NewsRecyclerView (Context context, @Nullable AttributeSet attrs) {
        super (context, attrs);
    }
    
    @Override
    protected ListRecyclerView<News>.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        return new ViewHolder (LayoutInflater
            .from (parent.getContext ())
            .inflate (viewType, parent, false));
    }
    
    @Override
    protected int itemViewType (int position) {
        if (position == 0)
            return R.layout.itemview_news_top;
        return R.layout.itemview_news;
    }
    
    class ViewHolder extends ListRecyclerView<News>.ViewHolder {
        
        @BindView (R.id.title) TextView title;
        @BindView (R.id.date) TextView date;
        @BindView (R.id.content) TextView content;
        @BindView (R.id.image) SimpleDraweeView image;
    
        public ViewHolder (View itemView) {
            super (itemView);
        }
    
        @Override
        protected void bind (News item) {
            title.setText (item.title);
            date.setText (item.date ());
            content.setText (item.content);
            image.setImageURI (Uri.parse (item.header));
        }
    }
}
