package org.firstinspires.ftc.teamcode.Subsystems;

// Turntable

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

//ToDo: May need multiple turntable spinners
//todo: turntable may be powered by servos instead of a full-on motor, just for sizing and power usage reasons.

public class Turn_Table {
    // Instantiate the  motor variables
    private DcMotorEx turn_table;
    private Drivetrain drivetrain=null; //fixme: why are we mapping a drivetrain object in the turntable program?


    public Turn_Table(HardwareMap hardwareMap){                 // Motor Mapping
        turn_table = hardwareMap.get(DcMotorEx.class, "turn_table");      //Sets the names of the hardware on the hardware map
// "DeviceName" must match the Config EXACTLY

    // Set motor direction based on which side of the robot the motors are on
        turn_table.setDirection(DcMotorEx.Direction.FORWARD);
        drivetrain = new Drivetrain(hardwareMap); //THE DRIVETRAIN IS USED FOR THE PRESET PROGRAM IN TELOP //fixme: you don't need to insert an entire drivetrain object in order to do this

    }

    public void Update_auto(boolean A, double Power){
        if (A == true) { //Does an outcome if the robot is on the RED side
            turn_table.setPower(Power); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON  runs the intake backwards for the RED side //fixme: this sets turntable to run the entire auto mode if A==true wiht no way to stop.
        }
        else { //Does an outcome is the robot is on the BLUE side
            turn_table.setPower(Power); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON  runs the intake backwards for the BLUE side
        }
    }

    public void Update_telop(Gamepad gamepad2, boolean A){ //Code to be run in Op Mode void Loop at top level
        if (A == true) { //Does an outcome is the robot is on the RED side //fixme: only need to check for this once at the beginning of teleop
            if (gamepad2.x) {        //runs the intake backwards for the RED side
                turn_table.setPower(-0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON
            }
        }
        else { //Does an outcome is the robot is on the BLUE side
            if (gamepad2.x) {        //runs the intake backwards for the BLUE side
                turn_table.setPower(0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON
            }
            else{
                turn_table.setPower(0);
            }
        }
    }

    public void Preset_TurnTable(Gamepad gamepad2, boolean A){ //Fixme: why are we using drive commands in Turntable? Create a function in the top-level to incorporate these instead. Turntable is ONLY for turntable commands.
        if (gamepad2.a && A == true) { //This code will check if the driver presses the a button and that the robot is on the RED side
            drivetrain.Strafing(-30, 1); //Will go to to the left approaching the turn table
            turn_table.setPower(-0.25); //Will turn on the motor that spins the turn table
        }
        else if (gamepad2.a) { //This is a different instance where if we are starting on the BLUE side
            drivetrain.Strafing(30, 1); //We will go to the right approaching the turn table
            turn_table.setPower(0.25); //We will spin the turn table
        }

    }
}
