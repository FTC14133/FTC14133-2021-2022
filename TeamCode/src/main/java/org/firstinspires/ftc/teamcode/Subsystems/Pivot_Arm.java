package org.firstinspires.ftc.teamcode.Subsystems;

// Generic Lift

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Pivot_Arm {
    // Instantiate the drivetrain motor variables
    private DcMotorEx lift;
    public int position = 0; // Integer position of the arm





    //ToDo: Add encoder count amount (still undetermined, just put a number in now)
    //ToDo: Add stop and reset encoder in init(), and run using encoder. See FTC_14133_2021_Auto.java
    //ToDo: Add limit switch sensing for either extreme location and to reset to 0 on one side (Homing) or in the middle
    //ToDo: Create set-point positions for different arm positions (Intake, place low, place mid, place high, different sides)
        //This can likely be done with looking at different states and using the arrows, bumpers, or something else to move up the integer positions
        //Pseudo code:

    public Pivot_Arm(HardwareMap hardwareMap){                 // Motor Mapping
        lift = hardwareMap.get(DcMotorEx.class, "lift");      //Sets the names of the hardware on the hardware map
    // "DeviceName" must match the Config EXACTLY

        // Set motor direction based on which side of the robot the motors are on
        lift.setDirection(DcMotorEx.Direction.FORWARD);
        position=0; //initial arm position
    }

    public void Pivot_Arm_Telop(Gamepad gamepad2){ //Code to be run in Op Mode void Loop at top level


         if (gamepad2.dpad_up==true) {
             position = position + 1; //Increase Arm position
             //Todo: add a limiter here to stop it from going too high
         }
         else if(gamepad2.dpad_down==true){
             position = position -1;
             //Todo: Add a limiter here to stop it from going too low
         }



         switch (position) {
             case -3: // Intake Back
                 System.out.println("Position=1");
                 break;
             case -2: // Mid Level back
                 System.out.println("Position=2");
                 break;
             case -1: //Upper Level Back
                 System.out.println("Position=3");
                 break;
             case 0: //Straight Up
                 System.out.println("Position=0");
                 break;
             case 1: //Upper Level Front
                 System.out.println("Position=1");
                break;
             case 2: //Mid Level Front
                 System.out.println("Position=2");
                 break;
             case 3:
                 System.out.println("Position=3");
                 break;
         }


        // if (gamepad.dpadup==1){   this needs to be a toggle methinks rather than simply a check
        // position = position + 1
        // else if(gamepad.dpaddown==1)
        // position = position - 1}
        //Limit this to however many positions we want (maybe 3 per side?

        // if (position == 0){
        // set encoder position to x
        // else if(position==1){
        // set encoder position to y //etc}
        // else if(position==-1){
        // set encoder position to -y //etc}
        //}


    }

    public int GetArmPosition(){
        return position;
    }
}
