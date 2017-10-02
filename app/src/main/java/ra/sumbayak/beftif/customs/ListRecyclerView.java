package ra.sumbayak.beftif.customs;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.*;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class ListRecyclerView<T> extends RecyclerView {
    
    protected List<T> data = new ArrayList<> ();
    protected Host<T> host;
    
    public ListRecyclerView (Context context, @Nullable AttributeSet attrs) {
        super (context, attrs);
        setHasFixedSize (true);
        setLayoutManager (new LinearLayoutManager (context));
        addItemDecoration (new DividerItemDecoration (context, VERTICAL));
        setAdapter (new Adapter ());
    }
    
    public ListRecyclerView setData (List<T> data) {
        this.data = data;
        getAdapter ().notifyDataSetChanged ();
        return this;
    }
    
    public boolean hasData () {
        return data != null;
    }
    
    public ListRecyclerView setHost (Host<T> host) {
        this.host = host;
        return this;
    }
    
    protected int itemViewType (int position) {
        return 0;
    }
    
    protected abstract ViewHolder onCreateViewHolder (ViewGroup parent, int viewType);
    
    private class Adapter extends RecyclerView.Adapter<ViewHolder> {
    
        @Override
        public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            return ListRecyclerView.this.onCreateViewHolder (parent, viewType);
        }
    
        @Override
        public void onBindViewHolder (final ViewHolder holder, int position) {
            holder.itemView.setOnClickListener (new OnClickListener () {
                @Override
                public void onClick (View view) {
                    if (host != null)
                        host.onItemClick (data.get (holder.getAdapterPosition ()));
                }
            });
            holder.bind (data.get (position));
        }
    
        @Override
        public int getItemCount () {
            return data.size ();
        }
    
        @Override
        public int getItemViewType (int position) {
            return itemViewType (position);
        }
    }
    
    protected abstract class ViewHolder extends RecyclerView.ViewHolder {
    
        public ViewHolder (View itemView) {
            super (itemView);
            ButterKnife.bind (this, itemView);
        }
        
        protected abstract void bind (T item);
    }
    
    public interface Host<T> {
        void onItemClick (T item);
    }
}
