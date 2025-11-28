package controller;

import transforms.Camera;
import transforms.Mat4;
import transforms.Vec3D;

public class Camera3D {
    private Camera camera;
    private double cameraSpeed;
    private double cameraSensitivity;

    public Camera3D(Vec3D position, double cameraSpeed, double cameraSensitivity) {
        this.camera = new Camera()
        .withPosition(position)
        .addAzimuth(Math.PI / 2)
        .withFirstPerson(true);  

        this.cameraSpeed = cameraSpeed;
        this.cameraSensitivity = cameraSensitivity;
    }

    public void forward(double deltaTime) {
        this.camera = camera.forward(cameraSpeed * deltaTime);
    }

    public void backward(double deltaTime) {
        this.camera = camera.backward(cameraSpeed * deltaTime);
    }

    public void left(double deltaTime) {
        this.camera = camera.left(cameraSpeed * deltaTime);
    }

    public void right(double deltaTime) {
        this.camera = camera.right(cameraSpeed * deltaTime);
    }

    public void up(double deltaTime) {
        this.camera = camera.up(cameraSpeed * deltaTime);
    }

    public void down(double deltaTime) {
        this.camera = camera.down(cameraSpeed * deltaTime);
    }

    public void look(double deltaX, double deltaY) {
        this.camera = camera
            .addAzimuth(deltaX * cameraSensitivity)
            .addZenith(deltaY * cameraSensitivity);
    }

    public Camera getCamera() {
        return camera;
    }

    public Mat4 getViewMatrix() {
        return camera.getViewMatrix();
    }

    public double getCameraSpeed() {
        return cameraSpeed;
    }

    public double getCameraSensitivity() {
        return cameraSensitivity;
    }
}
