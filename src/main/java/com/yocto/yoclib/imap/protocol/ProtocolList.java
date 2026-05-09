package com.yocto.yoclib.imap.protocol;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProtocolList extends ProtocolObject {

    private final ProtocolObject[] objects;

    public ProtocolList(ProtocolObject[] objects){
        this.objects = objects;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProtocolList)) return false;
        ProtocolList that = (ProtocolList) o;
        return Objects.deepEquals(objects, that.objects);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(objects);
    }

    public ProtocolObject[] getObjects() {
        return this.objects;
    }

    @Override
    public String toProtocolString(){
        return ProtocolConstants.PARENTHESIS_LEFT+ProtocolObject.joinObjects(this.objects)+ProtocolConstants.PARENTHESIS_RIGHT;
    }

    @Override
    public String toString() {
        return "ProtocolList{" +
                "objects=" + Arrays.toString(this.objects) +
                '}';
    }

}