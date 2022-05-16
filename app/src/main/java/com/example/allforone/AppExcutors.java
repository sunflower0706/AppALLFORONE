package com.example.allforone;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class AppExcutors {
    public static Future getInstance;
    private static AppExcutors instance;

    private static AppExcutors getInstance() {
        if (instance == null) {
            instance = new AppExcutors();
        }
        return instance;
    }
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public  ScheduledExecutorService netWorkIO(){
        return mNetworkIO;
    }
}