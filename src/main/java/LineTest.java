import scala.Int;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class LineTest {


    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Integer l = Integer.valueOf(input);
        String start = null;
        String temp = null;
        Integer i = 0;
        for (; i < l; i++){
            String input1 = br.readLine();
            String[] s1 = input1.split(" ");
            if (i.equals(0)) {
                start = s1[0];
                temp = s1[1];
                continue;
            }
            if (!(temp.equals(s1[0]))) {
                System.out.print("false");
            }
            temp = s1[1];
            if (i.equals(l-1)) {
                if (temp.equals(start)) {
                    System.out.print("true");
                }
            }
        }

    }

//    public static void s (String[] args) throws java.lang.Exception
//    {
//        int size = 0;
//        String[] s = input.split(" ");
//        String[] queue = new String[Integer.valueOf(s[1])];
//        int dir = 0;
//        for (int i = 0; i< Integer.valueOf(s[0]); i++) {
//            String input1 = br.readLine();
//            String[] s1 = input1.split(" ");
//            switch (s1[0]) {
//                case "OFFER":
//                    if (size < queue.length) {
//                        if (dir > size) {
//                            dir = 0;
//                        }
//                        queue[size] = s1[1];
//                        size++;
//                        System.out.println("true");
//                    } else {
//                        System.out.println("false");
//                    }
//                    break;
//                case "TAKE":
//                    System.out.println(queue[dir]);
//                    queue[dir] = null;
//                    dir++;
//                    break;
//                case "SIZE":
//                    System.out.println(size);
//                    break;
//                default:
//                    System.out.println("ERROR");
//            }
//        }
//
//
//    }

//    String[] s = input.split(" ");
//    int[] num = new int[s.length];
//    boolean find = false;
//    Integer result = Integer.valueOf(br.readLine());
//        for (int j =0; j< s.length; j++) {
//        num[j] = Integer.valueOf(s[j]);
//    }
//        for (int k =0; k<num.length; k++) {
//        for (int z = k+1; z< num.length; z++) {
//            if (result.equals(num[k]+num[z])) {
//                System.out.print(num[k] + " " + num[z]);
//                find = true;
//            }
//        }
//    }
//        if (!find)
//            System.out.print(-1);
}


