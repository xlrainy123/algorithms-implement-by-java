package design_pattern.factory.parts;

import design_pattern.factory.Factory;
import design_pattern.factory.Part;

public class Vision extends Part {
    public static class VisionFactory implements Factory<Vision> {
        public Vision create(){
            System.out.println("create Vision");
            return new Vision();
        }
    }
}
