package com.tt.utilidades.inventario;

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

import com.tt.modelo.inventario.OrdenProduccion;

public class ExportarExcelOrdenProduccion {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<OrdenProduccion> opList;

	public ExportarExcelOrdenProduccion(List<OrdenProduccion> listaOrdenProduccion) {
		this.opList = listaOrdenProduccion;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("listaOrdenProduccion");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Id", style);
		createCell(row, 1, "Fecha", style);
		createCell(row, 2, "Cantidad", style);
		createCell(row, 3, "Observacion", style);
		createCell(row, 4, "Saldo Inventario", style);
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

		for (OrdenProduccion op : this.opList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, op.getId(), style);
			createCell(row, columnCount++, op.getFecha().toString(), style);
			createCell(row, columnCount++, op.getCantidad(), style);
			createCell(row, columnCount++, op.getObservacion(), style);
			createCell(row, columnCount++, String.valueOf( op.getIdInventarioMateriaPrima().getSaldo())  , style);
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
