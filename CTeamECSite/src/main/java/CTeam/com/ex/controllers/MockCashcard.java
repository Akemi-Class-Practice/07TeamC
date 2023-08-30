package CTeam.com.ex.controllers;

public class MockCashcard {
	private int money;

	private int cardNumber;

	public MockCashcard(int money, int cardNumber) {
		this.money = money;
		this.cardNumber = cardNumber;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public boolean deductAmount(int amount) {
		if (amount > 0 && amount <= money) {
			money -= amount;
			System.out.println("Deducted " + amount + " JPY from card " + cardNumber);
			return true;
		} else {
			System.out.println("Insufficient funds or invalid amount");
			return false;
		}
	}

}
