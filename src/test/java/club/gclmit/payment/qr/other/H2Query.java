package club.gclmit.payment.qr.other;

//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.generator.config.querys.AbstractDbQuery;

//import java.sql.ResultSet;
//import java.sql.SQLException;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.paymentcode.other
 * @author: gclm
 * @date: 2019-02-13 08:08
 * @description:
 */
//public class H2Query extends AbstractDbQuery {
//
//    public static final String PK_QUERY_SQL = "select * from INFORMATION_SCHEMA.INDEXES WHERE TABLE_NAME = '%s'";
//
//
//    @Override
//    public DbType dbType() {
//        return DbType.H2;
//    }
//
//
//    @Override
//    public String tablesSql() {
//        return "SELECT * FROM INFORMATION_SCHEMA.TABLES";
//    }
//
//
//    @Override
//    public String tableFieldsSql() {
//        return "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME= '%s' ";
//    }
//
//
//    @Override
//    public String tableName() {
//        return "TABLE_NAME";
//    }
//
//
//    @Override
//    public String tableComment() {
//        return "REMARKS";
//    }
//
//
//    @Override
//    public String fieldName() {
//        return "COLUMN_NAME";
//    }
//
//
//    @Override
//    public String fieldType() {
//        return "TYPE_NAME";
//    }
//
//
//    @Override
//    public String fieldComment() {
//        return "REMARKS";
//    }
//
//
//    @Override
//    public String fieldKey() {
//        return "PRIMARY_KEY";
//    }
//
//
//    @Override
//    public boolean isKeyIdentity(ResultSet results) throws SQLException {
//        return "auto_increment".equals(results.getString("Extra"));
//    }
//}