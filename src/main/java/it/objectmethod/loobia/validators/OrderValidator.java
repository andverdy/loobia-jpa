package it.objectmethod.loobia.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.objectmethod.loobia.entity.Order;
import it.objectmethod.loobia.repository.CustomerRepository;
import it.objectmethod.loobia.repository.PaymentConditionsRepository;
import it.objectmethod.loobia.repository.ProductRepository;
import it.objectmethod.loobia.validator.rules.OrderCcdExistRule;
import it.objectmethod.loobia.validator.rules.OrderPayConditionRule;
import it.objectmethod.loobia.validator.rules.OrderTotalAmountRule;

@Component
public class OrderValidator {

	@Autowired
	private PaymentConditionsRepository paymentConditionRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PaymentConditionsRepository paymConditionRepo;

	@Autowired
	private ProductRepository productRepo;

	public List<String> orderValidator(Order order) {
		List<String> errors = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("errList", errors);
		params.put("order", order);
		params.put("paymentConditionRepo", paymentConditionRepo);
		params.put("customerRepo", customerRepo);
		params.put("paymConditionRepo", paymConditionRepo);
		params.put("productRepo", productRepo);

		new OrderPayConditionRule().validate(params);
		new OrderCcdExistRule().validate(params);
		new OrderTotalAmountRule().validate(params);
		// new CustomerOrderRule().validate(params);

		return errors;
	}

}
