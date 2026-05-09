package com.yocto.yoclib.imap.protocol;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProtocolLiteral)) return false;
        if (!super.equals(o)) return false;
        ProtocolLiteral that = (ProtocolLiteral) o;
        return isNonSynchronizing == that.isNonSynchronizing;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isNonSynchronizing);
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
                "isNonSynchronizing=" + isNonSynchronizing +
                ", value='" + value + '\'' +
                '}';
    }

}