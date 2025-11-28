package model;

import java.util.Arrays;

import transforms.Point3D;

public class Tetrahedron extends Solid implements Transformable {
    
    public Tetrahedron() {
        vertexBuffer.addAll(Arrays.asList(
            new Point3D( 1,  1,  1),
            new Point3D(-1, -1,  1),
            new Point3D(-1,  1, -1),
            new Point3D( 1, -1, -1)
        ));

        indexBuffer.addAll(Arrays.asList(
            0, 1,
            0, 2,
            0, 3,
            1, 2,
            1, 3,
            2, 3
        ));
    }
}
