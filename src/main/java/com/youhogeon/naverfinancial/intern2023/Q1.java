package com.youhogeon.naverfinancial.intern2023;

public class Q1 {

    class Solution {
        public int[] solution(int[] periods, int[][] payments, int[] estimates) {
            int newVIP = 0, newNotVIP = 0;
    
            for (int i = 0; i < periods.length; i++) {
                User u = new User(periods[i], payments[i], estimates[i]);
    
                boolean a = u.isVIPOnThisMonth(), b = u.isVIPOnNextMonth();
                if (a && !b) newNotVIP++;
                if (!a && b) newVIP++;
            }
    
    
            return new int[]{ newVIP, newNotVIP };
        }
    
        class User {
            int period;
            int[] payments;
            int estimates;
    
            int paymentsWithThisMonth = 0, paymentsWithNextMonth;
    
            public User(int period, int[] payments, int estimates) {
                this.period = period;
                this.payments = payments;
                this.estimates = estimates;
    
                for (int i = 0; i < payments.length; i++) paymentsWithThisMonth += payments[i];
        
                this.paymentsWithNextMonth = estimates + paymentsWithThisMonth;
                if (period >= 12) this.paymentsWithNextMonth -= payments[0];
            }
    
            public boolean isVIPOnThisMonth() {
                if (period >= 24 && paymentsWithThisMonth >= 900000) return true;
                if (period >= 60 && paymentsWithThisMonth >= 600000) return true;
    
                return false;
            }
    
            public boolean isVIPOnNextMonth() {
                if (period >= 23 && paymentsWithNextMonth >= 900000) return true;
                if (period >= 59 && paymentsWithNextMonth >= 600000) return true;
    
                return false;
            }
        }
    }
    

    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(new int[]{20, 23, 24},new int[][]{new int[]{100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000}, new int[]{100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000}, new int[]{350000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000}},new int[]{100000, 100000, 100000}));
        System.out.println(s.solution(new int[]{24, 59, 59, 60},new int[][]{new int[]{50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000}, new int[]{50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000}, new int[]{350000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000}, new int[]{50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000}},new int[]{350000, 50000, 40000, 50000}));
                
    }
}
