package CTeam.com.ex.controllers;

public class MockCashcard {
	// カードの名前
	private String name;
	// カード番号
	private String cardNumber;
	// カード中のお金
	private double money;

	// コンストラクタ
	public MockCashcard(String name, String cardNumber, double money) {
		this.name = name;
		this.cardNumber = cardNumber;
		this.money = money;
	}
	// getterとsetter

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	// カードの利用
	public boolean deductAmount(double total) {
		// 金額がカード中のお金より小さい場合
		if (total > 0 && total <= money) {
			// 利用した金額を減って
			money -= total;
			// 残りの金額を表示
			System.out.println("Deducted " + total + " JPY from card " + cardNumber);
			return true;
		} else {
			// 金額がカード中のお金より大きな場合利用できない
			System.out.println("Insufficient funds or invalid amount");
			return false;
		}
	}

}
