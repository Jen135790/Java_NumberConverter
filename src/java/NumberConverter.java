/*
    [File]
    NumberConverter.java

    [Author]
    Jeanis Sananikone

    [Description]
    Simple application that will convert decimals, hexadecimals, and roman numbers to the
    type of your choosing. Very simple and small GUI that's easy to use and navigate through.
*/
package numberconverter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class NumberConverter extends JFrame implements ActionListener{

    //Components
    private static String[] type1 = {"Decimal", "Hexidecimal", "Roman Numeral"};
    private static JComboBox cmbOrigType = new JComboBox(type1);
    private static JRadioButton rdbType1 = new JRadioButton("Hexidecimal"), rdbType2 = new JRadioButton("Roman Numeral");
    private static ButtonGroup rdbGroup = new ButtonGroup();
    private static JTextField txtOrigNum = new JTextField(), txtConvertedNum = new JTextField();
    private static JButton btnConvert = new JButton("Convert");
    private static JLabel lblErr = new JLabel("Enter a decimal number between 1 and 4294967295");
    
    public NumberConverter(){
        super("Number Converter");
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setResizable(false);
        
        //Components:
        rdbGroup.add(rdbType1);
        rdbGroup.add(rdbType2);
        rdbType1.setSelected(true);
        txtConvertedNum.setEditable(false);
        txtConvertedNum.setBackground(Color.WHITE);

        
        //Layouts:
        Container contents = new Container();
        contents.setLayout(new BorderLayout());
        JPanel cmbPanel = new JPanel();
        cmbPanel.add(new JLabel("Convert "));
        cmbPanel.add(cmbOrigType);
        cmbPanel.add(new JLabel(" To: "));
        cmbPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(3,2));
        midPanel.add(rdbType1);
        midPanel.add(rdbType2);
        midPanel.add(new JLabel("Number: "));
        midPanel.add(txtOrigNum);
        midPanel.add(new JLabel("Converted Value: "));
        midPanel.add(txtConvertedNum);
        JPanel botPanel = new JPanel();
        botPanel.setLayout(new GridLayout(2,1));
        botPanel.add(btnConvert);
        botPanel.add(lblErr);
       contents.add(cmbPanel, BorderLayout.NORTH);
       contents.add(midPanel, BorderLayout.CENTER);
       contents.add(botPanel, BorderLayout.SOUTH);
       this.add(contents);
       this.pack();
       this.setLocationRelativeTo(null);
       
       //ActionListeners:
       cmbOrigType.addActionListener(this);
       rdbType1.addActionListener(this);
       rdbType2.addActionListener(this);
       btnConvert.addActionListener(this);
        
    }
    
    //ActionListeners:
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == cmbOrigType){   //ComboBox
            //Change the text of the radiobuttons to their appropriate texts:
            if(cmbOrigType.getSelectedIndex() == 0){
                rdbType1.setText("Hexidecimal");
                rdbType2.setText("Roman Numeral");
                if(rdbType1.isSelected()){
                    setLabel(0, 0);
                }
                else{
                    setLabel(0, 1);
                }
            }
            else if(cmbOrigType.getSelectedIndex() == 1){
                rdbType1.setText("Decimal");
                rdbType2.setText("Roman Numeral");
                if(rdbType1.isSelected()){
                    setLabel(1, 0);
                }
                else{
                    setLabel(1, 1);
                }
            }
            else if(cmbOrigType.getSelectedIndex() == 2){
                rdbType1.setText("Hexidecimal");
                rdbType2.setText("Decimal");
                if(rdbType1.isSelected()){
                    setLabel(2, 0);
                }
                else{
                    setLabel(2, 1);
                }
            }
            
            txtOrigNum.setText("");
            txtConvertedNum.setText("");
        }
        else if(e.getSource() == rdbType1){     //Radio Buttons
            setLabel(cmbOrigType.getSelectedIndex(), 0);
            txtOrigNum.setText("");
            txtConvertedNum.setText("");
        }
        else if(e.getSource() == rdbType2){     //Radio Buttons
            setLabel(cmbOrigType.getSelectedIndex(), 1);
            txtOrigNum.setText("");
            txtConvertedNum.setText("");
        }
        else if(e.getSource() == btnConvert){   //Convert Button
            if(cmbOrigType.getSelectedIndex() == 0){    //DECIMAL
                DecimalNum input = new DecimalNum(txtOrigNum.getText());
                
                //Check which radio button is selected:
                int rdbSelectedIn = -1;
                if(rdbType1.isSelected()){  //HEX
                    rdbSelectedIn = 0;
                }
                else if(rdbType2.isSelected()){ //ROMAN
                    rdbSelectedIn = 1;
                }
                
                //Check if input is a valid decimal:
                if(input.isValidInput(rdbSelectedIn)){   //Input IS valid
                    lblErr.setForeground(Color.black);
                    
                    //Check if converting to Hex OR Roman:
                    if(rdbSelectedIn == 0){
                        txtConvertedNum.setText(input.convertToHex());
                    }
                    else if(rdbSelectedIn == 1){
                        txtConvertedNum.setText(input.convertToRoman());
                    }
                    
                }
                else{   //Input is NOT valid and nothing is converted
                    lblErr.setForeground(Color.red);
                    txtConvertedNum.setText("");
                }
            }
            else if(cmbOrigType.getSelectedIndex() == 1){   //HEXIDECIMAL
                HexNum input = new HexNum(txtOrigNum.getText());
                //Check which radio button is selected:
                int rdbSelectedIn = -1;
                if(rdbType1.isSelected()){  //DECIMAL
                    rdbSelectedIn = 0;
                }
                else if(rdbType2.isSelected()){ //ROMAN
                    rdbSelectedIn = 1;
                }
                
                //Check if input is a valid hexadecimal:
                if(input.isValidHex()){   //Input IS valid
                    lblErr.setForeground(Color.black);
                    
                    //Check if converting to DEC OR Roman:
                    if(rdbSelectedIn == 0){
                        txtConvertedNum.setText(input.convertToDec());
                    }
                    else if(rdbSelectedIn == 1){
                        txtConvertedNum.setText(input.convertToRoman());
                    }
                    
                }
                else{   //Input is NOT valid and nothing is converted
                    lblErr.setForeground(Color.red);
                    txtConvertedNum.setText("");
                }
            }
            else if(cmbOrigType.getSelectedIndex() == 2){   //HEXIDECIMAL
                RomanNumeral input = new RomanNumeral(txtOrigNum.getText());
                //Check which radio button is selected:
                int rdbSelectedIn = -1;
                if(rdbType1.isSelected()){  //DECIMAL
                    rdbSelectedIn = 0;
                }
                else if(rdbType2.isSelected()){ //ROMAN
                    rdbSelectedIn = 1;
                }
                
                //Check if input is a valid hexadecimal:
                if(input.isValidRom()){   //Input IS valid
                    lblErr.setForeground(Color.black);
                    
                    //Check if converting to HEX OR DEC:
                    if(rdbSelectedIn == 0){
                        txtConvertedNum.setText(input.convertToHex());
                    }
                    else if(rdbSelectedIn == 1){
                        txtConvertedNum.setText(input.convertToDec());
                    }
                    
                }
                else{   //Input is NOT valid and nothing is converted
                    lblErr.setForeground(Color.red);
                    txtConvertedNum.setText("");
                }
            }
        }
    }
    
    @Override
    public Insets getInsets(){
        return new Insets(30,15,15,15);
    }
    
    public static void setLabel(int cmbIndex, int convertType){
        //convertType: 0 is rdbType1 ; 1 is rdbType2
        if(cmbIndex == 0 && convertType == 0){
            lblErr.setText("Enter a decimal number between 1 and 4294967295");
        }
        else if(cmbIndex == 0 && convertType == 1){
            lblErr.setText("Enter a decimal number between 1 and 4999");
        }
        else if(cmbIndex == 1 && convertType == 0){
            lblErr.setText("Enter a Hex number between 1 and FFFFFFFF");
        }
        else if(cmbIndex == 1 && convertType == 1){
            lblErr.setText("Enter a Hex number between 1 and 1387");
        }
        else if(cmbIndex == 2){
            lblErr.setText("Enter a Hex number between I and MMMMCMXCIX");
        }
    }
    
    public static void main(String[] args) {
        NumberConverter GUI = new NumberConverter();
        GUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
}
