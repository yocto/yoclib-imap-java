package com.yocto.yoclib.imap.protocol;

public class ProtocolLiteral extends ProtocolString {

    private final boolean isNonSynchronizing;

    public ProtocolLiteral(String value,boolean isNonSynchronizing){
        super(value);
        this.isNonSynchronizing = isNonSynchronizing;
        // TODO: Add checks
    }

    public ProtocolLiteral(String value){
        this(value,false);
    }

    public boolean isNonSynchronizing(){
        return this.isNonSynchronizing;
    }

    @Override
    public String toProtocolString() {
        return ProtocolConstants.LITERAL_LEFT+(this.value.length()+(this.isNonSynchronizing?Character.toString(ProtocolConstants.PLUS):""))+ProtocolConstants.LITERAL_RIGHT+ProtocolConstants.CRLF+this.value;
    }

    @Override
    public String toString() {
        return "ProtocolLiteral{" +
                "value='" + this.value + '\'' +
                '}';
    }

}