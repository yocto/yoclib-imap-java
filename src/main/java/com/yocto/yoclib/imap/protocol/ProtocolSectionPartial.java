package com.yocto.yoclib.imap.protocol;

public class ProtocolSectionPartial extends ProtocolObject{

    private final ProtocolAtom atom;
    private final ProtocolSubordinate subordinate;
    private final Integer offset;
    private final Integer length;

    public ProtocolSectionPartial(ProtocolAtom atom,ProtocolSubordinate subordinate,Integer offset,Integer length){
        if(atom==null){
            throw new RuntimeException("Atom cannot be null.");
        }
        if(offset!=null && length==null){
            throw new RuntimeException("Length cannot be null.");
        }
        this.atom = atom;
        this.subordinate = subordinate;
        this.offset = offset;
        this.length = length;
    }

    public ProtocolSectionPartial(ProtocolAtom atom,ProtocolSubordinate subordinate,Integer length){
        this(atom,subordinate,null,length);
    }

    public ProtocolSectionPartial(ProtocolAtom atom,ProtocolSubordinate subordinate){
        this(atom,subordinate,null,null);
    }

    public ProtocolSectionPartial(ProtocolAtom atom,Integer offset,Integer length){
        this(atom,null,offset,length);
    }

    public ProtocolSectionPartial(ProtocolAtom atom,Integer length){
        this(atom,null,null,length);
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

    public Integer getOffset() {
        return this.offset;
    }

    public Integer getLength() {
        return this.length;
    }

    private String partialToProtocolString(){
        if(this.length!=null){
            if(this.offset!=null){
                return "<"+this.offset+"."+this.length+">";
            }
            return "<"+this.length+">";
        }
        return "";
    }

    @Override
    public String toProtocolString(){
        return this.atom.toProtocolString()+this.subordinate.toProtocolString()+this.partialToProtocolString();
    }

    @Override
    public String toString() {
        return "ProtocolSectionPartial{" +
                "atom=" + atom +
                ", subordinate=" + subordinate +
                ", offset=" + offset +
                ", length=" + length +
                '}';
    }

}