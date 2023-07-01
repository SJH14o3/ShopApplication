package source.application;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class RedCircleThread extends Thread{
    private final Circle circle;
    private final FadeTransition fade = new FadeTransition();
    @Override
    public void run() {
        fade.setNode(circle);
        fade.setDuration(Duration.millis(3000));
        fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setAutoReverse(true);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
    public void stopFading() {
        fade.stop();
        circle.setVisible(false);
        stop();
    }
    public RedCircleThread(Circle circle) {
        this.circle = circle;
    }
}
