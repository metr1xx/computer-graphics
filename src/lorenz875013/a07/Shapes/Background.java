package lorenz875013.a07.Shapes;

import cgtools.Vec3;
import lorenz875013.a07.RayTracer.Hit;
import lorenz875013.a07.Materials.Material;
import lorenz875013.a07.RayTracer.Ray;

import static cgtools.Vec3.*;

public class Background implements Shape {
    Material material;

    public Background(Material material){
        this.material = material;
    }

    public Hit intersect(Ray r) {
        /** x vector **/
        Vec3 hitVec = r.pointAt(Double.POSITIVE_INFINITY);
        Vec3 hitNormVec = normalize(hitVec);
        Hit hit = new Hit(Double.POSITIVE_INFINITY, hitVec, hitNormVec, material);
        return hit;
    }
}
