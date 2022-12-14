package com.marcolotz.db2parquet.adapter.avro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Transforms an SQL ResultSet into another format.
 */
public interface ResultSetTransformer {


  /**
   * Extracts the appropriate value from the ResultSet using the given SchemaSqlMapping.
   *
   * @param mapping
   * @param resultSet
   * @return
   * @throws SQLException
   */
  static Object extractResult(SchemaSqlMapping mapping, ResultSet resultSet) throws SQLException {

    switch (mapping.getSqlType()) {
      case Types.BOOLEAN:
        return resultSet.getBoolean(mapping.getSqlColumnName());
      case Types.TINYINT:
      case Types.SMALLINT:
      case Types.INTEGER:
      case Types.BIGINT:
      case Types.ROWID:
        return resultSet.getInt(mapping.getSqlColumnName());
      case Types.REAL:
      case Types.FLOAT:
        return resultSet.getFloat(mapping.getSqlColumnName());
      case Types.DOUBLE:
        return resultSet.getDouble(mapping.getSqlColumnName());
      case Types.NUMERIC:
      case Types.DECIMAL:
        return resultSet.getBigDecimal(mapping.getSqlColumnName());
      case Types.DATE:
        return resultSet.getDate(mapping.getSqlColumnName()).getTime();
      case Types.TIME:
      case Types.TIME_WITH_TIMEZONE:
        return resultSet.getTime(mapping.getSqlColumnName()).getTime();
      case Types.TIMESTAMP:
      case Types.TIMESTAMP_WITH_TIMEZONE:
        return resultSet.getTimestamp(mapping.getSqlColumnName()).getTime();
      case Types.BINARY:
      case Types.VARBINARY:
      case Types.LONGVARBINARY:
      case Types.NULL:
      case Types.OTHER:
      case Types.JAVA_OBJECT:
      case Types.DISTINCT:
      case Types.STRUCT:
      case Types.ARRAY:
      case Types.BLOB:
      case Types.CLOB:
      case Types.REF:
      case Types.DATALINK:
      case Types.NCLOB:
      case Types.REF_CURSOR:
        return resultSet.getByte(mapping.getSqlColumnName());
      default:
        /***
         * case Types.CHAR:
         * case Types.VARCHAR:
         * case Types.LONGVARCHAR:
         * case Types.NCHAR:
         * case Types.NVARCHAR:
         * case Types.LONGNVARCHAR:
         * case Types.SQLXML:
         */
        return resultSet.getString(mapping.getSqlColumnName());
    }
  }
}
