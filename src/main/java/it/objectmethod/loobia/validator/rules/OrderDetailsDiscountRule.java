package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;
import it.objectmethod.loobia.dto.OrderDetailsDto;
import it.objectmethod.loobia.dto.OrderDto;

@SuppressWarnings("unchecked")
public class OrderDetailsDiscountRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		OrderDto orderDto = (OrderDto) paramsToValidate.get("orderDto");
		List<String> errors = (List<String>) paramsToValidate.get("errList");

		List<OrderDetailsDto> orderDetailList = null;
		if (orderDto != null) {

			orderDetailList = orderDto.getDetailOrdersDto();
			if (orderDetailList != null) {
				for (OrderDetailsDto cdc : orderDetailList) {
					if (cdc.getSconto() > 100) {
						errors.add("Non è possibile inserire uno sconto maggiore del 100%");
					}
				}
			}

		}

	}

}
