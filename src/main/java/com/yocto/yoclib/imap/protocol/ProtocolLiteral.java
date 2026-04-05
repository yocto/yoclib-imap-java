package com.yocto.yoclib.imap.protocol;

public class ProtocolLiteral extends ProtocolString {

    private final int length;

    protected ProtocolLiteral(int length, String value){
        super(value);
        this.length = length;
        // TODO: Add checks
    }

    public ProtocolLiteral(int length){
        this(length,null);
    }

    public ProtocolLiteral(String value){
        this(value.length(),value);
    }

    @Override
    public String toProtocolString() {
        return "{"+this.length+"}\r\n"+this.value;
    }

    @Override
    public String toString() {
        return "ProtocolLiteral{" +
                "value='" + value + '\'' +
                '}';
    }

}