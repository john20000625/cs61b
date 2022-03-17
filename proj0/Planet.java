public class Planet  {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet s) {
        return Math.sqrt(Math.pow(this.xxPos - s.xxPos, 2) +
                Math.pow(this.yyPos - s.yyPos, 2));
    }

    public double calcForceExertedBy(Planet s) {
        double distance = this.calcDistance(s);
        return G * this.mass * s.mass / Math.pow(distance,2);
    }

    public double calcForceExertedByX(Planet s) {
        double distance = this.calcDistance(s);
        double f = calcForceExertedBy(s);
        return f * (s.xxPos - this.xxPos) / distance;
    }

    public double calcForceExertedByY(Planet s) {
        double distance = this.calcDistance(s);
        double f = calcForceExertedBy(s);
        return f * (s.yyPos - this.yyPos) / distance;
    }

    public double calcNetForceExertedByX (Planet[] allPlanets) {
        double netF = 0;
        for (Planet p : allPlanets) {
            if (p.equals(this)) {
                continue;
            } else {
                netF += this.calcForceExertedByX(p);
            }
        }
        return netF;
    }

    public double calcNetForceExertedByY (Planet[] allPlanets) {
        double netF = 0;
        for (Planet p : allPlanets) {
            if (p.equals(this)) {
                continue;
            } else {
                netF += this.calcForceExertedByY(p);
            }
        }
        return netF;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + aX * dt;
        this.yyVel = this.yyVel + aY * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}