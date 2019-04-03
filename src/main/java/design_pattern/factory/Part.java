package design_pattern.factory;
import design_pattern.factory.parts.Vision;
import design_pattern.factory.parts.KeyBoard;
import java.util.ArrayList;
import java.util.List;

/**
 * 组件类
 */
public class Part {
    private static List<Factory<? extends Part>> allPartFactories = new ArrayList<>();
    static {
        allPartFactories.add(new Vision.VisionFactory());
        allPartFactories.add(new KeyBoard.KeyBoardFactory());
    }

    public static Factory<? extends Part> getVisionFactory(){
        return allPartFactories.get(0);
    }

    public static Factory<? extends Part> getKeyBoardFactory(){
        return allPartFactories.get(1);
    }
}
