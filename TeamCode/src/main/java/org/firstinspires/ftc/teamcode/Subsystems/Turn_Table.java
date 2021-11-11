package org.firstinspires.ftc.teamcode.Subsystems;

// Turntable

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Sensors;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

//ToDo: May need multiple turntable spinners
//todo: turntable may be powered by servos instead of a full-on motor, just for sizing and power usage reasons.

public class Turn_Table {
    // Instantiate the  motor variables
    private DcMotorEx spin_table;
    private Sensors Sensors=null;
    private Drivetrain drivetrain=null;
    boolean WT;
    boolean A;


    public Turn_Table(HardwareMap hardwareMap){                 // Motor Mapping
        spin_table = hardwareMap.get(DcMotorEx.class, "spin_table");      //Sets the names of the hardware on the hardware map
// "DeviceName" must match the Config EXACTLY

    // Set motor direction based on which side of the robot the motors are on
        spin_table.setDirection(DcMotorEx.Direction.FORWARD);
        Sensors = new Sensors(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);

    }

    public void Update_auto(boolean stop){
        boolean[] switches = Sensors.Update_Red_Blue();
        WT = switches[0];
        A = switches[1];
        if (stop == true){
            spin_table.setPower(0);
        }
        else if (A == true) { //Does an outcome is the robot is on the blue side
            spin_table.setPower(-0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON  runs the intake backwards for the BLUE side
        }
        else { //Does an outcome is the robot is on the red side
            spin_table.setPower(0.25); // THIS WILL BE TUNED FOR PERFECTIIIIIOOOOON  runs the intake backwards for the RED side
        }
    }

    public void Update_telop(Gamepad gamepad2){ //Code to be run in Op Mode void Loop at top level
        boolean[] switches = Sensors.Update_Red_Blue();
        WT = switches[0];
        A = switches[1];

        if (A == true) { //Does an outcome is the robot is on the blue side
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

    public void Preset_TurnTable(Gamepad gamepad1){
        if (gamepad1.a){
            drivetrain.Strafing(30, 1);
            spin_table.setPower(-0.25);
        }
    }
}
