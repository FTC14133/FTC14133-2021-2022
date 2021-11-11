package org.firstinspires.ftc.teamcode;
// https://first-tech-challenge.github.io/SkyStone/  This is the link to ALL metered of FTC

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Generic_Lift;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Turn_Table;

@TeleOp(name="FTC_14133_2022", group="Iterative Opmode") // Labels program in Driver station Selection


//My favorite shape is a nonagon
//I like to ride dirt bikes RS

//ToDo: Add light subsystem for Blinkin' which will run primarily with while loops likely, maybe include this in sensors?

//fixme: COMMENT YOUR CODE VIHAAN! We're adding a lot of automation, which is nice, but it is going to be REAALLY difficult to troubleshoot.

public class  FTC_14133_2022 extends OpMode {
 private Drivetrain drivetrain=null;
 private Intake Intake=null;
 private Turn_Table Turn_Table=null;
 private Generic_Lift Generic_Lift=null;
 private int RED_BlUE;

 public void init() {
     drivetrain = new Drivetrain(hardwareMap);
     Intake = new Intake(hardwareMap);
     Turn_Table = new Turn_Table(hardwareMap);
     Generic_Lift = new Generic_Lift(hardwareMap);
     //Todo: run code determining which alliance we are on and which side of the field we are starting. This should only need to be run once and work for the entire match.
 }

 public void init_loop() {

 }

 public void start() { // ToDo: This won't do anything in teleop. Use in Auto with (maybe) a switch box.

 }

    public double tbegin = getRuntime();

 public void loop() {


     drivetrain.Update(gamepad1);
     Generic_Lift.Update(gamepad2);

    // getArmPosition here so that we can pass the minimal amount of data
    Intake.Update_telop(gamepad2, Generic_Lift.getArmPosition());
    Turn_Table.Update_telop(gamepad2);



}
}
