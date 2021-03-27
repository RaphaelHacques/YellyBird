package fr.iut.yellybird.game;
import fr.iut.yellybird.game.GameView;
import fr.iut.yellybird.models.SoundMeter;
public class VolumeThread extends Thread{
    static final long FPS = 100;
    private GameView view;
    private SoundMeter micro;
    private boolean running = false;
    public VolumeThread(GameView view){
        this.view = view;
        this.micro = new SoundMeter();
    }
    public void setRunning(boolean run) {
        running = run;
        if(run) micro.start();
        else micro.stop();
    }
    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime = 0;
        long sleepTime;
        long lastJump = 0;
        while (running) {
            startTime = System.currentTimeMillis();
            if (!view.isGameOver() && System.currentTimeMillis() - lastJump > 50) {
                if (  micro.getAmplitude() > 3000 ) {
                    lastJump = System.currentTimeMillis();
                    view.flyBird();
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {
            }
        }
    }
}