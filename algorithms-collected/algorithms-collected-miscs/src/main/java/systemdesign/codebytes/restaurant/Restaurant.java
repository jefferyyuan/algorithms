package systemdesign.codebytes.restaurant;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class BusBoy implements Runnable {

	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	Restaurant restaurant;

	BusBoy(Restaurant r) {
		restaurant = r;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {

				lock.lock();
				try {
					condition.await();
					System.out.println("BusBoy is Cleaning up!\n");
				} finally {
					lock.unlock();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("BusBoy interrupted!");
		}
	}

}

class Meal {

	private final int orderNum;

	// volatile int cleanUp = 0;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson implements Runnable {

	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	private Restaurant restaurant;

	public WaitPerson(Restaurant r) {
		restaurant = r;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				lock.lock();
				try {
					while (restaurant.meal == null) {
						condition.await(); // ... for the chef to produce a meal
					}
				} finally {
					lock.unlock();
				}
				System.out.println("Waitperson got " + restaurant.meal);

				restaurant.chef.lock.lock();
				try {
					restaurant.meal = null;
					System.out.println("Meal taken by the waitperson!");
					restaurant.chef.condition.signalAll(); // Ready for another
				} finally {
					restaurant.chef.lock.unlock();
				}

				try {
					restaurant.boy.lock.lock();
					System.out.println("Notifying BusBoy to cleanup...");
					restaurant.boy.condition.signalAll();
				} finally {
					restaurant.boy.lock.unlock();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("WaitPerson interrupted!");
		}
	}
}

class Chef implements Runnable {

	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	private Restaurant restaurant;
	private int count = 0;

	public Chef(Restaurant r) {
		restaurant = r;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				lock.lock();
				try {

					while (restaurant.meal != null) {
						condition.await();// ... for the meal to be taken
					}
				} finally {
					lock.unlock();
				}

				if (++count == 10) {
					System.out.println("Out of food, closing");
					restaurant.exec.shutdownNow();
					return;
				}
				System.out.println("Order up!");
				restaurant.waitPerson.lock.lock();
				try {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.condition.signalAll();
				} finally {
					restaurant.waitPerson.lock.unlock();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Chef interrupted!");
		}
	}
}

public class Restaurant {

	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);
	BusBoy boy = new BusBoy(this);

	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitPerson);
		exec.execute(boy);
	}

	public static void main(String[] args) {
		new Restaurant();
	}

}