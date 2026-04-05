package com.yocto.yoclib.imap.protocol;

public abstract class ProtocolObject {

    public abstract String toProtocolString();

    @Override
    public String toString() {
        return "ProtocolObject{}";
    }

}