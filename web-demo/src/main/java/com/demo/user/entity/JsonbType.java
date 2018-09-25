/*
 *jiji java
 */
package com.demo.user.entity;

public class JsonbType extends JsonType {


    public static int size = 90022;

    @Override
    public int[] sqlTypes() {
        return new int[] { size };
    }

}
