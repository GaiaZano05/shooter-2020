package frc.robot;

public final class Ports {
    public static final int TALON_PID_SLOT = 0;

    public static class Shooter {
        public static final int MASTER = 23;
        public static final int SLAVE_1 = 24;
        public static final int SLAVE_2 = 25;
        public static final boolean IS_MASTER_INVERTED = false;
        public static final boolean IS_SLAVE_1_INVERTED = true;
        public static final boolean IS_SLAVE_2_INVERTED = true;
        public static final boolean IS_ENCODER_INVERTED = true;
    }

    public static class UI {
        public static final int JOYSTICK_TRIGGER = 1;
    }
}
