/*
 *jiji java
 */
package com.test.model;

public class JsonbType extends JsonType {


    public static int size = 90022;

    @Override
    public int[] sqlTypes() {
        return new int[] { size };
    }

}
