package org.firstinspires.ftc.teamcode.Subsystems;

// Turntable

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Sensors;

//ToDo: May need multiple turntable spinners
//todo: turntable may be powered by servos instead of a full-on motor, just for sizing and power usage reasons.
//ToDo: Make pre-set program that auto goes the right distance, stops to place a new one, and then starts again (faster than a human can)

public class Turn_Table {
    // Instantiate the  motor variables
    private DcMotorEx spin_table;
    private Sensors Sensors=null;


    public Turn_Table(HardwareMap hardwareMap){                 // Motor Mapping
        spin_table = hardwareMap.get(DcMotorEx.class, "spin_table");      //Sets the names of the hardware on the hardware map
// "DeviceName" must match the Config EXACTLY

    // Set motor direction based on which side of the robot the motors are on
        spin_table.setDirection(DcMotorEx.Direction.FORWARD);
        Sensors = new Sensors(hardwareMap);

    }

    public void Update_auto(){
        int Red_Blue = Sensors.Update_Red_Blue(); //will get if the robot is on the red or blue side. //fixme: call this alliance
        if (Red_Blue == 1) { //Does an outcome is the robot is on the blue side
            spin_table.setPower(-0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON  runs the intake backwards for the BLUE side
        }
        else { //Does an outcome is the robot is on the red side
            spin_table.setPower(0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON  runs the intake backwards for the RED side
        }
    }

    public void Update_telop(Gamepad gamepad2){ //Code to be run in Op Mode void Loop at top level
        int Red_Blue = Sensors.Update_Red_Blue(); //will get if the robot is on the red or blue side.
        if (Red_Blue == 1) { //Does an outcome is the robot is on the blue side
            if (gamepad2.x) {        //runs the intake backwards for the BLUE side
                spin_table.setPower(-0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON
            }
        }
        else { //Does an outcome is the robot is on the red side
            if (gamepad2.x) {        //runs the intake backwards for the RED side
                spin_table.setPower(0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON
            }
        }
    }
}
