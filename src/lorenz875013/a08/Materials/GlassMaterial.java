package lorenz875013.a08.Materials;

import cgtools.Random;
import cgtools.Vec3;
import lorenz875013.a08.RayTracer.Hit;
import lorenz875013.a08.RayTracer.Ray;

import static cgtools.Vec3.*;

public class GlassMaterial implements Material {
    Vec3 albedo;
    Vec3 emission;
    double roughness;
    double refractionFactor;
    Ray reflectionRay;

    public GlassMaterial(Vec3 color, double roughness, double refractionFactor){
        this.albedo = color;
        this.emission = new Vec3(0,0,0);
        this.roughness = roughness;
        this.refractionFactor = refractionFactor;
    }

    public ReflectionProperties properties(Ray ray, Hit hit) {

        double ingoingRefr = 1.0;
        double outgoingRefr = this.refractionFactor;
        Vec3 rayNormVec = ray.normDirection;
        Vec3 hitNormVec = hit.normVec;

        if (dotProduct(rayNormVec, hitNormVec) > 0) {
            // Strahl kommt von innen
            hitNormVec = multiply(hitNormVec, -1);
            ingoingRefr = this.refractionFactor;;
            outgoingRefr = 1.0;
        }

        Vec3 dt;
        // Brechung findet statt
        if (Random.random() > schlick(rayNormVec, hitNormVec, ingoingRefr, outgoingRefr)) {
            //TransmissionsAnteil
            dt = refract(rayNormVec, hitNormVec, ingoingRefr, outgoingRefr);
        } else {
            // ReflexionsAnteil
            dt = reflect(rayNormVec, hitNormVec);
        }

        this.reflectionRay = new Ray(hit.hitVec, dt, 0.0001, Double.POSITIVE_INFINITY);
        ReflectionProperties properties = new ReflectionProperties(albedo, emission, this.reflectionRay);
        return properties;
    }

    static Vec3 refract(Vec3 d, Vec3 hitNormVec, double n1, double n2) {
        double r = n1 / n2;
        double c = -dotProduct(hitNormVec, d);
        double subsqrt = 1 - (r * r) * (1 - (c * c));
        if (subsqrt < 0) {
            // Totalreflexion;
            return reflect(d, hitNormVec);
        } else {
            return add(multiply(r, d), multiply((r * c) - Math.sqrt(subsqrt), hitNormVec));
        }
    }

    static double schlick(Vec3 d, Vec3 n, double n1, double n2) {
        double reflectionCoefficient = ((n1 - n2) / (n1 + n2)) * ((n1 - n2) / (n1 + n2));
        return reflectionCoefficient + (1 - reflectionCoefficient) * Math.pow((1 + dotProduct(d, n)), 5);
    }

    static Vec3 reflect(Vec3 d, Vec3 n) {
        return subtract(d, (multiply(2, multiply(dotProduct(d, n), n))));
    }
}
