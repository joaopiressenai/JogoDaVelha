package com.joao.jogodavelha;

import android.content.Context;
import android.media.MediaPlayer;

public class Som {
    private static MediaPlayer player;

    public static void executar(Context context, int somId) {
        player = MediaPlayer.create(context, somId);
        player.start();
        player.setLooping(true);
    }

    public static void parar() {
        player.stop();
    }
}
