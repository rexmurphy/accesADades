 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eac1.ex1.video;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author ioc
 */
public class EAC1Ex1Video {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String a = args[0];
        String b = args[1];
        if (a.equals(b)) {
            System.out.println("son iguals");
        }else{
            System.out.println("Son diferents");
        }


        File f = new File("/home/osboxes/");
        
        FileFilter filter = (File arxiuSeleccionat) -> {
            return (arxiuSeleccionat.getName().contains("x") || arxiuSeleccionat.getName().contains("X"));//
        };
        
        File[] llistaFiles = f.listFiles(filter);
        for(File file : llistaFiles){
            System.out.println(file.getAbsolutePath());
        }
    }

}
