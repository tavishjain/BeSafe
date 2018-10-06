package ytv.appathon.appathon_igdtu;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class HelpWidget extends AppWidgetProvider {

    public static String WIDGET_BUTTON = "ytv.appathon.appathon_igdtu.APPWIDGET_UPDATE";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.help_widget);
            Intent configIntent = new Intent(context, SplashScreen.class);

            PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
            Log.e("tavish", "click");
            remoteViews.setOnClickPendingIntent(R.id.appwidget_help_button, configPendingIntent);

            context.startActivity(configIntent);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

