package org.usfirst.frc.team6705.robot;

import org.usfirst.frc.team6705.robot.Constants;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultForwardPlaceGearAuto = "Default";
	/*this is my comment*/
	final String customAuto = "Custom Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	Spark testMotor = new Spark(0);
	
	Spark leftDriveTrain = new Spark(Constants.LEFT_MOTOR);
	Spark rightDriveTrain = new Spark(Constants.RIGHT_MOTOR);
	RobotDrive robotDrive = new RobotDrive(leftDriveTrain, rightDriveTrain);
	
	Joystick driveStick = new Joystick(Constants.DRIVE_STICK);
	Joystick rightStick = new Joystick(Constants.RIGHT_STICK);
	
	Timer timer = new Timer();


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto - Forward Place Gear", defaultForwardPlaceGearAuto);
		chooser.addObject("Auto - Custom Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultForwardPlaceGearAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		while (isOperatorControl() && isEnabled()) {
			robotDrive.tankDrive(driveStick.getRawAxis(2), driveStick.getRawAxis(5));
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		robotDrive.tankDrive(driveStick.getRawAxis(2), driveStick.getRawAxis(5));
		testMotor.set(0.5);
	}
}

