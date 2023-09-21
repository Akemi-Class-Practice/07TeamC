package CTeam.com.ex.services;

import org.springframework.stereotype.Service;

import CTeam.com.ex.controllers.MockCashcard;

@Service
public class PaymentService {
	//クレジットカード情報設定する
		private MockCashcard bankCard = new MockCashcard("zhang", "2023 0905 1431", 2000000.00);
		
		public MockCashcard getBankCard() {
			return bankCard;
		}

		public void setBankCard(MockCashcard bankCard) {
			this.bankCard = bankCard;
		}

		// 处理支付
		public boolean processPayment(String paymentMethod, double total) {
			if (bankCard.deductAmount(total)) {
				return true; // 支付成功
			}
			return false; // 支付失败
		}
}
