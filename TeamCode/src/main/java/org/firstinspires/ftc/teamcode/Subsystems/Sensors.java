package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Sensors {
    DigitalChannel Warehouse_Turntable; //Sets the varible of a switch on the robot
    DigitalChannel alliance_s; //Sets the varible of a switch on the robot
    boolean WT;
    boolean A;

    public Sensors(HardwareMap hardwareMap){
        Warehouse_Turntable = hardwareMap.get(DigitalChannel.class, "WT"); //Defines the switches on the robot
        alliance_s = hardwareMap.get(DigitalChannel.class, "A");
    }

    public boolean[] Update_Red_Blue(){
        WT = Warehouse_Turntable.getState();
        A = alliance_s.getState();

        return new boolean[] {WT, A};
    }
}
