package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RevTestTeleOp {
    private DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;

    public void init(HardwareMap hmMap) {
        frontLeftMotor = hmMap.get(DcMotor.class, "front_left_motor");
        backLeftMotor = hmMap.get(DcMotor.class, "back_left_motor");
        frontRightMotor = hmMap.get(DcMotor.class, "front_right_motor");
        backRightMotor = hmMap.get(DcMotor.class, "back_right_motor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //frontLeftMotor.setMode(DcMotor. lihlkj

    }

}
