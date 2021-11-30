package org.firstinspires.ftc.teamcode.Subsystems;

// Generic Lift

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.zip.DeflaterInputStream;


public class Pivot_Arm {
    // Instantiate the drivetrain motor variables
    private DcMotorEx lift;
    boolean Home;
    DigitalChannel HomeSwitch;

    public int position = 0; // Integer position of the arm





    //ToDo: Add encoder count amount (still undetermined, just put a number in now)

    public Pivot_Arm(HardwareMap hardwareMap){                 // Motor Mapping
        lift = hardwareMap.get(DcMotorEx.class, "lift");//Sets the names of the hardware on the hardware map
        HomeSwitch = hardwareMap.get(DigitalChannel.class, "HomeSwitch");
    // "DeviceName" must match the Config EXACTLY

        // Set motor direction based on which side of the robot the motors are on
        lift.setDirection(DcMotorEx.Direction.FORWARD);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        position=0; //initial arm position
    }

    public void Pivot_Arm_Telop(Gamepad gamepad2){ //Code to be run in Op Mode void Loop at top level


         if (gamepad2.dpad_up==true) {
             position = position + 1; //Increase Arm position
             if (position>3){
                 position=3;
             }
         }
         else if(gamepad2.dpad_down==true){
             position = position -1;
             if (position<-3){
                 position=-3;
             }
         }



         switch (position) {
             case -3: // Intake Back
                 System.out.println("Position=1");
                 lift.setTargetPosition(0); //Todo: Need to tune
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;

             case -2: // Mid Level back
                 System.out.println("Position=2");
                 lift.setTargetPosition(186); //Todo: Need to tune
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;

             case -1: //Upper Level Back
                 System.out.println("Position=3");
                 lift.setTargetPosition(372); //Todo: Need to tune
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;

             case 0: //Straight Up
                 System.out.println("Position=0");
                 lift.setTargetPosition(558); //Todo: Need to tune
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;

             case 1: //Upper Level Front
                 System.out.println("Position=1");
                 lift.setTargetPosition(744); //Todo: Need to tune
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                break;

             case 2: //Mid Level Front
                 System.out.println("Position=2");
                 lift.setTargetPosition(930); //Todo: Need to tune
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;
             case 3:
                 System.out.println("Position=3");
                 lift.setTargetPosition(1120); //Todo: Need to tune
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;
             default:
                 throw new IllegalStateException("Unexpected position value: " + position);
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

    public void HomeArm(){
        while (HomeSwitch.getState()==false){
            lift.setPower(.25);

        }
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setPower(0);
        Home=true;
    }
}
