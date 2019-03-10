package frc.robot.subsystems;

import java.util.ArrayList;
//import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Camera;
//import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import frc.robot.pixy2api.Pixy2CCC.Block;

public class TargetLargest {

    private static ArrayList<Block> blocks = Camera.getPixyCamera().getPixy().getCCC().getBlocks();
    private static final int blockSignature = 1;
    public static double largestX;
    public static double secondLargestX;
    public static double theCount;
        

    public static void run(int count) {
        theCount = count;
        if (count > 0) {
            Block largestBlock = null;
            Block secondLargestBlock = null;
            
            // Checks for Biggest Block that is Signature 1
            for (Block block : blocks) {
                if (block.getSignature() == blockSignature) {
                    if (largestBlock == null) {
                        largestBlock = block;
                    } else if (block.getWidth() > largestBlock.getWidth()) {
                        largestBlock = block;
                    } else if (count > 1 && secondLargestBlock == null){
                        secondLargestBlock = block;
                    }else if (count > 1 && secondLargestBlock.getWidth() < largestBlock.getWidth()){
                        secondLargestBlock = block;
                    }
                    
                }
            }

            //int ballX = largestBlock.getX();
            //double yaw = ((ballX - 157.5) * 0.1904761905);
            //Dashboard.getInstance().putNumber(false, "Ball Angle", yaw);
            
          
            SmartDashboard.putNumber("Target 1 X", largestBlock.getX());
            largestX=largestBlock.getX();
            SmartDashboard.putNumber("Target 1 Y", largestBlock.getY());
            SmartDashboard.putNumber("Target 1 Box Width", largestBlock.getWidth());
            SmartDashboard.putNumber("Target 1 Box Height", largestBlock.getHeight());
            if (secondLargestBlock != null){
                SmartDashboard.putNumber("Target 2 X", secondLargestBlock.getX());
                secondLargestX=secondLargestBlock.getX();
                SmartDashboard.putNumber("Target 2 Y", secondLargestBlock.getY());
                SmartDashboard.putNumber("Target 2 Box Width", secondLargestBlock.getWidth());
                SmartDashboard.putNumber("Target 2 Box Height", secondLargestBlock.getHeight());
            }
        }
        





       /* else if (count > 1) {
            Block largestBlock = null;
            Block secondLargestBlock = null;
            // Checks for Biggest Block that is of the Orange Color Signature
            for (Block block : blocks) {
                if (block.getSignature() == blockSignature) {
                    if (largestBlock == null) {
                        largestBlock = block;
                    } else if (block.getWidth() > largestBlock.getWidth()) {
                        largestBlock = block;
                    } else if (secondLargestBlock == null){
                        secondLargestBlock = block;
                    } else if (block.getWidth() > secondLargestBlock.getWidth()){
                        secondLargestBlock = block;
                    }
                }
            }

            //int ballX = largestBlock.getX();
            //double yaw = ((ballX - 157.5) * 0.1904761905);
            //Dashboard.getInstance().putNumber(false, "Ball Angle", yaw);
            
          
            SmartDashboard.putNumber("Target 1 X", largestBlock.getX());
            SmartDashboard.putNumber("Target 1 Y", largestBlock.getY());
            SmartDashboard.putNumber("Target 1 Box Width", largestBlock.getWidth());
            SmartDashboard.putNumber("Target 1 Box Height", largestBlock.getHeight());
            SmartDashboard.putNumber("Target 2 X", secondLargestBlock.getX());
            SmartDashboard.putNumber("Target 2 Y", secondLargestBlock.getY());
            SmartDashboard.putNumber("Target 2 Box Width", secondLargestBlock.getWidth());
            SmartDashboard.putNumber("Target 2 Box Height", secondLargestBlock.getHeight());
        }*/


        SmartDashboard.putBoolean("Target 1 Detected", count>0);
        SmartDashboard.putBoolean("Target 2 Detected", count>1);
        

    }

    public static double GetLargestX(){
        return largestX;
    }
    public static double GetSecondLargestX(){
        return secondLargestX;
    }
    
    public static double GetNumberOfTargets(){
        return theCount;
    }
    

}