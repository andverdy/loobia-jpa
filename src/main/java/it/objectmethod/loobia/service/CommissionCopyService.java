package it.objectmethod.loobia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.dto.CommissionCopyDto;
import it.objectmethod.loobia.entity.CommissionCopy;
import it.objectmethod.loobia.mapper.CommissionCopyMapper;
import it.objectmethod.loobia.repository.CommissionCopyRepository;

@Component
public class CommissionCopyService {

	@Autowired
	private CommissionCopyRepository commissionCopyRepo;

	@Autowired
	private CommissionCopyMapper cCMapper;

	public CommissionCopyDto commissionCopySave(CommissionCopy commissionCopy) {
		CommissionCopy commissCopy = commissionCopyRepo.save(commissionCopy);
		CommissionCopyDto cdto = cCMapper.toDto(commissCopy);
		return cdto;
	}
}
