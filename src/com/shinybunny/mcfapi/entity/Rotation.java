package com.shinybunny.mcfapi.entity;

public class Rotation {

    private float yaw;
    private boolean relativeYaw;
    private float pitch;
    private boolean relativePitch;

    public Rotation(float yaw, boolean relativeYaw, float pitch, boolean relativePitch) {
        this.yaw = yaw;
        this.relativeYaw = relativeYaw;
        this.pitch = pitch;
        this.relativePitch = relativePitch;
    }

    public static Rotation absolute(float yaw, float pitch) {
        return new Rotation(yaw,false,pitch,false);
    }

    public static Rotation relative(float yaw, float pitch) {
        return new Rotation(yaw,true,pitch,true);
    }

    @Override
    public String toString() {
        return (relativeYaw ? "~" : "") + yaw + " " + (relativePitch ? "~" : "") + pitch;
    }
}
