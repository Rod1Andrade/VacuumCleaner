package com.github.rod1andrade.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Rodrigo Andrade
 */
public class AudioPlayer {

    private Clip clip;

    public AudioPlayer(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            AudioFormat audioFormat = audioInputStream.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    audioFormat.getSampleRate(),
                    16,
                    audioFormat.getChannels(),
                    audioFormat.getChannels() * 2,
                    audioFormat.getSampleRate(),
                    false
            );

            AudioInputStream audioInputStreamDecode = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);
            clip = AudioSystem.getClip();
            clip.open(audioInputStreamDecode);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if(clip == null) return;

        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        if(clip.isRunning()) clip.stop();
    }
}
