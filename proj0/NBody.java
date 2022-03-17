public class NBody {

    public static double readRadius (String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets (String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        double R = in.readDouble();
        Planet [] Planets = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return Planets;
    }

    public static void main(String[] args) {
        double T =  Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double R = readRadius(filename);
        Planet [] Planets = readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-R, R);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p : Planets) {
            p.draw();
        }

        int n = Planets.length;

        int time = 0;
        for (int t = 0; t <= T; t += dt) {
            double[] xForces = new double[n];
            double[] yForces = new double[n];

            for (int i = 0; i < n; i++) {
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }

            for (int i = 0; i < n; i++) {
                Planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : Planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            StdOut.printf("%d\n", Planets.length);
            StdOut.printf("%.2e\n", R);
            for (int i = 0; i < Planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                        Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
            }
        }
    }
}