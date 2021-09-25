package com.jpsoft.onlinemusicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.jpsoft.onlinemusicplayer.ApplicationClass.ACTION_NEXT;
import static com.jpsoft.onlinemusicplayer.ApplicationClass.ACTION_PLAY;
import static com.jpsoft.onlinemusicplayer.ApplicationClass.ACTION_PREV;
import static com.jpsoft.onlinemusicplayer.ApplicationClass.MY_ACTION_NAME;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context,MusicService.class);
        if (intent.getAction() != null)
            switch (intent.getAction()) {
                case ACTION_PLAY:
                    Toast.makeText(context, "Play", Toast.LENGTH_SHORT).show();
                    intent1 .putExtra(MY_ACTION_NAME,intent.getAction());
                    context.startService(intent1);
                    break;
                case ACTION_PREV:
                    Toast.makeText(context, "Prev", Toast.LENGTH_SHORT).show();
                    intent1 .putExtra(MY_ACTION_NAME,intent.getAction());
                    context.startService(intent1);
                    break;
                case ACTION_NEXT:
                    Toast.makeText(context, "Next", Toast.LENGTH_SHORT).show();
                    intent1 .putExtra(MY_ACTION_NAME,intent.getAction());
                    context.startService(intent1);
                    break;

            }
    }
}
