import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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

    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    }

    public class BinaryTree {

        TreeNode root;

        // ...
        private TreeNode addRecursive(TreeNode current, int value) {
            if (current == null) {
                return new TreeNode(value);
            }

            if (value < current.value) {
                current.left = addRecursive(current.left, value);
            } else if (value > current.value) {
                current.right = addRecursive(current.right, value);
            } else {
                // value already exists
                return current;
            }

            return current;
        }

        public void add(int value) {
            root = addRecursive(root, value);
        }

        private boolean containsNodeRecursive(TreeNode current, int value) {
            if (current == null) {
                return false;
            }
            if (value == current.value) {
                return true;
            }
            return value < current.value
                    ? containsNodeRecursive(current.left, value)
                    : containsNodeRecursive(current.right, value);
        }

        public boolean containsNode(int value) {
            return containsNodeRecursive(root, value);
        }

        private TreeNode deleteRecursive(TreeNode current, int value) {
            if (current == null) {
                return null;
            }

            if (value == current.value) {
                // Node to delete found
                // ... code to delete the node will go here
                if (current.left == null && current.right == null) {
                    return null;
                }
                if (current.right == null) {
                    return current.left;
                }

                if (current.left == null) {
                    return current.right;
                }

                int smallestValue = findSmallestValue(current.right);
                current.value = smallestValue;
                current.right = deleteRecursive(current.right, smallestValue);
                return current;
            }
            if (value < current.value) {
                current.left = deleteRecursive(current.left, value);
                return current;
            }
            current.right = deleteRecursive(current.right, value);
            return current;
        }

        private int findSmallestValue(TreeNode root) {
            return root.left == null ? root.value : findSmallestValue(root.left);
        }

        public void traverseInOrder(TreeNode node) {
            if (node != null) {
                traverseInOrder(node.left);
                System.out.print(" " + node.value);
                traverseInOrder(node.right);
            }
        }

        public void traversePreOrder(TreeNode node) {
            if (node != null) {
                System.out.print(" " + node.value);
                traversePreOrder(node.left);
                traversePreOrder(node.right);
            }
        }

        public void traverseLevelOrder() {
            if (root == null) {
                return;
            }

            Queue<TreeNode> nodes = new LinkedList<>();
            nodes.add(root);

            while (!nodes.isEmpty()) {

                TreeNode node = nodes.remove();

                System.out.print(" " + node.value);

                if (node.left != null) {
                    nodes.add(node.left);
                }

                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
        }
    }
}
