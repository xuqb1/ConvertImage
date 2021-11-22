import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
//import java.awt.Transparentcy;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
//import javax.swing.Transparentcy;

import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
//import java.awt.image.Transparentcy;
//bmpתpng��͸����������͸���ı���ɫΪ����ɫ��250, 252, 253
public class ConvertImage1 {
  public static void main(String args[]) throws IOException {
    ConvertImage1 ci=new ConvertImage1();
    ci.createWindow();
  }
  private void createWindow() {
    JFrame frame = new JFrame("ͼƬ��ʽת��");//"����ת��bmp��ʽ��ͼƬΪpng��ʽ");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createUI(frame);
    frame.setSize(560, 200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  private void createUI( JFrame frame) {
    LayoutManager layout = new FlowLayout();
    JPanel panel1 = new JPanel();
    panel1.setLayout(layout);
    JLabel label1 = new JLabel("ԭͼĿ¼:");
    JLabel label3 = new JLabel("                    ");
    JTextField textfield1=new JTextField(30);
    JButton button1 = new JButton("ѡ��..");
    button1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        File f=new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(f);
        int option = fileChooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          textfield1.setText(file.getAbsolutePath());
        } else {
          textfield1.setText("");
        }
      }
    });
    panel1.add(label1);
    panel1.add(textfield1);
    panel1.add(button1);
    panel1.add(label3);
    frame.getContentPane().add(panel1, BorderLayout.NORTH);
    
    JPanel panel2 = new JPanel();
    panel2.setLayout(layout);
    JLabel label2 = new JLabel("���Ŀ¼:");
    JTextField textfield2=new JTextField(30);
    JButton button2 = new JButton("ѡ��..");
    button2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        File f=new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(f);
        int option = fileChooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          textfield2.setText(file.getAbsolutePath());
        } else {
          textfield2.setText("");
        }
      }
    });
    JButton button3 = new JButton("ת��");
    button3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String inPath=textfield1.getText().trim();
        String outPath=textfield2.getText().trim();
        if(inPath.equals("") || outPath.equals("")){
          JOptionPane.showMessageDialog(null,"��ָ��ԭͼĿ¼�����Ŀ¼","��ʾ",JOptionPane.ERROR_MESSAGE);
          return;
        }
        button3.setEnabled(false);
        try{
          int count=converToPng(inPath, outPath);
          JOptionPane.showMessageDialog(null,"�ɹ�ת���� "+count+" ���ļ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e1){
          e1.printStackTrace();
        }
        button3.setEnabled(true);
      }
    });
    panel2.add(label2);
    panel2.add(textfield2);
    panel2.add(button2);
    panel2.add(button3);
    
    frame.getContentPane().add(panel2, BorderLayout.CENTER);
  }
  public int converToPng(String inputpath, String outputpath) throws IOException {
    File inpath=new File(inputpath);
    if(!inpath.exists() || !inpath.isDirectory()){
      System.out.println(inputpath+" �����ڣ������ļ���");
      return 0;
    }
    if(outputpath==null){
      outputpath="";
    }
    outputpath=outputpath.trim();
    if("".equals(outputpath)){
      System.out.println(" δָ������ļ���");
      return 0;
    }
    int i=0;
    File outpath=new File(outputpath);
    if(!outpath.exists() || !outpath.isDirectory()){
      System.out.println(outputpath+" �����ڣ������ļ���");
      return 0;
    }
    if(!outputpath.endsWith("\\")){
      outputpath=outputpath+"\\";
    }
    File[] fs=inpath.listFiles();
    for(File f:fs){
      if(f.isDirectory()){
        continue;
      }
      String fileName=f.getName();
      String suffix="jpg";
      if(fileName.lastIndexOf(".")>=0){
        suffix=fileName.substring(fileName.lastIndexOf(".") + 1);
      }else{
        fileName=fileName+".jpg";
      }
      suffix=suffix.toLowerCase();
      if(!"bmp".equals(suffix) && !"jpg".equals(suffix)){
        System.out.println("��ʽδ֪��"+fileName);
        continue;
      }
      BufferedImage input = ImageIO.read(f);
      String outfilename=outputpath+fileName.substring(0, fileName.length()-3)+"png";
      File outputFile = new File(outfilename);//"c:\\test.png");
      //ImageIO.write(input, "BMP", outputFile);
      
      outputFile = new File(outfilename);
      ImageIO.write(input, "PNG", outputFile);
      i++;
    }
    return i;
  }
}