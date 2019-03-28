package DesignPattern.factory.parts;

import DesignPattern.factory.Factory;
import DesignPattern.factory.Part;

public class KeyBoard extends Part {
    public static class KeyBoardFactory implements Factory<KeyBoard> {
        public KeyBoard create(){
            System.out.println("create KeyBoard");
            return new KeyBoard();
        }
    }
}
