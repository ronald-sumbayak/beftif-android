package ra.sumbayak.beftif.api;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.*;

public abstract class QRCallback<T> implements Callback<T> {
    
    @Override
    public void onResponse (@NonNull Call<T> call, @NonNull Response<T> response) {
        Log.e ("QRCallback", call.request ().toString ());
        Log.e ("QRCallback", response.toString ());
        
        if (response.isSuccessful ()) {
            T body = response.body ();
            Log.e ("QRCallback", String.valueOf (body));
            
            if (body != null && isSuccess (body)) _onSuccessful (body);
            else onFailure ();
        }
        else if (response.code () == 401) onUnauthorized ();
        else onFailure ();
        onExit ();
    }
    
    private void _onSuccessful (@NonNull T body) {
        Log.e ("QRCallback", body.toString ());
        onSuccessful (body);
    }
    
    @Override
    public void onFailure (@NonNull Call<T> call, @NonNull Throwable t) {
        t.printStackTrace ();
        Log.e ("QRCallback", call.request ().toString ());
        Log.e ("QRCallback", t.getMessage ());
        Log.e ("QRCallback", t.getLocalizedMessage ());
        Log.e ("QRCallback", t.toString ());
        onFailure ();
    }
    
    protected abstract void onSuccessful (@NonNull T body);
    
    protected boolean isSuccess (@NonNull T body) {
        return true;
    }
    
    protected void onUnauthorized () {
        
    }
    
    protected void onFailure () {
        
    }
    
    protected void onExit () {
        
    }
}

