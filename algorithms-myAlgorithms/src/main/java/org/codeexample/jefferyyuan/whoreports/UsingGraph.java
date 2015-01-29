package org.codeexample.jefferyyuan.whoreports;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class UsingGraph {
	// { employee_id, manager_id }
	// ( { 2, 1 }, { 3, 1 }, { 4, 2 }, { 5, 7 }, {6, 3 }, { 7, 4 } )

	// whoReportsTo( 3 ) --> ( 6 )
	// whoReportsTo( 2 ) --> ( 4, 7, 5 )
	// { 4, 2 }, { 7, 4 } ==> {7, 4(manger), 2(manger)}

	// Graph represented by Adjacency list
	public static class Relations {
		// mangerid --> subids
		private Map<Integer, Set<Integer>> subRelations = new HashMap<>();

		public Relations(int[][] relations) {
			for (int i = 0; i < relations.length; i++) {
				manged(relations[i][0], relations[i][1]);
			}
			// if we also want to get the forward relationship, store it as
			// map<int, int>
		}

		public void manged(int empid, int mangerid) {
			Set<Integer> subs = subRelations.get(mangerid);
			if (subs == null) {
				subs = new HashSet<>();
				subRelations.put(mangerid, subs);
			}

			subs.add(empid);
		}

		public Set<Integer> whoReportsToBFS(int mangerid) {
			Set<Integer> mysubs = subRelations.get(mangerid);
			// if (mysubs == null)
			// throw new IllegalArgumentException(mangerid + " doesn't exist");

			// direct subs comes first
			Set<Integer> result = new LinkedHashSet<>();

			if (mysubs == null)
				return result;
			// BFS, but only for the paths that starts with mangerid
			Queue<Integer> queue = new LinkedList<>();
			queue.addAll(mysubs);
			while (!queue.isEmpty()) {
				Integer subid = queue.poll();
				// one empid is only managed by one manager, and there should be
				// no loop
				if (result.contains(subid)) {
					throw new LoopExistException("subid: " + subid);
				}
				result.add(subid);

				if (subRelations.containsKey(subid)) {
					queue.addAll(subRelations.get(subid));
				}
			}
			return result;
		}

		public Set<Integer> whoReportsToDFS(int mangerid) {
			Set<Integer> mysubs = subRelations.get(mangerid);
			// if (mysubs == null)
			// throw new IllegalArgumentException(mangerid + " doesn't exist");

			// direct subs comes first
			Set<Integer> result = new LinkedHashSet<>();

			if (mysubs == null)
				return result;
			// DFS, but only for the paths that starts with mangerid
			LinkedList<Integer> stack = new LinkedList<>();
			stack.addAll(mysubs);
			while (!stack.isEmpty()) {
				Integer subid = stack.pollLast();
				// one empid is only managed by one manager, and there should be
				// no loop
				if (result.contains(subid)) {
					throw new LoopExistException("subid: " + subid);
				}
				result.add(subid);
				if (subRelations.containsKey(subid)) {
					stack.addAll(subRelations.get(subid));
				}
			}
			return result;
		}

		public static void main(String[] args) {
			// { employee_id, manager_id }
			// ( { 2, 1 }, { 3, 1 }, { 4, 2 }, { 5, 7 }, {6, 3 }, { 7, 4 } )
			int[][] arr = new int[][] { { 2, 1 }, { 3, 1 }, { 4, 2 }, { 5, 7 },
					{ 6, 3 }, { 7, 4 } };
			Relations relations = new Relations(arr);
			// System.out.println(relations.whoReportsToBFS(2));
			System.out.println(relations.whoReportsToDFS(2));

			// there is loop in the input { 2, 1 }, { 1, 2 }
			arr = new int[][] { { 2, 1 }, { 1, 2 }, { 3, 1 }, { 4, 2 },
					{ 5, 7 }, { 6, 3 }, { 7, 4 } };
			relations = new Relations(arr);
			System.out.println(relations.whoReportsToDFS(2));
		}
	}
}
