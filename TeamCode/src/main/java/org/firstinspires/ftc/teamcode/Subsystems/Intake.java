package org.firstinspires.ftc.teamcode.Subsystems;

// Intake

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

//ToDo: May use servo to open/close or drop the team element
//ToDo: May enable/disable electromagnet?
//ToDo: May need to run in different directions depending where the arm is, i.e. when arm is on front of robot the intake will be reversed compared to when it is by the back of the robot

public class Intake {
    // Instantiate the motor variables
    private DcMotorEx intake;
    int pos= 0 ;
    boolean toggle = true;
    boolean Possession;
    DigitalChannel beambreak; //The "beambreak" sensor is a type of IR sensor that detects if it vision is broken

    public Intake(HardwareMap hardwareMap){                 // Motor Mapping
        intake = hardwareMap.get(DcMotorEx.class, "intake");      //Sets the names of the hardware on the hardware map
        // "DeviceName" must match the Config EXACTLY

        // Set motor direction based on which side of the robot the motors are on
        intake.setDirection(DcMotorEx.Direction.FORWARD);
    }
    
    /* JAKE- send the least amount of data possible for instance
    becuase the life only requires the use of an int (which is a primative type)
    you can just send the int. 

    My General Rule of thumb is if you need to less than 2 members from an object over send them individually (and make sure your argument names are declarative);
    if you need to access 2 or more members just send the entire object over and use that to get your required values

    if you need to access logic from an object ie a method that raises the arm
    */
    public void Update_intake(int speed, int position){ //todo: rather than having one method try to do everything in auto, make multiple methods like we did last year that each do something individually. Much easier to keep track of.
        if(position>0){
            speed = -speed;
        }
        if(!beambreak.getState()) { //if beam is broken
            Possession = true;
            intake.setPower(0);//Stop intake


        }
        else{ // if beam break not broken
            Possession = false;
            intake.setPower(speed); // Run intake
        }

    }

    public void Update_outtake(int speed){ //todo: rather than having one method try to do everything in auto, make multiple methods like we did last year that each do something individually. Much easier to keep track of.

        intake.setPower(speed);
        if() { //if beam is broken


        }

    }
    public void Update_telop(Gamepad gamepad2, int liftPosition){ //Code to be run in Op Mode void Loop at top level

        if (gamepad2.y){
            intake.setPower(0.25);
        }

        if (gamepad2.b){
            intake.setPower(-0.25);
        }
















                            // gets the current arm position
        /*if (gamepad2.right_trigger > 0 && liftPosition>0 ) {       //runs the intake forward based on arm position
            intake.setPower(1);
        }else if (gamepad2.right_trigger > 0 && liftPosition<0){
            intake.setPower(-1);                           // runs the intake forward, but the arm is backwards so it is negative
        }

        else{
            intake.setPower(0);
        }

         */



    }

}
