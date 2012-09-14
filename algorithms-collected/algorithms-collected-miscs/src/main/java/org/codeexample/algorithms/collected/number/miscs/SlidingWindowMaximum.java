package org.codeexample.algorithms.collected.number.miscs;

//A long array A[] is given to you. There is a sliding window of size w which is moving from the very left of the array to the very right. You can only see the w numbers in the window. Each time the sliding window moves rightwards by one position. Following is an example:
//The array is [1 3 -1 -3 5 3 6 7], and w is 3.
//
//Window position                Max
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//Input: A long array A[], and a window width w
//Output: An array B[], B[i] is the maximum value of from A[i] to A[i+w-1]
//Requirement: Find a good optimal way to get B[i]
public class SlidingWindowMaximum
{

    // A heap data structure quickly comes to mind. We could boost the run time
    // to approximately O(n lg w) (Note that I said approximately because the
    // size of the heap changes constantly and averages about w). Insert
    // operation takes O(lg w) time, where w is the size of the heap. However,
    // getting the maximum value is cheap, it merely takes constant time as the
    // maximum value is always kept in the root (head) of the heap.

    // typedef pair<int, int> Pair;
    // void maxSlidingWindow(int A[], int n, int w, int B[]) {
    // priority_queue<Pair> Q;
    // for (int i = 0; i < w; i++)
    // Q.push(Pair(A[i], i));
    // for (int i = w; i < n; i++) {
    // Pair p = Q.top();
    // B[i-w] = p.first;
    // while (p.second <= i-w) {
    // Q.pop();
    // p = Q.top();
    // }
    // Q.push(Pair(A[i], i));
    // }
    // B[n-w] = Q.top().first;
    // }

    // void maxSlidingWindow(int A[], int n, int w, int B[]) {
    // deque<int> Q;
    // for (int i = 0; i < w; i++) {
    // while (!Q.empty() && A[i] >= A[Q.back()])
    // Q.pop_back();
    // Q.push_back(i);
    // }
    // for (int i = w; i < n; i++) {
    // B[i-w] = A[Q.front()];
    // while (!Q.empty() && A[i] >= A[Q.back()])
    // Q.pop_back();
    // while (!Q.empty() && Q.front() <= i-w)
    // Q.pop_front();
    // Q.push_back(i);
    // }
    // B[n-w] = A[Q.front()];
    // }
    //
    // The above algorithm could be proven to have run time complexity of O(n).
    // This is because each element in the list is being inserted and then
    // removed at most once. Therefore, the total number of insert + delete
    // operations is 2n.

}
