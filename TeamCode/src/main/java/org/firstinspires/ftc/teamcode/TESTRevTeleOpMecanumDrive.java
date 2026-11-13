package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class TESTRevTeleOpMecanumDrive {
    private DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;
    private static IMU imu;

    public void init(HardwareMap hmMap) {
        frontLeftMotor = hmMap.get(DcMotor.class, "frontLeft");
        backLeftMotor = hmMap.get(DcMotor.class, "backLeft");
        frontRightMotor = hmMap.get(DcMotor.class, "frontRight");
        backRightMotor = hmMap.get(DcMotor.class, "backRight");

        //Reverse ALL motors for mecanum drivetrain
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //Achieve same speed/velocity across all motors regardless of friction on motor
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //built in imu on REV hub, NO PINPOINT NECCESSARY
        imu = hmMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                //CHECK usb facing direction on rev robot(s)
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT);

        imu.initialize(new IMU.Parameters(RevOrientation));
    }

    public void drive(double forward, double strafe, double rotate) {
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
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
        //can remove maxSpeed if driver is the only one driving
        frontLeftMotor.setPower(maxSpeed*(frontLeftPower/maxPower));
        backLeftMotor.setPower(maxSpeed*(backLeftPower/maxPower));
        frontRightMotor.setPower(maxSpeed*(frontRightPower/maxPower));
        backRightMotor.setPower(maxSpeed*(backRightPower/maxPower));


    }

    //FIELD ORIENTATED/CENTRIC DRIVING
//    public void driveFieldRelative(double forward, double strafe, double rotate) {
//        double theta = Math.atan2(forward, strafe);
//        double r = Math.hypot(strafe, forward);
//
//        theta = AngleUnit.normalizeRadians(theta-
//                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
//
//        double newForward = r * Math.sin(theta);
//        double newStrafe = r * Math.cos(theta);
//
//        //core of field orientated driving
//        this.drive(newForward, newStrafe, rotate);
//
//    }
}
