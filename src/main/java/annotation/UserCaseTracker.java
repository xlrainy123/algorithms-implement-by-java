package annotation;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserCaseTracker {

    public static void tracker(List<Integer> userCaseIds, Class<?> cl){
        Method[] methods =  cl.getDeclaredMethods();
        for (Method method : methods){
            UserCase userCase = method.getAnnotation(UserCase.class);
            if (userCase == null){
                continue;
            }
            System.out.println("userCase.id:"+userCase.id()+
                    ", userCase.description:"+userCase.description());
            userCaseIds.remove(new Integer(userCase.id()));
            System.out.println(userCaseIds);
        }
        System.out.println(userCaseIds);
    }

    public static void main(String[] args){
        List<Integer> ids = new ArrayList<>();
        Collections.addAll(ids,1,2,3,4);
        tracker(ids, PasswordUtils.class);
        Collections.reverse(ids);
    }
}
