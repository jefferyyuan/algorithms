package codebytes;
import java.awt.AWTException; 
import java.awt.Robot; 
import java.awt.event.InputEvent;

public class JRobot {
    Robot robot;
    public JRobot() throws AWTException{ 
        robot = new Robot();
        System.out.println(robot);
    } 
    
    public void type(String s){ 
        byte[] bytes = s.getBytes();

        for(byte b:bytes){
            if(b>96 && b<123) b-=32;
            robot.keyPress(b);
            robot.delay(200);
            robot.keyRelease(b);
        }
    }
    
    //Performing a right click
    public void rightClick(int x, int y){
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
  
    //Random cursor movement. yet to be reve
    public void testMouseMovement(){
        for(int i=0;i<200;++i){
            robot.mouseMove(i, 200); 
            robot.delay(5);
        }
    }
    
    //Scrolling down
    public void scrollDown(int amount){
        for(int i=0;i<amount;++i){
            robot.mouseWheel(i);
            robot.delay(500);
        }
    }
    
    //Getting pixel colors
    public void printColors(int x, int y){
        for(int i=0;i<200;++i,++x,++y){
            robot.mouseMove(x, y);
            System.out.print("\nPixel at "+x+", "+y+" has color ");
            System.out.print(robot.getPixelColor(x, y));
        }
    }
    
    public static void main(String[] args) throws AWTException{
        JRobot r = new JRobot();
        /*
          Delay before performing every action
          Can be added manually too (using robot.delay(int ms))
        */
        r.robot.setAutoDelay(5); 
        r.testMouseMovement();
        r.rightClick(200, 500);
        r.type("And the secret is... yet to be revealed");
        r.rightClick(590, 500);
        r.scrollDown(5); 
        r.printColors(0, 0);
    }
}