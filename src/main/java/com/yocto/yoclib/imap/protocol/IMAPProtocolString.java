package com.yocto.yoclib.imap.protocol;

public abstract class IMAPProtocolString extends IMAPProtocolObject{

    protected final String value;

    protected IMAPProtocolString(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public abstract String toProtocolString();

    @Override
    public String toString() {
        return "IMAPProtocolString{" +
                "value='" + value + '\'' +
                '}';
    }

}