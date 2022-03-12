package org.firstinspires.ftc.teamcode;
// https://first-tech-challenge.github.io/SkyStone/  This is the link to ALL metered of FTC

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Pivot_Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors;
import org.firstinspires.ftc.teamcode.Subsystems.Turn_Table;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

@Autonomous(name="FTC_14133_2022_Auto", group="Auto")


//My favorite shape is a nonagon
//I like to ride dirt bikes RS


public class  FTC_14133_2022_Auto extends LinearOpMode {
    private Drivetrain drivetrain = null; // This activate the sub systems
    private Intake Intake = null;
    private Turn_Table Turn_Table = null;
    private Pivot_Arm Pivot_Arm = null;
    private Sensors Sensors = null;
    boolean GateFlag = false;
    boolean[] switches;
    boolean WT; //This will decide if we are closer to the warehouse or turn table based on the switch on the robot
    boolean A; //This will tell us that we are either on the red or blue alliance side
    double total_speed = 0.5; //This is the speed of most of the motors.
    int i = 0;
    int counter = 0;
    int TSE = 0;
    private static final String TFOD_MODEL_ASSET = "model_20220212_125551.tflite";
    private static final String[] LABELS = {
            "TSE"
    };
    private static final String VUFORIA_KEY =
            "ASpmDeb/////AAABmRxze77upU+Eirum4kwztfeCR62IXUXk4nl6GbXS5ccPvrZ6U5leBd3C5/4DeVoUWNwQNpV2mh+gx1oUfoJ7WLC/LEwZxYKoHdiVwPYcKuNZcCFud4SM8Xkeb7Fdzdejaxi5tUPvMrcDOnyhs0zOQRY2aVWJWVZ/OeYo/l9Dq4TUIfmv7Xc4TQkynXC9fbbqcu4do+wRTCRtbT5sXvzCgSk1TsEnkFrARdGPHOIBbGA85n8ORpGdx3W/egnaji6pE5ie7E8wk+1sRk46qLPb0YTQkTI4GOWp13dIWqMqZVV8ZD7T4kupqbcjeePyyVpWDfbvLX5Cwk6HC9NcRhJpmDU18zoekAahJw0Y0YWc/whA";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    public void waitForStart() {
        telemetry.addData("Object Creation", "Start");
        telemetry.update();
        //switches = Sensors.Update_Switches(); // Here we will see from the switches on the robot. Below is what they represent
        //WT = switches[0]; //This will decide if we are closer to the warehouse or turn table based on the switch on the robot
        //A = switches[1]; //This will tell us that we are either on the red or blue alliance side
        A = false;
        drivetrain = new Drivetrain(hardwareMap);
        Intake = new Intake(hardwareMap);
        Turn_Table = new Turn_Table(hardwareMap);
        Pivot_Arm = new Pivot_Arm(hardwareMap);
        Sensors = new Sensors(hardwareMap);
        telemetry.addData("Object Creation", "Done");
        telemetry.update();

    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.5f; //TODO: if this value is to high it can return wrong results. 0.7 is good to use if the detection is not working
        tfodParameters.isModelTensorFlow2 = true;
        //tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromFile(TFOD_MODEL_ASSET, LABELS);
    }

