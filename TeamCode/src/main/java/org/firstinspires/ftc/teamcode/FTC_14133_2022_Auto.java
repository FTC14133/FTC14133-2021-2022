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

        if (A == false && WT == false) { //This code will check if the robot is on the BLUE side and on the Turntable side
            drivetrain.Strafing(-39, 1); //Will go to to the left approaching the turn table
            Turn_Table.Turn_Table_Auto(A); //We will spin the turn table
            drivetrain.Strafing(12, 1); //Lines up with the middle barcode
            //Need Camera Code //Sees where the duck is
            drivetrain.Strafing(12, 1); //Goes to the right to be in line with the shipping hub
            drivetrain.ForwardorBackwards(37, 1); //Goes to the shipping hub
            /*
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub

            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub

            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
             */

            Intake.Update_outtake(1, Pivot_Arm.position); //Places the freight on the correct level
            drivetrain.ForwardorBackwards(-37, 1); //Moves backwards
            Pivot_Arm.Auto(-3); //Puts down the arm
            drivetrain.Strafing(36, 1); //Goes to park at the warehouse

        }
        else if (WT == true) { //This is a different instance where if we are starting on the BLUE side and on the warehouse side
            drivetrain.Strafing(30, 1); //We will go to the right approaching the turn table
            Turn_Table.Turn_Table_Auto(A); //We will spin the turn table

        }

        /*
        drivetrain.ForwardorBackwards(10, 1);
        drivetrain.Rotate(360, 1);
        drivetrain.Strafing(10, 1);
        Intake.Update_intake(1, Pivot_Arm.position);
        Turn_Table.Turn_Table_Auto(A);
        Pivot_Arm.Auto(1);
         */


    }
}
