package collection;

import generic.Person;

import java.util.*;

public class IteratorDemo {
    static class Pet implements Comparable<Pet>{
        Pet(String name, String category){
            this.name = name;
            this.category = category;
        }
        String name;
        String category;

        @Override
        public boolean equals(Object o){
            return true;
        }


        @Override
        public int compareTo(Pet obj){
            return this.name.length() - obj.name.length();
        }
        @Override
        public String toString(){
            return "{name:"+name+",category:"+category+"}";
        }
    }

    public static void main(String[] args) {
        IteratorDemo.collectionQueue();
    }

    /**
     * List比較器
     */
    public static void collectionList(){
        Integer[] arr = {4,2,1,6,10,9,7};
        Comparator<Integer> comparator = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o2 - o1;         //降序
            }
        };
        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println(Arrays.asList(arr));
    }

    /**
     * TreeSet比較器
     */
    public static void collectionSet(){
        Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        Collections.addAll(set, 2,3,1,6,44,23,2,2,2,2);
        for (Integer ele : set){
            System.out.println(ele);
        }

        Set<Pet> petSet = new TreeSet<>();
        petSet.add(new Pet("zhang","12"));
        petSet.add(new Pet("chen","12"));
        petSet.add(new Pet("qi","12"));
        petSet.add(new Pet("qi","12"));
        System.out.println(petSet);

        Set<Pet> petSet2 = new HashSet<>();
        petSet2.add(new Pet("zhang","12"));
        petSet2.add(new Pet("chen","12"));
        petSet2.add(new Pet("qi","12"));
        petSet2.add(new Pet("qi","12"));
        System.out.println(petSet2);
    }

    /**
     * 隊列,優先級隊列
     */
    public static void collectionQueue(){
        /**
         * 在PriorityQueue的構造函數內傳入的比較器會覆蓋掉Pet實現的比較器接口
         */
        Queue<Pet> queue = new PriorityQueue<>(new Comparator<Pet>() {
            @Override
            public int compare(Pet o1, Pet o2) {
                return o2.name.length() - o1.name.length();
            }
        });
        queue.offer(new Pet("zhang","12"));
        queue.offer(new Pet("zhan","12"));
        queue.offer(new Pet("zha","12"));
        Iterator<Pet> it = queue.iterator();
        while (queue.peek() != null){
            System.out.println(queue.poll());
        }
    }


    public static void iterator(){
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("zhangsy","1"));
        pets.add(new Pet("xlcheng","2"));
        pets.add(new Pet("son","3"));

        for (Pet pet : pets){
            System.out.println(pet);
        }

        Iterator<Pet> petIterator = pets.iterator();
        while (petIterator.hasNext()) {
            Pet pet = petIterator.next();
            System.out.println(pet);
            if ("3".equals(pet.category)){
                petIterator.remove();
            }
        }
        System.out.println(pets);
    }
}
