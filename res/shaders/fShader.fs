varying vec3 N;
varying vec3 v;
#define MAX_LIGHTS 1 
void main (void)  
{  
   vec4 finalColour;

   for (int i=0; i<MAX_LIGHTS; i++)
   {
       vec3 L = normalize(gl_LightSource[i].position.xyz - v);   
       vec3 E = normalize(-v);
       vec3 R = normalize(-reflect(L,N));  
       vec4 Iamb = gl_FrontLightProduct[i].ambient;    
       vec4 Idiff = gl_FrontLightProduct[i].diffuse * max(dot(N,L), 0.0);
       Idiff = clamp(Idiff, 0.0, 1.0);     
       vec4 Ispec = gl_FrontLightProduct[i].specular 
                    * pow(max(dot(R,E),0.0),0.3*gl_FrontMaterial.shininess);
       Ispec = clamp(Ispec, 0.0, 1.0);
       finalColour += Iamb + Idiff + Ispec;
   }
   gl_FragColor = gl_FrontLightModelProduct.sceneColor + finalColour;
}
