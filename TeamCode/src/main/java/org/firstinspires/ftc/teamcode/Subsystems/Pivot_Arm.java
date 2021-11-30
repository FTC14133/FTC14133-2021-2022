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
        position=-3; //initial arm position
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
                 System.out.println("Position=3");
                 lift.setTargetPosition(0); //Todo: Need to tune all set target values here and below
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;

             case -2: // Mid Level back
                 System.out.println("Position=-2");
                 lift.setTargetPosition(186);
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;

             case -1: //Upper Level Back
                 System.out.println("Position=-1");
                 lift.setTargetPosition(372);
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;

             case 0: //Straight Up
                 System.out.println("Position=0");
                 lift.setTargetPosition(558);
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;

             case 1: //Upper Level Front
                 System.out.println("Position=1");
                 lift.setTargetPosition(744);
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                break;

             case 2: //Mid Level Front
                 System.out.println("Position=2");
                 lift.setTargetPosition(930);
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;
             case 3:
                 System.out.println("Position=3");
                 lift.setTargetPosition(1120);
                 lift.setPower(0.25);        //Sets the power for the lift
                 lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                 break;
             default:
                 throw new IllegalStateException("Unexpected position value: " + position);
         }

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

    }
}
