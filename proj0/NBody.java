
public class NBody {
    public static double readRadius(String url){
        In in = new In(url);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readBody(String url){
        In in = new In(url);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        for(int i = 0; i < num; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        /*
        Store the 0th and 1st command line arguments as doubles named T and dt.
        Store the 2nd command line argument as a String named filename.
        Read in the bodies and the universe radius
        */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readBody(filename);

        StdDraw.setScale(-radius,radius);
        StdDraw.enableDoubleBuffering();

        double t = 0;
        int num = planets.length;

        while (t < T){
            double[] xForce = new double[num];
            double[] yForce = new double[num];
            for(int i = 0; i<num; i++){
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i<num; i++){
                planets[i].update(dt, xForce[1], yForce[i]);
            }

            /*
            Draw the background image.
             */
            StdDraw.picture(0, 0, "./images/starfield.jpg");

            /*
            Draw all of the planets
             */
            for (Planet planet : planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            t+=dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}


