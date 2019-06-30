package com.t3h.ailatrieuphu.media;

import android.media.MediaPlayer;

import java.io.IOException;

public class Sound{
    private MediaPlayer m;

    public void sound(String path, MediaPlayer.OnCompletionListener ln){
        if (m != null){
            m.release();
        }
        m = new MediaPlayer();
        try{
            m.setDataSource(path);
            m.setOnCompletionListener(ln);
            m.prepare();
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
