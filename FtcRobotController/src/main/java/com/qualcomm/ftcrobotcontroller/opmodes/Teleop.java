package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by rohanchalla on 9/12/15.
 */
public class Teleop extends OpMode
{
    DcMotor rightDrive, leftDrive, intake, lift;

    @Override
    public void init()
    {
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        intake = hardwareMap.dcMotor.get("intake");
        lift = hardwareMap.dcMotor.get("lift");
        intake.setDirection(DcMotor.Direction.REVERSE);

        rightDrive.setPower(0.0);
        leftDrive.setPower(0.0);
        intake.setPower(0.0);
    }

    @Override
    public void loop()
    {
        drive();
        checkButtons();

    }

    public void drive()
    {
        float direction = -gamepad1.left_stick_y;
        float throttle = gamepad1.right_stick_x;
        float right = throttle - direction;
        float left = throttle + direction;

        // clip the right/left values so that the values never exceed +/- 1
        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        if (Math.abs(right) < 0.1)
        {
            right = 0;
        }

        if (Math.abs(left) < 0.1)
        {
            left = 0;
        }

        // write the values to the motors
        rightDrive.setPower(right);
        leftDrive.setPower(left);
    }

    public void checkButtons()
    {
        if(gamepad1.right_bumper)
        {
            lift.setPower(1.0);

        }
        else if(gamepad1.left_bumper)
        {
            lift.setPower(-1.0);
        }
        else
        {
            lift.setPower(0.0);
        }

        if(gamepad1.a)
        {
            intake.setPower(1.0);
        }
        else if(gamepad1.b)
        {
            intake.setPower(0.0);
        }
    }

}
