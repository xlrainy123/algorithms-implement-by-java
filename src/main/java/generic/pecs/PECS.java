package generic.pecs;

import java.util.ArrayList;
import java.util.List;

public class PECS {

    /**
     * 不可以往一个 ? extends Session 的容器里写任何的值，但是可以读
     * @param args
     */
    public static void main(String[] args){
        List<Spring> springs = new ArrayList<>();
        springs.add(new Spring());
        List<? extends Session> sessions = springs;
        System.out.println(sessions.get(0));
    }
}
