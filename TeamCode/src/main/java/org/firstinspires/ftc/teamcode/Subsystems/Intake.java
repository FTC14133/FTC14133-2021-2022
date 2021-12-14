package org.firstinspires.ftc.teamcode.Subsystems;

// Intake

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class  Intake {
    // Instantiate the motor variables
    private DcMotorEx intake;
    boolean toggle = true;
    boolean Possession; //Variable telling whether we have possession of a game piece or not
    DigitalChannel beambreak; //The "beambreak" sensor is a type of IR sensor that detects if it vision is broken
    Servo Hook = null;

    public Intake(HardwareMap hardwareMap){                 // Motor Mapping
        intake = hardwareMap.get(DcMotorEx.class, "intake");      //Sets the names of the hardware on the hardware map
        // "DeviceName" must match the Config EXACTLY

        // Set motor direction based on which side of the robot the motors are on
        intake.setDirection(DcMotorEx.Direction.FORWARD);
        Hook =hardwareMap.get(Servo.class,"Hook");
    }

    public void Update_intake(double speed, int position){ //Standard intake function
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

    public void Update_outtake(double speed, int position){ //Standard outtake function
        if(position<0){
            speed=-speed;

        }
        intake.setPower(speed);
    }

    public void Teleop(Gamepad gamepad2, int position){ //Code to be run in Op Mode void Loop at top level
        if(gamepad2.left_trigger>0){ //if the left trigger is pulled
            Update_outtake(gamepad2.left_trigger, position); //Run the outtake program

        }
        else {
            Update_intake(gamepad2.right_trigger ,position); //Otherwise run the Intake program

        }
    }
    public void Team_Shipping_Element(Gamepad gamepad2){ //Code to be run in Op Mode void Loop at top level
        if(gamepad2.x==true){ //if the x button is pressed
            Hook.setPosition(1); //Hook extended

        }
        else {
           Hook.setPosition(0); //Hook not extended

        }
    }
        public void Home_TSE(){ //Code to be run in Op Mode void Loop at top level
            Hook.setPosition(0);

        }
        public boolean getPossession(){
            return Possession;
        }
    }

