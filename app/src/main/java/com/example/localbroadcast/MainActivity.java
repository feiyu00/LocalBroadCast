package com.example.localbroadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter mIntentFilter;
    private LocalReceiver mLocalReceiver;
    private Button mBtn_broadcast;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn_broadcast = findViewById(R.id.btn_broadcast);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        mBtn_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.judexz.www");
                intent.setPackage("com.example.localbroadcast");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        //注册广播
        mLocalReceiver = new LocalReceiver();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("com.judexz.www");
        localBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        localBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    static class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "接收到了广播", Toast.LENGTH_SHORT).show();
        }
    }
}
