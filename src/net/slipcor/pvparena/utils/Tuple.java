package net.slipcor.pvparena.utils;

public class Tuple {
    private final String str;
    private final int num;

    public Tuple(String str, int num){
       this.str = str;
       this.num = num;
    }

    public String getStr(){
       return str;
    }

    public int getNum(){
        return num;
    }
}
