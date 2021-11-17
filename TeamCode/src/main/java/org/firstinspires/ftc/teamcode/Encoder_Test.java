package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Encoder_Test", group="Auto")

public class Encoder_Test extends LinearOpMode{
    DcMotor Motor; //Makes a motor that is a test

    public void runOpMode(){
        Motor = hardwareMap.dcMotor.get("motor"); //Sets the variable to the robot

        Motor.setDirection(DcMotor.Direction.FORWARD); //When running the motor in a positive power the motor will go forwards

        Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //Resets the current position of the encoder to 0.
//fixme: motor power needs to be ser before target position
        Motor.setTargetPosition(1120); //1120 is the amount of motor ticks are there for a AndyMark DcMotor, This also tells where to go

        telemetry.addData("Mode", "running"); //Will tell the phone/screen that the motor is going to run
        telemetry.update();

        Motor.setPower(0.25); //Will give the power needed for the position provided to go to

        while (Motor.isBusy()){ //If the motor is running the program will wait for it to run
        }

        Motor.setPower(0); //Will stop giving power to the motor

        try {
            wait(2000); //Will wait for 2 seconds (2000 milliseconds), QUESTION: I DO NOT KNOW WHY THIS HAS A ERROR, THANKS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Motor.setTargetPosition(0); //Sets the motor to its original position
//fixme: set motor power before target position. You don't need to stop motor power after each encoder position is reached.
        Motor.setPower(-0.25); //Goes to the original position except it is going backwards this time //fixme: I don't believe this is needed. Motor power can just stay on.

        while (Motor.isBusy()){ //Waits for the motor to go to its position
        } //fixme: do not need these while loops, will only stop other robot commands from happening. We only do this with the drivetrain in auto because we need the robot wheels to all get to position and not move on until they have all arrived.

        Motor.setPower(0); //Turns off the motor when it is done fixme: It will stop at the position but may not hold there.


    }
}
