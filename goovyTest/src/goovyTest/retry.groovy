output = '''/etc/pam.d/password-auth:password    requisite     pam_pwquality.so try_first_pass local_users_only retry=3 authtok_type=
/etc/pam.d/system-auth:password    requisite     pam_pwquality.so try_first_pass local_users_only retry=3 authtok_type=
'''
if (output == null || output.trim() == "") {
    return null;
}
String[] strs = output.split("\\R");
if (strs.size() > 1) {
    if(strs[0].contains("retry")){
        String [] strArr =  strs[0].split("[ \t]+");
        for(String s : strArr){
            if(s.contains("retry")){
                String[] value = s.split("=");
                return Integer.parseInt(value[1].trim());
            }
        }
    }
}
return null;