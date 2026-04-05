package com.yocto.yoclib.imap.protocol;

public abstract class IMAPProtocolObject {

    public abstract String toProtocolString();

    @Override
    public String toString() {
        return "IMAPProtocolObject{}";
    }

}