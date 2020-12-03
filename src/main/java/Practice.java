import java.util.HashMap;
import java.util.Map;

public class Practice {
    public static void main() {

    }
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1==null)
                return l2;
            else if (l2==null)
                return l1;
            if (l1.val<l2.val)
                return new ListNode(l1.val, mergeTwoLists(l1.next, l2));
            else
                return new ListNode(l2.val, mergeTwoLists(l1, l2.next));

        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    // Definition for a Node.
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    class SolutionForDeepCopy {
        public Node copyRandomList(Node head) {
            if (head == null)
                return null;
            Map<Node, Node> map = new HashMap<>();
            Node node = head;
            while (node != null){
                map.put(node, new Node(node.val));
                node = node.next;
            }

            node = head;
            while (node != null){
                map.get(node).next = map.get(node.next);
                map.get(node).random = map.get(node.random);
                node = node.next;
            }
            return map.get(head);
        }
    }

}
