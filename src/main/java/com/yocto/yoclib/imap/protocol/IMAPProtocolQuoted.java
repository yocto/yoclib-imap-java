package com.yocto.yoclib.imap.protocol;

public class IMAPProtocolQuoted extends IMAPProtocolString{

    public IMAPProtocolQuoted(String value){
        super(value);
        for(char c : value.toCharArray()){
            if(!((c>=0x01 && c<=0x09) || (c>=0x0B && c<=0x0C) || (c>=0x0E && c<=0x21) || (c>=0x23 && c<=0x5B) || (c>=0x5D && c<=0x7F)) && c!=IMAPProtocolConstants.DQUOTE && c!=IMAPProtocolConstants.BACKSLASH){
                throw new RuntimeException("Invalid characters for quoted.");
            }
        }
    }

    @Override
    public String toProtocolString(){
        return IMAPProtocolConstants.DQUOTE+this.value.replaceAll("\\\\","\\\\\\\\").replaceAll("\"","\\\\\"")+IMAPProtocolConstants.DQUOTE;
    }

    @Override
    public String toString() {
        return "IMAPProtocolQuoted{" +
                "value='" + value + '\'' +
                '}';
    }

}