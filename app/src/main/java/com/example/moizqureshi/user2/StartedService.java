package com.example.moizqureshi.user2;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class StartedService extends Service {

    Firebase mRef1 = new Firebase("https://moizqureshi-110.firebaseio.com/first");
    Firebase mRef2 = new Firebase("https://moizqureshi-110.firebaseio.com/second");


    public StartedService() {
    }

    final class MyThread implements Runnable {
        int startId;


        public MyThread(int startId) {
            this.startId = startId;
        }

        @Override
        public void run() {

            mRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getValue(String.class);
                    Toast.makeText(getBaseContext(), data, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            final String list[] = {"hello", "from", "user", "2"};




            for (int i = 0; i < list.length; i++) {
                final int n = i;
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRef1.setValue(list[n]);
                        handler.postDelayed(this, 18000);
                    }
                }, 4000);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(StartedService.this, "Service Started", Toast.LENGTH_SHORT).show();
        Thread thread = new Thread(new MyThread((startId)));
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(StartedService.this, "Service Stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
