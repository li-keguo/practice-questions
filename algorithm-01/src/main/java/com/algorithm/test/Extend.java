package com.algorithm.test;

/**
 * Extend.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
class P {
    public String p = "P";
    public static String sP = "SP";
    public void f() {System.out.print("Pf ");}
    public static void sF() {System.out.print("PsF ");}
}
class C extends P {
    public String p = "c";
    public static String sP = "SC";
    public void f() {System.out.print("Cf ");}
    public static void sF() {System.out.print("CsF ");}
}
public class Extend {
    public static void main(String[] args) {
        rP(new C());
        rP(new P());
    }
    static void rP(P p){
        System.out.print(p.p+" ");
        System.out.print(p.sP+" ");
        p.sF();
        p.f();
        System.out.println();
    }
}
