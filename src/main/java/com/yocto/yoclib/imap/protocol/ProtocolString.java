package com.yocto.yoclib.imap.protocol;

public abstract class ProtocolString extends ProtocolObject {

    protected final String value;

    protected ProtocolString(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public abstract String toProtocolString();

    @Override
    public String toString() {
        return "ProtocolString{" +
                "value='" + value + '\'' +
                '}';
    }

}