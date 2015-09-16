package io.github.pdxjohnny.goTextAndroidApp;

/**
 * Created by john on 9/15/15.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class RecieveSMS extends BroadcastReceiver {
    private final String DEBUG_TAG = getClass().getSimpleName().toString();
    private static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private Context mContext;
    private Intent mIntent;

    // Retrieve SMS
    public void onReceive(Context context, Intent intent) {
        Log.i("RecieveSMS", "onReceive() Started");
        mContext = context;
        mIntent = intent;

        String action = intent.getAction();

        if(action.equals(ACTION_SMS_RECEIVED)){

            String address, str = "";
            String contactId = "No Contact";

            SmsMessage[] msgs = getMessagesFromIntent(mIntent);
            if (msgs != null) {
                for (int i = 0; i < msgs.length; i++) {
                    address = msgs[i].getOriginatingAddress();
//                    contactId = ContactsUtils.getContactId(mContext, address, "address");
                    contactId = address;
                    str += msgs[i].getMessageBody().toString();
                    str += "\n";
                }
            }

            showNotification(contactId, str);

            // ---send a broadcast intent to update the SMS received in the
            // activity---
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("SMS_RECEIVED_ACTION");
            broadcastIntent.putExtra("sms", str);
            context.sendBroadcast(broadcastIntent);
        }

    }

    public static SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        byte[][] pduObjs = new byte[messages.length][];

        for (int i = 0; i < messages.length; i++) {
            pduObjs[i] = (byte[]) messages[i];
        }
        byte[][] pdus = new byte[pduObjs.length][];
        int pduCount = pdus.length;
        SmsMessage[] msgs = new SmsMessage[pduCount];
        for (int i = 0; i < pduCount; i++) {
            pdus[i] = pduObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
        }
        return msgs;
    }

    /**
     * The notification is the icon and associated expanded entry in the status
     * bar.
     */
    protected void showNotification(String contactId, String message) {
        Log.i("RecieveSMS", "showNotification() " + contactId + "\t\t" + message);
    }
}