    private int reconnition() {

        i = 0;
        counter = 0;
        while (counter <= 50){
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    // step through the list of recognitions and display boundary info.

                    for (Recognition recognition : updatedRecognitions) {
                        telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                recognition.getLeft(), recognition.getTop());
                        telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());
                        telemetry.update();
                        i++;
                    }
                }
            }
            counter++;
        }
        return i;
    }

    public void runOpMode() {

        //     if ((Pivot_Arm != null) && (drivetrain != null) && (Intake !=null) && (Sensors != null) && (Turn_Table != null))
        //     {

        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1, 16.0 / 9.0);
        }

        waitForStart();
        telemetry.addData("Object", "Passed waitForStart");
        telemetry.update();

        Pivot_Arm.SetArmHome(false);

        telemetry.addData("Object", "After SetArmHome");
        telemetry.update();
        Pivot_Arm.SetArmHome(false);
        while (Pivot_Arm.GetArmHome() == false) {
            Pivot_Arm.HomeArm(); //Runs the homing sequence for the arm to reset it
        }

        //Pivot_Arm.SetArmHome(true);

        telemetry.addData("Object", "Passed while loop");
        telemetry.update();

        Intake.Home_TSE();

        drivetrain.Rotate(-45, total_speed);
        telemetry.addData("Rotation", "Passed rotating to turn table");
        telemetry.update();
        Turn_Table.Auto(A, 8000);
        telemetry.addData("Spin", "Rotated the turn table");
        telemetry.update();
        drivetrain.Rotate(45, total_speed);
        telemetry.addData("Rotate", "Rotated to look forward");
        telemetry.update();
        drivetrain.Strafing(-11.5, total_speed);
        telemetry.addData("Strafe", "Strafed to look at TSE");
        telemetry.update();





        if (reconnition() > 0){
            telemetry.addData("Recognition", "Saw TSE");
            telemetry.update();
            Pivot_Arm.GotoPosition(-1);
            drivetrain.Strafing(-32, total_speed);
        }
        telemetry.addData("Recognition", "Ran code to see TSE");
        telemetry.addData("i", i);
        telemetry.update();

        if (reconnition() == 0){
            drivetrain.Strafing(-20, total_speed);
            if (reconnition() > 0){
                Pivot_Arm.GotoPosition(-3);
                drivetrain.Strafing(-12, total_speed);
            }
            else{
                Pivot_Arm.GotoPosition(-2);
                drivetrain.Strafing(-12, total_speed);
            }

        }



        drivetrain.ForwardorBackwards(-25, total_speed);
        Intake.Update_outtake(0.5, 1, gamepad2);
        sleep(500);
        drivetrain.ForwardorBackwards(25, total_speed);
        drivetrain.Strafing(-30, total_speed);
        /*
        drivetrain.ForwardorBackwards(42.5, total_speed);
        Intake.Update_outtake(0.5, Pivot_Arm.position);
        sleep(500);
        drivetrain.Strafing(35, total_speed);
        drivetrain.Rotate(30, total_speed);
        drivetrain.ForwardorBackwards(65, total_speed);
        Pivot_Arm.GotoPosition(3);

         */


            /*
            Pivot_Arm.GotoPosition(1); //Sets the arm to the position of top goal
            drivetrain.Strafing(10, total_speed); //Line up towards shipping hub
            drivetrain.ForwardorBackwards(26, total_speed); //Goes towards the shipping hub
            Intake.Update_outtake(1, Pivot_Arm.position); //Places the freight on the correct level
            drivetrain.ForwardorBackwards(-2.5, total_speed); //Moves backwards a bit
            Pivot_Arm.GotoPosition(0); //57 and 26
            Intake.Update_outtake(0, Pivot_Arm.position);
            drivetrain.Rotate(-210.25, total_speed); //rotate to be in line of the turn table
            drivetrain.Strafing(43.4, 0.2); //Goes to the turn table
            Turn_Table.Auto(A, 4); //Runs the turn table
            drivetrain.Rotate(15, total_speed); //Rotates to be in line with the storage hub
            drivetrain.Strafing(-100, total_speed); //Goes to the storage hub

             */

            if (A == false && WT == false && GateFlag == true) { //This code will check if the robot is on the BLUE side and on the Turntable side
                //Need Camera Code //Sees where the duck is
                /*
                if camera == 1: //if the duck is on the first barcode
                    Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub

                if camera == 2: //if the duck is on the second barcode
                    Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub

                if camera == 3: //if the duck is on the third barcode
                    Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
                 */

                drivetrain.Strafing(-12, total_speed); //Line up towards shipping hub
                drivetrain.ForwardorBackwards(35, total_speed); //Goes towards the shipping hub
                Intake.Update_outtake(1, Pivot_Arm.position, gamepad2); //Places the freight on the correct level
                drivetrain.ForwardorBackwards(-3, total_speed); //Moves backwards a bit
                drivetrain.Rotate(45, total_speed); //rotate to be in line of the turn table
                drivetrain.Strafing(56, total_speed); //Goes to the turn table
                Turn_Table.Auto(A, 1000); //Runs the turn table
                drivetrain.Rotate(-45, total_speed); //Rotates to be in line with the storage hub
                drivetrain.Strafing(-105, total_speed); //Goes to the storage hub


            } else if (A == false && WT == true && GateFlag == true) { //This is a different instance where if we are starting on the BLUE side and on the warehouse side
                //Need Camera Code //Sees where the duck is
            /*
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub

            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub

            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
             */
                drivetrain.Strafing(12, total_speed); //We will go to the right to the team shipping hub
                drivetrain.ForwardorBackwards(35, total_speed); //goes forward to the team shipping hub
                Intake.Update_outtake(1, Pivot_Arm.position, gamepad2); //puts the freight on the shipping hub
                drivetrain.ForwardorBackwards(-35, total_speed); //goes away from the shipping hub
                drivetrain.Strafing(-62, total_speed); //goes up to the warehouse
            } else if (A == true && WT == false && GateFlag == true) { //red and turntable side
                //Need Camera Code //Sees where the duck is
            /*
            if camera == 1: //if the duck is on the first barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the top of the shipping hub

            if camera == 2: //if the duck is on the second barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the middle of the shipping hub

            if camera == 3: //if the duck is on the third barcode
                Pivot_Arm.Auto(3); //it will set the arm position to place the freight on the bottom of the shipping hub
             */
                drivetrain.Strafing(12, total_speed); //Goes in line wit the shipping hub
                drivetrain.ForwardorBackwards(35, total_speed); //Goes to shipping hub
                Intake.Update_outtake(total_speed, Pivot_Arm.position, gamepad2); //Places freight
                drivetrain.ForwardorBackwards(-3, total_speed); //Goes backwards a bit
                drivetrain.Rotate(-45, total_speed); //Rotates to be in line with the turn table
                drivetrain.Strafing(56, total_speed); //Goes to the turntable
                Turn_Table.Auto(A, 1000); //Spins the turntable
                drivetrain.Rotate(45, total_speed); //Goes in line with the storage hub
                drivetrain.ForwardorBackwards(-105, total_speed); //Parks in the storage hub
            } else if (A == true && WT == true && GateFlag == true) { // red and warehouse side
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
                drivetrain.ForwardorBackwards(35, total_speed); //Goes towards the shipping hub
                Intake.Update_outtake(1, Pivot_Arm.position, gamepad2); //Runs the intake to release the fright
                drivetrain.ForwardorBackwards(-35, total_speed);//backs away from shipping hub
                drivetrain.Strafing(62, total_speed); //Parks in the storage hub
            }
        }


    }

