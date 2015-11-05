package com.example.hackeru.widgetexample;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


/**
 * Created by hackeru on 22/10/2015.
 */
public class MyWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        final int N = appWidgetIds.length;

        for(int i=0;i<N;i++){

            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context,MainActivity.class);
            intent.setAction("My awesome action");
            intent.putExtra("widget",true);

            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
            views.setOnClickPendingIntent(R.id.imageButton,pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId,views);
        }
    }
}
