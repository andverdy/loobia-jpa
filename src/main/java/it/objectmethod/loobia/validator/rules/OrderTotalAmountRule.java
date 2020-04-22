package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.Order;
import it.objectmethod.loobia.entity.OrderDetails;
import it.objectmethod.loobia.entity.PaymentConditions;
import it.objectmethod.loobia.repository.PaymentConditionsRepository;

@SuppressWarnings("unchecked")
public class OrderTotalAmountRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		Order order = (Order) paramsToValidate.get("order");
		PaymentConditionsRepository paymentCondRepo = (PaymentConditionsRepository) paramsToValidate
				.get("paymentConditionRepo");
		Float importoTotale = 0f;
		Float importoTotaleScontato = 0f;
		List<OrderDetails> dettList = order.getDetailOrders();

		// controllare se gli importi totali inseriti dall'utente sono coerenti
		if (dettList != null) {
			for (OrderDetails orderDetails : dettList) {
				float importoRiga = orderDetails.getPrezzoSingolo() * orderDetails.getTotPezzi();
				float discountValue = 0f;
				if (orderDetails.getSconto() > 0) {
					discountValue = (importoRiga * orderDetails.getSconto()) / 100;
				}
				importoTotale += importoRiga;
				importoTotaleScontato += importoRiga - discountValue;

			}
			// valorizza spesa incasso della copiaCommissione
			PaymentConditions paymCondition = paymentCondRepo.findSpesaById(order.getPaymentConditions().getId());
			order.setSpesaIncasso(paymCondition.getSpesa());
			if (order.getSpesaIncasso() != null) {
				importoTotale += order.getSpesaIncasso();
				importoTotaleScontato += order.getSpesaIncasso();
			}

			if (!order.getImportoTot().equals(importoTotale)) {
				errors.add("Importo totale errato");
			}
			if (!order.getImportoTotScontato().equals(importoTotaleScontato)) {
				errors.add("Importo totale scontato errato");
			}

		}

	}
}
