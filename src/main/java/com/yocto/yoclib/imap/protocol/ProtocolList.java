package com.yocto.yoclib.imap.protocol;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ProtocolList extends ProtocolObject {

    private final ProtocolObject[] value;

    public ProtocolList(ProtocolObject[] value){
        this.value = value;
    }

    public ProtocolObject[] getValue() {
        return this.value;
    }

    @Override
    public String toProtocolString(){
        return "("+Arrays.stream(this.value).map(ProtocolObject::toProtocolString).collect(Collectors.joining(" "))+")";
    }

    @Override
    public String toString() {
        return "ProtocolList{" +
                "value=" + Arrays.toString(value) +
                '}';
    }

}