package com.company;

import java.util.Arrays;

public class Main2 {

    private static int endCount;

    public static void main(String[] args) {
        // write your code here
        int[] weight;


//        solution(new int[][]{{12,15},{19,21}});
        solution(new int[][]{{12,15,1},{19,21,1},{1,1,1}});
    }




    public static int solution(int[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean[][] canPut = new boolean[board.length][board[0].length];
        endCount = board.length;



        dfs( 0 , visited , canPut);



        int answer = 0;
        return answer;
    }

    public static void dfs(int count, boolean[][] canPutsite, boolean[][] visited) {




        if(count == endCount)
        {
//            for (int i = 0; i < visited.length; i++) {
//                for (int j = 0; j < visited.length; j++) {
//                    System.out.print(visited[i][j]+ " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println();
            int sum =0;
            for (int i = 0; i < visited.length; i++) {
                for (int j = 0; j < visited.length; j++) {

                    if(visited[i][j])
                    {
                        sum = sum +
                    }
                }
            }


            return;
        }


        for (int i = 0; i < canPutsite.length; i++) {

            if(!canPutsite[count][i])
            {
                visited[count][i] = true;
                dfs(count+1  , checkVisited( count, i , canPutsite ) , visited);
                visited[count][i] = false;
            }
        }



    }

    public static boolean[][] checkVisited(int x, int y, boolean[][] canPutsite){

        boolean[][] temp = new boolean[canPutsite.length][canPutsite.length];
        for (int i = 0; i <canPutsite.length ; i++) {
            for (int j = 0; j < canPutsite.length; j++) {

                temp[i][j] = canPutsite[i][j];

            }
        }


        for (int i = 0; i < temp.length; i++) {
            temp[x][i] = true;
            temp[i][y] = true;
        }

        return temp;
    }
}
