package fr.iut.yellybird.models;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;

/**
 * The type Sound meter.
 */
public class SoundMeter {
    private AudioRecord ar = null;
    private int minSize;

    /**
     * Start.
     */
    public void start() {
        minSize= AudioRecord.getMinBufferSize(8000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        ar = new AudioRecord(MediaRecorder.AudioSource.MIC, 8000,AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT,minSize);
        ar.startRecording();
    }

    /**
     * Stop.
     */
    public void stop() {
        if (ar != null) {
            ar.stop();
        }
    }

    /**
     * Gets amplitude.
     *
     * @return the amplitude
     */
    public double getAmplitude() {
        short[] buffer = new short[minSize];
        ar.read(buffer, 0, minSize);
        int max = 0;
        for (short s : buffer)
        {
            if (Math.abs(s) > max)
            {
                max = Math.abs(s);
            }
        }
        return max;
    }

}
