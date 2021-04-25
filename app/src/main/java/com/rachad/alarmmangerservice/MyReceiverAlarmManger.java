package com.rachad.alarmmangerservice;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.os.IResultReceiver;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;


public class MyReceiverAlarmManger extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, MyService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent1);
        } else {
            context.startService(intent1);
        }

        notify(context,"work");
        /*Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2 * 1000);*/
    }

    private void notify(Context context, String msg) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        Notification notification =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                        .setContentText(msg)
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setContentIntent(contentIntent).build();

        notificationManager.notify(0, notification);
    }
}
