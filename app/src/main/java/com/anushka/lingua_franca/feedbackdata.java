package com.anushka.lingua_franca;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ravi Tamada on 07/10/16.
 * www.androidhive.info
 */

@IgnoreExtraProperties
public class feedbackdata {

    public String data;
    public String type;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public feedbackdata() {
    }

    public feedbackdata(String data, String type) {
        this.data = data;
        this.type = type;
    }
}
