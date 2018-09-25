output = '''1sdfa'''
output2 = '''6'''



println(output.isInteger());
println(output2.isInteger());

if (output.isInteger() && output2.isInteger()) {
    int out1 = Integer.parseInt(output);
    int out2 = Integer.parseInt(output2);
    if (out1 >= 2 && out2 >= 5) {
        println("return true");
        return true;
    } else {
        println("return false");
        return false;
    }
} else {
    println("return false");
    return false;
}
return false;

