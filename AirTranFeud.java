import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AirTranFeud extends JFrame implements ActionListener {
  private Container container;
  
  private GridBagLayout layout;
  
  private FlowLayout strikeLayout;
  
  private FlowLayout headerLayout;
  
  private GridBagConstraints constraints;
  
  private JButton[] panels;
  
  private JButton buzzer;
  
  private String[] names = new String[] { 
      "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
      "11", "12" };
  
  private Answers answers;
  
  Vector<String> surveys;
  
  String[][] answerList;
  
  private JComboBox surveysComboBox;
  
  private int pos;
  
  private int currentSurvey;
  
  private static int PANEL_SIZE = 12;
  
  private static int SHIFT_COL = 3;
  
  private static int STRIKE_LIMIT = 3;
  
  private Color color = Color.orange;
  
  private Color colorWhite = Color.white;
  
  private Color scoreColor = Color.black;
  
  JTextField askSurvey;
  
  private JPanel headerPanel;
  
  private JPanel strikePanel;
  
  private JButton[] strikeButtons;
  
  private JLabel[] strikeLabels;
  
  private int strikeCount = 0;
  
  private JPanel teamOneScorePanel;
  
  private JPanel teamTwoScorePanel;
  
  private JPanel bankScorePanel;
  
  private JLabel teamOneScoreLabel;
  
  private JLabel teamTwoScoreLabel;
  
  private JLabel bankScoreLabel;
  
  private JButton teamOneArrow;
  
  private JButton teamTwoArrow;
  
  private Icon emptyStrike;
  
  private Icon redStrike;
  
  private boolean correctAnswer = true;
  
  private int panelLocation = 0;
  
  Team teamOne;
  
  Team teamTwo;
  
  Bank bank;
  
  boolean roundOver = false;
  
  private int answersDisplayed = 0;
  
  private int surveySize = 0;
  
  AudioClip Music;
  
  private ObjectInputStream input;
  
  public AirTranFeud() {
    super("AirTranFeud");
    this.pos = -1;
    this.currentSurvey = 0;
    this.bank = new Bank();
    this.teamOne = new Team();
    this.teamTwo = new Team();
    this.teamOne.setHasControl(true);
    this.teamTwo.setHasControl(false);
    this.answers = new Answers();
    this.answerList = this.answers.openFile("surveys1.txt");
    this.container = getContentPane();
    this.layout = new GridBagLayout();
    this.container.setLayout(this.layout);
    this.container.setBackground(new Color(137, 225, 184));
    this.constraints = new GridBagConstraints();
    this.constraints.fill = 1;
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic('F');
    JMenuItem openItem = new JMenuItem("Open");
    openItem.setMnemonic('O');
    openItem.addActionListener(
        
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            AirTranFeud.this.openFile();
          }
        });
    fileMenu.add(openItem);
    JMenuItem exitItem = new JMenuItem("Exit");
    exitItem.setMnemonic('x');
    exitItem.addActionListener(
        
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            System.exit(0);
          }
        });
    fileMenu.add(exitItem);
    JMenuBar bar = new JMenuBar();
    setJMenuBar(bar);
    bar.add(fileMenu);
    this.headerPanel = new JPanel();
    this.headerLayout = new FlowLayout();
    this.headerPanel.setLayout(this.headerLayout);
    this.headerLayout.setAlignment(2);
    Icon logo = new ImageIcon(getClass().getResource("/resources/airTranFeudLogoSm.jpg"));
    JLabel title = new JLabel("", logo, 2);
    title.setBorder(BorderFactory.createRaisedBevelBorder());
    this.headerPanel.add(title);
    this.bankScorePanel = new JPanel();
    this.bankScorePanel.setBackground(this.scoreColor);
    this.bankScoreLabel = new JLabel("0", 0);
    this.bankScoreLabel.setForeground(Color.yellow);
    this.bankScoreLabel.setFont(new Font("TimesRoman", 0, 72));
    this.bankScoreLabel.setPreferredSize(new Dimension(100, 150));
    this.bankScoreLabel.setBorder(BorderFactory.createRaisedBevelBorder());
    this.bankScorePanel.add(this.bankScoreLabel);
    this.headerPanel.add(this.bankScorePanel);
    addComponent(this.headerPanel, 0, 0 + SHIFT_COL, 2, 1);
    this.surveys = new Vector<String>();
    this.surveysComboBox = new JComboBox();
    loadSurveys();
    this.surveysComboBox.setMaximumRowCount(3);
    this.surveysComboBox.addItemListener(
        new ItemListener() {
          public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == 1) {
              AirTranFeud.this.pos = 0;
              AirTranFeud.this.currentSurvey = AirTranFeud.this.surveysComboBox.getSelectedIndex();
              AirTranFeud.this.initializePanels();
              AirTranFeud.this.roundOver = false;
              AirTranFeud.this.strikeButtons[0].setIcon(AirTranFeud.this.emptyStrike);
              AirTranFeud.this.strikeButtons[1].setIcon(AirTranFeud.this.emptyStrike);
              AirTranFeud.this.strikeButtons[2].setIcon(AirTranFeud.this.emptyStrike);
              AirTranFeud.this.strikeCount = 0;
              AirTranFeud.this.bank.setScore(0);
              AirTranFeud.this.bankScoreLabel.setText((new StringBuilder(String.valueOf(AirTranFeud.this.bank.getScore()))).toString());
              AirTranFeud.this.answersDisplayed = 0;
            } 
          }
        });
    addComponent(this.surveysComboBox, 1, 0 + SHIFT_COL, 3, 1);
    setupBoard();
    initializePanels();
    this.teamOneScorePanel = new JPanel();
    this.teamOneScorePanel.setBackground(this.scoreColor);
    this.teamOneScoreLabel = new JLabel("0", 0);
    this.teamOneScoreLabel.setForeground(Color.yellow);
    this.teamOneScoreLabel.setFont(new Font("TimesRoman", 0, 72));
    this.teamOneScoreLabel.setPreferredSize(new Dimension(100, 150));
    this.teamOneScoreLabel.setBorder(BorderFactory.createRaisedBevelBorder());
    this.teamOneScorePanel.add(this.teamOneScoreLabel);
    addComponent(this.teamOneScorePanel, 6, 0, 3, 3);
    this.teamTwoScorePanel = new JPanel();
    this.teamTwoScorePanel.setBackground(this.scoreColor);
    this.teamTwoScoreLabel = new JLabel("0", 0);
    this.teamTwoScoreLabel.setForeground(Color.yellow);
    this.teamTwoScoreLabel.setFont(new Font("TimesRoman", 0, 72));
    this.teamTwoScoreLabel.setPreferredSize(new Dimension(100, 150));
    this.teamTwoScoreLabel.setBorder(BorderFactory.createRaisedBevelBorder());
    this.teamTwoScorePanel.add(this.teamTwoScoreLabel);
    addComponent(this.teamTwoScorePanel, 6, 2 + SHIFT_COL, 3, 3);
    this.askSurvey = new JTextField("SurveySays!");
    this.askSurvey.addActionListener(this);
    addComponent(this.askSurvey, 8, 0 + SHIFT_COL, 1, 1);
    this.buzzer = new JButton("X");
    this.buzzer.addActionListener(this);
    addComponent(this.buzzer, 8, 1 + SHIFT_COL, 1, 1);
    createStrikeButtons();
    addComponent(this.strikePanel, 9, 0 + SHIFT_COL, 2, 1);
    setSize(1000, 800);
    setVisible(true);
  }
  
  private void openFile() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(
        0);
    int result = fileChooser.showOpenDialog(this);
    if (result == 1)
      return; 
    File fileName = fileChooser.getSelectedFile();
    if (fileName == null || fileName.getName().equals("")) {
      JOptionPane.showMessageDialog(this, "Invalid File Name", "Invalid File Name", 0);
    } else {
      this.answerList = this.answers.openFile(fileName.getName());
      loadSurveys();
    } 
  }
  
  private void loadSurveys() {
    if (this.surveysComboBox.getItemCount() > 0)
      this.surveysComboBox.removeAllItems(); 
    for (int i = 1; i <= this.answers.getSurveySize(); i++)
      this.surveysComboBox.addItem("Survey" + i + ": " + this.answerList[i - 1][0]); 
  }
  
  public void addComponent(Component component, int row, int column, int width, int height) {
    this.constraints.gridx = column;
    this.constraints.gridy = row;
    this.constraints.gridwidth = width;
    this.constraints.gridheight = height;
    this.layout.setConstraints(component, this.constraints);
    this.container.add(component);
  }
  
  public void setupBoard() {
    this.panels = new JButton[this.names.length];
    for (int count = 0; count < this.panels.length; count++) {
      this.panels[count] = new JButton(this.names[count]);
      this.panels[count].setPreferredSize(new Dimension(330, 60));
      addComponent(this.panels[count], count % 6 + 2, count / 6 + SHIFT_COL, 1, 1);
    } 
  }
  
  public void initializePanels() {
    int i;
    for (i = 0; i < PANEL_SIZE; i++)
      this.panels[i].removeActionListener(this); 
    this.surveySize = (this.answerList[this.currentSurvey]).length;
    for (i = 0; i < this.surveySize && i < PANEL_SIZE; i++) {
      this.panels[i].setText(this.names[i].toUpperCase());
      this.panels[i].setEnabled(true);
      this.panels[i].setFont(new Font("Arial", 1, 15));
      this.panels[i].addActionListener(this);
    } 
    for (int j = PANEL_SIZE - 1; j >= this.surveySize - 1; j--) {
      this.panels[j].setText(this.names[j]);
      this.panels[j].setEnabled(false);
      this.panels[j].setFont(new Font("Arial", 1, 15));
    } 
  }
  
  public void createStrikeButtons() {
    this.strikeButtons = new JButton[3];
    this.strikeLabels = new JLabel[3];
    this.strikePanel = new JPanel();
    this.strikeLayout = new FlowLayout();
    this.strikePanel.setLayout(this.strikeLayout);
    this.strikeLayout.setAlignment(1);
    this.teamOneArrow = new JButton("<");
    if (this.teamOne.isHasControl())
      this.teamOneArrow.setBackground(this.color); 
    this.teamOneArrow.setPreferredSize(new Dimension(50, 50));
    this.teamOneArrow.addActionListener(this);
    this.strikePanel.add(this.teamOneArrow);
    for (int i = 0; i < this.strikeButtons.length; i++) {
      this.emptyStrike = new ImageIcon(getClass().getResource("/resources/clearStrike.gif"));
      this.strikeButtons[i] = new JButton(this.emptyStrike);
      this.strikeButtons[i].setPreferredSize(new Dimension(50, 50));
      this.strikePanel.add(this.strikeButtons[i]);
    } 
    this.teamTwoArrow = new JButton(">");
    if (this.teamTwo.isHasControl())
      this.teamTwoArrow.setBackground(this.color); 
    this.teamTwoArrow.setPreferredSize(new Dimension(50, 50));
    this.teamTwoArrow.addActionListener(this);
    this.strikePanel.add(this.teamTwoArrow);
  }
  
  public void actionPerformed(ActionEvent event) {
    this.correctAnswer = true;
    if (event.getSource() == this.panels[0]) {
      this.panels[0].setText(this.answerList[this.currentSurvey][1].toUpperCase());
      this.panels[0].removeActionListener(this);
    } else if (event.getSource() == this.panels[1]) {
      this.panels[1].setText(this.answerList[this.currentSurvey][2].toUpperCase());
      this.panels[1].removeActionListener(this);
    } else if (event.getSource() == this.panels[2]) {
      this.panels[2].setText(this.answerList[this.currentSurvey][3].toUpperCase());
      this.panels[2].removeActionListener(this);
    } else if (event.getSource() == this.panels[3]) {
      this.panels[3].setText(this.answerList[this.currentSurvey][4].toUpperCase());
      this.panels[3].removeActionListener(this);
    } else if (event.getSource() == this.panels[4]) {
      this.panels[4].setText(this.answerList[this.currentSurvey][5].toUpperCase());
      this.panels[4].removeActionListener(this);
    } else if (event.getSource() == this.panels[5]) {
      this.panels[5].setText(this.answerList[this.currentSurvey][6].toUpperCase());
      this.panels[5].removeActionListener(this);
    } else if (event.getSource() == this.panels[6]) {
      this.panels[6].setText(this.answerList[this.currentSurvey][7].toUpperCase());
      this.panels[6].removeActionListener(this);
    } else if (event.getSource() == this.panels[7]) {
      this.panels[7].setText(this.answerList[this.currentSurvey][8].toUpperCase());
      this.panels[7].removeActionListener(this);
    } else if (event.getSource() == this.panels[8]) {
      this.panels[8].setText(this.answerList[this.currentSurvey][9].toUpperCase());
      this.panels[8].removeActionListener(this);
    } else if (event.getSource() == this.panels[9]) {
      this.panels[9].setText(this.answerList[this.currentSurvey][10].toUpperCase());
      this.panels[9].removeActionListener(this);
    } else if (event.getSource() == this.panels[10]) {
      this.panels[10].setText(this.answerList[this.currentSurvey][11].toUpperCase());
      this.panels[10].removeActionListener(this);
    } else if (event.getSource() == this.panels[11]) {
      this.panels[11].setText(this.answerList[this.currentSurvey][12].toUpperCase());
      this.panels[11].removeActionListener(this);
    } else if (event.getSource() == this.askSurvey || event.getSource() == this.buzzer) {
      this.panelLocation = this.answers.surveySays(this.askSurvey.getText(), this.currentSurvey);
      if (this.panelLocation == 0) {
        try {
          this.Music = JApplet.newAudioClip(getClass().getResource("/resources/buzz.wav"));
          this.Music.play();
        } catch (Exception u) {
          System.out.println(u);
        } 
        this.redStrike = new ImageIcon(getClass().getResource("/resources/redStrike.gif"));
        this.strikeCount++;
        this.strikeButtons[(this.strikeCount - 1) % STRIKE_LIMIT].setIcon(this.redStrike);
        this.correctAnswer = false;
        if (this.strikeCount == STRIKE_LIMIT)
          changeControl(); 
        if (this.strikeCount > STRIKE_LIMIT) {
          if (this.teamOne.isHasControl()) {
            this.teamTwo.addPoints(this.bank.getScore());
            this.teamTwoScoreLabel.setText((new StringBuilder(String.valueOf(this.teamTwo.getScore()))).toString());
          } else {
            this.teamOne.addPoints(this.bank.getScore());
            this.teamOneScoreLabel.setText((new StringBuilder(String.valueOf(this.teamOne.getScore()))).toString());
          } 
          this.strikeButtons[1].setIcon(this.emptyStrike);
          this.strikeButtons[2].setIcon(this.emptyStrike);
          this.bank.setScore(0);
          this.bankScoreLabel.setText((new StringBuilder(String.valueOf(this.bank.getScore()))).toString());
          this.strikeCount = 0;
          this.roundOver = true;
        } 
      } else {
        this.panels[this.panelLocation - 1].setText(this.answerList[this.currentSurvey][this.panelLocation]);
        this.answersDisplayed++;
      } 
    } 
    if (event.getSource() == this.teamOneArrow || event.getSource() == this.teamTwoArrow) {
      changeControl();
      this.correctAnswer = false;
    } 
    if (this.correctAnswer && !this.roundOver) {
      try {
        this.Music = JApplet.newAudioClip(getClass().getResource("/resources/ding.wav"));
        this.Music.play();
      } catch (Exception u) {
        System.out.println(u);
      } 
      this.answersDisplayed++;
      if (this.strikeCount == STRIKE_LIMIT || this.answersDisplayed == this.surveySize - 1) {
        if (this.answersDisplayed == this.surveySize - 1)
          this.bank.addPoints(1); 
        if (this.teamOne.isHasControl()) {
          this.teamOne.addPoints(this.bank.getScore());
          this.teamOneScoreLabel.setText((new StringBuilder(String.valueOf(this.teamOne.getScore()))).toString());
        } else {
          this.teamTwo.addPoints(this.bank.getScore());
          this.teamTwoScoreLabel.setText((new StringBuilder(String.valueOf(this.teamTwo.getScore()))).toString());
        } 
        this.bank.setScore(0);
        this.strikeCount = 0;
        this.roundOver = true;
      } else {
        this.bank.addPoints(1);
      } 
      this.bankScoreLabel.setText((new StringBuilder(String.valueOf(this.bank.getScore()))).toString());
    } 
  }
  
  class MyJPanel extends JPanel {
    private ImageIcon imageIcon;
    
    public MyJPanel() {
      this.imageIcon = new ImageIcon(getClass().getResource("/resources/airtran_col.jpg"));
    }
    
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.imageIcon.paintIcon(this, g, 0, 0);
    }
  }
  
  public void changeControl() {
    boolean teamOneControl = this.teamOne.isHasControl();
    boolean teamTwoControl = this.teamTwo.isHasControl();
    teamOneControl = !teamOneControl;
    teamTwoControl = !teamTwoControl;
    if (teamOneControl) {
      this.teamOne.setHasControl(true);
      this.teamTwo.setHasControl(false);
      this.teamOneArrow.setBackground(this.color);
      this.teamTwoArrow.setBackground(this.colorWhite);
    } 
    if (teamTwoControl) {
      this.teamTwo.setHasControl(true);
      this.teamOne.setHasControl(false);
      this.teamTwoArrow.setBackground(this.color);
      this.teamOneArrow.setBackground(this.colorWhite);
    } 
  }
  
  public static void main(String[] args) {
    AirTranFeud application = new AirTranFeud();
    application.setDefaultCloseOperation(3);
  }
}
