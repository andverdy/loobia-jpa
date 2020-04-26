package it.objectmethod.loobia.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.objectmethod.loobia.dto.OrderDto;
import it.objectmethod.loobia.repository.ProductRepository;
import it.objectmethod.loobia.validator.rules.OrderDetailsAmountRule;
import it.objectmethod.loobia.validator.rules.OrderDetailsDiscountRule;
import it.objectmethod.loobia.validator.rules.OrderDetailsSinglePriceRule;

@Component
public class OrderDetailValidator {

	@Autowired
	private ProductRepository productRepo;

	public List<String> orderDetailValidator(OrderDto orderDto) {
		List<String> errors = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("errList", errors);
		params.put("orderDto", orderDto);
		params.put("productRepo", productRepo);

		new OrderDetailsSinglePriceRule().validate(params);
		new OrderDetailsDiscountRule().validate(params);
		new OrderDetailsAmountRule().validate(params);
		return errors;
	}
}
