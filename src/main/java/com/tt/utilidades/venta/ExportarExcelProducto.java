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

import com.tt.modelo.venta.Producto;

public class ExportarExcelProducto {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Producto> pList;

	public ExportarExcelProducto(List<Producto> listaProducto) {
		this.pList = listaProducto;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("listaProducto");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Id", style);
		createCell(row, 1, "Nombre", style);
		createCell(row, 2, "Precio", style);
		createCell(row, 3, "Cantidad", style);
		createCell(row, 4, "Marca", style);
		createCell(row, 5, "Descripcion", style);
		createCell(row, 6, "Usuario", style);
		createCell(row, 7, "Proveedor Empresa", style);
		createCell(row, 8, "Referencia Producto", style);
		createCell(row, 9, "Proveedor Materia Prima", style);
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

		for (Producto p : this.pList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, p.getId(), style);
			createCell(row, columnCount++, p.getNombre(), style);
			createCell(row, columnCount++, String.valueOf(p.getValorUnitario()), style);
			createCell(row, columnCount++, p.getCantidad(), style);
			createCell(row, columnCount++, p.getMarca(), style);
			createCell(row, columnCount++, p.getDescripcion(), style);
			createCell(row, columnCount++, p.getIdUsuario().getNombre(), style);
			createCell(row, columnCount++, p.getIdProveedorEmpresa().getNombre(), style);
			createCell(row, columnCount++, p.getIdReferenciaProducto().getColor(), style);
			createCell(row, columnCount++, p.getIdProveedorMateria().getNombre(), style);
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
