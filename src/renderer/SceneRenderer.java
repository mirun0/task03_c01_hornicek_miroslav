package renderer;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import controller.input.InputState;
import controller.mode.Action;
import model.Solid;
import model.Transformable;
import rasterize.LineRasterizerTrivial;
import rasterize.Raster;
import transforms.Mat4;
import transforms.Point2D;
import transforms.Point3D;
import transforms.Vec3D;
import utils.MathUtils;
import world.Scene3D;

public class SceneRenderer {

    private final Raster raster;
    private LineRasterizerTrivial lineRasterizer;
    private double[][] zBuffer;

    private boolean trivialClip;

    private Solid selectedSolid;
    private Solid activeSolid;

    private InputState input;

    public SceneRenderer(Raster raster, InputState input) {
        this.raster = raster;
        this.lineRasterizer = new LineRasterizerTrivial(raster);
        zBuffer = new double[raster.getWidth()][raster.getHeight()];
        this.input = input;
    }


    public void renderScene(Scene3D scene) {
        for (int x = 0; x < raster.getWidth(); x++) {
            for (int y = 0; y < raster.getHeight(); y++) {
                zBuffer[x][y] = Double.POSITIVE_INFINITY;
            }
        }
        boolean solidChanged = false;
        for (Solid solid : scene.getSolids()) {
            lineRasterizer.setColor(solid.getColor());
            Mat4 mvp = solid.getTransform()
                .mul(scene.getCamera().getViewMatrix())
                .mul(scene.getProjection());

            for (int i = 0; i < solid.getIndexBuffer().size(); i += 2) {
                int i1 = solid.getIndexBuffer().get(i);
                int i2 = solid.getIndexBuffer().get(i + 1);

                Point3D p1 = solid.getVertexBuffer().get(i1).mul(mvp);
                Point3D p2 = solid.getVertexBuffer().get(i2).mul(mvp);

                if(trivialClip) {
                    if(!isInsideClipVolume(p1) || !isInsideClipVolume(p2)) {
                        continue;
                    }
                } else {
                    Point3D[] line = {p1, p2};
                    if (!clipLine(line)) continue;
                    p1 = line[0];
                    p2 = line[1];
                }
                
                Vec3D n1 = toNDC(p1);
                Vec3D n2 = toNDC(p2);

                Point2D s1 = toScreen(n1);
                Point2D s2 = toScreen(n2);

                if(Action.POINT_SELECTION.isOn()) {
                    if(closeToPoint(s1, input.getMouseX(), input.getMouseY()) || closeToPoint(s2, input.getMouseX(), input.getMouseY())) {
                        if(solid instanceof Transformable && !solidChanged) {
                            activeSolid = solid;
                            solidChanged = true;
                        }
                    } else if(!solidChanged) {
                        activeSolid = null;
                    }

                    if(input.isButtonDown(MouseEvent.BUTTON1) && selectedSolid == null && activeSolid != null) {
                        selectedSolid = activeSolid;
                        selectedSolid.setScale(2);
                    }

                    if(input.isKeyDown(KeyEvent.VK_ESCAPE) && selectedSolid != null) {
                        selectedSolid = null;
                    }

                    if(activeSolid != null) {
                        drawPoint(s1, solid.getColor());
                        drawPoint(s2, solid.getColor());
                    }

                    if(selectedSolid != null && selectedSolid == solid) {
                        lineRasterizer.setColor(0xFFFF00);
                    }
                }

                lineRasterizer.rasterize(
                    (int)s1.getX(), (int)s1.getY(), n1.getZ(), 
                    (int)s2.getX(), (int)s2.getY(), n2.getZ(), zBuffer);

                lineRasterizer.setColor(solid.getColor());
            }
            solidChanged = false;
        }
    }

    private void drawPoint(Point2D point, int color) {
        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 2; dy++) {
                raster.setPixel((int)point.getX() + dx, (int)point.getY() + dy, color);
            }
        }
    }

    public static boolean closeToPoint(Point2D point, double x, double y) {
        double l = MathUtils.length(x, y, point.getX(), point.getY());
        return l <= 10;
    }

    private boolean isInsideClipVolume(Point3D p) {
        double x = p.getX(), y = p.getY(), z = p.getZ(), w = p.getW();
        return (-w <= x && x <= w) && (-w <= y && y <= w) && (0 <= z && z <= w);
    }

    private Vec3D toNDC(Point3D p) {
        return new Vec3D(p.getX() / p.getW(), p.getY() / p.getW(), p.getZ() / p.getW());
    }

    private Point2D toScreen(Vec3D ndc) {
        double sx = (ndc.getX() + 1) * 0.5 * raster.getWidth();
        double sy = (1 - ndc.getY()) * 0.5 * raster.getHeight();
        return new Point2D(sx, sy);
    }

    // liang–barsky algorithm
    private boolean clipLine(Point3D[] line) {
        Point3D a = line[0];
        Point3D b = line[1];

        double t0 = 0.0;
        double t1 = 1.0;

        double[] p = {
            -(b.getX() - a.getX()) - (b.getW() - a.getW()), // x >= -w
            (b.getX() - a.getX()) - (b.getW() - a.getW()), // x <= w
            -(b.getY() - a.getY()) - (b.getW() - a.getW()), // y >= -w
            (b.getY() - a.getY()) - (b.getW() - a.getW()), // y <= w
            -(b.getZ() - a.getZ()),                         // z >= 0
            (b.getZ() - a.getZ()) - (b.getW() - a.getW())  // z <= w
        };

        double[] q = {
            a.getX() + a.getW(),  // x >= -w
            a.getW() - a.getX(),  // x <= w
            a.getY() + a.getW(),  // y >= -w
            a.getW() - a.getY(),  // y <= w
            a.getZ(),             // z >= 0
            a.getW() - a.getZ()   // z <= w
        };

        for (int i = 0; i < 6; i++) {
            double Pi = p[i];
            double Qi = q[i];

            if (Pi == 0 && Qi < 0)
                return false;

            double t = Qi / Pi;

            if (Pi < 0) {
                if (t > t1) return false;
                if (t > t0) t0 = t;
            } else if (Pi > 0) {
                if (t < t0) return false;
                if (t < t1) t1 = t;
            } else if (Qi < 0) {
                return false;
            }
        }

        if (t1 < t0) return false;

        Point3D newA = new Point3D(
            a.getX() + (b.getX() - a.getX()) * t0,
            a.getY() + (b.getY() - a.getY()) * t0,
            a.getZ() + (b.getZ() - a.getZ()) * t0,
            a.getW() + (b.getW() - a.getW()) * t0
        );

        Point3D newB = new Point3D(
            a.getX() + (b.getX() - a.getX()) * t1,
            a.getY() + (b.getY() - a.getY()) * t1,
            a.getZ() + (b.getZ() - a.getZ()) * t1,
            a.getW() + (b.getW() - a.getW()) * t1
        );

        line[0] = newA;
        line[1] = newB;

        return true;
    }

    public void setClipping(boolean clipping) {
        trivialClip = clipping ? false : true;
    }
}
