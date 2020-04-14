package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.entity.Municipality;
import it.objectmethod.loobia.repository.MunicipalityRepository;

@SuppressWarnings("unchecked")
public class CustomerAddressesCityRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CustomerAddressesDto customAddressDto = (CustomerAddressesDto) paramsToValidate.get("customerAddressDto");
		MunicipalityRepository municipRepo = (MunicipalityRepository) paramsToValidate.get("municipalityRepo");

		Municipality municipality = municipRepo.findByNome(customAddressDto.getCitta());

		if (municipality != null) {
			String cap = municipality.getCap();
			cap = cap.replaceAll("x", "");
			if (customAddressDto.getCap() == null || (cap.length() == 5 && !customAddressDto.getCap().equals(cap))
					|| (cap.length() < 5 && !customAddressDto.getCap().startsWith(cap))) {
				errors.add("Il Cap inserito è errato!");
			}
			if (!(customAddressDto.getProvincia().equals(municipality.getProvincia()))) {
				errors.add("La Provincia inserita è errata!");
			}
		} else {
			errors.add("La Città inserita è errata!");
		}

	}

}
