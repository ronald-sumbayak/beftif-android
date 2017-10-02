package ra.sumbayak.beftif.api.models;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class News {
    
    @SerializedName ("id")
    public int id;
    
    @SerializedName ("title")
    public String title;
    
    @SerializedName ("header")
    public String header;
    
    @SerializedName ("content")
    public String content;
    
    @SerializedName ("category")
    public String category;
    
    @SerializedName ("created_at")
    public String created_at;
    
    public String date () {
        SimpleDateFormat sdf = new SimpleDateFormat ("y-M-d", Locale.getDefault ());
        try {
            Date date = sdf.parse (created_at);
            sdf = new SimpleDateFormat ("d MMMM y", Locale.getDefault ());
            return sdf.format (date);
        }
        catch (ParseException e) { e.printStackTrace (); }
        return "-";
    }
}
