package com.yocto.yoclib.imap.protocol;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class ProtocolObject {

    public abstract String toProtocolString();

    public ProtocolList asList(boolean deeper){
        if(!deeper && this instanceof ProtocolList){
            return (ProtocolList) this;
        }
        return new ProtocolList(new ProtocolObject[]{
                this,
        });
    }

    @Override
    public String toString() {
        return "ProtocolObject{}";
    }

    public static String joinObjects(ProtocolObject[] objects){
        return Arrays.stream(objects).map(ProtocolObject::toProtocolString).collect(Collectors.joining(Character.toString(ProtocolConstants.SPACE)));
    }

}