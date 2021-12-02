package org.firstinspires.ftc.teamcode;
// https://first-tech-challenge.github.io/SkyStone/  This is the link to ALL metered of FTC

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Pivot_Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Turn_Table;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors;

@TeleOp(name="FTC_14133_2022_Auto", group="Auto")


//My favorite shape is a nonagon
//I like to ride dirt bikes RS


public class  FTC_14133_2022_Auto extends LinearOpMode {
    private Drivetrain drivetrain=null; // This activate the sub systems
    private Intake Intake=null;
    private Turn_Table Turn_Table=null;
    private Pivot_Arm Pivot_Arm =null;
    private Sensors Sensors=null;
    boolean[] switches;
    boolean WT ; //This will decide if we are closer to the warehouse or turn table based on the switch on the robot
    boolean A ; //This will tell us that we are either on the red or blue alliance side


    public void waitForStart() {
        switches = Sensors.Update_Switches(); // Here we will see from the switches on the robot. Below is what they represent
        WT = switches[0]; //This will decide if we are closer to the warehouse or turn table based on the switch on the robot
        A = switches[1]; //This will tell us that we are either on the red or blue alliance side
        drivetrain = new Drivetrain(hardwareMap);
        Intake = new Intake(hardwareMap);
        Turn_Table = new Turn_Table(hardwareMap);
        Pivot_Arm = new Pivot_Arm(hardwareMap);
        Sensors = new Sensors(hardwareMap);
    }

    public void runOpMode() { //Todo: Program all auto routines. Start with one, then copy all to another. We can make these subprograms if that is easier.
        Pivot_Arm.HomeArm(); //Runs the homing sequence for the arm to reset it

        if (A == true && WT == true) { //This code will check if the robot is on the RED side and on the warehouse side
            drivetrain.Strafing(-30, 1); //Will go to to the left approaching the turn table
            Turn_Table.Turn_Table_Auto(A); //We will spin the turn table
        }
        else if (WT == true) { //This is a different instance where if we are starting on the BLUE side and on the warehouse side
            drivetrain.Strafing(30, 1); //We will go to the right approaching the turn table
            Turn_Table.Turn_Table_Auto(A); //We will spin the turn table
        }



        drivetrain.ForwardorBackwards(10, 1);
        drivetrain.Rotate(360, 1);
        drivetrain.Strafing(10, 1);
        Intake.Update_intake(1, Pivot_Arm.position);
        Turn_Table.Turn_Table_Auto(A);
        Pivot_Arm.Auto(1);


    }
}
