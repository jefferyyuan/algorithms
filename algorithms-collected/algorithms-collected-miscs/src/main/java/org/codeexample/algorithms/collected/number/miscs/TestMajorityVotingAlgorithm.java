package org.codeexample.algorithms.collected.number.miscs;

public class TestMajorityVotingAlgorithm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Object[] data = { 'A', 'A', 'A', 'C', 'C', 'B', 'B', 'C', 'C', 'C',
				'B', 'C', 'C' };

		TestMajorityVotingAlgorithm tm = new TestMajorityVotingAlgorithm();
		Majority majority = tm.findMajority(data);

		System.out.println(majority.getCandidate().getValue());
		System.out.println(majority.leading());

	}

	public Majority findMajority(Object[] data) {

		Majority majority = new Majority();

		for (Object obj : data) {

			// If the counter is 0, we set the current candidate to e and we set
			// the counter to 1.

			if (majority.leading() == 0) {
				majority.setCandidate(new Candidate(obj));
			} else {

				// If the counter is not 0, we increment or decrement the
				// counter according to whether e is the current candidate.
				if (majority.getCandidate().equals(obj)) {
					majority.increment();
				} else {
					majority.deccrement();
				}

			}

		}

		return majority;
	}

	public static class Majority {

		private Candidate candidate;
		private int leading;

		public Majority() {

		}

		public Candidate getCandidate() {
			return candidate;
		}

		public void setCandidate(Candidate cand) {
			this.candidate = cand;
			leading = 1;
		}

		public int leading() {
			return leading;
		}

		public void increment() {
			leading++;
		}

		public void deccrement() {
			leading--;
		}
	}

	public static class Candidate {

		private Object obj;

		public Candidate(Object obj) {
			this.obj = obj;
		}

		public Object getValue() {
			return this.obj;
		}

		@Override
		public boolean equals(Object obj) {
			return this.obj.equals(obj);
		}

	}

}