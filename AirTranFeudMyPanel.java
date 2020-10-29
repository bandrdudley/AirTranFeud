import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class MyJPanel extends JPanel {
  private ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/airtran_col.jpg"));
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.imageIcon.paintIcon(this, g, 0, 0);
  }
}
