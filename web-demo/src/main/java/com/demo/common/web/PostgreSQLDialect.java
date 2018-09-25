/*
 *jiji java
 */
package com.demo.common.web;

import java.util.Properties;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.Type;

public class PostgreSQLDialect extends PostgreSQL9Dialect{


    public PostgreSQLDialect() {
        super();
    }

    /**
     * 获取标的主键生成器（Serial 类型）
     *
     * @return 表的主键生成器
     */
    @Override
    public Class<?> getNativeIdentifierGeneratorClass(){
        return TableNameSequenceGenerator.class;
    }

    /**
     * 针对Serial 类型，创建缺省主键序列号（Sequence）生成器
     * 参考<a href='http://www.hibernate.org/296.html'>http://www.hibernate.org/296.html<a/>
     *
     */
    public static class TableNameSequenceGenerator extends SequenceGenerator{

        /**
         * {@inheritDoc}}
         * 如果实体的配置参数中没有包含{@link SequenceGenerator#SEQUENC} 名，则Serial 类型定义自动生成。
         */
        @Override
        public void configure(final Type type,final Properties params ,final Dialect dialect) {
            Boolean sequencePerTable = Boolean.TRUE;

            if (sequencePerTable) {
                if (params.getProperty(SEQUENCE) == null || params.getProperty(SEQUENCE).length() == 0) {
                    String tableName = params.getProperty(PersistentIdentifierGenerator.TABLE);
                    String pkName = params.getProperty(PersistentIdentifierGenerator.PK);
                    String schemaName = params.getProperty("schemaName");
                    if (schemaName != null) {
                        params.getProperty(PersistentIdentifierGenerator.SCHEMA,schemaName);
                    }
                    if (tableName != null) {
                        params.setProperty(SEQUENCE, tableName + "_" + pkName + "_seq");
                    }
                }
            }
            super.configure(type, params, dialect);

        }

    }


}
