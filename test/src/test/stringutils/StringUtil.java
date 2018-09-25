package test.stringutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {

    /**
     * ʹ��commons-lang-2.5.jar �� �汾commons-lang-2.1.jar ��������ȫ
     *
     */

    public static void TestStr() {
        // null �� ""����~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // �ж��Ƿ�Null ���� ""
        // System.out.println(StringUtils.isEmpty(null));
        // System.out.println(StringUtils.isNotEmpty(null));
        // �ж��Ƿ�null ���� "" ȥ�ո�~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // System.out.println(StringUtils.isBlank(" "));
        // System.out.println(StringUtils.isNotBlank(null));
        // ȥ�ո�.Null����null~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // System.out.println(StringUtils.trim(null));
        // ȥ�ո񣬽�Null��"" ת��ΪNull
        // System.out.println(StringUtils.trimToNull(""));
        // ȥ�ո񣬽�NULL �� "" ת��Ϊ""
        // System.out.println(StringUtils.trimToEmpty(null));
        // �����Ƕ�����ո����ȥ������
        // System.out.println(StringUtils.strip("��Һ� �� \t"));
        // ͬ�ϣ���""��nullת��ΪNull
        // System.out.println(StringUtils.stripToNull(" \t"));
        // ͬ�ϣ���""��nullת��Ϊ""
        // System.out.println(StringUtils.stripToEmpty(null));
        // ��""����Null ת��Ϊ ""
        // System.out.println(StringUtils.defaultString(null));
        // �����ַ���ΪNullʱ ת��Ϊָ�����ַ���(������)
        // System.out.println(StringUtils.defaultString(null, "hehe"));
        // ���ַ���Ϊnull����""ʱ��ת��Ϊָ�����ַ���(������)
        // System.out.println(StringUtils.defaultIfEmpty("", "sos"));
        // ȥ�ո�.ȥ�ַ�,����ڶ�������Ϊnullȥ�����߿ո�(����ȥ���ַ�������һ�����ַ�,�м��������,����һ��Ϊֹ)
        // System.out.println(StringUtils.strip("abacada", "a"));
        // ����ڶ�������Ϊnullֻȥ��ǰ��ո�(����ȥ���ַ���ǰ��һ�����ַ�,�м��������,����һ��Ϊֹ)
        // System.out.println(StringUtils.stripStart("ddsuuu ", "dd"));
        // ����ڶ�������Ϊnullֻȥ����ո�(����ȥ���ַ�������һ�����ַ�������һ��Ϊֹ)
        // System.out.println(StringUtils.stripEnd("dabads", "das"));
        // ������ÿ���ַ�������ȥ�ո�
        // ArrayToList(StringUtils.stripAll(new String[]{" �л� ", "�� �� ", "����
        // "}));
        // ����ڶ�������Ϊnull.������ÿ���ַ�������ȥ�ո�(����ȥ������ÿ��Ԫ�ؿ�ʼ�ͽ�βһ�����ַ�)
        // ArrayToList(StringUtils.stripAll(new String[]{" �л� ", "�� ��", "�����͹�"},
        // "��"));
        // ����,�ж������ַ����Ƿ����,NullҲ���
        // System.out.println(StringUtils.equals(null, null));
        // �����ִ�Сд�Ƚ�,���Ĳ�������,�ո�������
        // System.out.println(StringUtils.equalsIgnoreCase("abc", "ABc"));
        // ���ң���֪����ôŪ��ô����ң��ܶ಻֪���������ģ��Ѿ�~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // ��ͨ�����ַ������һ����Ϊnull����""����-1
        // System.out.println(StringUtils.indexOf("sdadsfasa", "a"));
        // ��ָ��λ��(������)��ʼ���ң������ӵ�2���ַ���ʼ����k�ַ�
        // System.out.println(StringUtils.indexOf("akfekcd�л�", "k", 2));
        // δ���ֲ�֮ͬ��
        // System.out.println(StringUtils.ordinalIndexOf("akfekcfd�л�", "f", 2));
        // ����,�����ִ�Сд
        // System.out.println(StringUtils.indexOfIgnoreCase("adfs", "D"));
        // ��ָ��λ��(������)��ʼ����,�����ִ�Сд
        // System.out.println(StringUtils.indexOfIgnoreCase("adfs", "a", 3));
        // �Ӻ���ǰ����
        // System.out.println(StringUtils.lastIndexOf("adfas", "a"));
        // δ���,�˽��Ϊ2
        // System.out.println(StringUtils.lastIndexOf("d��abasdafs��", "a", 3));
        // δ��,�˽��Ϊ-1
        // System.out.println(StringUtils.lastOrdinalIndexOf("yksdfdht", "f",
        // 2));
        // �Ӻ���ǰ�飬�����ִ�Сд
        // System.out.println(StringUtils.lastIndexOfIgnoreCase("sdffet", "E"));
        // δ��,�˽��Ϊ1
        // System.out.println(StringUtils.lastIndexOfIgnoreCase("efefrfs��", "F"
        // , 2));
        // ����Ƿ�鵽������boolean,null���ؼ�
        // System.out.println(StringUtils.contains("sdf", "d"));
        // ����Ƿ�鵽������boolean,null���ؼ�,�����ִ�Сд
        // System.out.println(StringUtils.containsIgnoreCase("sdf", "D"));
        // ����Ƿ��к��пո�,����boolean
        // System.out.println(StringUtils.containsWhitespace(" d"));
        // ��ѯ�ַ�����������һԪ����ͬ�ĵ�һ����ͬ��λ��
        // System.out.println(StringUtils.indexOfAny("absfekf", new
        // String[]{"f", "e"}));
        // ��ѯ�ַ�����ָ���ַ���(������)��һ�γ��ֵĵ�λ��
        // System.out.println(StringUtils.indexOfAny("afefese", "s"));
        // �����ַ������Ƿ����ַ���������ͬ���ַ�������boolean
        // System.out.println(StringUtils.containsAny("asfsd", new char[]{'k',
        // 'e', 's'}));
        // δ�����lastIndexOf��֮ͬ�����Ƿ�鵽������boolean
        // System.out.println(StringUtils.containsAny("��f����", "��"));
        // δ��
        // System.out.println(StringUtils.indexOfAnyBut("seefaff", "af"));
        // �ж��ַ����������ַ����Ƿ��ǳ��Բ������С�
        // System.out.println(StringUtils.containsOnly("Ϊ������", "���й�"));
        // �ж��ַ����������ַ����Ƿ��ǳ��Բ������������С�
        // System.out.println(StringUtils.containsOnly("�л��й�", new char[]{'��',
        // '��'}));
        // �ж��ַ����������ַ����Ƿ񶼲��ڲ������С�
        // System.out.println(StringUtils.containsNone("�л���", "��"));
        // �ж��ַ����������ַ����Ƿ񶼲��ڲ������������С�
        // System.out.println(StringUtils.containsNone("�л���", new char[]{'��',
        // '��'}));
        // �Ӻ���ǰ�����ַ��������ַ���������ͬ��Ԫ�ص�һ�γ��ֵ�λ�á�����Ϊ4
        // System.out.println(StringUtils.lastIndexOfAny("�й����񹲺͹�", new
        // String[]{"����", "����"}));
        // δ������indexOfAny��֮ͬ�� ��ѯ�ַ�����ָ���ַ���(������)���ֵĴ���
        // System.out.println(StringUtils.countMatches("�й����񹲺��й�", "�й�"));
        // ����Ƿ�CharSequence��ֻ����Unicode����ĸ���ս�����false��һ���յ�CharSequence��������=
        // 0��������true
        // System.out.println(StringUtils.isAlpha("asd�й�as"));
        // ����Ƿ�ֻ����Unicode��CharSequence����ĸ�Ϳո�''�����ս�����һ���յ�CharSequence�٣�������=
        // 0��������true��
        // System.out.println(StringUtils.isAlphaSpace("NBAֱ�� 2"));
        // ����Ƿ�ֻ����Unicode��CharSequence����ĸ�����֡��ս�����false��һ���յ�CharSequence��������=
        // 0��������true��
        // System.out.println(StringUtils.isAlphanumeric("NBAֱ��"));
        // �������Unicode
        // CharSequence��ֻ������ĸ�����ֻ�ո�''�����ս�����false��һ���յ�CharSequence��������=
        // 0��������true��
        // System.out.println(StringUtils.isAlphanumericSpace("NBAֱ��"));
        // ����Ƿ�ֻ����ASCII��CharSequence���ַ����ս�����false��һ���յ�CharSequence��������=
        // 0��������true��
        // System.out.println(StringUtils.isAsciiPrintable("NBAֱ��"));
        // ����Ƿ�ֻ������ֵ��
        // System.out.println(StringUtils.isNumeric("123asdf"));
        // ����Ƿ�ֻ������ֵ���߿ո�
        // System.out.println(StringUtils.isNumericSpace("33545"));
        // ����Ƿ�ֻ�ǿո��""��
        // System.out.println(StringUtils.isWhitespace(" "));
        // ����Ƿ�ȫ��Ӣ��Сд��
        // System.out.println(StringUtils.isAllLowerCase("kjk33"));
        // ����Ƿ�ȫ��Ӣ�Ĵ�д��
        // System.out.println(StringUtils.isAllUpperCase("KJKJ"));
        // ��������~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // ȥ������2�ַ������ڲ���һ�п�ͷ���ֹ��еĲ��֣����Ϊ:���񹲺ͼ���
        // System.out.println(StringUtils.difference("�й�����", "�й����񹲺ͼ���"));
        // ͳ��2���ַ�����ʼ���ֹ��е��ַ�����
        // System.out.println(StringUtils.indexOfDifference("www.taobao",
        // "www.baidu.com"));
        // ͳ�������и���Ԫ�ص��ַ�����ʼ��һ�����ַ�����
        // System.out.println(StringUtils.indexOfDifference(new String[]
        // {"�й�����", "�й�����", "�й�����"}));
        // ȡ����ÿ��Ԫ�ع�ͬ�Ĳ����ַ���
        // System.out.println(StringUtils.getCommonPrefix(new String[] {"�й�����",
        // "�й�����", "�й�����"}));
        // ͳ�Ʋ���һ��ÿ���ַ����������ÿ���ַ���ͬ���ֵ��ַ�����
        // System.out.println(StringUtils.getLevenshteinDistance("�й����ͷ�������",
        // "���͹�"));
        // �жϿ�ʼ�����Ƿ����������ͬ
        // System.out.println(StringUtils.startsWith("�й����͹�����", "�й�"));
        // �жϿ�ʼ�����Ƿ����������ͬ�������ִ�Сд
        // System.out.println(StringUtils.startsWithIgnoreCase("�й����͹�����",
        // "�й�"));
        // �ж��ַ�����ʼ�����Ƿ��������е�ĳһԪ����ͬ
        // System.out.println(StringUtils.startsWithAny("abef", new
        // String[]{"ge", "af", "ab"}));
        // �жϽ�β�Ƿ���ͬ
        // System.out.println(StringUtils.endsWith("abcdef", "def"));
        // �жϽ�β�Ƿ���ͬ�������ִ�Сд
        // System.out.println(StringUtils.endsWithIgnoreCase("abcdef", "Def"));
        // �ַ�����ȡ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // ��ȡָ��λ�õ��ַ���null����null,""����""
        // System.out.println(StringUtils.substring("����", 1));
        // ��ȡָ��������ַ�
        // System.out.println(StringUtils.substring("�й����񹲺͹�", 2, 4));
        // �����ȡָ�����ȵ��ַ���
        // System.out.println(StringUtils.left("˵��ʲô����", 3));
        // ���ҽ�ȡָ�����ȵ��ַ���
        // System.out.println(StringUtils.right("˵��ʲô����", 3));
        // �������ӵڼ�����ʼ��ȡ����������ʾ��ȡ�ĳ���
        // System.out.println(StringUtils.mid("˵��ʲô����", 3, 2));
        // ��ȡ�����ڵڶ����������ַ���Ϊֹ
        // System.out.println(StringUtils.substringBefore("˵��ʲô����", "��"));
        // �������Ҳ鵽��ȵ��ַ���ʼ��������ߵģ����������ڵ��ַ���������ʲô����
        // System.out.println(StringUtils.substringAfter("˵��ʲô����", "��"));
        // ���Ҳ�ǽ�ȡ����ȵ��ַ��������Ǵ�������.���������˵��ʲô��
        // System.out.println(StringUtils.substringBeforeLast("˵��ʲô�õ���", "��"));
        // �����ȡͬ���Ǵ������󡣵��Ǳ����ұߵ��ַ�
        // System.out.println(StringUtils.substringAfterLast("˵��ʲô�õ��أ�", "��"));
        // ��ȡ���ҵ���һ�ε�λ�ã��͵ڶ��ε�λ���м���ַ������û�ҵ��ڶ�������null���������:2010���籭��
        // System.out.println(StringUtils.substringBetween("�Ϸ�2010���籭���Ϸǣ����Ϸ�",
        // "�Ϸ�"));
        // ���ز������Ͳ������м���ַ���������������ʽ
        // ArrayToList(StringUtils.substringsBetween("[a][b][c]", "[", "]"));
        // �ָ�~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // �ÿո�ָ�����飬nullΪnull
        // ArrayToList(StringUtils.split("�л� ���� ����"));
        // ��ָ���ַ��ָ������
        // ArrayToList(StringUtils.split("�л� ,����,����", ","));
        // ��ָ���ַ��ָ�����飬������������ʾ�ָ�������ĳ��ȣ����Ϊ0ȫ��ָ�
        // ArrayToList(StringUtils.split("�л� �����񣺹���", "��", 2));
        // δ���ֲ�ͬ�ĵط�,ָ���ַ��ָ������
        // ArrayToList(StringUtils.splitByWholeSeparator("ab-!-cd-!-ef",
        // "-!-"));
        // δ���ֲ�ͬ�ĵط�,��ָ���ַ��ָ�����飬������������ʾ�ָ�������ĳ���
        // ArrayToList(StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-",
        // 2));
        // �ָ��" "���ᱻ������һ��Ԫ��,������ΪnullĬ��Ϊ�ո�ָ�
        // ArrayToList(StringUtils.splitByWholeSeparatorPreserveAllTokens(" ab
        // de fg ", null));
        // ͬ�ϣ��ָ�," "���ᱻ������һ��Ԫ�ء���������������ָ�����鳤�ȡ�
        // ArrayToList(StringUtils.splitByWholeSeparatorPreserveAllTokens("ab de
        // fg", null, 3));
        // δ���ֲ�ͬ�ط�,�ָ�
        // ArrayToList(StringUtils.splitPreserveAllTokens(" ab de fg "));
        // δ���ֲ�ͬ�ط�,ָ���ַ��ָ������
        // ArrayToList(StringUtils.splitPreserveAllTokens(" ab de fg ", null));
        // δ���ֲ�ͬ�ط�,��ָ���ַ��ָ�����飬������������ʾ�ָ�������ĳ���
        // ArrayToList(StringUtils.splitPreserveAllTokens(" ab de fg ", null,
        // 2));
        // �Բ�ͬ���ͽ��зָ�
        // ArrayToList(StringUtils.splitByCharacterType("AEkjKr i39:������"));
        // δ��
        // ArrayToList(StringUtils.splitByCharacterTypeCamelCase("ASFSRules234"));
        // ƴ��~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // ������ת��Ϊ�ַ�����ʽ
        // System.out.println(StringUtils.concat(getArrayData()));
        // ƴ��ʱ�ò���һ���ַ�������.ע��nullҲ�����ӷ�������
        // System.out.println(StringUtils.concatWith(",", getArrayData()));
        // Ҳ��ƴ�ӡ�δ��������
        // System.out.println(StringUtils.join(getArrayData()));
        // �����ӷ�ƴ�ӣ�Ϊ��������
        // System.out.println(StringUtils.join(getArrayData(), ":"));
        // ƴ��ָ�������±�Ŀ�ʼ(������)�ͽ���(�Ĳ���,������)���м���ЩԪ�أ������ӷ�����
        // System.out.println(StringUtils.join(getArrayData(), ":", 1, 3));
        // ���ڼ��������ַ���.���ڼ���
        // System.out.println(StringUtils.join(getListData(), ":"));
        // �Ƴ���ɾ��~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // ɾ�����пո��
        // System.out.println(StringUtils.deleteWhitespace(" s �� �� 4j"));
        // �Ƴ���ʼ���ֵ���ͬ���ַ�
        // System.out.println(StringUtils.removeStart("www.baidu.com", "www."));
        // �Ƴ���ʼ���ֵ���ͬ���ַ�,�����ִ�Сд
        // System.out.println(StringUtils.removeStartIgnoreCase("www.baidu.com",
        // "WWW"));
        // �Ƴ�������ͬ�Ĳ���
        // System.out.println(StringUtils.removeEnd("www.baidu.com", ".com"));
        // �Ƴ�������ͬ�Ĳ��֣������ִ�Сд
        // System.out.println(StringUtils.removeEndIgnoreCase("www.baidu.com",
        // ".COM"));
        // �Ƴ�������ͬ�Ĳ���
        // System.out.println(StringUtils.remove("www.baidu.com/baidu", "bai"));
        // �Ƴ���β�ַ�Ϊ"\n", "\r", ���� "\r\n".
        // System.out.println(StringUtils.chomp("abcrabc\r"));
        // Ҳ���Ƴ���δ�⡣ȥ��β��ͬ�ַ�
        // System.out.println(StringUtils.chomp("baidu.com", "com"));
        // ȥ��ĩβ���һ���ַ�.�����"\n", "\r", ���� "\r\n"Ҳȥ��
        // System.out.println(StringUtils.chop("wwe.baidu"));
        // �滻~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // �滻ָ�����ַ���ֻ�滻��һ�γ��ֵ�
        // System.out.println(StringUtils.replaceOnce("www.baidu.com/baidu",
        // "baidu", "hao123"));
        // �滻���г��ֹ����ַ�
        // System.out.println(StringUtils.replace("www.baidu.com/baidu",
        // "baidu", "hao123"));
        // Ҳ���滻�����һ��������ʾ�滻����
        // System.out.println(StringUtils.replace("www.baidu.com/baidu",
        // "baidu", "hao123", 1));
        // �������ʶ������������Ӧ�����飬���Ҷ���������һ����ֵ���滻��������Ӧ�����ֵ������:baidu�滻Ϊtaobao��com�滻Ϊnet
        // System.out.println(StringUtils.replaceEach("www.baidu.com/baidu", new
        // String[]{"baidu", "com"}, new String[]{"taobao", "net"}));
        // ͬ�ϣ�δ���ֲ�ͬ
        // System.out.println(StringUtils.replaceEachRepeatedly("www.baidu.com/baidu",
        // new String[]{"baidu", "com"}, new String[]{"taobao", "net"}));
        // ������ã����������Ӧ�����ַ����������Ͳ�������Ӧ�滻.(������������Ӧ�Ļ����Լ������)
        // System.out.println(StringUtils.replaceChars("www.baidu.com", "bdm",
        // "qo"));
        // �滻ָ����ʼ(������)�ͽ���(������)�м�������ַ�
        // System.out.println(StringUtils.overlay("www.baidu.com", "hao123", 4,
        // 9));
        // ��ӣ�����~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // ���Ʋ���һ���ַ�����������Ϊ���ƵĴ���
        // System.out.println(StringUtils.repeat("ba", 3));
        // ���Ʋ���һ���ַ�����������Ϊ���ƵĴ�����������Ϊ�����ַ����м�������ַ���
        // System.out.println(StringUtils.repeat("ab", "ou", 3));
        // ����ַ�������С�ڲ�������ֵ��ĩβ�ӿո�ȫ��(С���ַ������Ȳ�������)
        // System.out.println(StringUtils.rightPad("����", 4));
        // �ַ�������С�ڶ�������ĩβ�ò��������ϣ����ڵĽ�ȡ(��ȡ���ϵ��ַ���)
        // System.out.println(StringUtils.rightPad("����", 4, "������"));
        // ͬ����ǰ�油ȫ�ո�
        // System.out.println(StringUtils.leftPad("����", 4));
        // �ַ�������С�ڶ�������ǰ���ò��������ϣ����ڵĽ�ȡ(��ȡ���ϵ��ַ���)
        // System.out.println(StringUtils.leftPad("����", 4, "��Һ�"));
        // �ַ�������С�ڶ��������������ÿո�ƽ����ȫ�����Ժ��油�ո����ȣ�
        // System.out.println(StringUtils.center("����", 3));
        // �ַ�������С�ڶ������������������������ַ���ƽ����ȫ�����Ժ��油�ո����ȣ�
        // System.out.println(StringUtils.center("����", 5, "��"));
        // ֻ��ʾָ������(������)���ַ�,�����������㲹��(����һ��ȡ+������=������)
        // System.out.println(StringUtils.abbreviate("�л����񹲺͹�", 5));
        // 2ͷ�ӵ�����е��ҡ��������: ...ijklmno
        // System.out.println(StringUtils.abbreviate("abcdefghijklmno", 12,
        // 10));
        // ����ָ�����ȣ����һ���ַ�ǰ�ӵ�.�������: ab.f
        // System.out.println(StringUtils.abbreviateMiddle("abcdef", ".", 4));
        // ת��,ˢѡ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // ת����һ���ַ�Ϊ��д.��ε�һ���ַ��Ǵ�дԭʼ����
        // System.out.println(StringUtils.capitalize("odf"));
        // ת����һ���ַ�ΪСд.��ε�һ���ַ���Сдԭʼ����
        // System.out.println(StringUtils.uncapitalize("DTf"));
        // ����ת������д��Сд��Сд���д
        // System.out.println(StringUtils.swapCase("I am Jiang, Hello"));
        // ���ַ�����������
        // System.out.println(StringUtils.reverse("�й�����"));
        // �����ض��ַ�(������)�ָ����з�ת
        // System.out.println(StringUtils.reverseDelimited("��:��:����", ':'));
    }

    // ������ת��ΪList
    @SuppressWarnings("unused")
    private static void ArrayToList(String[] str) {
        System.out.println(Arrays.asList(str) + " ����:" + str.length);
    }

    // ��ü�������
    @SuppressWarnings({ "unchecked" })
    private static List getListData() {
        List list = new ArrayList();
        list.add("���");
        list.add(null);
        list.add("����");
        list.add("��Һ�");
        return list;
    }

    // �����������
    @SuppressWarnings({ "unused", "unchecked" })
    private static String[] getArrayData() {
        return (String[]) getListData().toArray(new String[0]);
    }

    public static void main(String[] args) {
        TestStr();
    }

}