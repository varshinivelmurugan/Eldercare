//code written by myself
package com.example.eldercareapp;


public class ColorMap {
    private final int value;

    private final String name;

    public ColorMap(int value, String name) {

        this.value = value;
        this.name = name;
    }

    public final int getValue() {
        return this.value;
    }

    public final String getName() {
        return this.name;
    }

    public String toString() {
        return "ColorMap(value=" + this.value + ", name=" + this.name + ")";
    }

    public int hashCode() {
        int var10000 = this.value * 31;
        String var10001 = this.name;
        return var10000 + (var10001 != null ? var10001.hashCode() : 0);
    }

    public boolean equals(Object var1) {
        if (this != var1) {
            if (var1 instanceof ColorMap) {
                ColorMap var2 = (ColorMap) var1;
                if (this.value == var2.value && this.name.equals(var2.name)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}
