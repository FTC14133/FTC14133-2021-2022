package org.firstinspires.ftc.teamcode.Subsystems;

//This is an example subsystem. Reference this to set up new devices.

//demo on how the device works:
//https://www.youtube.com/watch?v=wMdkM2rr1a4&ab_channel=REVRobotics

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lights {
    RevBlinkinLedDriver blinkinLedDriver;

    public Lights(HardwareMap hardwareMap){ //Run this in Int to map the class items
        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "LED");
    }

    public void Teleop(boolean possession,boolean Rotation){ //Run this inside of the main program.
        if(possession==true) {
            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.GOLD);
        }

        else if(Rotation==true){
            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_LAVA_PALETTE);
            }
        else if (Rotation==false){
            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.AQUA);
        }


    }
}
