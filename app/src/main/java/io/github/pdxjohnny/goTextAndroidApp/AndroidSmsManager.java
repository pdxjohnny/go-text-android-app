package io.github.pdxjohnny.goTextAndroidApp;

import android.util.Log;

import java.util.ArrayList;

import go.hello.Hello;

/**
 * Created by john on 9/15/15.
 */
public class AndroidSmsManager extends Hello.SmsManager.Stub {
    public void Send(String phoneNumber, String message) {
        int MAX_SMS_MESSAGE_LENGTH = 160;
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();

        int length = message.length();
        if(length > MAX_SMS_MESSAGE_LENGTH) {
            ArrayList<String> messagelist = smsManager.divideMessage(message);
            smsManager.sendMultipartTextMessage(phoneNumber, null, messagelist, null, null);
        }
        else {
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        }
        Log.i("AndroidSmsManager", "Send() Sent " + phoneNumber + "\t\t" + message);
    }
}
