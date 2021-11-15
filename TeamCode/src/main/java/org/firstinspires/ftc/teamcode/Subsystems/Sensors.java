package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Sensors {
    DigitalChannel Warehouse_Turntable; //Sets the variable of a switch on the robot // fixme: which switch?
    DigitalChannel alliance_s; //Sets the variable of a switch on the robot //fixme: Which switch?
    boolean WT;
    boolean A;

    public Sensors(HardwareMap hardwareMap){
        Warehouse_Turntable = hardwareMap.get(DigitalChannel.class, "WT"); //Defines the switches on the robot
        alliance_s = hardwareMap.get(DigitalChannel.class, "A"); //Defines the switches on the robot
    }

    public boolean[] Update_Red_Blue(){
        WT = Warehouse_Turntable.getState(); // Sees if the switches are on.
        A = alliance_s.getState();

        /*IMPORTANT:
                                IMPORTANT VERY VERY VERY!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
                                    WAREHOUSE/TURNTABLE SWITCH:
                                        IF ON THE ROBOT IS WAREHOUSE SIDE
                                        IF OFF THE ROBOT IS ON THE TURNTABLE SIDE

                                    ALLIANCE SWITCH:
                                        IF ON THE ROBOT IS ON THE RED ALLIANCE
                                        IF ON THE ROBOT IS ON THE BLUE ALLIANCE //fixme: both are on?
         */

        return new boolean[] {WT, A}; //Allows the main program or others using this function to access the variables
    }
}
