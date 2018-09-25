output = '''password    requisite     pam_cracklib.so try_first_pass retry=3'''


if (output == null || output.trim() == "") {
    return null;
}
String[] strs = output.split("[ \t]+");
if(strs[0].equals("password")){
    for(String s : strs){
        if(s.contains("retry")){
            String[] value = s.split("=");
            println(Math.abs(Integer.parseInt(value[1].trim())));
            return Math.abs(Integer.parseInt(value[1].trim()));
        }
    }
    return 1;
}
return null;





