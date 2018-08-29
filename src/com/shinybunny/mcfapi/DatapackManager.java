package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.server.Datapack;

public class DatapackManager {

    private static FunctionWriter writer;
    private static Datapack datapack;

    public static void setDatapack(Datapack datapack) {
        DatapackManager.datapack = datapack;
    }

    public static void setFunctionWriter(FunctionWriter container) {
        DatapackManager.writer = container;
    }

    public static void writeCommand(String command) {
        if (writer != null) {
            writer.write(command);
        }
    }

    public static FunctionWriter getFunctionWriter() {
        return writer;
    }

    public static Datapack getDatapack() {
        return datapack;
    }
}
