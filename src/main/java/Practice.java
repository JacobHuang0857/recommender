import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Practice {
    public static void main(String[] args) {
        generateParenthesis(3).stream().forEach(x -> System.out.print(x));
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

    public static List<String> generateParenthesis (int n) {
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            int j = 0;
            while (j<i) {
                int k = j;
                for (; k<i; k++){
                    stack.push("(");
                }
                for (; k>0; k--) {
                    stack.push(")");
                }
                j++;
            }
            while(j<n){
                int z = j;
                for (; z<n; z++){
                    stack.push("(");
                }
                for (; z>j; z--) {
                    stack.push(")");
                }
                j++;
            }
        }
        stack.forEach(x-> result.add(x));
        return result;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        int a = sum / 10;
        int b = sum % 10;
        if (l1.next == null && l2.next == null){
            ListNode listNode = new ListNode(b);
            if (a != 0) {
                ListNode next = new ListNode(a);
                listNode.next = next;
            }
            return  listNode;
        }
        ListNode listNode = new ListNode(b);
        if (a != 0){
            l1.next.val += a;
        }
        listNode.next = addTwoNumbers(l1.next, l2.next);
        return listNode;
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((s.length() - i) <= max )
                break;
            Stack<Character> chars = new Stack<Character>();
            for (int j = i; j < s.length(); j++) {
                if(!chars.contains(s.charAt(j)))
                    chars.push(s.charAt(j));
                else {
                    if (chars.size() > max)
                        max = chars.size();
                    break;
                }
            }
        }
        return max;
    }

}
