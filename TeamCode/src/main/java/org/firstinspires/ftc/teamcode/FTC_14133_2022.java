package org.firstinspires.ftc.teamcode;
// https://first-tech-challenge.github.io/SkyStone/  This is the link to ALL metered of FTC

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Lights;
import org.firstinspires.ftc.teamcode.Subsystems.Pivot_Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors;
import org.firstinspires.ftc.teamcode.Subsystems.Turn_Table;

@TeleOp(name="FTC_14133_2022", group="Iterative Opmode") // Labels program in Driver station Selection


//My favorite shape is a nonagon
//I like to ride dirt bikes RS

//COMMENT YOUR CODE (VIHAAN)! We're adding a lot of automation, which is nice, but it is going to be REAALLY difficult to troubleshoot.

public class  FTC_14133_2022 extends OpMode {
 private Drivetrain drivetrain=null;
 private Intake Intake=null;
 private Turn_Table Turn_Table=null;
 private Pivot_Arm Pivot_Arm=null;
 private Sensors Sensors=null;
 //private Lights Lights=null;
 boolean [] switches;
 boolean Alliance;

 public void init() {
     drivetrain = new Drivetrain(hardwareMap);
     Intake = new Intake(hardwareMap);
     Turn_Table = new Turn_Table(hardwareMap);
     Pivot_Arm = new Pivot_Arm(hardwareMap);
     Sensors = new Sensors(hardwareMap);
     //Lights = new   Lights(hardwareMap);
 }

 public void init_loop() {

 }
 public void start() {
     switches = Sensors.Update_Switches(); //Reads the switches for which alliance we are on
     Alliance = switches[1]; //Assigns a variable to the state of our alliance (true red, false blue)
     telemetry.addData("Status", "Start");
     Turn_Table.Direction(Alliance);
     Intake.Home_TSE();
 }

 public void loop() {
     telemetry.addData("Status", "Looping");
     if (Pivot_Arm.GetArmHome()==false){
         telemetry.addData("Status", "Homing");
         telemetry.update();
         Pivot_Arm.HomeArm(); //Runs the homing sequence for the arm to reset it
     }
     else if (gamepad2.back){
         Pivot_Arm.HomeArm(); //Runs the homing sequence for the arm to reset it
         telemetry.addData("Status", "Homed");
     }

    drivetrain.Teleop(gamepad1);
    Pivot_Arm.Teleop(gamepad2);
    //Lights.Teleop(Intake.getPossession(),Turn_Table.getRotation());

    Intake.Teleop(gamepad2,Pivot_Arm.GetArmPosition()); //Passes position of the arm so intake direction can change.
    Intake.Team_Shipping_Element(gamepad2);
    Turn_Table.Teleop(gamepad2);

    telemetry.update();

 }
}
