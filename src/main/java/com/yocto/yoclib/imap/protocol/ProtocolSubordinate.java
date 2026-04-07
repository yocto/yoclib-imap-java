package com.yocto.yoclib.imap.protocol;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ProtocolSubordinate extends ProtocolObject {

    private final ProtocolObject[] value;

    public ProtocolSubordinate(ProtocolObject[] value){
        this.value = value;
    }

    public ProtocolObject[] getValue() {
        return this.value;
    }

    @Override
    public String toProtocolString(){
        return ProtocolConstants.SQUARE_BRACKET_LEFT+Arrays.stream(this.value).map(ProtocolObject::toProtocolString).collect(Collectors.joining(Character.toString(ProtocolConstants.SPACE)))+ProtocolConstants.SQUARE_BRACKET_RIGHT;
    }

    @Override
    public String toString() {
        return "ProtocolSubordinate{" +
                "value=" + Arrays.toString(value) +
                '}';
    }

}