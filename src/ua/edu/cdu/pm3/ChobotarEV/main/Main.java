package ua.edu.cdu.pm3.ChobotarEV.main;

import ua.edu.cdu.pm3.ChobotarEV.managment.Management;

public class Main {
//  parameters of main window 
    public static final int     WIDTH   = 1350 ,
                                HEIGHT  = 760;
    public static final String  TITLE   = "SoM";
    
    public static void main(String[] args) {
//      starting powerfull run system
        Management  manage = new Management();
        manage.initialize();
    }
}