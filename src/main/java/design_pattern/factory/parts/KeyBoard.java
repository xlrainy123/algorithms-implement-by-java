package design_pattern.factory.parts;

import design_pattern.factory.Factory;
import design_pattern.factory.Part;

public class KeyBoard extends Part {
    public static class KeyBoardFactory implements Factory<KeyBoard> {
        public KeyBoard create(){
            System.out.println("create KeyBoard");
            return new KeyBoard();
        }
    }
}
