package piechart;

public class TestFrame {
    public static void main(String[] args) {

        /*JFrame frame = new JFrame("LoL");
        frame.setSize(400, 300);
        frame.setLocation(200, 200);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JCheckBox box1 = new JCheckBox("one");
        JCheckBox box2 = new JCheckBox("two",true);
        JCheckBox box3 = new JCheckBox("three");
        frame.add(box1);
        frame.add(box2);
        frame.add(box3);
        frame.setLayout(new GridLayout(3, 1));
        box1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println(e.getItem());
                System.out.println(e.getStateChange());
                System.out.println(e.getItemSelectable());
            }
        });
        System.out.println(box2.isSelected());*/

        String a = "°¡°¡´ó·ù¶Èb,10.10.2.18";
        int last = a.lastIndexOf(",");
        System.out.println(last +"  " + a.length());
        System.out.println(a.substring(0,last));
        System.out.println(a.substring(last + 1,a.length()));


    }


}