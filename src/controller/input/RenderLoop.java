package controller.input;

import controller.Controller3D;

public class RenderLoop implements Runnable {

    private Controller3D controller;
    private boolean running = true;
    private final int targetFPS = 60;

    Thread loopThread;

    public RenderLoop(Controller3D controller) {
        this.controller = controller;
        loopThread = new Thread(this);
    }

    public void start() {
        loopThread.start();
    }

    @Override
    public void run() {
        final double frameTime = 1_000_000_000.0 / targetFPS;
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            controller.update(deltaTime);

            while (System.nanoTime() - lastTime < frameTime) {
                Thread.onSpinWait();
            }
        }
    }
}
