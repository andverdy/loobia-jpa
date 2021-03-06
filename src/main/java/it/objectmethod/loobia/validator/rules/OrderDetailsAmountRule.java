package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;
import it.objectmethod.loobia.dto.OrderDetailsDto;
import it.objectmethod.loobia.dto.OrderDto;

@SuppressWarnings("unchecked")
public class OrderDetailsAmountRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		OrderDto orderDto = (OrderDto) paramsToValidate.get("orderDto");
		double impScontato = 0;
		double differenzaImporti = 0;

		List<OrderDetailsDto> listOrderDet = orderDto.getDetailOrdersDto();
		for (OrderDetailsDto orderDet : listOrderDet) {

			impScontato = orderDet.getImporto() - ((orderDet.getImporto() / 100) * orderDet.getSconto());
			differenzaImporti = impScontato - orderDet.getImportoScontato();
			if (differenzaImporti > 0.01d || differenzaImporti < -0.01d) {
				errors.add("Importo ed importo scontato non sono coerenti con lo sconto applicato alla riga: "
						+ orderDet.getIdProdotto());
			}
		}

	}

}
