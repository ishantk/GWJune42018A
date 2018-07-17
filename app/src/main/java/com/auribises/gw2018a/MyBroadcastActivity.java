package com.auribises.gw2018a;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MyBroadcastActivity extends AppCompatActivity {

    MyReceiver myReceiver;
    YourReceiver yourReceiver;


    void initMyReceiver(){

        myReceiver = new MyReceiver();

        // IntentFilter will register System Defined Actions
        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_BATTERY_LOW);

        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);

        filter.addAction(Intent.ACTION_BOOT_COMPLETED);

        filter.addDataScheme("package"); // data scheme package shall give me the details of the package

        registerReceiver(myReceiver,filter);
    }

    void initYourReceiver(){

        yourReceiver = new YourReceiver();

        // IntentFilter will register User Defined Actions
        IntentFilter filter = new IntentFilter();
        filter.addAction("a.b.c.d");
        filter.addAction("kuch.bhi.ho.sakta.hai");
        filter.addAction("apna.action");

        LocalBroadcastManager.getInstance(this).registerReceiver(yourReceiver,filter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broadcast);

        initMyReceiver();
        initYourReceiver();

    }

    // To Display Notification
    void showNotification(){

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("myChannelId","MyChannel",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("This is Title");
        builder.setContentText("This is Text");
        builder.setChannelId("myChannelId");
        builder.setSmallIcon(R.drawable.ic_menu_camera);

        builder.setDefaults(Notification.DEFAULT_ALL); // Lights, Sound and Vibration

        Intent intent = new Intent(MyBroadcastActivity.this,AllStudentsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,111,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("This is Big Text"));
        builder.addAction(R.drawable.ic_menu_camera,"Camera",pendingIntent);
        builder.addAction(R.drawable.ic_menu_send,"Send",pendingIntent);

        Notification notification = builder.build();

        notificationManager.notify(101,notification);


    }


    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            String packageName = intent.getData().getSchemeSpecificPart();

            if(action.equals(Intent.ACTION_PACKAGE_ADDED)){
                Log.i("MyBroadcastActivity","==Package Added=="+packageName);
            }

            if(action.equals(Intent.ACTION_BATTERY_LOW)){
                Log.i("MyBroadcastActivity","==Battery Low==");
                showNotification();
            }

            if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){
                Log.i("MyBroadcastActivity","==Package Removed=="+packageName);
            }

        }
    }

    class YourReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("a.b.c.d")){
                Toast.makeText(MyBroadcastActivity.this,"a.b.c.d received",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void clickHandler(View view){
        //Intent intent = new Intent("a.b.c.d");
        //LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        showNotification();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(myReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(yourReceiver);
    }
}
