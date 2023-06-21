package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.utils.units.UnitModel;

import java.util.Set;

import static frc.robot.Constants.TALON_TIMEOUT;
import static frc.robot.Ports.Shooter.IS_SLAVE_1_INVERTED;
import static frc.robot.Ports.Shooter.IS_SLAVE_2_INVERTED;
import static frc.robot.Ports.TALON_PID_SLOT;

public class Shooter extends SubsystemBase {
//    public static Shooter INSTANCE = null;
    private final TalonSRX shooterMaster = new TalonSRX(Ports.Shooter.MASTER);
    private final UnitModel rpsUnitModel = new UnitModel(Constants.Shooter.TICKS_PER_ROTATION);

    public Shooter(){
        shooterMaster.configFactoryDefault();
        VictorSPX shooterSlave1 = new VictorSPX(Ports.Shooter.SLAVE_1);
        shooterSlave1.configFactoryDefault();
        VictorSPX shooterSlave2 = new VictorSPX(Ports.Shooter.SLAVE_2);
        shooterSlave2.configFactoryDefault();
        shooterMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, TALON_TIMEOUT);

        shooterMaster.setInverted(Ports.Shooter.IS_MASTER_INVERTED);
        shooterMaster.setSensorPhase(Ports.Shooter.IS_ENCODER_INVERTED);

        shooterMaster.config_kP(TALON_PID_SLOT, Constants.Shooter.KP, TALON_TIMEOUT);
        shooterMaster.config_kI(TALON_PID_SLOT, Constants.Shooter.KI, TALON_TIMEOUT);
        shooterMaster.config_kD(TALON_PID_SLOT, Constants.Shooter.KD, TALON_TIMEOUT);
        shooterMaster.config_kF(TALON_PID_SLOT, Constants.Shooter.KF, TALON_TIMEOUT);

        shooterMaster.enableCurrentLimit(true);

        shooterSlave1.follow(shooterMaster);
        shooterSlave2.follow(shooterMaster);
        shooterSlave1.setInverted(IS_SLAVE_1_INVERTED);
        shooterSlave2.setInverted(IS_SLAVE_2_INVERTED);
    }

//    public static Shooter getInstance(){
//        if (INSTANCE == null){
//            return new Shooter();
//        }
//        return INSTANCE;
//    }

    public double getSpeed() {
        return rpsUnitModel.toVelocity(shooterMaster.getSelectedSensorVelocity());
    }


    public void setVelocity(double velocity) {
        shooterMaster.set(ControlMode.Velocity, rpsUnitModel.toTicks100ms(velocity / 60.0));
    }


    public void setPower(double power) {
        shooterMaster.set(ControlMode.PercentOutput, power);
    }
//69
    public void stop() {
        setPower(0);
    }


}
