package com.purehub.common;

import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

    public static void generateReport() throws IOException, SQLException {
        // 连接到数据库
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/give_it_to_you_plus", "root", "123456");

        // 执行查询语句，获取结果集
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM repayment_record");

        // 创建一个新的Excel文档对象
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("报表");

        // 创建标题行
        XSSFRow headerRow = sheet.createRow(0);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            XSSFCell cell = headerRow.createCell(i - 1);
            cell.setCellValue(columnName);
        }

        // 填充数据行
        int rowNum = 1;
        while (resultSet.next()) {
            XSSFRow dataRow = sheet.createRow(rowNum);
            for (int i = 1; i <= columnCount; i++) {
                String value = resultSet.getString(i);
                XSSFCell cell = dataRow.createCell(i - 1);
                cell.setCellValue(value);
            }
            rowNum++;
        }

        // 输出到文件
        FileOutputStream out = new FileOutputStream("report.xlsx");
        workbook.write(out);
        out.close();

        System.out.println("报表生成成功！");

        // 关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
