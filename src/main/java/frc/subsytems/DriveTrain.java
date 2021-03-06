package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.Drive;
//import frc.commands.TurnToAngle;
import frc.robot.OI;
import frc.robot.Robot;

public class DriveTrain extends Subsystem
{
    DifferentialDrive d;
    WPI_TalonSRX leftMaster, rightMaster;
    AHRS navX;

  public DriveTrain(WPI_TalonSRX left, WPI_TalonSRX right)
  {
    navX = Robot.getNavX();

    leftMaster = left;
    rightMaster = right;

    d = new DifferentialDrive(leftMaster, rightMaster);

    d.setSafetyEnabled(false);

    Robot.logNumber("DriveTrain", 1);
  }

  public void drive(Joystick stick)
  {
    d.arcadeDrive(stick.getRawAxis(1), stick.getRawAxis(4)*-1, true);
  }

  public void stop()
  {
    leftMaster.set(ControlMode.PercentOutput, 0);
    rightMaster.set(ControlMode.PercentOutput, 0);
  }

  public void turn(double input)
  {
    leftMaster.set(ControlMode.PercentOutput, input);
    rightMaster.set(ControlMode.PercentOutput, input);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}