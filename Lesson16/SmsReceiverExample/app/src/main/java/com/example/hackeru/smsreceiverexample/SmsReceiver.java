package com.example.hackeru.smsreceiverexample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by hackeru on 17/08/2015.
 */
public class SmsReceiver extends BroadcastReceiver {

    final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {

        if(!intent.getAction().equals(SMS_ACTION)) return;

        //sends a broadcast in all android system
        Intent local = new Intent();
        local.setAction("com.pzlapps.kill"); //the mainactivity register to kill the app upon receiving this mewssage
        context.sendBroadcast(local);   //all broadcast receiver who resgistered this action through intent filter will receive this intent

        Toast.makeText(context,"Sms received",Toast.LENGTH_SHORT).show();

        Bundle bundle = intent.getExtras();
        if(bundle==null) return;

        SmsMessage smsMessage;
        if(Build.VERSION.SDK_INT>=19) {//KITKAT 4.4 and above
            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            smsMessage = msgs[0];
        }
        else {
            Object pdus[] = (Object[])bundle.get("pdus");
            smsMessage = SmsMessage.createFromPdu((byte[])pdus[0]);
        }
        String message = smsMessage.getMessageBody();
        String title = smsMessage.getDisplayOriginatingAddress();

        Intent intent1 = new Intent(context,MainActivity.class);
        intent1.putExtra("message",message);
        intent1.putExtra("number",title);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);





    }
}
