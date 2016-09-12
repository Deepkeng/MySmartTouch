package com.example.administrator.myapplication;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by psq on 2016/9/12
 */
public class AcceptCommandService extends IntentService{
    public AcceptCommandService(){
        super("");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AcceptCommandService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
