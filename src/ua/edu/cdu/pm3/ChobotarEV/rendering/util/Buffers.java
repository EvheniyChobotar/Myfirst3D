package ua.edu.cdu.pm3.ChobotarEV.rendering.util;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

public class Buffers {

    public static FloatBuffer toFloatBuffer(float f1,float f2,float f3,float f4){
        float[] data = new float[]{f1,f2,f3,f4};
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(4);
        floatBuffer.put(data);
        floatBuffer.flip();
        return  floatBuffer;
    }
}
