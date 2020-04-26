package it.objectmethod.loobia.jobs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import it.objectmethod.loobia.entity.Order;
import it.objectmethod.loobia.entity.OrderDetails;
import it.objectmethod.loobia.repository.OrderRepository;

@Component
public class ordersJobs {
	@Autowired
	private OrderRepository orderRepo;

	// esegui ogni 10 secondi => "*/10 * * * * ?"
	@Scheduled(cron = "00 00 20 * * ?")
	public void writeOrdersToFlatFile() {
		List<Order> orderList = orderRepo.findByEsportato("N");
		if (orderList != null && !orderList.isEmpty()) {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String dataForExtensionFile = "";
			for (int i = 0; i < orderList.size(); i++) {
				String dataParse = df.format(orderList.get(i).getData());
				String orderNumber = Integer.toString(orderList.get(i).getNumero());
				String totAmount = amountGenerate(orderList.get(i).getImportoTot());
				String totAmountDiscount = amountGenerate(orderList.get(i).getImportoTotScontato());
				
				// aggiungo spazi vuoti se la lung non è suffic. e lo inserisco nel file
				String customerCode = String.format("%-10s", orderList.get(i).getCustomerOrder().getCodiceCliente());
				String customerBuisnessName = String.format("%-21s", orderList.get(i).getRagioneSocialeCliente());
				String areaCode = String.format("%-4s", orderList.get(i).getCustomerOrder().getCodZona());
				String finalOrderNumber = String.format("%-11s", orderNumber);

				for (int j = 0; j < 10; j++) {
					if (dataParse.charAt(j) != '-') {
						dataForExtensionFile += dataParse.charAt(j);
					}
				}
				try {
					File file = new File(
							"C:\\Users\\music\\OneDrive\\Desktop\\Loobia\\ORDER_" + orderList.get(i).getNumero() + "_"
									+ orderList.get(i).getArea().getCodZona() + "_" + dataForExtensionFile + ".txt");
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					String finalDataForExtensionFile = String.format("%-11s", dataForExtensionFile);

					bw.write(customerCode + customerBuisnessName + areaCode + finalOrderNumber
							+ finalDataForExtensionFile + totAmount + totAmountDiscount);
					// Codice relativo alla costruzione e inserimento delle righe dei dettagli
					List<OrderDetails> listOrderDet = orderList.get(i).getDetailOrders();
					for (OrderDetails orderDetails : listOrderDet) {
						String idProd = Long.toString(orderDetails.getProduct().getId());
						String descrizione = orderDetails.getProduct().getDescrizione();
						String totPezzi = Integer.toString(orderDetails.getTotPezzi());
						String singlePrice = amountGenerate(orderDetails.getPrezzoSingolo());
						String convertedDiscount = discountConvert(orderDetails.getSconto());
						String finalProductId = String.format("%-20s", idProd);
						String finalTotPezzi = String.format("%-11s", totPezzi);
						String finalDescrizione = String.format("%-41s", descrizione);
						if (descrizione.length() > 40) {
							finalDescrizione = descrizione.substring(0, 41);
						}
						bw.newLine();
						bw.write(finalProductId + finalDescrizione + finalTotPezzi + singlePrice + convertedDiscount);
					}
					dataForExtensionFile = "";
					bw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			orderRepo.esportatoUpdate();
		}
	}

	private String amountGenerate(Float amount) {
		String finalAmount = null;
		if (amount != null) {
			String amountString = amount.toString();
			String zerosForIntegerNumbers = "000000000";
			String zerosForDecimalNumber = "00";
			int sizeAmountBeforeComma = 0;
			int sizeAmountAfterComma = 0;
			int index = 0;
			boolean pass = false;
			finalAmount = "";
			
			// ricavo la quantità di cifre che ci sono prima e dopo il punto
			if (!amountString.isEmpty()) {
				for (int i = 0; i < amountString.length(); i++) {
					if (amountString.charAt(i) != '.') {
						sizeAmountBeforeComma++;
					} else {
						break;
					}
					index = i;
				}
				index++;
				for (int i = index; i < amountString.length(); i++) {
					if (amountString.charAt(i) == '.' || pass == true) {
						pass = false;
						i++;
					}
					if (amountString.charAt(i) != '.') {
						sizeAmountAfterComma++;
					}
				}
			}
			
			// converto l'importo concatenando la cifra con gli "0"
			if (sizeAmountBeforeComma == 6) {
				finalAmount += zerosForIntegerNumbers;
				finalAmount += amountString.substring(0, 6);
			}
			if (sizeAmountBeforeComma == 5) {
				zerosForIntegerNumbers += "0";
				finalAmount += zerosForIntegerNumbers;
				finalAmount += amountString.substring(0, 5);
			}
			if (sizeAmountBeforeComma == 4) {
				zerosForIntegerNumbers += "00";
				finalAmount += zerosForIntegerNumbers;
				finalAmount += amountString.substring(0, 4);
			}
			if (sizeAmountBeforeComma == 3) {
				zerosForIntegerNumbers += "000";
				finalAmount += zerosForIntegerNumbers;
				finalAmount += amountString.substring(0, 3);
			}
			if (sizeAmountBeforeComma == 2) {
				zerosForIntegerNumbers += "0000";
				finalAmount += zerosForIntegerNumbers;
				finalAmount += amountString.substring(0, 2);
			}
			if (sizeAmountBeforeComma == 1) {
				zerosForIntegerNumbers += "00000";
				finalAmount += zerosForIntegerNumbers;
				finalAmount += amountString.substring(0, 1);
			}
			
			// concatenazione numeri decimali
			if (sizeAmountAfterComma >= 3) {
				finalAmount += zerosForDecimalNumber;
				finalAmount += amountString.substring(sizeAmountBeforeComma + 1, sizeAmountBeforeComma + 4);

			} else if (sizeAmountAfterComma == 2) {
				zerosForDecimalNumber += "0";
				finalAmount += zerosForDecimalNumber;
				finalAmount += amountString.substring(sizeAmountBeforeComma + 1, sizeAmountBeforeComma + 3);
			} else {
				zerosForDecimalNumber += "00";
				finalAmount += zerosForDecimalNumber;
				finalAmount += amountString.substring(sizeAmountBeforeComma + 1, sizeAmountBeforeComma + 2);
			}
		}
		return finalAmount;
	}

	private String discountConvert(Integer discount) {
		String scontoString = null;
		if (discount != null) {
			scontoString = Integer.toString(discount);
			if (scontoString.length() == 1) {
				scontoString = "00" + scontoString;
				return scontoString;
			} else if (scontoString.length() == 2) {
				scontoString = "0" + scontoString;
				return scontoString;
			}
		}
		return scontoString;
	}
}
