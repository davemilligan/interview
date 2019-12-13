package com.davemilligan.interview.hackerrank.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Passes {
    public static void main(String[] args) {
        List<Integer> ticketQueue = new ArrayList<>();
        ticketQueue.add(2);
        ticketQueue.add(6);
        ticketQueue.add(3);
        ticketQueue.add(4);
        ticketQueue.add(5);

        int myPositionInQueue = 2;

        long startTime = System.nanoTime();
        System.out.println(waitingTime(new ArrayList<>(ticketQueue), myPositionInQueue));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("waitingTime Duration:" + duration);

        startTime = System.nanoTime();
        System.out.println(streamsReduceWaitingTime(new ArrayList<>(ticketQueue), myPositionInQueue));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("streamsReduceWaitingTime Duration:" + duration);

        startTime = System.nanoTime();
        System.out.println(enhancedLoopWaitingTime(new ArrayList<>(ticketQueue), myPositionInQueue));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("enhancedLoopWaitingTime Duration:" + duration);

        startTime = System.nanoTime();
        System.out.println(forLoopWaitingTime(new ArrayList<>(ticketQueue), myPositionInQueue));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("forLoopWaitingTime Duration:" + duration);
    }


    /**
     * Best performance performing one pass and doing the math in place.
     *
     * @param ticketQueue
     * @param myPositionInQueue
     * @return
     */
    public static long forLoopWaitingTime(List<Integer> ticketQueue, int myPositionInQueue) {
        int pNeeded = ticketQueue.get(myPositionInQueue);
        long wait = 0;
        for(int i = 0; i < ticketQueue.size(); i++) {
            int currentCustomer = ticketQueue.get(i);
            wait += Math.min(currentCustomer, (i <= myPositionInQueue) ? pNeeded : pNeeded -1);
        }
        return wait;
    }

    /**
     * Using enhanched queue, removing myself from the queue adds expense, as does incrementing the idx.
     *
     * 2* slower than forLoopWaitingTime
     *
     * @param ticketQueue
     * @param myPositionInQueue
     * @return
     */
    public static long enhancedLoopWaitingTime(List<Integer> ticketQueue, int myPositionInQueue) {
        int pNeeded = ticketQueue.get(myPositionInQueue);
        long wait = pNeeded;
        int idx = 0;
        for(Integer currentCustomer: ticketQueue) {
            wait += Math.min(currentCustomer, (idx++ <= myPositionInQueue) ? pNeeded : pNeeded -1);
        }
        return wait;
    }

    /**
     * Lovely code but kills performance.
     *
     * This is 600* slower than forLoopWaitingTime.
     *
     * @param ticketQueue
     * @param myPositionInQueue
     * @return
     */
    public static long streamsReduceWaitingTime(List<Integer> ticketQueue, int myPositionInQueue) {
        int pNeeded = ticketQueue.get(myPositionInQueue);
        long wait = pNeeded;
        int lastIdx = ticketQueue.size() - 1;
        wait += ticketQueue.subList(0, myPositionInQueue).stream().reduce((w, t) -> w + Math.min(pNeeded, t)).get();
        wait += ticketQueue.subList(myPositionInQueue+1, lastIdx).stream().reduce((w, t) -> w + Math.min(pNeeded - 1, t)).get();
        return wait;
    }

    /**
     * Treating the problem literally, moving queued customers to back of the queue each time they are served.
     *
     * This is 30* slower than forLoopWaitingTime.
     *
     * @param ticketQueue
     * @param myPositionInQueue
     * @return
     */
    public static long waitingTime(List<Integer> ticketQueue, int myPositionInQueue) {
        long wait = 0;
        LinkedList<Integer> list = new LinkedList<Integer>(ticketQueue);
        while (list.get(myPositionInQueue) > 0) {
            if(myPositionInQueue == 0)
                myPositionInQueue = (ticketQueue.size() -1);
            else
                myPositionInQueue -=1;
            int ticketsNeeded = list.poll();
            if (ticketsNeeded > 0) {
                list.offer(ticketsNeeded - 1);
                wait++;
            } else
                list.offer(ticketsNeeded);

        }
        return wait;
    }
}
