package org.firstinspires.ftc.teamcode.Subsystems;

// Turntable

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

//ToDo: May need multiple turntable spinners
//todo: turntable may be powered by servos instead of a full-on motor, just for sizing and power usage reasons.

public class Turn_Table {
    // Instantiate the  motor variables
    private DcMotorEx turn_table;


    public Turn_Table(HardwareMap hardwareMap){                 // Motor Mapping
        turn_table = hardwareMap.get(DcMotorEx.class, "turn_table");      //Sets the names of the hardware on the hardware map
// "DeviceName" must match the Config EXACTLY

    // Set motor direction based on which side of the robot the motors are on
        turn_table.setDirection(DcMotorEx.Direction.FORWARD);

    }

    public void Turn_Table_Auto(boolean A){
        if (A == true) { //Does an outcome if the robot is on the RED side
            turn_table.setPower(0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON  runs the intake backwards for the RED side
        }
        else { //Does an outcome is the robot is on the BLUE side
            turn_table.setPower(-0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON  runs the intake backwards for the BLUE side
        }
    }

    public void Turn_Table_Direction(boolean A){ //This code will set the direction of the turn table motor
        if (A == true){ //If the robot is on the red side it will set the motor forward
            turn_table.setDirection(DcMotorEx.Direction.FORWARD);
        }else{ //If the robot is on the blue side it will set the motor backwards
            turn_table.setDirection(DcMotorEx.Direction.REVERSE);
        }
    }

    public void Turn_Table_Telop(Gamepad gamepad2){ //Code to be run in Op Mode void Loop at top levelzaZ
        if (gamepad2.x) {        //runs the intake backwards for the BLUE side
            turn_table.setPower(0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON
        }else{
            turn_table.setPower(0);
        }
    }
}
