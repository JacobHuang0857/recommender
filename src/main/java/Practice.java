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
            ListNode listNode = new ListNode();
            do {
                if (l1.val < l2.val)
                    listNode = l1;
                else
                    listNode = l2;
                l1 = l1.next;
                l2 = l2.next;
            } while (l1.next != null && l2.next != null);
            while (l1.next != null ) {
                listNode.next = l1;
                l1 = l1.next;
            }
            while (l2.next != null) {
                listNode.next = l2;
                l2 = l2.next;
            }
            return listNode;
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
            if (head.next == null) {
                Node copy = new Node(head.val);
                copy.random = head.random;
                return copy;
            }
            return copyRandomList(head);
        }
    }

}
