package it.objectmethod.loobia.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.objectmethod.loobia.dto.OrderDetailsDto;
import it.objectmethod.loobia.dto.OrderDto;
import it.objectmethod.loobia.entity.Area;
import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.entity.Order;
import it.objectmethod.loobia.entity.OrderDetails;
import it.objectmethod.loobia.entity.PaymentConditions;
import it.objectmethod.loobia.entity.Product;
import it.objectmethod.loobia.repository.AreaRepository;
import it.objectmethod.loobia.repository.CustomerRepository;
import it.objectmethod.loobia.repository.PaymentConditionsRepository;
import it.objectmethod.loobia.repository.ProductRepository;

@Component
public class OrderMapperCustomized {

	@Autowired
	private OrderDetailsMapper orderDetailMapper;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PaymentConditionsRepository payCondRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private AreaRepository areaRepo;

	public Order toEntity(OrderDto dto) {
		if (dto == null) {
			return null;
		}
		Order order = new Order();
		order.setDetailOrders(orderDetailMapper.toEntity(dto.getDetailOrdersDto()));
		Area area = areaRepo.findById(dto.getIdAgente());
		Customer customer = customerRepo.findById(dto.getIdCliente()).get();
		List<OrderDetails> orderDet = order.getDetailOrders();
		PaymentConditions payCond = payCondRepo.findById(dto.getIdCondizioniPagamento()).get();

		order.setData(dto.getData());
		order.setEsportato(dto.getEsportato());
		order.setEvaso(dto.getEvaso());
		order.setId(dto.getId());
		order.setIdIndirizziCliente(dto.getIdIndirizziCliente());
		order.setImportoTot(dto.getImportoTot());
		order.setImportoTotScontato(dto.getImportoTotScontato());
		order.setMezzoConsegnaVettore(dto.getMezzoConsegnaVettore());
		order.setNote(dto.getNote());
		order.setNumero(dto.getNumero());
		order.setRagioneSocialeCliente(dto.getRagioneSocialeCliente());
		order.setSpesaIncasso(dto.getSpesaIncasso());
		order.setStato(dto.getStato());
		order.setCustomerOrder(customer);
		order.setPaymentConditions(payCond);
		order.setArea(area);

		for (int i = 0; i < orderDet.size(); i++) {
			if (orderDet.get(i).getOrder() == null) {
				Product product = productRepo.findById(dto.getDetailOrdersDto().get(i).getIdProdotto());
				orderDet.get(i).setProduct(product);
				orderDet.get(i).setOrder(order);
			}
		}
		return order;
	}

	public OrderDto toDto(Order entity) {
		if (entity == null) {
			return null;
		}
		OrderDto orderDto = new OrderDto();
		Integer idAgenteInteger = entity.getArea().getUser().getIdUtente();
		Long idAgenteLong = Long.valueOf(idAgenteInteger);

		orderDto.setIdCliente(entityCustomerOrderId(entity));
		orderDto.setIdCondizioniPagamento(entityPaymentConditionsId(entity));
		orderDto.setDetailOrdersDto(orderDetailMapper.toDto(entity.getDetailOrders()));
		orderDto.setId(entity.getId());
		orderDto.setData(entity.getData());
		orderDto.setNote(entity.getNote());
		orderDto.setStato(entity.getStato());
		orderDto.setNumero(entity.getNumero());
		orderDto.setMezzoConsegnaVettore(entity.getMezzoConsegnaVettore());
		orderDto.setIdIndirizziCliente(entity.getIdIndirizziCliente());
		orderDto.setIdAgente(idAgenteLong);
		orderDto.setRagioneSocialeCliente(entity.getRagioneSocialeCliente());
		orderDto.setEsportato(entity.getEsportato());
		orderDto.setEvaso(entity.getEvaso());
		orderDto.setImportoTot(entity.getImportoTot());
		orderDto.setImportoTotScontato(entity.getImportoTotScontato());
		orderDto.setSpesaIncasso(entity.getSpesaIncasso());
		List<OrderDetailsDto> orderDetDto = orderDto.getDetailOrdersDto();
		for (int i = 0; i < orderDetDto.size(); i++) {
			orderDto.getDetailOrdersDto().get(i).setIdProdotto(entity.getDetailOrders().get(i).getProduct().getId());
		}
		return orderDto;
	}

	private Integer entityCustomerOrderId(Order order) {
		if (order == null) {
			return null;
		}
		Customer customerOrder = order.getCustomerOrder();
		if (customerOrder == null) {
			return null;
		}
		Integer id = customerOrder.getId();
		if (id == null) {
			return null;
		}
		return id;
	}

	private Long entityPaymentConditionsId(Order order) {
		if (order == null) {
			return null;
		}
		PaymentConditions paymentConditions = order.getPaymentConditions();
		if (paymentConditions == null) {
			return null;
		}
		Long id = paymentConditions.getId();
		if (id == null) {
			return null;
		}
		return id;
	}

}
