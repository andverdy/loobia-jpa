package it.objectmethod.loobia.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.CommissionCopy;
import it.objectmethod.loobia.repository.CustomerRepository;
import it.objectmethod.loobia.repository.PaymentConditionsRepository;
import it.objectmethod.loobia.repository.ProductRepository;
import it.objectmethod.loobia.validator.rules.CommissionCopyCcdExistRule;
import it.objectmethod.loobia.validator.rules.CommissionCopyCustomerRule;
import it.objectmethod.loobia.validator.rules.CommissionCopyPayConditionRule;
import it.objectmethod.loobia.validator.rules.CommissionCopyTotalAmountRule;
import it.objectmethod.loobia.validator.rules.CommissionDetailAmountRule;
import it.objectmethod.loobia.validator.rules.CommissionDetailDiscountRule;
import it.objectmethod.loobia.validator.rules.CommissionDetailSinglePriceRule;

@Component
public class CommissionCopyValidator {

	@Autowired
	private PaymentConditionsRepository paymentConditionRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PaymentConditionsRepository paymConditionRepo;

	@Autowired
	private ProductRepository productRepo;

	public List<String> commissionCopyValidator(CommissionCopy commissionCopy) {
		List<String> errors = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("errList", errors);
		params.put("commissionCopy", commissionCopy);
		params.put("paymentConditionRepo", paymentConditionRepo);
		params.put("customerRepo", customerRepo);
		params.put("paymConditionRepo", paymConditionRepo);
		params.put("productRepo", productRepo);

		new CommissionDetailDiscountRule().validate(params);
		new CommissionDetailAmountRule().validate(params);
		new CommissionDetailSinglePriceRule().validate(params);
		new CommissionCopyPayConditionRule().validate(params);
		new CommissionCopyCcdExistRule().validate(params);
		new CommissionCopyTotalAmountRule().validate(params);
		new CommissionCopyCustomerRule().validate(params);

		return errors;
	}

}
