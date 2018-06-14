package offer;

import java.util.Arrays;

public class NoRepeat {
    public void Insert(char ch) {
        if (chArr[ch] == -1) {
            chArr[ch] = index;
        } else if (chArr[ch] > 0) {
            chArr[ch] = -2;
        }
        index++;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        int index = 256;
        for (int i = 0; i < 256; i++) {
            if (chArr[i] >= 0 && i < index) {
                index = i;
            }
        }
        if (index < 256)
            return (char) index;
        return '#';

    }

    static int index = 0;
    int[] chArr = new int[256];

    public class Solution {
        public ListNode deleteDuplication(ListNode pHead) {
            ListNode cur = pHead;
            ListNode last = null;
            ListNode next = null;
            ListNode head = null;
            boolean flag = false;
            while (cur != null) {
                next = cur.next;
                while (next != null && cur.val == next.val) {
                    next = next.next;
                    flag = true;
                }
                if (last == null) {
                    if (!flag) {
                        head = cur;
                        last = cur;
                    }
                } else {
                    if (flag) {
                        last.next = next;
                    } else {
                        last = cur;
                    }
                }
                cur = next;
                flag = false;
            }
            return head;
        }
    }
}
