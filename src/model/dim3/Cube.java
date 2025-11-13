package model.dim3;

import java.util.Arrays;

import transforms.Point3D;

public class Cube extends Solid {
    
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

        // cube pomoci trojuhelniku
        /*vertexBuffer.addAll(Arrays.asList(
            new Point3D( 0.5, -0.5, -0.5), // 0
            new Point3D( 0.5, -0.5,  0.5), // 1
            new Point3D(-0.5, -0.5,  0.5), // 2
            new Point3D(-0.5, -0.5, -0.5), // 3
            new Point3D( 0.5,  0.5, -0.5), // 4
            new Point3D( 0.5,  0.5,  0.5), // 5
            new Point3D(-0.5,  0.5,  0.5), // 6
            new Point3D(-0.5,  0.5, -0.5)  // 7
        ));

        indexBuffer.addAll(Arrays.asList(
            1, 2, 3,
            4, 7, 6,
            4, 5, 1,
            1, 5, 6,
            6, 7, 3,
            4, 0, 3,
            0, 1, 3,
            5, 4, 6,
            0, 4, 1,
            2, 1, 6,
            2, 6, 3,
            7, 4, 3
        ));*/
    }
}
