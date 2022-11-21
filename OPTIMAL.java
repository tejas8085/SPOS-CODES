import java.util.Scanner;

class OPTIMAL {


    static int check2(int p[], int pf[], int count[], int j, int s) {
        int k;
        for (int i = 0; i < s; ++i) {
            count[i] = 0;
        }
        for (k = 0; k < s; ++k) {
            loop:
            for (int i = j - 1;; --i) {
                ++count[k];
                if (p[i] == pf[k]) {
                    break loop;
                }

        }
        int max = count[0];
        int pos = 0;
        for (int l = 0; l < s; ++l) {
            if (count[l] > max) {
                max = count[l];
                pos = l;
            }
        }
        return pos;
    }

    static int check(int p[], int pf[], int count[], int j, int s) {
        int k;
        for (int i = 0; i < s; ++i) {
            count[i] = 0;
        }
        int found = 0;
        for (k = 0; k < s; ++k) {
            loop:
            for (int i = j + 1; i < p.length; ++i) {
                ++count[k];
                if (p[i] == pf[k]) {
                    ++found;
                    break loop;
                }
            }
        }
        int pos;
        if (found < (s - 1)) {
            System.out.println();
            System.out.println("More than 1 used pages not required for furthur operation.\nSwapping LRU  page.");
            pos = check2(p, pf, count, j, s);
        } else {
            int max = count[0];
            pos = 0;
            for (int l = 0; l < s; ++l) {
                if (count[l] > max) {
                    max = count[l];
                    pos = l;
                }
            }
        }
        return pos;
    }

    static boolean check1(int p[], int x, int n) {
        for (int i = 0; i < n; ++i) {
            if (p[i] == x) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter the number of page entries");
        int n = scr.nextInt();
        int fault = 0, hit = 0;
        int p[] = new int[n];
        System.out.println("Enter the pages ");
        for (int i = 0; i < n; ++i) {
            p[i] = scr.nextInt();
        }
        System.out.print("The page table entries are ");
        for (int i = 0; i < n; ++i) {
            System.out.print(" " + p[i]);
        }
        System.out.println();
        int s = 3;
        System.out.println("Do you wish to set a the number of page frames?(1=yes/0=no)");
        int ch = scr.nextInt();

        if (ch == 1) {
            System.out.println("Enter the number of page frames ");
            s = scr.nextInt();
            System.out.println("The number of page frames set to " + s);
        } else {
            System.out.println("The default number of page frames are " + s);
        }
        int count[] = new int[s];
        int pf[] = new int[s];
        int t = 0;
        int incr = 0;
        for (int i = 0; i < s || (t != s); ++i) {
            if (check1(pf, p[i], t)) {
                ++incr;
                System.out.println();
                for (int y = 0; y < t; ++y) {
                    System.out.print(pf[y] + " ");
                }
                System.out.print(" Hit");
                ++hit;
            } else {
                ++incr;
                ++t;
                pf[t - 1] = p[i];
                System.out.println();
                for (int y = 0; y < t; ++y) {
                    System.out.print(pf[y] + " ");
                }
                System.out.print(" F");
                ++fault;
            }
        }
        for (int j = incr; j < n; ++j) {
            if (check1(pf, p[j], s)) {
                ++hit;
                System.out.println();
                for (int k = 0; k < s; ++k) {
                    System.out.print(pf[k] + " ");
                }
                System.out.print(" H");
            } else {
                int choice = check(p, pf, count, j, s);
                ++fault;
                pf[choice] = p[j];
                System.out.println();
                for (int k = 0; k < s; ++k) {
                    System.out.print(pf[k] + " ");
                }
                System.out.print(" F");
            }
        }
        System.out.println();
        System.out.println("Page faults= " + fault + " Page hits= " + hit);
    }
}