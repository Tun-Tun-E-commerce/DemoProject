package com.tt.utilidades.venta;

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

import com.tt.modelo.venta.Factura;

public class ExportarExcelFactura {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Factura> fList;

	public ExportarExcelFactura(List<Factura> listaFactura) {
		this.fList = listaFactura;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("listaFactura");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Id", style);
		createCell(row, 1, "Codigo", style);
		createCell(row, 2, "Fecha", style);
		createCell(row, 3, "Valor", style);
		createCell(row, 4, "Carrito", style);
		createCell(row, 5, "Metodo Pago", style);
		createCell(row, 6, "Periodo", style);
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

		for (Factura f : this.fList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, f.getId(), style);
			createCell(row, columnCount++, f.getCodigo(), style);
			createCell(row, columnCount++, f.getFecha().toString(), style);
			createCell(row, columnCount++, String.valueOf(f.getValor()), style);
			createCell(row, columnCount++, String.valueOf(f.getIdCarrito().getCantidad()), style);
			createCell(row, columnCount++, f.getIdMetodoPago().getNombre(), style);
			createCell(row, columnCount++, f.getIdPeriodo().getId(), style);
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
