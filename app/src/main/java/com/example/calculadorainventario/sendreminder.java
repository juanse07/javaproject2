package com.example.calculadorainventario;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class sendreminder extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int notificatioid=intent.getIntExtra("notificationid",0);
        String message= intent.getStringExtra("todo");
        Intent mainIntent= new Intent(context,reminderAc.class);
        PendingIntent contentIntent= PendingIntent.getActivity(context, 0,mainIntent,0);
        NotificationManager mynotificationmanager= (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,"notifypyme")
                .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
                .setContentTitle("Cobro Pendiente")
                .setContentText("hoha")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL);
        mynotificationmanager.notify(notificatioid,builder.build());

        //NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(context);
        //notificationManagerCompat.notify(100,builder.build());

    }
}
