package com.purehub.common;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    private static final Map<String, String> fieldTitleMap = new HashMap<>();

    static {
        // 添加字段和标题的映射关系
        fieldTitleMap.put("user_name", "用户名");
        fieldTitleMap.put("term", "总期数");
        fieldTitleMap.put("current_term", "当前期数");
        fieldTitleMap.put("payment_method", "支付方式");
        fieldTitleMap.put("total_amount", "总金额");
        fieldTitleMap.put("plan_start_time", "开始时间");
        fieldTitleMap.put("loan_type", "产品类型");
        fieldTitleMap.put("interest_rate", "产品利率");
        fieldTitleMap.put("payment_time", "支付时间");

    }

    public static void generateReport(Integer userId) throws IOException, SQLException {
        // 连接到数据库
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/give_it_to_you_plus", "root", "123456");

        // 执行查询语句，获取结果集
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select DISTINCT user_name,repayment_plan.term,current_term,total_amount,plan_start_time,loan_type,interest_rate,payment_time,payment_method\n" +
                "from `user`,repayment_plan,loan_application,repayment_record\n" +
                "where user.user_id = repayment_plan.user_id and repayment_plan.user_id = repayment_record.user_id and repayment_record.user_id = loan_application.user_id and `user`.user_id = " + userId + ";");

        // 创建一个新的Excel文档对象
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("报表");


        // 设置标题行的样式
        XSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setFontName("宋体"); // 设置字体为宋体，用于显示中文
        headerCellStyle.setFont(headerFont);

        // 创建标题行
        XSSFRow headerRow = sheet.createRow(0);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String title = fieldTitleMap.get(columnName); // 根据字段名查找对应的中文标题
            if (title == null) {
                title = columnName; // 如果找不到对应的中文标题，使用英文字段名作为标题
            }
            XSSFCell cell = headerRow.createCell(i - 1);
            cell.setCellValue(title);
            cell.setCellStyle(headerCellStyle);
        }

        // 设置数据行的样式
        XSSFCellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);

        // 填充数据行
        int rowNum = 1;
        while (resultSet.next()) {
            XSSFRow dataRow = sheet.createRow(rowNum);
            for (int i = 1; i <= columnCount; i++) {
                String value = resultSet.getString(i);
                XSSFCell cell = dataRow.createCell(i - 1);
                cell.setCellValue(value);
                cell.setCellStyle(dataCellStyle);
            }
            rowNum++;
        }

        // 自适应列宽
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
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
