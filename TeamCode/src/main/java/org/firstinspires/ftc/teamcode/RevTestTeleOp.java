package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class RevTestTeleOp {
    private DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;
    private IMU imu;

    public void init(HardwareMap hmMap) {
        frontLeftMotor = hmMap.get(DcMotor.class, "front_left_motor");
        backLeftMotor = hmMap.get(DcMotor.class, "back_left_motor");
        frontRightMotor = hmMap.get(DcMotor.class, "front_right_motor");
        backRightMotor = hmMap.get(DcMotor.class, "back_right_motor");

        //Reverse for mecanum drivetrain
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //Achieve same speed/velocity across all motors regardless of friction on motor
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //built in imu on REV hub
        imu = hmMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                //check usb facing direction on rev robot(s)
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT);

        imu.initialize(new IMU.Parameters(RevOrientation));
    }

    public void drive(double forward, double strafe, double rotate) {
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe - rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = 1.0;
        //if others will be driving robot(ex: outreach) , use maxSpeed
        double maxSpeed = 1.0;

        //checks if maxPower is greater/less than or equal to motor powers to create ratio between all motor powers
        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        //set motor powers
        frontLeftMotor.setPower(maxSpeed*(frontLeftPower/maxPower));
        backLeftMotor.setPower(maxSpeed*(backLeftPower/maxPower));
        frontRightMotor.setPower(maxSpeed*(frontRightPower/maxPower));
        backRightMotor.setPower(maxSpeed*(backRightPower/maxPower));

    }

    //FIELD ORIENTATED DRIVING
    public void driveFieldRelative(double forward, double strafe, double rotate) {

    }
}
