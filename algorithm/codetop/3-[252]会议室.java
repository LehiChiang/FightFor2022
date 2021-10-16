package codetop;

import java.util.Arrays;

class minMeetingRoomsSolution {
    public int minMeetingRooms(int[][] meetings) {
        int n = meetings.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int count = 0;
        int i = 0, j = 0, max = 0;
        while (i < n && j < n) {
            if (start[i] < end[j]) {
                count++;
                i++;
            } else {
                count--;
                j++;
            }
            max = Math.max(max, count);
        }
        return max;
    }

    public static void main(String[] args) {
        minMeetingRoomsSolution solution = new minMeetingRoomsSolution();
        int[][] meetings = new int[][]{{10, 12}, {1, 4}, {5, 20}, {2, 6}};
        System.out.println(solution.minMeetingRooms(meetings));
    }
}
