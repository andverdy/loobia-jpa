package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;
import it.objectmethod.loobia.dto.OrderDetailsDto;
import it.objectmethod.loobia.dto.OrderDto;
import it.objectmethod.loobia.entity.PaymentConditions;
import it.objectmethod.loobia.repository.PaymentConditionsRepository;

@SuppressWarnings("unchecked")
public class OrderTotalAmountRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		OrderDto orderDto = (OrderDto) paramsToValidate.get("orderDto");
		PaymentConditionsRepository paymentCondRepo = (PaymentConditionsRepository) paramsToValidate
				.get("paymentConditionRepo");
		Float importoTotale = 0f;
		Float importoTotaleScontato = 0f;
		List<OrderDetailsDto> dettList = orderDto.getDetailOrdersDto();

		// controllare se gli importi totali inseriti dall'utente sono coerenti
		if (dettList != null) {
			for (OrderDetailsDto orderDetails : dettList) {
				float importoRiga = orderDetails.getPrezzoSingolo() * orderDetails.getTotPezzi();
				float discountValue = 0f;
				if (orderDetails.getSconto() > 0) {
					discountValue = (importoRiga * orderDetails.getSconto()) / 100;
				}
				importoTotale += importoRiga;
				importoTotaleScontato += importoRiga - discountValue;

			}
			// valorizza spesa incasso della copiaCommissione
			PaymentConditions paymCondition = paymentCondRepo.findSpesaById(orderDto.getIdCondizioniPagamento());
			orderDto.setSpesaIncasso(paymCondition.getSpesa());
			if (orderDto.getSpesaIncasso() != null) {
				importoTotale += orderDto.getSpesaIncasso();
				importoTotaleScontato += orderDto.getSpesaIncasso();
			}

			if (!orderDto.getImportoTot().equals(importoTotale)) {
				errors.add("Importo totale errato");
			}
			if (!orderDto.getImportoTotScontato().equals(importoTotaleScontato)) {
				errors.add("Importo totale scontato errato");
			}

		}

	}
}
