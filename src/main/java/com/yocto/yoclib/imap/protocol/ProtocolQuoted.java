package com.yocto.yoclib.imap.protocol;

public class ProtocolQuoted extends ProtocolString {

    public ProtocolQuoted(String value){
        super(value);
        // TODO: Add checks
    }

    @Override
    public String toProtocolString(){
        return ProtocolConstants.DQUOTE+this.value.replace(Character.toString(ProtocolConstants.BACKSLASH),ProtocolConstants.BACKSLASH+Character.toString(ProtocolConstants.BACKSLASH)).replace(Character.toString(ProtocolConstants.DQUOTE),ProtocolConstants.BACKSLASH +Character.toString(ProtocolConstants.DQUOTE))+ ProtocolConstants.DQUOTE;
    }

    @Override
    public String toString() {
        return "ProtocolQuoted{" +
                "value='" + value + '\'' +
                '}';
    }

}