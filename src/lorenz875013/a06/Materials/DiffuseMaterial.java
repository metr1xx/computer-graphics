package lorenz875013.a06.Materials;

import cgtools.Random;
import cgtools.Vec3;
import lorenz875013.a06.Main;
import lorenz875013.a06.RayTracer.Hit;
import lorenz875013.a06.RayTracer.Ray;

public class DiffuseMaterial implements Material {
    Vec3 albedo;
    Vec3 emission;
    Ray scatteredRay;

    public DiffuseMaterial(Vec3 albedo, Vec3 emission){
        this.albedo = albedo;
        this.emission = emission;
    }

    public ReflectionProperties properties(Ray ray, Hit hit){
        /** create new ray from last intersect / hitpoint but with randomness factor for refraction **/
        double x = 2 * (Main.random.nextDouble() - 0.5);
        double y = 2 * (Main.random.nextDouble() - 0.5);
        double z = 2 * (Main.random.nextDouble() - 0.5);
        Vec3 ranVec = new Vec3(
                hit.normVec.x + x,
                hit.normVec.y + y,
                hit.normVec.z + z);
        scatteredRay = new Ray(hit.hitVec, ranVec, 0.0001, Double.POSITIVE_INFINITY);
        ReflectionProperties properties = new ReflectionProperties(albedo, emission, scatteredRay);
        return properties;
    }
}
