package com.marcolotz.db2parquet.adapter.avro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import org.apache.avro.Schema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName( "When converting JDBC to Avro schemas" )
class ResultSetSchemaGeneratorTest {

  @Test
  @DisplayName( "Then the schema is correctly converted" )
  public void testGenerateSchema() throws SQLException {

    String schemaName = "SchemaName";
    String namespace = "org.namespace";

    ResultSetSchemaGenerator generator = new ResultSetSchemaGenerator();

    ResultSet resultSet = mock(ResultSet.class);
    ResultSetMetaData metaData = mock(ResultSetMetaData.class);
    when(resultSet.getMetaData()).thenReturn(metaData);

    // mock metadata so schema is created
    when(metaData.getColumnCount()).thenReturn(4);
    when(metaData.getColumnName(1)).thenReturn("id");
    when(metaData.getColumnType(1)).thenReturn(Types.INTEGER);

    when(metaData.getColumnName(2)).thenReturn("name");
    when(metaData.getColumnType(2)).thenReturn(Types.VARCHAR);

    when(metaData.getColumnName(3)).thenReturn("timestamp");
    when(metaData.getColumnType(3)).thenReturn(Types.TIMESTAMP);

    when(metaData.getColumnName(4)).thenReturn("double");
    when(metaData.getColumnType(4)).thenReturn(Types.DOUBLE);

    ParsedAvroSchema schemaResults = generator.generateSchema(resultSet, schemaName, namespace);

    List<SchemaSqlMapping> mappings = schemaResults.getMappings();

    // Number of colums must match
    assertEquals(4, mappings.size());

    assertEquals("id", mappings.get(0).getSqlColumnName());
    assertEquals(Types.INTEGER, mappings.get(0).getSqlType());
    assertEquals(Schema.Type.INT, mappings.get(0).getSchemaType());

    assertEquals("name", mappings.get(1).getSqlColumnName());
    assertEquals(Types.VARCHAR, mappings.get(1).getSqlType());
    assertEquals(Schema.Type.STRING, mappings.get(1).getSchemaType());

    assertEquals("timestamp", mappings.get(2).getSqlColumnName());
    assertEquals(Types.TIMESTAMP, mappings.get(2).getSqlType());
    assertEquals(Schema.Type.LONG, mappings.get(2).getSchemaType());

    assertEquals("double", mappings.get(3).getSqlColumnName());
    assertEquals(Types.DOUBLE, mappings.get(3).getSqlType());
    assertEquals(Schema.Type.DOUBLE, mappings.get(3).getSchemaType());

  }

}