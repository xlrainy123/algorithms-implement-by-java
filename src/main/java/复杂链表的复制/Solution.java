package 复杂链表的复制;

import java.util.*;

public class Solution {

    static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * with map
     * @param head
     * @return
     */
    public RandomListNode Clone(RandomListNode head)
    {
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode newHead = copyList(head, map);
        RandomListNode current = newHead;

        while(current != null){
            current.random = map.get(current).random == null ? null :
                                new RandomListNode(map.get(current).random.label);
            current = current.next;
        }

        return newHead;
    }

    public RandomListNode copyList(RandomListNode head, Map<RandomListNode, RandomListNode> map){
        if (head == null) return null;
        RandomListNode newHead = new RandomListNode(head.label);
        map.put(newHead, head);
        newHead.next = copyList(head.next, map);
        return newHead;
    }

    /**
     * without map
     * @param head
     * @return
     */
    public RandomListNode Clone(RandomListNode head, boolean withmap) {
        RandomListNode newhead = copy(head);
        RandomListNode current = newhead;
        while (current != null){
            current.random = current.next.random;
            current = current.next.next;
        }
        current = reConstruct(newhead);
        return current;
    }

    public RandomListNode reConstruct(RandomListNode head){
        if (head == null) return null;
        RandomListNode node =head;
        RandomListNode clone = node.next;
        while(clone.next != null){
            node.next = clone.next;
            node = node.next;
            clone = node.next;
        }
        return head;
    }
    public RandomListNode copy(RandomListNode head){
        if (head == null) return null;
        RandomListNode newHead = new RandomListNode(head.label);
        newHead.next = head;
        head.next = copy(head.next);
        return newHead;
    }

}