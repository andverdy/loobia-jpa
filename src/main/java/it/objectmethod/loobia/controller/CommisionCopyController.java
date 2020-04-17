package it.objectmethod.loobia.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.objectmethod.loobia.dto.CommissionCopyDto;
import it.objectmethod.loobia.entity.CommissionCopy;
import it.objectmethod.loobia.service.CommissionCopyService;
import it.objectmethod.loobia.validators.CommissionCopyValidator;

@RestController
@CrossOrigin
@RequestMapping("/api/commission-copy")
public class CommisionCopyController {

	@Autowired
	private CommissionCopyValidator commissionCopyValidator;

	@Autowired
	private CommissionCopyService commissionCopyService;

	@PutMapping("/save")
	public CommissionCopyDto commissionCopySave(@RequestBody CommissionCopy commissionCopy) {

		List<String> errors = commissionCopyValidator.commissionCopyValidator(commissionCopy);
		if ((!errors.isEmpty())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		return commissionCopyService.commissionCopySave(commissionCopy);

	}

}
