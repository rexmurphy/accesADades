/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ex1;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author joan
 */
public class Eac1 {

    /**
     * Mètode principal del programa
     *
     * @param args els arguments especificats en l'enunciat de l'EAC
     */
    public static void main(String[] args) {
        
        //Control the number of parameter in console
        
        if(args.length<4 || args.length>5){
                System.err.println("Error: The number of parameters must be four or five");
                System.exit(2);
            }
        
        String copyOrMove = args[0].toLowerCase();
        String destinyFolder=args[1];
        String typeFilterFile=args[2].toLowerCase();
        String typeFilterFileValue=args[3];
        boolean includeHidden=(args.length==5 && args[4].equalsIgnoreCase("h"));
        
        File destinationFolder= new File(destinyFolder);
        
        /********************************** ERROR CONTROL*******************************************/
        
        
        if(!copyOrMove.equals("c")&&!copyOrMove.equals("m")){
            System.err.println("Error: The first parameter must be: 'c' (copy) or 'm' (move) ");
            System.exit(2);
        }
        
        
        if(!destinationFolder.isDirectory()){
            System.err.println("Error: The second parameter is not a valid directory");
        }
        
        if(!destinationFolder.canWrite()){
            System.err.println("Error: No write permission for the destination folder");
            System.exit(2);
        }
        
        if(!typeFilterFile.equals("m")&&!typeFilterFile.equals("n")){
            System.err.println("Error: The third parameter must be 'm' (size) or 'n' (name)");
            System.exit(2);
        }  
        long maxSize=0;
        if(typeFilterFile.equals("m")){
            try{
                maxSize = Long.parseLong(typeFilterFileValue);
            }catch(NumberFormatException e){
                System.err.println("Error: The fourth parameter must be an integer when using 'm");
            }
        }
        
        if(args.length==5&&!args[4].equalsIgnoreCase("h")){
            System.err.println("Error: Fifth parameter must be 'h");
            System.exit(2);
        }
        
        /********************** CREATE THE FILTER *********************************/
        
        String workspacePath=System.getProperty("user.dir");     
        File workspaceFolder= new File(workspacePath);
        
        //We need 'final' variables for the lamda
        final long finalMaxSize=maxSize;
        final String finalTypeFilterFile= typeFilterFile;
        final String finalTypeFilterFileValue= typeFilterFileValue;
        final boolean finalIncludeHidden= includeHidden;
        
        FileFilter filterFile = (File f) -> {
            //Check is the given folder adress  is actually a folder
            if (f.isDirectory()) return false;
            //Check if file is hidden and user want to include it in the copy or move action
            if(f.isHidden()&&!finalIncludeHidden) return false;
            //Check the filter option to apply: "by size" or "starts with"
            if(finalTypeFilterFile.equals("m")){
                return f.length() <= finalMaxSize;
            }else{
                return f.getName().toLowerCase().startsWith(finalTypeFilterFileValue);
            }
        };
        
        /**********************APPLY THE FILTER****************************************/
        
        File[] selectedFiles = workspaceFolder.listFiles(filterFile);
        
        
        /**********************EXECUTE: COPY OR MOVE FILES*****************************************/
        
        int count = 0;
        long totalSize=0;
        
        if(selectedFiles != null){
            for (File f: selectedFiles){
                //Check if file have read permissions and skipping from processs if is the case
                if(!f.canRead()){
                    System.out.println("File " +f.getName() + "cannot be read. Skipping");
                    continue;
                }
                
                try{
                    File destinationFile= new File(destinationFolder,f.getName());
                    
                    if (copyOrMove.equals("c")){
                        Files.copy(f.toPath(),destinationFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
                    }
                    else {
                        Files.move(f.toPath(),destinationFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
                    }
                    
                    count++;
                    totalSize+= f.length();
                    
                }catch(IOException e){
                    System.err.println("Error processing "+ f.getName() + ": "+ e.getMessage());
                }
            }
        }
        
        /************************PRINT SUMMARY*****************************************************/
        
        String operationName = copyOrMove.equals("c") ? "copied" : "moved";
        System.out.println("\n" + count + " files " + operationName + ", " + totalSize + " B.");
        
                 
        
    }    
}