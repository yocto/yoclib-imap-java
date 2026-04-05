package com.yocto.yoclib.imap.protocol;

public class IMAPProtocolLiteral extends IMAPProtocolString{

    private final int length;

    protected IMAPProtocolLiteral(int length,String value){
        super(value);
        this.length = length;
        // TODO: Add checks
    }

    public IMAPProtocolLiteral(int length){
        this(length,null);
    }

    public IMAPProtocolLiteral(String value){
        this(value.length(),value);
    }

    @Override
    public String toProtocolString() {
        return "{"+this.length+"}\r\n"+this.value;
    }

    @Override
    public String toString() {
        return "IMAPProtocolLiteral{" +
                "value='" + value + '\'' +
                '}';
    }

}