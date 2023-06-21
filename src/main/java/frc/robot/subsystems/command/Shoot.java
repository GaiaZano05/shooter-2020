package frc.robot.subsystems.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Ports;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
    private final Shooter shooter = new Shooter();
    private final int velocity;
//
    public Shoot(int velocity) {
        this.velocity = velocity;
        addRequirements(shooter);
    }
//
//    @Override
//    public void execute() {
//        shooter.setVelocity(velocity);
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//        shooter.stop();
//    }

}
