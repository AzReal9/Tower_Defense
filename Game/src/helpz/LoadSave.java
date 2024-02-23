
package helpz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class LoadSave {
    

    
    public static BufferedImage getSpriteAtlas() {
         
        BufferedImage img = null;
        
       InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("Sprites/spriteatlas.png"); //Finds the Path for the images
       try {       
           img = ImageIO.read(is);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return img;
    }
    
    //text file
    public static void createFile(){ 
        File textFile = new File("res/testFile.txt");
        
        try {
            textFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void CreateLvl(String name, int[] idArr){
         File newLevel = new File("res " + name + ".txt");
         
         if(newLevel.exists()){
             System.out.println("File " + name + " already exists");
             return;
         }else{
             try {
                 newLevel.createNewFile();
             } catch (IOException ex) {
                 Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             writeToFile(newLevel, idArr);
         }
    }
    
    
    private static void writeToFile(File f, int[] idArr) {
        
        try {
            PrintWriter pw = new PrintWriter(f);
            for(int i : idArr)
            pw.println(i);
            
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void ReadFromFile(){
        File textFile = new File("res/text.txt");
        try {
            Scanner sc = new Scanner(textFile);
            
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
