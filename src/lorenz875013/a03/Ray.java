package lorenz875013.a03;

import cgtools.Vec3;

public class Ray {
    Vec3 origin;
    Vec3 normDirection;
    double min;
    double max;

    public Ray(Vec3 origin, Vec3 normDirection, double min, double max){
        this.origin = origin;
        this.normDirection = Vec3.normalize(normDirection);
        this.min = min;
        this.max = max;
    }

    public Vec3 pointAt(double t){
        return origin.add(Vec3.multiply(t, normDirection));
    }
}