package com.example.administrator.myapplication;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by psq on 2016/9/8
 */
public class Myaccessibility extends AccessibilityService {
    private static final String TAG = "MyAccessibility";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        int eventType = event.getEventType();


        CharSequence className = event.getClassName();

        Log.d(TAG, "className:"+className);



        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
            /*    CharSequence className = getRootInActiveWindow().getClassName();
                Log.d(TAG, "className:"+className);*/
                break;
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:

                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:

                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:

                break;

        }


    }
    @Override
    public void onInterrupt() {

    }
}
