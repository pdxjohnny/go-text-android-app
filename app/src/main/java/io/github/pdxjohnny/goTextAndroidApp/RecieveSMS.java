package io.github.pdxjohnny.goTextAndroidApp;

/**
 * Created by john on 9/15/15.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import go.hello.Hello;

public class RecieveSMS extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MainActivity", "onReceive() Started");
        Bundle bundle = intent.getExtras();
//        Hello.SendUpdate(bundle);
        SmsMessage[] recievedMsgs = null;
        String str = "";
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            recievedMsgs = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                recievedMsgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                str += "SMS from " + recievedMsgs[i].getOriginatingAddress() + " :" + recievedMsgs[i].getMessageBody().toString();
            }
        }

        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
