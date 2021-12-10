package org.firstinspires.ftc.teamcode.Subsystems;

// Turntable

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class Turn_Table {
    // Instantiate the  motor variables
    private DcMotorEx turn_table;


    public Turn_Table(HardwareMap hardwareMap){                 // Motor Mapping
        turn_table = hardwareMap.get(DcMotorEx.class, "turn_table");      //Sets the names of the hardware on the hardware map
// "DeviceName" must match the Config EXACTLY

    // Set motor direction based on which side of the robot the motors are on
        turn_table.setDirection(DcMotorEx.Direction.FORWARD);

    }

    public void Auto(boolean A, int turntabledist){
        if (A == true) { //Does an outcome if the robot is on the RED side
            // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON  runs the intake backwards for the RED side
        }
        else { //Does an outcome is the robot is on the BLUE side
            turn_table.setDirection(DcMotorEx.Direction.REVERSE);// THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON  runs the intake backwards for the BLUE side
        }
        turn_table.setPower(-0.25);
        turn_table.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turn_table.setTargetPositionTolerance(15);
        turn_table.setTargetPosition(turntabledist);
        turn_table.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (turn_table.isBusy()) {
        }

    }



    public void Direction(boolean A){ //This code will set the direction of the turn table motor
        if (A == true){ //If the robot is on the red side it will set the motor forward
            turn_table.setDirection(DcMotorEx.Direction.FORWARD);
        }else{ //If the robot is on the blue side it will set the motor backwards
            turn_table.setDirection(DcMotorEx.Direction.REVERSE);
        }
    }

    public void Teleop(Gamepad gamepad2){ //Code to be run in Op Mode void Loop at top levelzaZ
        if (gamepad2.x) {        //runs the intake backwards for the BLUE side
            turn_table.setPower(0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON //Todo: This should also be run at a rate using velocity control.
        }else{
            turn_table.setPower(0);
        }
    }
}
