package it.objectmethod.loobia.validator.rules;

import java.util.Map;

public interface IValidatorRule {

	public void validate(Map<String, Object> paramsToValidate);
}
