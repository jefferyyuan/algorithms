package codebytes;
public class SpiralPrint {

    static void printSpiral(int[][] array) {
        System.out.println();
        int top = 0;
        int bottom = array.length - 1;
        int left = 0;
        int right = array[0].length - 1;
      
        while (bottom >= top || right >= left) {
            for (int i = left; i <= right && bottom >= top; ++i) {
                System.out.print(array[top][i]);
            }
            top++;
            for (int i = top; i <= bottom && left <= right; ++i) {
                System.out.print(array[i][right]);
            }
            right--;
            for (int i = right; i >= left && bottom >= top; --i) {
                System.out.print(array[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top && left <= right; --i) {
                System.out.print(array[i][left]);
            }
            left++;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        //test 1
        int[][] array = {{0, 1, 2, 3, 4},
                         {9, 8, 7, 6, 5}};
        printSpiral(array);
        
        //test 2
        array = new int[][]{
            {1, 2, 3, 4, 5},
            {0, 9, 8, 7, 6},
            {1, 2, 3, 4, 5},};
        printSpiral(array);
        
        //test 3
        array = new int[][]{
            {1, 0, 1},
            {2, 9, 2},
            {3, 8, 3},
            {4, 7, 4},
            {5, 6, 5}};
        printSpiral(array);
        
        //test 4
        array = new int[][]{
            {1},
            {2},
            {3},
            {4}
        };
        printSpiral(array);
        
        //test 5
        array = new int[][]{
            {1, 2, 3, 4}
        };
        printSpiral(array);

        //test 6
        array = new int[][]{
            {1, 2, 3, 4,},
            {3, 4, 5, 5,},
            {2, 7, 6, 6,},
            {1, 9, 8, 7,},
        };
        printSpiral(array);
        
        //test 7
        array = new int[][]{
            {1, 2, 3, 4,},
            {3, 4, 5, 5,},
            {2, 7, 6, 6,},
            {1, 9, 8, 7,},
            {0, 0, 0, 0,},
        };
        printSpiral(array);
        
        //test 8
        array = new int[][]{
            {1, 2, 3, 4,},
            {3, 4, 5, 5,},
        };
        printSpiral(array);
        
        //test 9
        array = new int[][]{
            {1, 2},
            {2, 3},
            {3, 4},
            {4, 5}
        };
        printSpiral(array);
    }  
}