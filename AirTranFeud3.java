import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

final class null implements ItemListener {
  public void itemStateChanged(ItemEvent event) {
    if (event.getStateChange() == 1) {
      AirTranFeud.access$1(AirTranFeud.this, 0);
      AirTranFeud.access$3(AirTranFeud.this, AirTranFeud.access$2(AirTranFeud.this).getSelectedIndex());
      AirTranFeud.this.initializePanels();
      AirTranFeud.this.roundOver = false;
      AirTranFeud.access$4(AirTranFeud.this)[0].setIcon(AirTranFeud.access$5(AirTranFeud.this));
      AirTranFeud.access$4(AirTranFeud.this)[1].setIcon(AirTranFeud.access$5(AirTranFeud.this));
      AirTranFeud.access$4(AirTranFeud.this)[2].setIcon(AirTranFeud.access$5(AirTranFeud.this));
      AirTranFeud.access$6(AirTranFeud.this, 0);
      AirTranFeud.this.bank.setScore(0);
      AirTranFeud.access$7(AirTranFeud.this).setText((new StringBuilder(String.valueOf(AirTranFeud.this.bank.getScore()))).toString());
      AirTranFeud.access$8(AirTranFeud.this, 0);
    } 
  }
}
