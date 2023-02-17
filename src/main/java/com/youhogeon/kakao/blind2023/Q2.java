package com.youhogeon.kakao.blind2023;

import java.util.*;

public class Q2 {

    class Solution {
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            Stack<House> deliveryStack = new Stack<>();
            Stack<House> pickupStack = new Stack<>();
    
            for (int i = 0; i < n; i++) {
                House h = new House(i + 1, deliveries[i], pickups[i]);
    
                deliveryStack.push(h);
                pickupStack.push(h);
            }
    
            long answer = 0;
            while (true) {
                int dID = 0, pID = 0;
                int dLeft = cap, pLeft = cap;
    
                while (!deliveryStack.isEmpty() && dLeft > 0) {
                    House h = deliveryStack.peek();
    
                    if (h.delivery > 0) {
                        int k = Math.min(dLeft, h.delivery);
                        dLeft -= k;
                        h.delivery -= k;
    
                        if (dID == 0) dID = h.id;
                    }
    
                    if (h.delivery == 0) deliveryStack.pop();
                }
    
                while (!pickupStack.isEmpty() && pLeft > 0) {
                    House h = pickupStack.peek();
    
                    if (h.pickup > 0) {
                        int k = Math.min(pLeft, h.pickup);
                        pLeft -= k;
                        h.pickup -= k;
    
                        if (pID == 0) pID = h.id;
                    }
    
                    if (h.pickup == 0) pickupStack.pop();
                }
    
                if (dID == 0 && pID == 0) break;
    
                answer += Math.max(dID, pID);
            }
    
            return answer * 2;
        }
        
    
        class House {
            int id;
            int delivery, pickup;
    
            public House(int id, int delivery, int pickup) {
                this.id = id;
                this.delivery = delivery;
                this.pickup = pickup;
            }
        }
    }
    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(1	,	3	,	new int[]{4, 0, 0}	,	new int[]{0, 0, 4}));
        solution.add(s.solution(4	,	5	,	new int[]{1, 0, 3, 1, 2}	,	new int[]{0, 3, 0, 4, 0}));
        solution.add(s.solution(2	,	7	,	new int[]{1, 0, 2, 0, 1, 0, 2}	,	new int[]{0, 2, 0, 1, 0, 2, 0}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
