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
    int tolerance = 2; // Encoder tolerance
    final double countsperrev = 28; // Counts per rev of the motor
    final double gearratio=3*4*5*4; //Ratio of the entire Pivot Arm from the motor to the arm
    final double countsperdegree=countsperrev*gearratio/360;
    final int countsperdegreeint=(int)countsperdegree;
    final double liftpower=0.5;



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

    public void Teleop(Gamepad gamepad2){ //Code to be run in Op Mode void Loop at top level


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

        GotoPosition(position);

    }

    public void Auto(int position){
        GotoPosition(position);
    }

    public void GotoPosition(int position){
        switch (position) {
            case -3: // Intake Back
                System.out.println("Position=-3");
                lift.setTargetPosition(0*countsperdegreeint); //Todo: Need to tune
                lift.setPower(liftpower);        //Sets the power for the lift
                lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                break;

            case -2: // Mid Level back
                System.out.println("Position=-2");
                lift.setTargetPosition(34*countsperdegreeint); //Todo: Need to tune
                lift.setPower(liftpower);        //Sets the power for the lift
                lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                break;

            case -1: //Upper Level Back
                System.out.println("Position=-1");
                lift.setTargetPosition(75*countsperdegreeint); //Todo: Need to tune
                lift.setPower(liftpower);        //Sets the power for the lift
                lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                break;

            case 0: //Straight Up
                System.out.println("Position=0");
                lift.setTargetPosition(120*countsperdegreeint); //Todo: Need to tune
                lift.setPower(liftpower);        //Sets the power for the lift
                lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                break;

            case 1: //Upper Level Front
                System.out.println("Position=1");
                lift.setTargetPosition(170*countsperdegreeint); //Todo: Need to tune
                lift.setPower(liftpower);        //Sets the power for the lift
                lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                break;

            case 2: //Mid Level Front
                System.out.println("Position=2");
                lift.setTargetPosition(210*countsperdegreeint); //Todo: Need to tune
                lift.setPower(liftpower);        //Sets the power for the lift
                lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                break;
            case 3:
                System.out.println("Position=3");
                lift.setTargetPosition(265*countsperdegreeint); //Todo: Need to tune
                lift.setPower(liftpower);        //Sets the power for the lift
                lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //Allows the lift to run
                break;
            default:
                throw new IllegalStateException("Unexpected position value: " + position);
        }
    };

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
