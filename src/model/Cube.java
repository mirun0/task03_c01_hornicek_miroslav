package model;

import java.util.Arrays;

import transforms.Point3D;

public class Cube extends Solid implements Transformable {
    
    public Cube() {
        vertexBuffer.addAll(Arrays.asList(
            new Point3D( 1, -1, -1),
            new Point3D( 1, -1,  1),
            new Point3D(-1, -1,  1),
            new Point3D(-1, -1, -1),
            new Point3D( 1,  1, -1),
            new Point3D( 1,  1,  1),
            new Point3D(-1,  1,  1),
            new Point3D(-1,  1, -1)
        ));

        indexBuffer.addAll(Arrays.asList(
            0, 1,
            1, 2,
            2, 3,
            3, 0,
            4, 5,
            5, 6,
            6, 7,
            7, 4,
            0, 4,
            1, 5,
            2, 6,
            3, 7
        ));
    }
}
