package com.tt.utilidades.envio;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tt.modelo.envio.Pedido;

public class ExportarExcelPedido {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Pedido> pList;

	public ExportarExcelPedido(List<Pedido> listaPedido) {
		this.pList = listaPedido;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("listaPedido");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Id", style);
		createCell(row, 1, "Fecha Pedido", style);
		createCell(row, 2, "Fecha Entrega", style);
		createCell(row, 3, "Fecha Envio", style);
		createCell(row, 4, "Forma Envio", style);
		createCell(row, 5, "Destinario", style);
		createCell(row, 6, "Direccion", style);
		createCell(row, 7, "Codigo Factura", style);
		createCell(row, 8, "Nombre Envio", style);
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Pedido pd : this.pList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, pd.getId(), style);
			createCell(row, columnCount++, pd.getFechaPedido().toString(), style);
			createCell(row, columnCount++, pd.getFechaEntrega().toString(), style);
			createCell(row, columnCount++, pd.getFechaEnvio().toString(), style);
			createCell(row, columnCount++, pd.getFormaEnvio(), style);
			createCell(row, columnCount++, pd.getDestinatario(), style);
			createCell(row, columnCount++, pd.getDireccion(), style);
			createCell(row, columnCount++, pd.getIdFactura().getCodigo(), style);
			createCell(row, columnCount++, pd.getIdEnvio().getNombreLogistico(), style);
		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
