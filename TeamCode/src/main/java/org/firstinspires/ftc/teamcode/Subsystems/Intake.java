package org.firstinspires.ftc.teamcode.Subsystems;

// Intake

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

//ToDo: May use servo to open/close or drop the team element
//ToDo: May enable/disable electromagnet?

public class Intake {
    // Instantiate the motor variables
    private DcMotorEx intake;
    boolean toggle = true;
    boolean Possession;
    DigitalChannel beambreak; //The "beambreak" sensor is a type of IR sensor that detects if it vision is broken

    public Intake(HardwareMap hardwareMap){                 // Motor Mapping
        intake = hardwareMap.get(DcMotorEx.class, "intake");      //Sets the names of the hardware on the hardware map
        // "DeviceName" must match the Config EXACTLY

        // Set motor direction based on which side of the robot the motors are on
        intake.setDirection(DcMotorEx.Direction.FORWARD);
    }

    public void Update_intake(double speed, int position){
        if(position<0){ //if the arm is towards the back
            speed = -speed; //flip the direction of the intake
        }
        if(!beambreak.getState()) { //if beam is broken
            Possession = true; //we have possession
            intake.setPower(0);//Stop intake
        }
        else{ // if beam break not broken
            Possession = false; //we do not have possession
            intake.setPower(speed); // Run intake
        }
    }

    public void Update_outtake(double speed, int position){
        if(position<0){
            speed=-speed;
            intake.setPower(speed);
        }
        else{
        }

    }

    public void Update_telop(Gamepad gamepad2, int position){ //Code to be run in Op Mode void Loop at top level
        if(gamepad2.right_trigger>0){
        Update_intake(gamepad2.right_trigger ,position);
        }
        else {
            Update_outtake(gamepad2.left_trigger, position);
        }

    }

}
