package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Textures;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import ua.edu.cdu.pm3.ChobotarEV.rendering.util.SkyDomeVertex;

public class SkyDome extends Textures{
    public static int   skyList;
    public static int   cloudsList;
    public int          domeRadius   =  250;
    public final double radians = Math.PI / 180;
    public final double degrees = 180 / Math.PI;
    public double       dPi     = 5.0,
                        dTheta  = 5.0;
//we are using strips, to connect the dots, and each strip is made up of 4 domeVertices,
//we need to multiply that result by 4 to get the actual number    
    public int          numOfVertices =(int) ((180/dTheta)*(180/dPi)*4);
    public SkyDomeVertex domeVertices;
    public SkyDomeVertex cloudsVertices;
    public double x, y, z;
    public static float rotation;
    

    public SkyDome() {
         calculateDomeCoordinates(domeVertices,domeRadius);
         skyList = drawDome(domeVertices,textureSky,3);
         calculateDomeCoordinates(cloudsVertices,domeRadius-5);
         cloudsList = drawDome(cloudsVertices,textureClouds,4);
    }

    
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
    public int drawDome(SkyDomeVertex vertices,Texture texture,int numList) {
        int list = glGenLists(numList);
        glNewList(list, GL_COMPILE);
        glBindTexture(GL_TEXTURE_2D,texture.getTextureID());
        
        glBegin(GL_TRIANGLE_STRIP);
        
        for(int i = 0; i<numOfVertices;i++) {
            glTexCoord2d(vertices.getU(i), vertices.getV(i));
            glVertex3d (vertices.getX(i), vertices.getY(i),vertices.getZ(i));
        }
        glEnd();
        glEndList();
        return list;
    }
    
    public void check(SkyDomeVertex vertices) {
        for (int i=0; i < numOfVertices-2; i++) {
            if (vertices.getU(i) - vertices.getU(i+1) > 0.9f)
                vertices.u[i+1] += 1.0f;
            
            if (vertices.getU(i+1)  - vertices.getU(i) > 0.9f)
                vertices.u[i] += 1.0f;
            
            if (vertices.getU(i) - vertices.getU(i+2) > 0.9f)
                vertices.u[i+2] += 1.0f;
            
            if (vertices.getU(i+2) - vertices.getU(i) > 0.9f)
                vertices.u[i] += 1.0f;
            
            if (vertices.getU(i+1) - vertices.getU(i+2)> 0.9f)
               vertices.u[i+2] += 1.0f;
            
            if (vertices.getU(i+2) - vertices.getU(i+1) > 0.9f)
                vertices.u[i+1] += 1.0f;
            
            if (vertices.getV(i) - vertices.getV(i+1) > 0.8f)
                vertices.v[i+1] += 1.0f;
            
            if (vertices.getV(i+1)- vertices.getV(i) > 0.8f)
                vertices.v[i] += 1.0f;
            
            if (vertices.getV(i) - vertices.getV(i+2) > 0.8f)
                vertices.v[i+2] += 1.0f;
            
            if (vertices.getV(i+2) - vertices.getV(i) > 0.8f)
                vertices.v[i] += 1.0f;
            
            if (vertices.getV(i+1) - vertices.getV(i+2) > 0.8f)
                vertices.v[i+2] += 1.0f;
            
            if (vertices.getV(i+2) - vertices.getV(i+1) > 0.8f)
                vertices.v[i+1] += 1.0f;
        }
        
    }
    
    public void calculateDomeCoordinates(SkyDomeVertex vertices,int radius) {
 
/*      calculation of each point of the sphere
        ρ , ø
        ρ + ∆ρ, ø
        ρ, ø + ∆ø 
        ρ +∆ρ, ø + ∆ø
*/
        this.domeVertices = new SkyDomeVertex(numOfVertices);
        int i=0;
        for (double pi = 0.0; pi < 180.0; pi += dPi) 
            for (double theta = 0; theta < 180.0; theta += dTheta) {
                
                this.domeVertices.x[i] = radius * Math.sin(pi*radians) * Math.cos(radians*theta);
                this.domeVertices.y[i] = radius * Math.sin(pi*radians) * Math.sin(radians*theta);
                this.domeVertices.z[i] = radius * Math.cos(pi*radians);
                
                i++;
                this.domeVertices.x[i] = radius * Math.sin((pi+dPi)*radians) * Math.cos(theta*radians);
                this.domeVertices.y[i] = radius * Math.sin((pi+dPi)*radians) * Math.sin(theta*radians);
                this.domeVertices.z[i] = radius * Math.cos((pi+dPi)*radians);
                
                i++;
                this.domeVertices.x[i] = radius * Math.sin(radians*pi) * Math.cos(radians*(theta+dTheta));
                this.domeVertices.y[i] = radius * Math.sin(radians*pi) * Math.sin(radians*(theta+dTheta));
                this.domeVertices.z[i] = radius * Math.cos(radians*pi);
                i++;
                
                this.domeVertices.x[i] = radius * Math.sin((pi+dPi)*radians) * Math.cos(radians*(theta+dTheta));
                this.domeVertices.y[i] = radius * Math.sin((pi+dPi)*radians) * Math.sin(radians*(theta+dTheta));
                this.domeVertices.z[i] = radius * Math.cos((pi+dPi)*radians);
                i++;
        }
        
        for(i=0;i<numOfVertices;i++) {
            
            double vx = this.domeVertices.x[i];
            double vy = this.domeVertices.y[i];
            double vz = this.domeVertices.z[i];
            
            double magnitude = (float)Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2) + Math.pow(vz, 2));
            vx/=magnitude;
            vy/=magnitude;
            vz/=magnitude;
//          We add 0.5 to scale U and V so they fall within the 0 and 1 range we need.  
            this.domeVertices.u[i] = (float)(Math.atan2(vx, vz)/(Math.PI*2))+0.5f;
            this.domeVertices.v[i] = (float)(Math.asin(vy)/Math.PI) + 0.5f;
        }
        check(vertices);
    }
    
    public static void rotation() {
        rotation += 0.01f;
        glRotatef(rotation, 0, 1, 0);
    }
    
}
