package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Sensors {
    DigitalChannel Red; //Sets the varible of a switch on the robot
    DigitalChannel Blue; //Sets the varible of a switch on the robot
    public int Red_Blue; // Makes a varible to find the differnce between the red or blue switches

    public Sensors(HardwareMap hardwareMap){
        Red = hardwareMap.get(DigitalChannel.class, "R"); //Defines the switches on the robot
        Blue = hardwareMap.get(DigitalChannel.class, "B");
    }

    public int Update_Red_Blue(){
        if (Red.getState()) { //Gets the state of the red switch
            Red_Blue = 1;
        }
        if (Blue.getState()) { //Gets the state of the red switch
            Red_Blue = 0;
        }
        return Red_Blue;
    }
}
