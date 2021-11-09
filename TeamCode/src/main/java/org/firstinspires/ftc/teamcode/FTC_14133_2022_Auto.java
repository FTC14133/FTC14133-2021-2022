package org.firstinspires.ftc.teamcode;
// https://first-tech-challenge.github.io/SkyStone/  This is the link to ALL metered of FTC

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

@TeleOp(name="FTC_14133_2022_Auto", group="Auto")


//My favorite shape is a nonagon
//I like to ride dirt bikes RS


public class  FTC_14133_2022_Auto extends LinearOpMode {
    private Drivetrain drivetrain=null;
    private Intake Intake=null;
    private Turn_Table Turn_Table=null;
    private Generic_Lift Generic_Lift=null;

    public void waitForStart() {
    }

    public void runOpMode() {
        drivetrain = new Drivetrain(hardwareMap);
        Intake = new Intake(hardwareMap);
        Turn_Table = new Turn_Table(hardwareMap);
        Generic_Lift = new Generic_Lift(hardwareMap);

        drivetrain.ForwardorBackwards(10, 1);
        drivetrain.Rotate(360, 1);
        drivetrain.Strafing(10, 1);
        Intake.Update(gamepad2, 2); // Fixme This update command will not work in a linear mode with gamepad inputs.
        Turn_Table.Update_a(gamepad2);      //Fixme   Same with this, it runs once, not loop. Also, no gamepad input in autonomous
        Generic_Lift.Update(gamepad2);      //Fixme     See above, will not work in auto. Needs a different method for auto.


    }
}
