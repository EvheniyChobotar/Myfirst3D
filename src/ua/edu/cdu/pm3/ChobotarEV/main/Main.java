package ua.edu.cdu.pm3.ChobotarEV.main;

import ua.edu.cdu.pm3.ChobotarEV.managment.Management;

public class Main {
//  parameters of main window 
    public static final int     WIDTH   = 1360 ,
                                HEIGHT  = 768;
    public static final String  TITLE   = "SoM";
    
    public static void main(String[] args) {
//      starting run system
        Management  manage = new Management();
        manage.initialize();
    }
}
