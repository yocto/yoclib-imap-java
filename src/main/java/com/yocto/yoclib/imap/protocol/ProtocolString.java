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
    public String toString() {
        return "ProtocolString{" +
                "value='" + this.value + '\'' +
                '}';
    }

    public static ProtocolString create(String string){
        return ProtocolString.create(string,false);
    }

    public static ProtocolString create(String string,boolean forceQuoted){
        if(string.contains("\r") || string.contains("\n")){
            return new ProtocolLiteral(string);
        }
        if(string.contains(" ") || string.contains("\t") || string.isEmpty() || forceQuoted){
            return new ProtocolQuoted(string);
        }
        return new ProtocolAtom(string);
    }

}