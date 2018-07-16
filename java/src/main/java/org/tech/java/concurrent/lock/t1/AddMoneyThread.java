package org.tech.java.concurrent.lock.t1;

public class AddMoneyThread implements Runnable {
	private Account account; // 存入账户
	private double money; // 存入金额

	public AddMoneyThread(Account account, double money) {
		this.account = account;
		this.money = money;
	}

	@Override
	public void run() {
		account.deposit(money);
	}

}
