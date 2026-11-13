package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TESTRevMecanumFieldOrientationOpMode extends OpMode {

    TESTRevTeleOpMecanumDrive drive = new TESTRevTeleOpMecanumDrive();
    double forward, strafe, rotate;
    private Object driveFieldRelative;

    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        forward = gamepad1.left_stick_y;
        //strafe is left stick
        strafe = -gamepad1.right_stick_x;
        //rotate is right stick
        rotate = -gamepad1.left_stick_x;

        drive.drive(forward, strafe, rotate);


    }
}
