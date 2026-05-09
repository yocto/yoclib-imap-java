package com.yocto.yoclib.imap.protocol;

import java.util.Arrays;

public class ProtocolSubordinate extends ProtocolObject {

    private final ProtocolObject[] objects;

    public ProtocolSubordinate(ProtocolObject[] objects){
        this.objects = objects;
    }

    public ProtocolObject[] getObjects(){
        return this.objects;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProtocolSubordinate)) return false;
        ProtocolSubordinate that = (ProtocolSubordinate) o;
        return Arrays.deepEquals(this.objects, that.objects);
    }

    @Override
    public String toProtocolString(){
        return ProtocolConstants.SQUARE_BRACKET_LEFT+ProtocolObject.joinObjects(this.objects)+ProtocolConstants.SQUARE_BRACKET_RIGHT;
    }

    @Override
    public String toString() {
        return "ProtocolSubordinate{" +
                "objects=" + Arrays.toString(this.objects) +
                '}';
    }

}