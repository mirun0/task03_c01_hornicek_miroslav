package model.dim3;

import java.util.Arrays;

import transforms.Point3D;

public class Axis extends Solid {
    
    public Axis() {
        vertexBuffer.add(new Point3D( 0,  0,  0));
        vertexBuffer.add(new Point3D( 1,  0,  0));

        indexBuffer.addAll(Arrays.asList(0, 1));
    }
}
