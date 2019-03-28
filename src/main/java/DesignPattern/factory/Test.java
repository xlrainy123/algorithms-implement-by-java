package DesignPattern.factory;

import DesignPattern.factory.parts.Vision;

public class Test {


    public static void main(String[] args){
        Factory factory = Part.getVisionFactory();
        factory.create();

        factory = Part.getKeyBoardFactory();
        factory.create();
    }
}
