package com.yocto.yoclib.imap.protocol;

public class ProtocolQuoted extends ProtocolString {

    public ProtocolQuoted(String value){
        super(value);
        for(char c : value.toCharArray()){
            if(!((c>=0x01 && c<=0x09) || (c>=0x0B && c<=0x0C) || (c>=0x0E && c<=0x21) || (c>=0x23 && c<=0x5B) || (c>=0x5D && c<=0x7F)) && c!= ProtocolConstants.DQUOTE && c!= ProtocolConstants.BACKSLASH){
                throw new RuntimeException("Invalid characters for quoted.");
            }
        }
    }

    @Override
    public String toProtocolString(){
        return ProtocolConstants.DQUOTE+this.value.replaceAll("\\\\","\\\\\\\\").replaceAll("\"","\\\\\"")+ ProtocolConstants.DQUOTE;
    }

    @Override
    public String toString() {
        return "ProtocolQuoted{" +
                "value='" + value + '\'' +
                '}';
    }

}