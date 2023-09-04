package cteam.com.ex.service;

import org.springframework.stereotype.Service;

import cteam.com.ex.contraller.MockCashcard;

@Service
public class PaymentService {
	private MockCashcard bankCard = new MockCashcard(1000000, 123456);

	// 获取银行卡信息
	public MockCashcard getBankCard() {
		return bankCard;
	}

	// 设置银行卡信息
	public void setBankCard(MockCashcard bankCard) {
		this.bankCard = bankCard;
	}

	// 处理支付
	public boolean processPayment(String paymentMethod) {
		int paymentAmount = 10000; // 假设支付金额为10000日元
		if (bankCard.deductAmount(paymentAmount)) {
			return true; // 支付成功
		}
		return false; // 支付失败
	}
}
