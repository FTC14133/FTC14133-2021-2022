package org.firstinspires.ftc.teamcode;
// https://first-tech-challenge.github.io/SkyStone/  This is the link to ALL metered of FTC

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Pivot_Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors;
import org.firstinspires.ftc.teamcode.Subsystems.Turn_Table;

@Autonomous(name="FTC_14133_2022_MemberNoAuto", group="Auto")


//My favorite shape is a nonagon
//I like to ride dirt bikes RS


public class FTC_14133_2022_MemberNoAuto extends LinearOpMode {
    private Drivetrain drivetrain=null; // This activate the sub systems
    private Intake Intake=null;
    private Turn_Table Turn_Table=null;
    private Pivot_Arm Pivot_Arm =null;
    private Sensors Sensors=null;
    boolean[] switches;
    boolean WT ; //This will decide if we are closer to the warehouse or turn table based on the switch on the robot
    boolean A ; //This will tell us that we are either on the red or blue alliance side
    double total_speed = 0.25; //This is the speed of most of the motors.


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
        Intake.Home_TSE();

        if (A == false && WT == false) { //BLUE side and on the Turntable side
            //Need Camera Code //Sees where the duck is
            /*
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub

            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub

            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
             */

            drivetrain.Strafing(-12, total_speed);
            drivetrain.ForwardorBackwards(23, total_speed);
            Intake.Update_outtake(1, Pivot_Arm.position); //Places the freight on the correct level
            drivetrain.ForwardorBackwards(-23, total_speed); //Moves backwards
            Pivot_Arm.Auto(-3); //Puts down the arm
            drivetrain.Strafing(-47, total_speed); //Goes to park at the warehouse

            drivetrain.Strafing(23,1); // Goes to line up with middle barcode
            //Need Camera Code //Sees where the duck is
            /*
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub

            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub

            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
             */
            drivetrain.Strafing(23,1); //lines up with shipping hub
            drivetrain.ForwardorBackwards(23,1); //Goes to shipping hub
            Intake.Update_outtake(1, Pivot_Arm.position); //Releases freight
            drivetrain.ForwardorBackwards(-23,1); //Goes backwards
            drivetrain.Strafing(-47,1); //Parks in warehouse
        }
        else if (A == false && WT == true) { //BLUE side and on the warehouse side
            //Need Camera Code //Sees where the duck is
            /*
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub

            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub

            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
             */
            drivetrain.Strafing(11, 1); //We will go to the right to the team shipping element
            drivetrain.ForwardorBackwards(23, 1); //goes forward to the team shipping element
            Intake.Update_outtake(1, Pivot_Arm.position); //puts the freight on the shipping element
            drivetrain.ForwardorBackwards(-23, 1); //goes away from the shipping element
            drivetrain.Strafing(-48,1); //goes up to the warehouse

            drivetrain.Strafing(70,1);//goes down to the ducks
            /*
            //Need Camera Code //Sees where the duck is
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub
            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub
            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
                */
            drivetrain.Strafing(-23,1);//goes up to the shipping hub
            drivetrain.ForwardorBackwards(23,1); //goes forward to the shipping hub
            Intake.Update_outtake(1, Pivot_Arm.position); // puts freight on the shipping hub
            drivetrain.ForwardorBackwards(-23,1);//goes back from the shipping hub
            drivetrain.Strafing(-48,1);//goes up to the warehouse
        }
        else if (A == true && WT == false) { //red and turntable side
            //Need Camera Code //Sees where the duck is
            /*
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub

            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub

            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
             */
            drivetrain.Strafing(35,1); //goes down to the turntable
            Turn_Table.Auto(A,1000); //spins the turntable
            drivetrain.Strafing(47,1); //goes up to the shipping element
            drivetrain.ForwardorBackwards(23,1); //goes forward to the shipping element
            Intake.Update_outtake(1, Pivot_Arm.position); //puts freight on the shipping element
            drivetrain.ForwardorBackwards(-23,1);//goes away from the shipping element
            drivetrain.Strafing(48,1);//parks in the warehouse

            Intake.Update_outtake(1, Pivot_Arm.position);
            drivetrain.Strafing(-23,1);
            //Need Camera Code //Sees where the duck is
            /*
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub

            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub

            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
             */

            drivetrain.Strafing(-23,1);
            drivetrain.ForwardorBackwards(23,1);
            Intake.Update_outtake(1, Pivot_Arm.position);
            drivetrain.ForwardorBackwards(-23,1);
            drivetrain.Strafing(48,1);

        }
        else if (A == true && WT == true) { // red and warehouse side
            //Need Camera Code //Sees where the duck is
            /*
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub

            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub

            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
             */
            drivetrain.Strafing(-12, total_speed); // Lines up with shipping hub
            drivetrain.ForwardorBackwards(23, total_speed); //Goes towards the shipping hub
            Intake.Update_outtake(1, Pivot_Arm.position); //Runs the intake to release the fright
            drivetrain.ForwardorBackwards(-23, total_speed);//backs away from shipping hub
            drivetrain.Strafing(-47, total_speed); //Goes to park at the warehouse

            drivetrain.Strafing(23, 1);//goes down to the ducks
            /*
            //Need Camera Code //Sees where the duck is
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub
            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub
            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
                */
            drivetrain.Strafing(23,1);//goes down to the shipping hub
            drivetrain.ForwardorBackwards(23,1);//goes forward to the shipping hub
            Intake.Update_outtake(1, Pivot_Arm.position);//puts freight on the shipping hub
            drivetrain.ForwardorBackwards(-23,1);//goes back from the shipping hub
            drivetrain.Strafing(-48,1);//goes up to the warehouse
        }


    }
}
