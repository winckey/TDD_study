package com.company;

public class Main {
    int[] visited;
    int[] weightList;
    int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        // write your code here
        int[] weight;


    }

    public int[] solution(int[] weight) {

        visited = new int[weight.length];
        weightList = weight;
        int[] answer = {};
        return answer;
    }

    public void combine(int start, int count, int endCount) {

        if (count == endCount) {
            int aTeam = 0;
            int bTeam = 0;

            for (int i = 0; i < visited.length; i++) {
                    if(visited[i] == 0 )
                        aTeam = aTeam+weightList[i];
                    else
                        bTeam = bTeam+weightList[i];
            }

            if(aTeam == bTeam && max >=aTeam)
            {

            }
        }




    }
}
