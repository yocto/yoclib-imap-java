package com.yocto.yoclib.imap.protocol;

public class ProtocolAtom extends ProtocolString {

    public static final ProtocolAtom NIL = new ProtocolAtom(ProtocolConstants.NIL);

    public ProtocolAtom(String value){
        super(value);
        // TODO: Add checks
    }

    public boolean isNIL(){
        return ProtocolAtom.NIL.value.equalsIgnoreCase(this.value);
    }

    @Override
    public String toProtocolString(){
        return this.value;
    }

    @Override
    public String toString() {
        return "ProtocolAtom{" +
                "value='" + this.value + '\'' +
                '}';
    }

}