package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Sensors {
    DigitalChannel Red; //Sets the varible of a switch on the robot fixme: one input should be for alliance side and another for warehouse or turntable start. Call this one alliance switch.
    DigitalChannel Blue; //Sets the varible of a switch on the robot fixme: red/blue is the determination from the switches. In other words, one switch has 2 different states, on (red) or off (blue)
   //fixme: The other digital channel should be field side - on=warehouse off=turntable.
    public int Red_Blue; // Makes a varible to find the differnce between the red or blue switches fixme: call this Alliance instead

    public Sensors(HardwareMap hardwareMap){
        Red = hardwareMap.get(DigitalChannel.class, "R"); //Defines the switches on the robot
        Blue = hardwareMap.get(DigitalChannel.class, "B");
    }

    public int Update_Red_Blue(){
        if (Red.getState()) { //Gets the state of the red switch
            Red_Blue = 1; // fixme: this logic changes slightly:
            // if(alliance.getState()
            //alliance=blue
            //else
            //alliance=red
        }
        if (Blue.getState()) { //Gets the state of the red switch
            Red_Blue = 0; //fixme: same thing here
        }
        return Red_Blue;
    }
}
