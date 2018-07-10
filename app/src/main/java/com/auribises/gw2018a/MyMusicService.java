package com.auribises.gw2018a;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MyMusicService extends Service {

    String songToPlay;
    MediaPlayer mediaPlayer;

    public MyMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyMusicService","==onCreate==");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyMusicService","==onStartCommand==");

        songToPlay = intent.getStringExtra("keySong");

        Log.i("MyMusicService","==SongToPlay: "+songToPlay+"==");

        try {
            mediaPlayer = new MediaPlayer();

            String path = Environment.getExternalStorageDirectory().getPath()+"/"+songToPlay;
            Log.i("MyMusicService","==SongToPlay: "+path+"==");

            //Uri uri = Uri.parse("http://..../someSongName.mp3");
            //mediaPlayer.setDataSource(this,uri);

            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();

        }catch (Exception e){
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        mediaPlayer.release();

        Log.i("MyMusicService","==onDestroy==");

    }
}
