import java.util.*;

public class Practice {
    public static void main(String[] args) {
        generateParenthesis(3).stream().forEach(x -> System.out.print(x));
        System.out.println(mostDuplicateSum(new int[] {1,2,3,4,5,6,7,8,9}));
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

        public void traversePostOrder(TreeNode node) {
            if (node != null) {
                traversePostOrder(node.left);
                traversePostOrder(node.right);
                System.out.print(" " + node.value);
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

    public boolean isValidBST (TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.remove();
            if (node.left != null) {
                if (node.left.value > node.value) {
                    return false;
                }
                q.add(node.left);
            }
            if (node.right != null) {
                if (node.value > node.right.value) {
                    return false;
                }
                q.add(root.right);
            }
        }
        return true;
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public void rotate(int[][] matrix) {

        int size = matrix.length;
        for(int i = 0 ; i < (size + 1) / 2 ; i++){
            for(int j = 0 ; j < size / 2 ; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[size - j - 1][i];
                matrix[size - j - 1][i] = matrix[size - i - 1][size - j - 1];
                matrix[size - i - 1][size - j - 1] = matrix[j][size - i - 1];
                matrix[j][size - i - 1] = temp;

            }
        }

    }

    public static int mostDuplicateSum (int[] numbers) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++){
            for (int j = i+1; j < numbers.length; j ++) {
                int sum = numbers[i] + numbers[j];
                if (map.containsKey(sum)) {
                    int temp = map.get(sum)+1;
                    map.put(sum, temp);
                } else {
                    map.put(sum, 1);
                }
            }
        }
        return map.values().stream().max(Integer::compare).get();
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root != null) {
            results.addAll(inorderTraversal(root.left));
            results.add(root.value);
            results.addAll(inorderTraversal(root.right));
        }
        return results;
    }

}
