package ra.sumbayak.beftif;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;

public class BEFTIf extends Application {
    
    @Override
    public void onCreate () {
        super.onCreate ();
        Fresco.initialize (this);
        registerActivityLifecycleCallbacks (new ActivityLifecycleCallbacks () {
            @Override
            public void onActivityCreated (Activity activity, Bundle bundle) {
                activity.setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            
            @Override
            public void onActivityStarted (Activity activity) {
                
            }
            
            @Override
            public void onActivityResumed (Activity activity) {
                
            }
            
            @Override
            public void onActivityPaused (Activity activity) {
                
            }
            
            @Override
            public void onActivityStopped (Activity activity) {
                
            }
            
            @Override
            public void onActivitySaveInstanceState (Activity activity, Bundle bundle) {
                
            }
            
            @Override
            public void onActivityDestroyed (Activity activity) {
                
            }
        });
    }
}
