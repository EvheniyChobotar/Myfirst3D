package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import static org.lwjgl.opengl.GL11.*;
import ua.edu.cdu.pm3.ChobotarEV.rendering.util.SkyDomeVertex;

public class SkyDome {
    public static int skyList;
    int             radius  = 186;
    final double    radians = Math.PI / 180;
    final double    degrees = 180 / Math.PI;
    double          dPi     = 5.0,
                    dTheta  = 5.0;
//we are using strips, to connect the dots, and each strip is made up of 4 vertices,
//we need to multiply that result by 4 to get the actual number    
    int             numOfVertices =(int) ((360/dTheta)*(90/dPi)*4);
    public SkyDomeVertex   Vertices;
    double x, y, z;
/*
    start by converting the 3D point P to spherical coordinates
    We can find θ with some trigonometry: tan(θ) = x / z -> θ = tan -1 (x/z)
    Now that we have θ we see that it varies from 0..2π, but for the texture map we need values
    from 0..1, we can get them by dividing θ by 2π
    To get φ we can
    make a vector from the point on the sphere to the origin, this is easily done since the vector is
    the point itself, we then normalize the vector to get the unit vector. The Y component of this
    vector is sin(φ), so we get φ by getting the arcsin and scaling it to the 0..1 range, this gives us
    the V coordinate we need.
*/
    public void drawDome() {
        double  vx,
                vy,
                vz,
                magnitude;
//        glPolygonMode (GL_FRONT_AND_BACK, GL_LINE);
        skyList = glGenLists(3);
        glNewList(skyList, GL_COMPILE);
        Textures.textureSky.bind();
        glBegin(GL_TRIANGLE_STRIP);
        for(int i = 0; i<numOfVertices;i++) {
            glColor3f(1f,1f,1f);
            vx = Vertices.x[i];
            vy = Vertices.y[i];
            vz = Vertices.z[i];
            
            magnitude = (float)Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2) + Math.pow(vz, 2));
            
            vx/=magnitude;
            vy/=magnitude;
            vz/=magnitude;
            
//          We add 0.5 to scale U and V so they fall within the 0 and 1 range we need.  
            Vertices.u[i] = (float)(Math.atan2(vx, vz)/(Math.PI*2))+0.5f;
            Vertices.v[i] = (float)(Math.asin(vy)/Math.PI) + 0.5f;
            
            glTexCoord2d(Vertices.getU(i), Vertices.getV(i));
            glVertex3d (Vertices.getX(i),Vertices.getZ(i),Vertices.getY(i));
        }
        
        glEnd();
        glEndList();
    }
    
    public void calculateDomeCoordinates() {
 
/*      calculation of each point of the sphere
        ρ , ø
        ρ + ∆ρ, ø
        ρ, ø + ∆ø 
        ρ +∆ρ, ø + ∆ø
*/
        Vertices = new SkyDomeVertex(numOfVertices);
        int i=0;
        for (double pi = 0.0; pi <= 90.0-dPi; pi += dPi) 
            for (double theta = 0; theta <= 360.0-dTheta; theta += dTheta) {
                
                Vertices.x[i] = radius * Math.sin(pi*radians) * Math.cos(radians*theta);
                Vertices.y[i] = radius * Math.sin(pi*radians) * Math.sin(radians*theta);
                Vertices.z[i] = radius * Math.cos(pi*radians);
                
                i++;
                Vertices.x[i] = radius * Math.sin((pi+dPi)*radians) * Math.cos(theta*radians);
                Vertices.y[i] = radius * Math.sin((pi+dPi)*radians) * Math.sin(theta*radians);
                Vertices.z[i] = radius * Math.cos((pi+dPi)*radians);
                
                i++;
                Vertices.x[i] = radius * Math.sin(radians*pi) * Math.cos(radians*(theta+dTheta));
                Vertices.y[i] = radius * Math.sin(radians*pi) * Math.sin(radians*(theta+dTheta));
                Vertices.z[i] = radius * Math.cos(radians*pi);
                i++;
                
                Vertices.x[i] = radius * Math.sin((pi+dPi)*radians) * Math.cos(radians*(theta+dTheta));
                Vertices.y[i] = radius * Math.sin((pi+dPi)*radians) * Math.sin(radians*(theta+dTheta));
                Vertices.z[i] = radius * Math.cos((pi+dPi)*radians);
                i++;
            }
    }
}
