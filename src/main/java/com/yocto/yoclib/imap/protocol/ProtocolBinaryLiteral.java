package com.yocto.yoclib.imap.protocol;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ProtocolBinaryLiteral extends ProtocolObject {

    private final byte[] value;
    private final boolean isNonSynchronizing;

    public ProtocolBinaryLiteral(byte[] value, boolean isNonSynchronizing){
        this.value = value;
        this.isNonSynchronizing = isNonSynchronizing;
        // TODO: Add checks
    }

    public ProtocolBinaryLiteral(String value,boolean isNonSynchronizing){
        this(value.getBytes(StandardCharsets.ISO_8859_1),isNonSynchronizing);
    }

    public ProtocolBinaryLiteral(byte[] value){
        this(value,false);
    }

    public ProtocolBinaryLiteral(String value){
        this(value.getBytes(StandardCharsets.ISO_8859_1));
    }

    public byte[] getValue(){
        return this.value;
    }

    public boolean isNonSynchronizing(){
        return this.isNonSynchronizing;
    }

    @Override
    public String toProtocolString() {
        return Character.toString(ProtocolConstants.TILDE)+ProtocolConstants.LITERAL_LEFT+(this.value.length+(this.isNonSynchronizing?Character.toString(ProtocolConstants.PLUS):""))+ProtocolConstants.LITERAL_RIGHT+ProtocolConstants.CRLF+new String(this.value);
    }

    @Override
    public String toString() {
        return "ProtocolBinaryLiteral{" +
                "value='" + Base64.getEncoder().encodeToString(value) + '\'' +
                ", isNonSynchronizing=" + isNonSynchronizing +
                '}';
    }

}