package DesignPattern.factory.parts;

import DesignPattern.factory.Factory;
import DesignPattern.factory.Part;

public class Vision extends Part {
    public static class VisionFactory implements Factory<Vision> {
        public Vision create(){
            System.out.println("create Vision");
            return new Vision();
        }
    }
}
