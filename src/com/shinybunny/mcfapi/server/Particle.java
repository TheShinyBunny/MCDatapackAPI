package com.shinybunny.mcfapi.server;

import com.shinybunny.mcfapi.BlockItem;
import com.shinybunny.mcfapi.Coordinate;
import com.shinybunny.mcfapi.entity.PlayerList;

import java.awt.*;

public class Particle {

    private ResourceLocation id;
    private BlockItem blockItem;
    private Color dustColor;
    private Coordinate pos;
    private double deltaX;
    private double deltaY;
    private double deltaZ;
    private float speed;
    private int count;
    private Force force;
    private PlayerList viewers;
    private boolean delta;

    public Particle(ResourceLocation id) {
        this(id, (Coordinate) null);
    }

    public Particle(ResourceLocation id, BlockItem blockItem) {
        this(id,blockItem,null);
    }

    public Particle(ResourceLocation id, Color dustColor) {
        this(id,dustColor,null);
    }

    public Particle(ResourceLocation id, BlockItem blockItem, Coordinate pos) {
        this(id,blockItem,pos,0,0,0);
        delta = false;
    }

    public Particle(ResourceLocation id, Color dustColor, Coordinate pos) {
        this(id,dustColor,pos,0,0,0);
        delta = false;
    }

    public Particle(ResourceLocation id, Coordinate pos) {
        this(id,pos,0,0,0);
        delta = false;
    }

    public Particle(ResourceLocation id, BlockItem blockItem, Coordinate pos, double deltaX, double deltaY, double deltaZ) {
        this(id,blockItem,pos,deltaX,deltaY,deltaZ,0);
    }

    public Particle(ResourceLocation id, Color dustColor, Coordinate pos, double deltaX, double deltaY, double deltaZ) {
        this(id,dustColor,pos,deltaX,deltaY,deltaZ,0);
    }

    public Particle(ResourceLocation id, Coordinate pos, double deltaX, double deltaY, double deltaZ) {
        this(id,pos,deltaX,deltaY,deltaZ,0);
    }

    public Particle(ResourceLocation id, BlockItem blockItem, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed) {
        this(id,blockItem,pos,deltaX,deltaY,deltaZ,speed,1);
    }

    public Particle(ResourceLocation id, Color dustColor, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed) {
        this(id,dustColor,pos,deltaX,deltaY,deltaZ,speed,1);
    }

    public Particle(ResourceLocation id, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed) {
        this(id,pos,deltaX,deltaY,deltaZ,speed,1);
    }

    public Particle(ResourceLocation id, BlockItem blockItem, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed, int count) {
        this(id,blockItem,pos,deltaX,deltaY,deltaZ,speed,count,null);
    }

    public Particle(ResourceLocation id, Color dustColor, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed, int count) {
        this(id,dustColor,pos,deltaX,deltaY,deltaZ,speed,count,null);
    }

    public Particle(ResourceLocation id, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed, int count) {
        this(id,pos,deltaX,deltaY,deltaZ,speed,count,null);
    }

    public Particle(ResourceLocation id, BlockItem blockItem, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed, int count, Force force) {
        this(id,blockItem,pos,deltaX,deltaY,deltaZ,speed,count,force,null);
    }

    public Particle(ResourceLocation id, Color dustColor, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed, int count, Force force) {
        this(id,dustColor,pos,deltaX,deltaY,deltaZ,speed,count,force,null);
    }

    public Particle(ResourceLocation id, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed, int count, Force force) {
        this(id,pos,deltaX,deltaY,deltaZ,speed,count,force,null);
    }

    public Particle(ResourceLocation id, BlockItem blockItem, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed, int count, Force force, PlayerList viewers) {
        this.id = id;
        this.blockItem = blockItem;
        this.pos = pos;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
        this.speed = speed;
        this.count = count;
        this.force = force;
        this.viewers = viewers;
        this.delta = true;
    }

    public Particle(ResourceLocation id, Color dustColor, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed, int count, Force force, PlayerList viewers) {
        this.id = id;
        this.dustColor = dustColor;
        this.pos = pos;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
        this.speed = speed;
        this.count = count;
        this.force = force;
        this.viewers = viewers;
        this.delta = true;
    }

    public Particle(ResourceLocation id, Coordinate pos, double deltaX, double deltaY, double deltaZ, float speed, int count, Force force, PlayerList viewers) {
        this(id, (BlockItem) null,pos,deltaX,deltaY,deltaZ,speed,count,force,viewers);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("particle ");
        b.append(id);
        if (blockItem != null) {
            b.append(" " + blockItem.toString());
        }
        if (dustColor != null) {
            b.append(" " + dustColor.getRed() / 255.0f + " " + dustColor.getGreen() / 255.0f + " " + dustColor.getBlue() / 255.0f + " " + dustColor.getAlpha() / 255.0f);
        }
        if (delta) {
            if (deltaX != 0 && deltaY != 0 && deltaZ != 0) {
                b.append(" " + deltaX + "" + deltaY + " " + deltaZ);
            }
        }
        if (speed != 0) {
            b.append(" " + speed);
        }
        if (count != 0) {
            b.append(" " + count);
        }
        if (force != null) {
            b.append(" " + force.toString().toLowerCase());
        }
        if (viewers != null) {
            b.append(" " + viewers);
        }
        return b.toString();
    }
}
