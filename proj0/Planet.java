import java.lang.Math;
import java.util.Random;

public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static final double G = 6.67e-11;

    /*
    constructor
    */
    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /*
    Your Body class should NOT have a main method, because we’ll never run the Body class directly.
    All methods should be non-static.
     */
    public double calDistance(Planet b){
        /*
        there is no built-in operator that does squaring or exponentiation.
        import Math class
         */
        return Math.sqrt((xxPos - b.xxPos)*(xxPos - b.xxPos) + (yyPos - b.yyPos)*(yyPos - b.yyPos));
    }

    public double calcForceExertedBy(Planet b){
        double r = calDistance(b);
        return  (G*mass*b.mass)/(r * r);
    }

    public double calForceExertedByX(Planet b){
        double F = calcForceExertedBy(b);
        double r = calDistance(b);
        double dx = b.xxPos - xxPos;
        return F*dx/r;
    }

    public double calForceExertedByY(Planet b){
        double F = calcForceExertedBy(b);
        double r = calDistance(b);
        double dy = b.yyPos - yyPos;
        return F*dy/r;
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double totalFx = 0;
        for (Planet planet : planets){
            if (this.equals(planet)){
                continue;}
            totalFx += calForceExertedByX(planet);
        }
        return totalFx;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double totalFy = 0;
        for (Planet planet : planets){
            if (this.equals(planet)){
                continue;}
            totalFy += calForceExertedByY(planet);
        }
        return totalFy;
    }
    
    public void update(double dt, double fX, double fY){
        double ax = fX/mass; /* accelerate rate in x component*/
        double ay = fY/mass; /* accelerate rate in y component*/
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+ imgFileName);
    }
}


