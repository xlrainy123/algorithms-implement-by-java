package list;

import java.util.HashMap;
import java.util.Map;

public class LinkList {
    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val){
            this.val = val;
        }

        public String toString(){
            return this.val+"";
        }
    }

    public ListNode head;

    public LinkList(ListNode head){
        this.head = head;
    }



    public ListNode copy(ListNode head, Map<Object, Object> map){

        if (head == null) return null;
        ListNode newHead = new ListNode(head.val);
        map.put(newHead, head);
        newHead.next = copy(head.next, map);

        return newHead;
    }


    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        LinkList list = new LinkList(node1);
        Map<Object, Object> map = new HashMap<>();
        ListNode newHead = list.copy(node1, map);
        while(newHead != null){
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
        System.out.println(map);
    }
}
