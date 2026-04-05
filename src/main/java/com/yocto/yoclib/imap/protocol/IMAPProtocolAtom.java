package com.yocto.yoclib.imap.protocol;

public class IMAPProtocolAtom extends IMAPProtocolString{

    public IMAPProtocolAtom(String value){
        super(value);
        // TODO: Add checks
    }

    @Override
    public String toProtocolString(){
        return this.value;
    }

    @Override
    public String toString() {
        return "IMAPProtocolAtom{" +
                "value='" + value + '\'' +
                '}';
    }

}