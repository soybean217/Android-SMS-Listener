package com.example.nsalerni.smslistener;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import java.util.regex.Pattern;

/**
 * Created by fumin_000 on 2016/12/13.
 */

public class SmsContent extends ContentObserver {

    public static final String SMS_URI_INBOX = "content://sms/inbox";
    private Activity activity = null;
    private String smsContent = "";

    public SmsContent(Activity activity, Handler handler) {
        super(handler);
        this.activity = activity;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.d("SmsContent:", "enter ContentObserver");
        Cursor cursor = null;
        // read unread sms
        cursor = activity.managedQuery(Uri.parse(SMS_URI_INBOX), new String[]{"_id", "address", "body", "read"}, "read=?", new String[]{"0"}, "date desc");
        if (cursor != null) {
            Log.d("SmsContent:", "try to get read == 0 sms");
            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
//                String smsbody = cursor.getString(cursor.getColumnIndex("body"));
//                System.out.println("smsbody=======================" + smsbody);
//                String regEx = "[^0-9]";
//                Pattern p = Pattern.compile(regEx);
//                Matcher m = p.matcher(smsbody.toString());
//                smsContent = m.replaceAll("").trim().toString();
//                verifyText.setText(smsContent);
            }
        }
    }
}
