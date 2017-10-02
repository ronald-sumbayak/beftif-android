package ra.sumbayak.beftif;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import ra.sumbayak.beftif.main.fragments.*;

public class SingleSourceTabLayout<T> extends TabLayout implements OnPageChangeListener,
                                                                   OnTabSelectedListener {
    
    private AppCompatActivity context;
    private List<String> categories = new ArrayList<> ();
    private List<NewsFragment> fragmentList = new ArrayList<> ();
    private List<T> data;
    private boolean is_activated;
    
    public SingleSourceTabLayout (Context context) {
        super (context);
        this.context = (AppCompatActivity) context;
        initialize ();
    }
    
    public SingleSourceTabLayout (Context context, AttributeSet attrs) {
        super (context, attrs);
        this.context = (AppCompatActivity) context;
        initialize ();
    }
    
    public SingleSourceTabLayout (Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        this.context = (AppCompatActivity) context;
        initialize ();
    }
    
    public SingleSourceTabLayout setData (List<T> data) {
        this.data = data;
        return this;
    }
    
    private void initialize () {
        createTab ("Terbaru", "created_at", new OrderingFragment ());
        createTab ("Populer", "popularity___popularity", new OrderingFragment ());
    }
    
    private void createTab (String title, String query, NewsFragment fragment) {
        addTab (newTab ().setText (title));
        Bundle args = new Bundle ();
        args.putString ("query", query);
        fragment.setArguments (args);
        fragmentList.add (fragment);
        categories.add (title);
    }
    
    public SingleSourceTabLayout addTab (String title) {
        createTab (title, title, new CategoryFragment ());
        return this;
    }
    
    public void activate (ViewPager viewpager) {
        if (is_activated)
            return;
        addOnTabSelectedListener (this);
        viewpager.setOffscreenPageLimit (getTabCount () - 1);
        viewpager.setAdapter (new ViewPagerAdapter (context.getSupportFragmentManager ()));
        viewpager.addOnPageChangeListener (this);
        viewpager.getAdapter ().notifyDataSetChanged ();
        setupWithViewPager (viewpager);
        is_activated = true;
    }
    
    @Override
    public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {
        
    }
    
    @Override
    public void onPageSelected (int position) {
        
    }
    
    @Override
    public void onPageScrollStateChanged (int state) {
        
    }
    
    @Override
    public void onTabSelected (Tab tab) {
        
    }
    
    @Override
    public void onTabUnselected (Tab tab) {
        
    }
    
    @Override
    public void onTabReselected (Tab tab) {
        
    }
    
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
    
        ViewPagerAdapter (FragmentManager fm) {
            super (fm);
        }
    
        @Override
        public Fragment getItem (int position) {
            return fragmentList.get (position);
        }
    
        @Override
        public int getCount () {
            return fragmentList.size ();
        }
    
        @Override
        public CharSequence getPageTitle (int position) {
            return categories.get (position);
        }
    }
}
