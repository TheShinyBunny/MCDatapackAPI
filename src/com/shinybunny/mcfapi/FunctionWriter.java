package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.entity.Player;
import com.shinybunny.mcfapi.execute.Execute;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FunctionWriter extends Player {

    private MCFunction currentMCF;
    private Execute currentExecute;
    private Segment currentTextComponent;
    private boolean textComponentHandled;
    private File currentFile;
    private File currentFolder;
    private FunctionContainer container;

    public void createFunctions(FunctionContainer container) {
        DatapackManager.setFunctionWriter(this);
        this.container = container;
        currentFolder = container.getNamespace().getFunctionsFolder();
        compile(currentFolder,container);
    }

    private void compile(File folder, Object instance) {
        currentFolder = folder;
        for (Method m : instance.getClass().getDeclaredMethods()) {
            System.out.println("method: " + m);
            if (!m.getName().startsWith("lambda")) {
                compile(instance,m);
            }
        }
        for (Class<?> c : instance.getClass().getDeclaredClasses()) {
            if (FunctionContainer.class.isAssignableFrom(c)) {
                MCFFolder f = c.getAnnotation(MCFFolder.class);
                if (f == null) {
                    continue;
                }
                File subFolder = new File(folder,f.value());
                if (!currentFolder.exists()) {
                    currentFolder.mkdirs();
                }
                try {
                    compile(subFolder,c.getConstructor(Namespace.class).newInstance(container.getNamespace()));
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void write(String command) {
        if (currentMCF == null) {
            throw new IllegalStateException("No current MCFunction!");
        }
        if (currentTextComponent != null) {
            if (textComponentHandled) {
                throw new IllegalStateException("Cannot run more than 1 command in a text component click action!");
            } else {
                currentTextComponent.setClickValue(command);
                textComponentHandled = true;
                return;
            }
        }
        textComponentHandled = false;
        if (currentExecute == null) {
            addCommand(command);
        } else {
            addCommand(currentExecute.toString() + " run " + command);
        }
    }

    public void setCurrentExecute(Execute currentExecute) {
        this.currentExecute = currentExecute;
    }

    public void setCurrentTextComponent(Segment currentTextComponent) {
        this.currentTextComponent = currentTextComponent;
    }

    private void addCommand(String command) {
        if (currentFile == null) {
            currentFile = new File(currentFolder,currentMCF.value() + ".mcfunction");
            currentFile.delete();
        }
        if (!currentFile.exists()) {
            try {
                currentFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter w = new BufferedWriter(new FileWriter(currentFile,true))) {
            w.newLine();
            w.write(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(currentMCF.value() + ": " + command);
    }

    private void compile(Object o, Method m) {
        System.out.println("compiling " + m);
        MCFunction f = m.getAnnotation(MCFunction.class);
        if (f != null) {
            currentFile = null;
            currentMCF = f;
            try {
                m.invoke(o);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
