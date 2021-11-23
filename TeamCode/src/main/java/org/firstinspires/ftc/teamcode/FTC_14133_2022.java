package org.firstinspires.ftc.teamcode;
// https://first-tech-challenge.github.io/SkyStone/  This is the link to ALL metered of FTC

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Pivot_Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors;
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
 private Pivot_Arm Pivot_Arm=null;
 private Sensors Sensors=null;

 boolean[] switches ; //fixme: This code needs to be run in one of the steps below, likely init. You can still instantiate "switches" here.
 boolean A;

 public void init() {
     drivetrain = new Drivetrain(hardwareMap);
     Intake = new Intake(hardwareMap);
     Turn_Table = new Turn_Table(hardwareMap);
     Pivot_Arm = new Pivot_Arm(hardwareMap);
     Sensors = new Sensors(hardwareMap);
     switches = Sensors.Update_Red_Blue();
     A = switches[1];
 }

 public void init_loop() {

 }
 public void start() {

 }

 public void loop() {

    drivetrain.Update(gamepad1);
    Pivot_Arm.Pivot_Arm_Telop(gamepad2);


    Intake.Update_telop(gamepad2,Pivot_Arm.GetArmPosition()); //Passes position of the arm so intake direction can change.
    Turn_Table.Turn_Table_Telop(gamepad2, A);



 }
}
