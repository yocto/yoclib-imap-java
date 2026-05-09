package com.yocto.yoclib.imap.protocol;

import java.util.Objects;

public class ProtocolSectionPartial extends ProtocolObject{

    private final ProtocolAtom atom;
    private final ProtocolSubordinate subordinate;
    private final Integer partialOffset;
    private final Integer partialLength;

    public ProtocolSectionPartial(ProtocolAtom atom,ProtocolSubordinate subordinate,Integer partialOffset,Integer partialLength){
        this.atom = atom;
        this.subordinate = subordinate;
        this.partialOffset = partialOffset;
        this.partialLength = partialLength;
        if(this.atom==null){
            throw new RuntimeException("Atom cannot be null.");
        }
        if(this.partialOffset==null && this.partialLength!=null){
            throw new RuntimeException("Offset cannot be null.");
        }
    }

    public ProtocolSectionPartial(ProtocolAtom atom,ProtocolSubordinate subordinate,Integer partialOffset){
        this(atom,subordinate,partialOffset,null);
    }

    public ProtocolSectionPartial(ProtocolAtom atom,ProtocolSubordinate subordinate){
        this(atom,subordinate,null,null);
    }

    public ProtocolSectionPartial(ProtocolAtom atom,Integer partialOffset,Integer partialLength){
        this(atom,null,partialOffset,partialLength);
    }

    public ProtocolSectionPartial(ProtocolAtom atom,Integer partialOffset){
        this(atom,null,partialOffset,null);
    }

    public ProtocolSectionPartial(ProtocolAtom atom){
        this(atom,null,null,null);
    }

    public ProtocolAtom getAtom() {
        return this.atom;
    }

    public ProtocolSubordinate getSubordinate() {
        return this.subordinate;
    }

    public Integer getPartialOffset() {
        return this.partialOffset;
    }

    public Integer getPartialLength() {
        return this.partialLength;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProtocolSectionPartial)) return false;
        ProtocolSectionPartial that = (ProtocolSectionPartial) o;
        return Objects.equals(this.atom, that.atom) && Objects.equals(this.subordinate, that.subordinate) && Objects.equals(this.partialOffset, that.partialOffset) && Objects.equals(this.partialLength, that.partialLength);
    }

    @Override
    public String toProtocolString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.atom.toProtocolString());
        if(this.subordinate!=null){
            sb.append(this.subordinate.toProtocolString());
        }
        if(this.partialOffset!=null){
            sb.append("<");
            sb.append(this.partialOffset);
            if(this.partialLength!=null){
                sb.append(".");
                sb.append(this.partialLength);
            }
            sb.append(">");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ProtocolSectionPartial{" +
                "atom=" + atom +
                ", subordinate=" + subordinate +
                ", partialOffset=" + partialOffset +
                ", partialLength=" + partialLength +
                '}';
    }

}