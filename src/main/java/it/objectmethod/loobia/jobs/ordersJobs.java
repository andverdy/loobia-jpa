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
	@Scheduled(cron = "*/10 * * * * ?")
	public void writeOrdersToFlatFile() {
		List<Order> orderList = orderRepo.findByEsportato("N");
		if (orderList != null && !orderList.isEmpty()) {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String newDataForExtensionFile = "";

			for (int i = 0; i < orderList.size(); i++) {
				String dataParse = df.format(orderList.get(i).getData());
				int lengthCodiceCliente = orderList.get(i).getCustomerOrder().getCodiceCliente().length();
				int lengthRagSoc = orderList.get(i).getRagioneSocialeCliente().length();
				int lengthCodiceZona = orderList.get(i).getCustomerOrder().getCodZona().length();
				String orderNumb = Integer.toString(orderList.get(i).getNumero());
				int lengthNumOrder = orderNumb.length();
				String totAmount = amountGenerate(orderList.get(i).getImportoTot());
				String totAmountDiscount = amountGenerate(orderList.get(i).getImportoTotScontato());

				for (int j = 0; j < 10; j++) {
					if (dataParse.charAt(j) != '-') {
						newDataForExtensionFile += dataParse.charAt(j);
					}
				}
				int lengthData = newDataForExtensionFile.length();
				try {
					File file = new File(
							"C:\\Users\\music\\OneDrive\\Desktop\\Loobia\\ORDER_" + orderList.get(i).getNumero() + "_"
									+ orderList.get(i).getArea().getCodZona() + "_" + newDataForExtensionFile + ".txt");
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);

					while (lengthCodiceCliente < 10) {
						orderList.get(i).getCustomerOrder()
								.setCodiceCliente(orderList.get(i).getCustomerOrder().getCodiceCliente() + " ");
						lengthCodiceCliente++;
					}
					while (lengthRagSoc <= 20) {
						orderList.get(i).setRagioneSocialeCliente(orderList.get(i).getRagioneSocialeCliente() + " ");
						lengthRagSoc++;
					}
					while (lengthCodiceZona <= 3) {
						orderList.get(i).getCustomerOrder()
								.setCodZona(orderList.get(i).getCustomerOrder().getCodZona() + " ");
						lengthCodiceZona++;
					}
					while (lengthNumOrder <= 10) {
						orderNumb += " ";
						lengthNumOrder++;
					}
					while (lengthData <= 10) {
						newDataForExtensionFile += " ";
						lengthData++;
					}
					bw.write(orderList.get(i).getCustomerOrder().getCodiceCliente()
							+ orderList.get(i).getRagioneSocialeCliente()
							+ orderList.get(i).getCustomerOrder().getCodZona() + orderNumb + newDataForExtensionFile
							+ totAmount + totAmountDiscount);

					// Codice relativo alla costruzione e inserimento delle righe dei dettagli
					List<OrderDetails> listOrderDet = orderList.get(i).getDetailOrders();
					for (OrderDetails orderDetails : listOrderDet) {
						Long productId = orderDetails.getProduct().getId();
						String idProd = Long.toString(productId);
						int lengthIdProd = idProd.length();
						String descrizione = orderDetails.getProduct().getDescrizione();
						int lengthDescrizione = descrizione.length();
						String totPezzi = Integer.toString(orderDetails.getTotPezzi());
						int lengthTotPezzi = totPezzi.length();
						String singlePrice = amountGenerate(orderDetails.getPrezzoSingolo());
						String convertedDiscount = discountConvert(orderDetails.getSconto());

						while (lengthIdProd < 20) {
							idProd += " ";
							lengthIdProd++;
						}
						if (lengthDescrizione <= 40) {
							while (lengthDescrizione <= 40) {
								descrizione += " ";
								lengthDescrizione++;
							}
						} else if (descrizione.length() > 40) {
							descrizione = descrizione.substring(0, 41);
						}
						while (lengthTotPezzi <= 10) {
							totPezzi += " ";
							lengthTotPezzi++;
						}
						bw.newLine();
						bw.write(idProd + descrizione + totPezzi + singlePrice + convertedDiscount);
					}
					newDataForExtensionFile = "";
					bw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			orderRepo.esportatoUpdate();
		}
	}

	private String amountGenerate(Float amount) {
		String amountString = amount.toString();
		int sizeAmountBeforeComma = 0;
		int sizeAmountAfterComma = 0;
		int index = 0;
		boolean pass = false;
		String finalAmount = "";

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
		StringBuffer sbMax = new StringBuffer();
		sbMax.append("00000000000000");
		StringBuffer sbMin = new StringBuffer();
		sbMin.append("0000");

		if (sizeAmountBeforeComma == 1) {
			finalAmount += sbMax;
			finalAmount += amountString.charAt(0);
		}
		// 00000000000002000345
		if (sizeAmountBeforeComma == 2) {
			sbMax.deleteCharAt(sbMax.length() - 1);
			finalAmount += sbMax;
			finalAmount += amountString.charAt(0);
			finalAmount += amountString.charAt(1);
		}
		// 00000000000020000345
		if (sizeAmountBeforeComma == 3) {
			sbMax.deleteCharAt(sbMax.length() - 1);
			sbMax.deleteCharAt(sbMax.length() - 2);
			finalAmount += sbMax;
			finalAmount += amountString.charAt(0);
			finalAmount += amountString.charAt(1);
			finalAmount += amountString.charAt(2);
		}
		// 00000000000210000346
		if (sizeAmountBeforeComma == 4) {
			sbMax.deleteCharAt(sbMax.length() - 1);
			sbMax.deleteCharAt(sbMax.length() - 2);
			sbMax.deleteCharAt(sbMax.length() - 3);
			finalAmount += sbMax;
			finalAmount += amountString.charAt(0);
			finalAmount += amountString.charAt(1);
			finalAmount += amountString.charAt(2);
			finalAmount += amountString.charAt(3);
		}
		// 00000000002034500034
		if (sizeAmountBeforeComma == 5) {
			sbMax.deleteCharAt(sbMax.length() - 1);
			sbMax.deleteCharAt(sbMax.length() - 2);
			sbMax.deleteCharAt(sbMax.length() - 3);
			sbMax.deleteCharAt(sbMax.length() - 4);
			finalAmount += sbMax;
			finalAmount += amountString.charAt(0);
			finalAmount += amountString.charAt(1);
			finalAmount += amountString.charAt(2);
			finalAmount += amountString.charAt(3);
			finalAmount += amountString.charAt(4);
		}
		// 00000000022034500038
		if (sizeAmountBeforeComma == 6) {
			sbMax.deleteCharAt(sbMax.length() - 1);
			sbMax.deleteCharAt(sbMax.length() - 2);
			sbMax.deleteCharAt(sbMax.length() - 3);
			sbMax.deleteCharAt(sbMax.length() - 4);
			sbMax.deleteCharAt(sbMax.length() - 5);
			finalAmount += sbMax;
			finalAmount += amountString.charAt(0);
			finalAmount += amountString.charAt(1);
			finalAmount += amountString.charAt(2);
			finalAmount += amountString.charAt(3);
			finalAmount += amountString.charAt(4);
			finalAmount += amountString.charAt(5);
		}
		if (sizeAmountAfterComma == 1) {
			finalAmount += sbMin;
			finalAmount += amountString.charAt(sizeAmountBeforeComma + 1);
		} else if (sizeAmountAfterComma == 2) {
			sbMin.deleteCharAt(sbMin.length() - 1);
			finalAmount += sbMin;
			finalAmount += amountString.charAt(sizeAmountBeforeComma + 1);
			finalAmount += amountString.charAt(sizeAmountBeforeComma + 2);
		} else {
			sbMin.deleteCharAt(sbMin.length() - 1);
			sbMin.deleteCharAt(sbMin.length() - 2);
			finalAmount += sbMin;
			finalAmount += amountString.charAt(sizeAmountBeforeComma + 1);
			finalAmount += amountString.charAt(sizeAmountBeforeComma + 2);
			finalAmount += amountString.charAt(sizeAmountBeforeComma + 3);
		}
		return finalAmount;
	}

	private String discountConvert(Integer discount) {
		String scontoString = Integer.toString(discount);
		if (scontoString.length() == 1) {
			scontoString = "00" + scontoString;
		} else if (scontoString.length() == 2) {
			scontoString = "0" + scontoString;
		}
		return scontoString;
	}
}
