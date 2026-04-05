package com.yocto.yoclib.imap.protocol;

public class ProtocolAtom extends ProtocolString {

    public ProtocolAtom(String value){
        super(value);
        // TODO: Add checks
    }

    @Override
    public String toProtocolString(){
        return this.value;
    }

    @Override
    public String toString() {
        return "ProtocolAtom{" +
                "value='" + value + '\'' +
                '}';
    }

}