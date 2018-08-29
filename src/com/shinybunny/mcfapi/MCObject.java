package com.shinybunny.mcfapi;

public abstract class MCObject {


    protected void updateProperty(String property, Object value) {
        this.postCommand(getCommand() + " " + getUpdateSyntax(property,value));
    }

    protected void getProperty(String property) {
        this.postCommand(getCommand() + " " + getGetterSyntax(property));
    }

    protected abstract String getGetterSyntax(String property);

    public void remove() {
        this.postCommand(getCommand() + " " + getDeletionSyntax());
    }

    protected void postCommand(String cmd) {
        DatapackManager.writeCommand(cmd);
    }

    protected abstract String getCommand();

    protected abstract String getUpdateSyntax(String property, Object value);

    protected abstract String getCreationSyntax();

    protected abstract String getDeletionSyntax();

    protected void create() {
        this.postCommand(getCommand() + " " + getCreationSyntax());
    }
}
